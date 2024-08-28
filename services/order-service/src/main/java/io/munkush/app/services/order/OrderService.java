package io.munkush.app.services.order;

import io.munkush.app.services.clients.CustomerServiceClient;
import io.munkush.app.services.clients.ProductServiceClient;
import io.munkush.app.services.clients.dto.ProductPurchaseResponse;
import io.munkush.app.services.kafka.OrderConfirmation;
import io.munkush.app.services.kafka.OrderProducer;
import io.munkush.app.services.order.dto.OrderRequest;
import io.munkush.app.services.order.dto.OrderResponse;
import io.munkush.app.services.order.exception.BusinessException;
import io.munkush.app.services.order.exception.OrderNotFoundException;
import io.munkush.app.services.order.mapper.OrderToResponseMapper;
import io.munkush.app.services.order.mapper.RequestToOrderMapper;
import io.munkush.app.services.orderline.mapper.RequestToOrderLineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static java.lang.String.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RequestToOrderMapper requestToOrderMapper;
    private final CustomerServiceClient customerServiceClient;
    private final ProductServiceClient productServiceClient;
    private final RequestToOrderLineMapper requestToOrderLineMapper;
    private final OrderProducer orderProducer;
    private final OrderToResponseMapper orderToResponseMapper;

    @Transactional
    public Long createdOrder(OrderRequest request) {

        // 1) чекать customer (на существование)

        if (!Boolean.TRUE.equals(customerServiceClient.existsById(request.customerId())
                .getBody())) {
            throw new BusinessException(format("Cannot create order: Customer with id: %s doesnt exists",
                    request.customerId()));
        }

        // 2) минусовать product (purchase)
        var purchasedProducts = productServiceClient.purchase(request.products()).getBody();

        // 3) сохранить заказ (Order, OrderLine)
        var order = requestToOrderMapper.map(request);
        var orderLines = request.products().stream().map(requestToOrderLineMapper::map).toList();
        order.setOrderLines(orderLines);
        var savedOrder = orderRepository.save(order);

        // 4) TODO: отправка к payment ( процесс оплаты )

        // 5) TODO: отправка сообщение подтверждение заказа на кафку

        orderProducer.sendOrderConfirmation(
                OrderConfirmation.builder()
                .totalAmount(request.amount())
                .orderReference(request.reference())
                .paymentMethod(request.paymentMethod())
                .products(purchasedProducts)
                .customer(customerServiceClient.fetchById(request.customerId()).getBody())
                .build()
        );


        return savedOrder.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(orderToResponseMapper::map).toList();
    }

    public OrderResponse findById(Long orderId) {
        return orderToResponseMapper.map(orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(format("order with id: %s not found", orderId))));
    }
}

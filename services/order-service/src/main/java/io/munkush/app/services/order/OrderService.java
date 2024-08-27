package io.munkush.app.services.order;

import io.munkush.app.services.clients.CustomerServiceClient;
import io.munkush.app.services.clients.ProductServiceClient;
import io.munkush.app.services.order.dto.OrderRequest;
import io.munkush.app.services.order.exception.BusinessException;
import io.munkush.app.services.order.mapper.RequestToOrderMapper;
import io.munkush.app.services.orderline.OrderLine;
import io.munkush.app.services.orderline.mapper.RequestToOrderLineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RequestToOrderMapper requestToOrderMapper;
    private final CustomerServiceClient customerServiceClient;
    private final ProductServiceClient productServiceClient;

    private final RequestToOrderLineMapper requestToOrderLineMapper;

    public Long createdOrder(OrderRequest request) {

        // 1) чекать customer (на существование)

        if (!Boolean.TRUE.equals(customerServiceClient.existsById(request.customerId())
                .getBody())) {
            throw new BusinessException(format("Cannot create order: Customer with id: %s doesnt exists",
                    request.customerId()));
        }

        // 2) минусовать product (purchase)
        productServiceClient.purchase(request.products());

        // 3) сохранить заказ (Order, OrderLine)
        var order = requestToOrderMapper.map(request);
        var orderLines = request.products().stream().map(requestToOrderLineMapper::map).toList();
        order.setOrderLines(orderLines);
        var savedOrder = orderRepository.save(order);

        // 4) TODO: payment process ( процесс оплаты )

        // 5) TODO: отправка сообщение подтверждение заказа на кафку



        return savedOrder.getId();

    }
}

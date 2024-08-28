package io.munkush.app.services;

import io.munkush.app.services.dto.PaymentRequest;
import io.munkush.app.services.kafka.PaymentConfirmation;
import io.munkush.app.services.kafka.PaymentProducer;
import io.munkush.app.services.mapper.RequestToPaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;
    private final RequestToPaymentMapper requestToPaymentMapper;

    public Long create(PaymentRequest request){


        // 1) Сохраним Payment
        var payment = paymentRepository.save(requestToPaymentMapper.map(request));


        // 2) Отправка оплаты на кафку
        paymentProducer.send(
                PaymentConfirmation.builder()
                        .amount(request.amount())
                        .paymentMethod(request.paymentMethod())
                        .customerEmail(request.customer().email())
                        .customerFirstname(request.customer().firstname())
                        .customerLastname(request.customer().lastname())
                        .orderReference(request.orderReference())
                        .build()
        );


        return payment.getId();
    }
}


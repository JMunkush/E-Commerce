package io.munkush.app.kafka;

import io.munkush.app.kafka.dto.OrderConfirmation;
import io.munkush.app.kafka.dto.PaymentConfirmation;
import io.munkush.app.mail.MailService;
import io.munkush.app.notification.Notification;
import io.munkush.app.notification.NotificationRepository;
import io.munkush.app.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static io.munkush.app.notification.NotificationType.*;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final MailService mailService;
    private final NotificationRepository notificationRepository;



    @KafkaListener(topics = "order-topic")
    public void consumeOrder(OrderConfirmation orderConfirmation){
        // 1) Сохранить к MONGO
        notificationRepository.save(
                Notification.builder()
                .orderConfirmation(orderConfirmation)
                .type(ORDER)
                .notificationDate(LocalDateTime.now())
                .build()
        );

        // 2) отправить сообщение к EMAIL
        mailService.sendOrder(orderConfirmation);
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePayment(PaymentConfirmation paymentConfirmation){
        // 1) Сохранить к MONGO
        notificationRepository.save(
                Notification.builder()
                        .paymentConfirmation(paymentConfirmation)
                        .type(PAYMENT)
                        .notificationDate(LocalDateTime.now())
                        .build()
        );

        // 2) отправить сообщение к EMAIL
        mailService.sendPayment(paymentConfirmation);
    }
}

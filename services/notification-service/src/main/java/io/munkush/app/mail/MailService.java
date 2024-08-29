package io.munkush.app.mail;

import io.munkush.app.kafka.dto.OrderConfirmation;
import io.munkush.app.kafka.dto.PaymentConfirmation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;



    @Async
    @SneakyThrows
    public void sendOrder(OrderConfirmation orderConfirmation) {

        MimeMessage mimeMessage = getNewMimeMessage();
        MimeMessageHelper  messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        // 1) От Кого
        messageHelper.setFrom("ecommerce@gmail.com");

        String templateName = "order-confirmation.html";

        Map<String, Object> model = new HashMap<>();
        model.put("customerName", orderConfirmation.customer().firstname());
        model.put("orderReference", orderConfirmation.orderReference());
        model.put("products", orderConfirmation.products());
        model.put("totalAmount", orderConfirmation.totalAmount());

        Context context = new Context();
        context.setVariables(model);

        var finishedHTML = templateEngine.process(templateName, context);

        // 2) Что
        messageHelper.setText(finishedHTML, true);

        // 3) Кому
        messageHelper.setTo(orderConfirmation.customer().email());

        // 4) Заголовок
        messageHelper.setSubject("Order Confirmation Notification");

        mailSender.send(mimeMessage);
    }

    @Async
    @SneakyThrows
    public void sendPayment(PaymentConfirmation paymentConfirmation){

        MimeMessage mimeMessage = getNewMimeMessage();
        MimeMessageHelper  messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        // 1) От Кого
        messageHelper.setFrom("ecommerce@gmail.com");

        String templateName = "payment-confirmation.html";

        Map<String, Object> model = new HashMap<>();
        model.put("customerName", paymentConfirmation.customerFirstname());
        model.put("amount", paymentConfirmation.amount());
        model.put("orderReference", paymentConfirmation.orderReference());

        Context context = new Context();
        context.setVariables(model);

        var finishedHTML = templateEngine.process(templateName, context);

        // 2) Что
        messageHelper.setText(finishedHTML, true);

        // 3) Кому
        messageHelper.setTo(paymentConfirmation.customerEmail());

        // 4) Заголовок
        messageHelper.setSubject("Payment Confirmation Notification");

        mailSender.send(mimeMessage);
    }


    private MimeMessage getNewMimeMessage(){
        return mailSender.createMimeMessage();
    }

}

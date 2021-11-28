package com.taisiia.travelagency.mailservice;

import com.taisiia.travelagency.domain.dao.Discount;
import com.taisiia.travelagency.domain.dao.TourOrder;
import com.taisiia.travelagency.domain.dao.User;
import com.taisiia.travelagency.pdf.PdfGeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;
    private final PdfGeneratorService pdfGeneratorService;

    public void sendConfirmationEmail(String userEmail, String confirmationToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("shoptaisiia@gmail.com");
            helper.setTo(userEmail);
            helper.setSubject("Confirmation Email");
            helper.setText(mailContentBuilder.generateContent(Map.ofEntries(Map.entry("token", "http://localhost:8080/confirm-account?token=" + confirmationToken)), "registration"), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }


    }

    public void sendWelcomeMail(User user) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("shoptaisiia@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Welcome email");
            helper.setText(mailContentBuilder.generateContent(Map.ofEntries(Map.entry("name", user.getFirstName())), "welcome"), true);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }


    public void sendOrderConfirmation(TourOrder tourOrder) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("shoptaisiia@gmail.com");
            helper.setTo(tourOrder.getUser().getEmail());
            helper.setSubject("Order Confirmation");
            helper.setText(mailContentBuilder.generateContent(Map.ofEntries(Map.entry("name", tourOrder.getUser().getFirstName())), "order_confirmation"), true);
            helper.addAttachment("OrderConfirmation.pdf", new File(pdfGeneratorService.generatePdf(tourOrder)));
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }

    public void sendResetPasswordEmail(String userEmail, String passwordToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("shoptaisiia@gmail.com");
            helper.setTo(userEmail);
            helper.setSubject("Reset Password");
            helper.setText(mailContentBuilder.generateContent(Map.ofEntries(Map.entry("token", "http://localhost:8080/reset_password?token=" + passwordToken)), "reset"), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());

        }
    }

    public void sendBirthDayDiscount(User user, Discount discount) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

        } catch (MessagingException e) {
            log.error(e.getMessage());
        }

    }
}


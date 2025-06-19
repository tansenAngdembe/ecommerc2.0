package com.ecommerce.ecommerce_20.util.service;


import com.ecommerce.ecommerce_20.dto.Request.SendMailRequest;
import com.ecommerce.ecommerce_20.util.constant.Constants;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private  final JavaMailSender mailSender;
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String productMailSender(SendMailRequest sendMailRequest) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(Constants.EMAIL);
            helper.setTo(sendMailRequest.getRecipient());// gmail
            helper.setSubject(sendMailRequest.getSubject());
            helper.setText(sendMailRequest.getMessage(), true);

            return "main send";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";


        }


    }

}

package com.s14.petshop.service;

import com.s14.petshop.model.beans.Discount;
import com.s14.petshop.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.file.Paths;

@Service
public class EmailSenderService extends AbstractService {

    @Autowired
    private JavaMailSender emailSender;

    private void sendEmail(String to, String subject, String text) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("noreply.petshop.bg@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(
                    Paths.get("C:\\Users\\user1\\OneDrive\\Работен плот\\demo\\sale-banner-2.jpg"));
            helper.addAttachment(file.getFilename(), file);
            emailSender.send(mimeMessage);

        } catch (MessagingException e) {
            System.out.println("Exception in sendEmail method");
            e.printStackTrace();
        }

    }

    public void sendEmailToAllSubscribedUsers(Discount discount) {


    }
}

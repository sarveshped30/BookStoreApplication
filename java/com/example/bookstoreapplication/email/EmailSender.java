package com.example.bookstoreapplication.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * EmailSender class contains methods for sending mail's to user mail address
 * sending mail by using classes form javax mail dependency
 **/
@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends main to user mail address in form of proper subject and mail body
     **/
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pednekarsarvesh30@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
        System.out.println("Mail sent successfully!!!");
    }
}

// Implements the EmailService interface with logic to send the verification emails

package com.dissertation.WritingApp.service.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    public EmailServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }
    
    @Override
    public void sendConfirmationEmail(String to, String subject, String body) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions as needed
        }
    }
}
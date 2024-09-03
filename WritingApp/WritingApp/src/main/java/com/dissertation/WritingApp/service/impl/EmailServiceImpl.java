// Implements the EmailService interface with logic to send the verification emails

package com.dissertation.WritingApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dissertation.WritingApp.models.EmailConfirmationToken;
import com.dissertation.WritingApp.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;
    
 //   @Autowired
 //   private EmailService emailService;

    public EmailServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

//    @Override
//    public void sendConfirmationEmail(String to, String token) {
//        //MIME - HTML message
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(emailConfirmationToken.getUser().getUsername());
//        helper.setSubject("Confirm your E-Mail - MFA Application Registration");
//        helper.setText("<html>" +
//                        "<body>" +
//                        "<h2>Dear "+ emailConfirmationToken.getUser().getFullname() + ",</h2>"
//                        + "<br/> We're excited to have you get started. " +
//                        "Please click on below link to confirm your account."
//                        + "<br/> "  + generateConfirmationLink(emailConfirmationToken.getToken())+"" +
//                        "<br/> Regards,<br/>" +
//                        "MFA Registration team" +
//                        "</body>" +
//                        "</html>"
//                , true);
//
//        sender.send(message);
//    }
    
    @Override
    public void sendConfirmationEmail(String to, String token) {
        String subject = "Please verify your email address";
        String message = "Click the link below to verify your email address:\n"
                        + "http://localhost:8080/confirm?token=" + token;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        try {
            sender.send(mailMessage);
            System.out.println("Email sent to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to send email to: " + to);
        }
    }

//    private String generateConfirmationLink(String token){
//        return "<a href=http://localhost:8080/confirm-email?token="+token+">Confirm Email</a>";
//    }
}
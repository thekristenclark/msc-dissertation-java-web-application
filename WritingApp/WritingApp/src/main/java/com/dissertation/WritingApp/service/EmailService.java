package com.dissertation.WritingApp.service;

import com.dissertation.WritingApp.models.EmailConfirmationToken;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendConfirmationEmail(EmailConfirmationToken emailConfirmationToken) throws MessagingException;
}
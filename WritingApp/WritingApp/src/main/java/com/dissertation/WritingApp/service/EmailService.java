// Defines the methods for sending the verification emails

package com.dissertation.WritingApp.service;

public interface EmailService {
    void sendConfirmationEmail(String to, String subject, String body);
}
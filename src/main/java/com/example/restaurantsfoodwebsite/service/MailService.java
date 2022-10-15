package com.example.restaurantsfoodwebsite.service;


public interface MailService {

    void sendEmail(String to, String subject, String text);
}
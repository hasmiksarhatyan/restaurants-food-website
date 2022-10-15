package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.entity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken createToken(User user);

    VerificationToken findByPlainToken(String plainToken);

    void delete(VerificationToken token);
}


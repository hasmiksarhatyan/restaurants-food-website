package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.user.ChangePasswordDto;
import com.example.restaurantsfoodwebsite.dto.user.CreateUserDto;
import com.example.restaurantsfoodwebsite.dto.user.EditUserDto;
import com.example.restaurantsfoodwebsite.dto.user.UserOverview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;

public interface UserService {

    Page<UserOverview> getUsers(Pageable pageable);

    void saveUser(CreateUserDto dto) throws MessagingException;

    void verifyUser(String token) throws Exception;

    void delete(int id);

    void changePassword(Integer id, ChangePasswordDto dto);

    void editUser(EditUserDto dto, int userId);

}



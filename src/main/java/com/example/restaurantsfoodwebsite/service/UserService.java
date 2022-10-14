package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.user.ChangePasswordDto;
import com.example.restaurantsfoodwebsite.dto.user.CreateUserDto;
import com.example.restaurantsfoodwebsite.dto.user.EditUserDto;
import com.example.restaurantsfoodwebsite.dto.user.UserOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserOverview> getUsers(Pageable pageable);

    void saveUser(CreateUserDto dto);

    void verifyUser(String token) throws Exception;

    UserOverview getByEmail(String email);

    void delete(int id);

    void changePassword(Integer id, ChangePasswordDto dto);

    void editUser(EditUserDto dto, User user);

}



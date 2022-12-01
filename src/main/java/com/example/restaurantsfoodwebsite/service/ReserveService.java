package com.example.restaurantsfoodwebsite.service;


import com.example.restaurantsfoodwebsite.dto.reserve.CreateReserveDto;
import com.example.restaurantsfoodwebsite.dto.reserve.EditReserveDto;
import com.example.restaurantsfoodwebsite.dto.reserve.ReserveOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ReserveService {
    List<ReserveOverview> findAll();

    Page<ReserveOverview> getAll(Pageable pageable);

    Page<ReserveOverview> getReserveByRestaurant(User user,Pageable pageable);

    Page<ReserveOverview> getReserveByUser(int id, Pageable pageable);

    void addReserve(CreateReserveDto dto, User user);

    void delete(int id);

    void editReserve(EditReserveDto dto, int id);

    ReserveOverview getById(int id);
}



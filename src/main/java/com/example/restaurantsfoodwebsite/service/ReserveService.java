package com.example.restaurantsfoodwebsite.service;


import com.example.restaurantsfoodwebsite.dto.reserve.CreateReserveDto;
import com.example.restaurantsfoodwebsite.dto.reserve.ReserveOverview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReserveService {

    Page<ReserveOverview> getReserve(Pageable pageable);

    void addReserve(CreateReserveDto dto);

    void delete(int id);
}



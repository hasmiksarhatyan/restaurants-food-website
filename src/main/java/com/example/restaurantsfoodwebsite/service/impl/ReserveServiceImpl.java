package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.reserve.CreateReserveDto;
import com.example.restaurantsfoodwebsite.dto.reserve.ReserveOverview;
import com.example.restaurantsfoodwebsite.mapper.ReserveMapper;
import com.example.restaurantsfoodwebsite.repository.ReserveRepository;
import com.example.restaurantsfoodwebsite.service.ReserveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {

    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;


    @Override
    public Page<ReserveOverview> getReserve(Pageable pageable) {
        return null;
    }

    @Override
    public void addReserve(CreateReserveDto dto) {
    }

    @Override
    public void delete(int id) {

    }
}

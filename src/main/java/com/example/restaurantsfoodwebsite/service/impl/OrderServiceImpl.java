package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.order.CreateOrderDto;
import com.example.restaurantsfoodwebsite.dto.order.OrderOverview;
import com.example.restaurantsfoodwebsite.entity.Order;
import com.example.restaurantsfoodwebsite.mapper.OrderMapper;
import com.example.restaurantsfoodwebsite.repository.OrderRepository;
import com.example.restaurantsfoodwebsite.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Page<OrderOverview> getOrders(Pageable pageable) {
        Page<Order> all = orderRepository.findAll(pageable);
        List<OrderOverview> orderOverviews = orderMapper.mapToDto(all);
        return new PageImpl<>(orderOverviews, pageable, orderOverviews.size());
    }

    @Override
    public void addOrder(CreateOrderDto dto) {
        orderRepository.save(orderMapper.mapToEntity(dto));
    }

    @Override
    public void delete(int id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        orderRepository.deleteById(id);
    }
}

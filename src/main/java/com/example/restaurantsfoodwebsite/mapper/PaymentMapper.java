package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.payment.CreatePaymentDto;
import com.example.restaurantsfoodwebsite.dto.payment.PaymentOverview;
import com.example.restaurantsfoodwebsite.entity.Payment;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment mapToEntity(CreatePaymentDto createPaymentDto);

    PaymentOverview mapToDto(Payment payment);

    List<PaymentOverview> mapToDto(List<Payment> payments);

    List<PaymentOverview> mapToDto(Page<Payment> payments);
}


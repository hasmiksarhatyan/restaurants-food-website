package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.payment.CreatePaymentDto;
import com.example.restaurantsfoodwebsite.dto.payment.PaymentOverview;
import com.example.restaurantsfoodwebsite.entity.Payment;
import com.example.restaurantsfoodwebsite.mapper.PaymentMapper;
import com.example.restaurantsfoodwebsite.repository.PaymentRepository;
import com.example.restaurantsfoodwebsite.service.PaymentService;
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
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Page<PaymentOverview> getPayments(Pageable pageable) {
        Page<Payment> all = paymentRepository.findAll(pageable);
        List<PaymentOverview> paymentOverviews = paymentMapper.mapToDto(all);
        return new PageImpl<>(paymentOverviews, pageable, paymentOverviews.size());
    }

    @Override
    public void addPayment(CreatePaymentDto createPaymentDto) {
        paymentRepository.save(paymentMapper.mapToEntity(createPaymentDto));
    }

    @Override
    public void delete(int id) {
        if (!paymentRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        paymentRepository.deleteById(id);
    }
}

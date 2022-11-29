package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.creditCard.CreateCreditCardDto;
import com.example.restaurantsfoodwebsite.dto.payment.CreatePaymentDto;
import com.example.restaurantsfoodwebsite.dto.payment.PaymentOverview;
import com.example.restaurantsfoodwebsite.entity.*;
import com.example.restaurantsfoodwebsite.mapper.PaymentMapper;
import com.example.restaurantsfoodwebsite.repository.CreditCardRepository;
import com.example.restaurantsfoodwebsite.repository.PaymentRepository;
import com.example.restaurantsfoodwebsite.service.OrderService;
import com.example.restaurantsfoodwebsite.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.restaurantsfoodwebsite.entity.OrderStatus.NEW;
import static java.lang.String.format;
import static java.time.LocalDate.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final CreditCardRepository creditCardRepository;
    private final OrderService orderService;

    @Override
    public Page<PaymentOverview> getPayments(Pageable pageable) {
        Page<Payment> all = paymentRepository.findAll(pageable);
        List<PaymentOverview> paymentOverviews = paymentMapper.mapToDto(all);
        return new PageImpl<>(paymentOverviews, pageable, paymentOverviews.size());
    }

    @Override
    public boolean addPayment(CreatePaymentDto createPaymentDto, CreateCreditCardDto creditCardDto, User user) {
        Payment payment = paymentMapper.mapToEntity(createPaymentDto);
        payment.setUser(user);

        validateCreditCard(creditCardDto,user);

        CreditCard creditCard = CreditCard.builder()
                .cardHolder(creditCardDto.getCardHolder())
                .cardExpiresAt(creditCardDto.getCardExpiresAt())
                .cvv(creditCardDto.getCvv())
                .cardNumber(creditCardDto.getCardNumber())
                .user(user)
                .build();
        creditCardRepository.save(creditCard);

        orderService.addOrder(Order.builder()
                .orderAt(LocalDateTime.now())
                .user(user)
                .payment(paymentRepository.save(payment))
                .status(NEW)
                .totalPrice(payment.getTotalPrice())
                .isPaid(true)
                .build());
        return true;
    }

    private void validateCreditCard(CreateCreditCardDto creditCardDto, User user) {
        String cardHolder = creditCardDto.getCardHolder();
        String userName = format("%s %s",user.getFirstName(),user.getLastName());

        if(!cardHolder.equalsIgnoreCase(userName)){
            throw new IllegalStateException();
        }
        if(creditCardDto.getCardExpiresAt().isBefore(now())){
            throw new IllegalStateException();
        }
    }

    @Override
    public void delete(int id) {
        if (!paymentRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        paymentRepository.deleteById(id);
    }
}

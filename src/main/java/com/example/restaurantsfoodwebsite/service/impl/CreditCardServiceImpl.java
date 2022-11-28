package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.creditCard.CreateCreditCardDto;
import com.example.restaurantsfoodwebsite.dto.creditCard.CreditCardOverview;
import com.example.restaurantsfoodwebsite.entity.CreditCard;
import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.mapper.CreditCardMapper;
import com.example.restaurantsfoodwebsite.repository.CreditCardRepository;
import com.example.restaurantsfoodwebsite.service.CreditCardService;
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
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;

    @Override
    public Page<CreditCardOverview> getCreditCards(Pageable pageable, User user) {
        Page<CreditCard> creditCard = creditCardRepository.findCreditCardByUser(user, pageable);
        if (creditCard.isEmpty()) {
            throw new IllegalStateException("You don't have a basket");
        }
        List<CreditCardOverview> creditCardOverviews = creditCardMapper.mapToDto(creditCard);
        return new PageImpl<>(creditCardOverviews, pageable, creditCardOverviews.size());
    }

    @Override
    public void addCreditCard(CreateCreditCardDto dto, User user) {
        CreditCard creditCard = creditCardMapper.mapToEntity(dto);
        creditCard.setUser(user);
        creditCardRepository.save(creditCard);
    }
}

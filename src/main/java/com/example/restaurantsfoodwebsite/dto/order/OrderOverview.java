package com.example.restaurantsfoodwebsite.dto.order;

import com.example.restaurantsfoodwebsite.dto.payment.PaymentOverview;
import com.example.restaurantsfoodwebsite.dto.user.UserOverview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOverview {

    private Integer id;
    private String additionalAddress;
    private String additionalPhone;
    private LocalDateTime orderAt;
    private String status;
    private PaymentOverview paymentOverview;
//    private List<ProductOverview> productOverviews;
    private UserOverview userOverview;
}

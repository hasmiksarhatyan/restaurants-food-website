package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
}

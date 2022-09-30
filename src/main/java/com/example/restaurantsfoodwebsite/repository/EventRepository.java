package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}

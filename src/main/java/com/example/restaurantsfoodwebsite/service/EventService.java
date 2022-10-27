package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.event.EventDto;
import com.example.restaurantsfoodwebsite.dto.event.EventOverview;
import com.example.restaurantsfoodwebsite.dto.restaurant.EditRestaurantDto;
import com.example.restaurantsfoodwebsite.entity.Event;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.metamodel.SingularAttribute;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventService {
    Page<EventOverview> findAll( Pageable pageable);

//    List<EventOverview> findEventsByRestaurantId(int id);
    Page<EventOverview> findEventsByRestaurantId(int id,Pageable pageable);
    void save(EventDto eventDto, MultipartFile[] files) throws IOException;

    void editEvent(EventDto dto, int id,MultipartFile[] files) throws IOException;

    byte[] getEventImage(String fileName) throws IOException;


    void deleteEvent(int id);

    EventOverview findById(int id);


    List<Integer> pageNumbers(Page<EventOverview> eventOverviews);

    Map<Integer, List<EventOverview>> sortEventsByRestaurant();
}

package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.event.EventDto;
import com.example.restaurantsfoodwebsite.dto.event.EventOverview;
import com.example.restaurantsfoodwebsite.entity.Event;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.mapper.EventMapper;
import com.example.restaurantsfoodwebsite.mapper.RestaurantMapper;
import com.example.restaurantsfoodwebsite.repository.EventRepository;
import com.example.restaurantsfoodwebsite.repository.RestaurantRepository;
import com.example.restaurantsfoodwebsite.service.EventService;
import com.example.restaurantsfoodwebsite.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final FileUtil fileUtil;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final RestaurantRepository restaurantRepository;


    public Page<EventOverview> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable).map(eventMapper::mapToOverview);

    }

//    @Override
//    public List<EventOverview> findEventsByRestaurantId(int id) {
//        return eventMapper.mapToOverviewList(eventRepository.findEventsByRestaurant_Id(id));
//    }

    public Page<EventOverview> findEventsByRestaurantId(int id,Pageable pageable) {
        return eventRepository.findEventsByRestaurantId(id,pageable).map(eventMapper::mapToOverview);
    }
    public Map<Integer, List<EventOverview>> sortEventsByRestaurant() {
        Map<Integer, List<EventOverview>> events = new HashMap<>();
        List<Restaurant> all = restaurantRepository.findAll();
        for (Restaurant restaurant : all) {
            if (restaurant != null) {
                List<EventOverview> eventsByRestaurant = eventMapper.mapToOverviewList(eventRepository.findEventsByRestaurant_Id(restaurant.getId()));
                events.put(restaurant.getId(), eventsByRestaurant);
            }
        }
        return events;
    }

    @Override
    public void save(EventDto dto, MultipartFile[] files) throws IOException {
        dto.setPictures(fileUtil.uploadImages(files));
        eventRepository.save(eventMapper.mapToEntity(dto));
    }



    @Override
    public byte[] getEventImage(String fileName) throws IOException {
        return fileUtil.getImage(fileName);
    }


    @Override
    public void editEvent(EventDto dto, int id, MultipartFile[] files) throws IOException {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isEmpty()) {
            throw new IllegalStateException("Sorry, something went wrong, try again.");
        }
        Event event = eventOptional.get();
        String name = dto.getName();
        if (StringUtils.hasText(name)) {
            event.setName(name);
        }
        String description = dto.getDescription();
        if (StringUtils.hasText(description)) {
            event.setDescription(description);
        }

        double price = dto.getPrice();
        if (price >= 0) {
            event.setPrice(price);
        }

        Date eventDateTime = dto.getEventDateTime();
        if (eventDateTime != null) {
            event.setEventDateTime(eventDateTime);
        }

        Restaurant restaurant = dto.getRestaurant() ;
        if (restaurant != event.getRestaurant()) {
            event.setRestaurant(restaurant);
        }

        List<String> pictures = dto.getPictures();
        if (pictures != event.getPictures()) {
            event.setPictures(fileUtil.uploadImages(files));
        }
        eventRepository.save(event);
    }


    @Override
    public void deleteEvent(int id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        eventRepository.deleteById(id);
    }

    @Override
    public EventOverview findById(int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new IllegalStateException("There is no event");
        }
        return eventMapper.mapToOverview(event.get());
    }

    @Override
    public List<Integer> pageNumbers(Page<EventOverview> eventOverviews) {
        int totalPages = eventOverviews.getTotalPages();
        if (totalPages > 0) {
            return IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return null;
    }
}


package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.event.EventOverview;
import com.example.restaurantsfoodwebsite.mapper.EventMapper;
import com.example.restaurantsfoodwebsite.repository.EventRepository;
import com.example.restaurantsfoodwebsite.repository.RestaurantRepository;
import com.example.restaurantsfoodwebsite.service.EventService;
import com.example.restaurantsfoodwebsite.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class EventController {
    private final EventService eventService;
    private final RestaurantService restaurantService;


    @GetMapping("/events")
    public String events(@RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size,
                         ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);
        Page<EventOverview> allEventsPage = eventService.findAll(PageRequest.of(currentPage-1, pageSize));
        modelMap.addAttribute("events", allEventsPage);
        modelMap.addAttribute("pageNumbers", eventService.pageNumbers(allEventsPage));
        modelMap.addAttribute("restaurants", restaurantService.findAll());
        modelMap.addAttribute("eventsByRestaurant", eventService.sortEventsByRestaurant());
        return "events";
    }

    @GetMapping("/restaurants/{id}/events")
    public String restaurantEvents(@PathVariable("id") int id,
                                   @RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size,
                                   ModelMap modelMap, Pageable pageable) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);
        Page<EventOverview> eventsPageByRestaurant = eventService.findEventsByRestaurantId(id,PageRequest.of(currentPage-1,pageSize));
        modelMap.addAttribute("events",eventsPageByRestaurant);
        modelMap.addAttribute("restaurant",restaurantService.getRestaurant(id));
        modelMap.addAttribute("eventsByRestaurant", eventService.findEventsByRestaurantId(id,pageable));
        modelMap.addAttribute("pageNumbers", eventService.pageNumbers(eventsPageByRestaurant));
        return "restaurantEvents";
    }

    @GetMapping("/events/{id}")

    public String events(@PathVariable("id") int id,ModelMap modelMap) {
    modelMap.addAttribute("event", eventService.findById(id));
    return "event";

    }


    @GetMapping(value = "/events/getImages", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return eventService.getEventImage(fileName);
    }
}

package com.example.restaurantsfoodwebsite.controller.managerController;

import com.example.restaurantsfoodwebsite.dto.event.EventDto;
import com.example.restaurantsfoodwebsite.dto.event.EventOverview;
import com.example.restaurantsfoodwebsite.service.EventService;
import com.example.restaurantsfoodwebsite.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/events")
public class EventManagerController {
    private final EventService eventService;
    private final RestaurantService restaurantService;

    @GetMapping
    public String events(@RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size,
                         ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);
        Page<EventOverview> allEventsPage = eventService.findAll(PageRequest.of(currentPage-1, pageSize));
        modelMap.addAttribute("events", allEventsPage);
        modelMap.addAttribute("pageNumbers", eventService.pageNumbers(allEventsPage));
        return "manager/events";
    }


    @GetMapping("/add")
    public String addEventPage(ModelMap modelMap) {
        modelMap.addAttribute("restaurants", restaurantService.findAll());
        return "manager/addEvent";
    }

    @PostMapping("/add")
    public String addEvent(@RequestParam("eventImage") MultipartFile[] files,
                           @ModelAttribute EventDto dto,
                           ModelMap modelMap) throws IOException {

        eventService.save(dto, files);
        return "redirect:/manager/events";
    }

    @GetMapping(value = "/getImages", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
            return eventService.getEventImage(fileName);

    }

    @GetMapping("/edit/{id}")
    public String editEventPage(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            modelMap.addAttribute("event", eventService.findById(id));
            modelMap.addAttribute("restaurants", restaurantService.findAll());
            return "manager/editEvent";
        } catch (IllegalStateException ex) {
            modelMap.addAttribute("errorMessageEdit", ex.getMessage());
            return "redirect:/manager/events";
        }
    }

    @PostMapping("/edit/{id}")
    public String editEvent(@PathVariable("id") int id, @ModelAttribute EventDto dto, @RequestParam("eventImage") MultipartFile[] files) throws IOException {
        eventService.editEvent(dto, id, files);
        return "redirect:/manager/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            eventService.deleteEvent(id);
            return "redirect:/manager/events";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "manager/events";
        }
    }
}

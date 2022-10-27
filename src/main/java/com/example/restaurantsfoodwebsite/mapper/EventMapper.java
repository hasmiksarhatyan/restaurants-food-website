package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.event.EventDto;
import com.example.restaurantsfoodwebsite.dto.event.EventOverview;
import com.example.restaurantsfoodwebsite.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event mapToEntity(EventDto eventDto);

    @Mapping(source = "event.restaurant",target = "restaurantOverview")
    EventOverview mapToOverview(Event event);

    List<EventOverview> mapToOverviewList(List<Event> events);

}

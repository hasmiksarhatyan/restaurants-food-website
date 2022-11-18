package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.event.CreateEventDto;
import com.example.restaurantsfoodwebsite.dto.event.EventOverview;
import com.example.restaurantsfoodwebsite.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(source = "eventDto.restaurantId", target = "restaurant.id")
    Event mapToEntity(CreateEventDto eventDto);

    @Mapping(source = "event.restaurant",target = "restaurantOverview")
    EventOverview mapToOverview(Event event);

    List<EventOverview> mapToOverviewList(List<Event> events);

}

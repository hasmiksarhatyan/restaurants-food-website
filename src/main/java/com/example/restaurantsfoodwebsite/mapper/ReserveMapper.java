package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.reserve.CreateReserveDto;
import com.example.restaurantsfoodwebsite.dto.reserve.ReserveOverview;
import com.example.restaurantsfoodwebsite.entity.Reserve;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ReserveMapper {
    @Mapping(source = "dto.restaurantId", target = "restaurant.id")
    Reserve mapToEntity(CreateReserveDto dto);
    @Mapping(source = "reserve.restaurant", target = "restaurantOverview")
    @Mapping(source = "reserve.user", target = "userOverview")
    ReserveOverview mapToOverview(Reserve reserve);

    List<ReserveOverview> mapToOverviewList(List<Reserve> reserves);

}


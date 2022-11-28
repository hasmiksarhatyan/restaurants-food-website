package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.reserve.CreateReserveDto;
import com.example.restaurantsfoodwebsite.dto.reserve.ReserveOverview;
import com.example.restaurantsfoodwebsite.entity.Reserve;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReserveMapper {

    Reserve mapToEntity(CreateReserveDto createReserveDto);

    ReserveOverview mapToDto(Reserve reserve);

    List<ReserveOverview> mapToDto(List<Reserve> reserves);

    List<ReserveOverview> mapToDto(Page<Reserve> reserves);
}


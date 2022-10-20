package com.example.restaurantsfoodwebsite.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class PageUtil {

    public List<Integer> getTotalPages(Page<?> object) {
        int totalPages = object.getTotalPages();
        if (totalPages > 0) {
            return IntStream.rangeClosed(0, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return null;
    }
}
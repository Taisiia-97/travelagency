package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.dao.TourOrder;
import com.taisiia.travelagency.dto.TourOrderDto;

import java.util.Set;
import java.util.stream.Collectors;

public class TourOrderMapper {
    public static TourOrderDto map(TourOrder order) {
return null;
    }

    public static Set<TourOrderDto> map(Set<TourOrder> orders) {
        if (orders == null) {
            return Set.of();
        }
        return orders.stream().map(TourOrderMapper::map).collect(Collectors.toSet());
    }
}

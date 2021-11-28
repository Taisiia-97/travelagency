package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.domain.dto.OrderDto;
import com.taisiia.travelagency.domain.form.OrderForm;
import com.taisiia.travelagency.domain.dao.TourOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TourMapper.class, UserMapper.class}, imports = {LocalDateTime.class, BigDecimal.class})
public interface OrderMapper {

    @Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    OrderDto toDto(TourOrder tourOrder);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "totalPriceWithoutDiscount",ignore = true)
    @Mapping(source = "childPlaces", target = "childPlaces", defaultValue = "0")
    @Mapping(target = "orderDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "totalDiscount", expression = "java(BigDecimal.valueOf(0))")
    TourOrder toDao(OrderForm orderForm);

    @Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    List<OrderDto> toListDto(List<TourOrder> orders);
}

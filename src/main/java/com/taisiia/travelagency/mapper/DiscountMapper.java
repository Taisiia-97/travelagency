package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.domain.dao.Discount;
import com.taisiia.travelagency.domain.dto.DiscountDto;
import com.taisiia.travelagency.domain.form.DiscountForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    @Mapping(target = "status", ignore = true)
    Discount toDao(DiscountForm promotionForm);

    DiscountDto toDto(Discount promotion);

    List<DiscountDto> toListDto(List<Discount> promotions);
}

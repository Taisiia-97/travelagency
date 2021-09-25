package com.taisiia.travelagency.promotion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface PromotionMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID().toString())")
    Promotion toDao(PromotionForm promotionForm);

    PromotionDto toDto(Promotion promotion);

    List<PromotionDto> toListDto(List<Promotion> promotions);
}

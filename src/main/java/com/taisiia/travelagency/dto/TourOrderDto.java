package com.taisiia.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.tour.Tour;
import com.taisiia.travelagency.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourOrderDto {
    private Long id;
    private Tour tour;
    private Set<UserDto> users;
    private Double totalPrice;
}

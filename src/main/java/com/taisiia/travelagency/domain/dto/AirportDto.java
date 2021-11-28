package com.taisiia.travelagency.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.domain.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirportDto {
    private Long id;
    private String uuid;
    private AddressDto address;

}

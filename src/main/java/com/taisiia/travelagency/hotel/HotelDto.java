package com.taisiia.travelagency.hotel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.address.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDto {
    private Long id;
    private String uuid;
    private String hotelName;
    private Integer standard;
    private String description;
    private HotelType hotelType;
    private String photo;
    private AddressDto address;
}

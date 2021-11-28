package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.Hotel;
import com.taisiia.travelagency.enums.HotelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByHotelNameContaining(String hotelName);

    List<Hotel> findAllByAddressCityCityName(String cityName);

    List<Hotel> findAllByAddressCityCountryCountryName(String countryName);

    List<Hotel> findAllByDescriptionContaining(String description);

    List<Hotel> findAllByStandard(Integer standard);

    List<Hotel> findAllByHotelType(HotelType hotelType);

    List<Hotel> findAllByHotelTypeAndStandardAndAddressCityCityName(HotelType hotelType, Integer standard, String city);

    List<Hotel> findAllByAddressCityCountryCountryNameAndStandard(String countryName, Integer standard);

    Optional<Hotel> findByUuid(String uuid);
}

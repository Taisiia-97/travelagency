package com.taisiia.travelagency.tour;

import com.taisiia.travelagency.hotel.HotelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> findAllByFromAirPortAddressCityCityName(String cityName);

    List<Tour> findAllByFromAirPortAddressCityCountryCountryName(String countryName);

    List<Tour> findAllByFromAirPortAddressCityCountryContinentsContinentName(String continentName);

    List<Tour> findAllByHotelHotelName(String hotelName);

    List<Tour> findAllByHotelHotelType(HotelType hotelType);

    List<Tour> findAllByPurposeAirportAddressCityCityName(String cityName);

   List<Tour> findAllByDepartureDateIsBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Tour> findAllByPromotionsIsNotNull();

    List<Tour> findAllByDepartureDateAfterOrderByPurposeAirportAddressCityCityName(LocalDateTime date);

    List<Tour> findAllByPlacesAdultsBetweenOrPlacesChildBetween(Integer placesAdults, Integer placesAdults2, Integer placesChild, Integer placesChild2);


}

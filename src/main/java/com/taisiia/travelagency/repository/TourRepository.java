package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.Tour;
import com.taisiia.travelagency.enums.HotelType;
import com.taisiia.travelagency.enums.TourStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {

    List<Tour> findAllByFromAirPortAddressCityCityNameAndStatusIs(String cityName, TourStatus tourStatus);

    List<Tour> findAllByFromAirPortAddressCityCountryCountryName(String countryName);

    List<Tour> findAllByFromAirPortAddressCityCountryContinentsContinentName(String continentName);

    List<Tour> findAllByHotelHotelName(String hotelName);

    List<Tour> findAllByHotelHotelType(HotelType hotelType);

    List<Tour> findAllByPurposeAirportAddressCityCityName(String cityName);

    List<Tour> findAllByDepartureDateIsBetween(LocalDateTime startDate, LocalDateTime endDate);


    List<Tour> findAllByDepartureDateAfterOrderByPurposeAirportAddressCityCityName(LocalDateTime date);

    List<Tour> findAllByPlacesAdultsBetweenOrPlacesChildBetween(Integer placesAdults, Integer placesAdults2, Integer placesChild, Integer placesChild2);

    List<Tour> findByDepartureDateAfter(LocalDateTime localDateTime);

    Optional<Tour> findByUuid(String uuid);


    List<Tour> findAllByDepartureDateBefore(LocalDateTime localDateTime);

    List<Tour> findAllByPurposeAirportAddressCityCountryContinentsContinentName(String continent);

    List<Tour> findAll(Specification<Tour> specification);

    List<Tour> findAllByPurposeAirportAddressCityCountryCountryName(String country);


    // List<Tour> findAllByFromAirPortAddressCityCityNameAndHotelStandardAndDepartureDateBetweenAAAndReturnDateBetweenAndHotelHotelTypeAndHotelStandard


}

package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    City findCityByCityName(String cityName);


    List<City> findAllByCountryCountryName(String countryNme);


    Optional<City> findByCityName(String cityName);
}

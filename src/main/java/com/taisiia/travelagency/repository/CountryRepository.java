package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country getCountryByCountryName(String countryName);

    Optional<Country> findCountryByCountryName (String countryName);

     List<Country> findAllByContinentsContinentName(String continentName);




}

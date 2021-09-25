package com.taisiia.travelagency.airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findAllByAddressCityCityName(String cityName);

    Optional<Airport> findByUuid(String uuid);
}

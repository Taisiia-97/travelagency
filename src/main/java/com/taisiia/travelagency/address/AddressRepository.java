package com.taisiia.travelagency.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByCityCityName(String cityName);


    Optional<Address> findByStreetNameAndStreetNumberAndLocalNumberAndCityCityName(String streetName, Integer streetNumber,
                                                                         Integer LocalNumber, String cityName);
}

package com.taisiia.travelagency.airport;

import com.taisiia.travelagency.address.Address;
import com.taisiia.travelagency.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final AddressService addressService;


    public Airport save(Airport airport, String city) {
        Address address = addressService.getOrCreate(airport.getAddress(), city);
        airport.setAddress(address);
        return airportRepository.save(airport);

    }

    @Transactional
    public Airport update(Airport airport, String city, Long id) {
        Airport updated = findById(id);
        Address address = addressService.getOrCreate(airport.getAddress(), city);
        updated.setAddress(address);
        return updated;
    }

    public Airport findById(Long id) {
        return airportRepository.getById(id);
    }

    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    public List<Airport> findByCityName(String cityName) {
        return airportRepository.findAllByAddressCityCityName(cityName);
    }

    public Page<Airport> getPage(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    public Optional<Airport> findByUuid(String uuid) {
        return airportRepository.findByUuid(uuid);
    }
}

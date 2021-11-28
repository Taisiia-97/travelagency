package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.Address;
import com.taisiia.travelagency.exception.CityException;
import com.taisiia.travelagency.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CityService cityService;

    public Address findById(Long id) {
        return addressRepository.getById(id);
    }

    public Address save(Address address, String city) {
        cityService.findCityByName(city).orElseThrow(() -> new CityException("City " + city + " doesn't exsits"));
        address.setCity(cityService.findByCityName(city));
        return addressRepository.save(address);
    }

    @Transactional
    public Address update(Address address, String city, Long id) {
        Address toUpdate = findById(id);
        toUpdate.setStreetName(address.getStreetName());
        toUpdate.setStreetNumber(address.getLocalNumber());
        toUpdate.setLocalNumber(address.getLocalNumber());
        cityService.findCityByName(city).orElseThrow(() -> new CityException("City " + city + " doesn't exsits"));
        toUpdate.setCity(cityService.findByCityName(city));
        return toUpdate;
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public Page<Address> getAddressPage(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public List<Address> getAddressesByCity(String city) {
        return addressRepository.findAllByCityCityName(city);
    }

    private Optional<Address> findAddressByValues(String streetName, Integer streetNumber, Integer localNumber, String city) {
        return addressRepository.findByStreetNameAndStreetNumberAndLocalNumberAndCityCityName(streetName, streetNumber, localNumber, city);

    }

    public Address getOrCreate(Address address, String city) {
        cityService.findCityByName(city).orElseThrow(() -> new CityException("City " + city + " doesn't exsits"));
        if (findAddressByValues(address.getStreetName(), address.getStreetNumber(),
                address.getLocalNumber(), city).isEmpty()) {
            return save(address, city);
        }
        address.setCity(cityService.findByCityName(city));
        return address;
    }
}

package com.taisiia.travelagency.city;

import com.taisiia.travelagency.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CountryService countryService;


    public City findById(Long id) {
        return cityRepository.getById(id);
    }


    public City save(City city, String country) {

        if (countryService.findByCountryName(country).isPresent()) {
            city.setCountry(countryService.findByName(country));
        }

        return cityRepository.save(city);
    }


    @Transactional
    public City update(City city, String country, Long id) {
        City cityUpdate = findById(id);
        if (countryService.findByCountryName(country).isPresent()) {
            cityUpdate.setCountry(countryService.findByName(country));
            cityUpdate.setCityName(city.getCityName());
        }
        return cityUpdate;
    }


    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }


    public City findByCityName(String cityName) {
        return cityRepository.findCityByCityName(cityName);
    }


    public Page<City> getPage(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }


    public List<City> findByCountryName(String countryName) {
        return cityRepository.findAllByCountryCountryName(countryName);
    }

    public Optional<City> findCityByName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

}

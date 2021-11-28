package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.Country;
import com.taisiia.travelagency.repository.ContinentRepository;
import com.taisiia.travelagency.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final ContinentRepository continentRepository;


    public Country findById(Long id) {
        return countryRepository.getById(id);
    }


    public Country save(Country country, Set<String> continents) {
        country.setContinents(continentRepository.findByContinentNameIn(continents));
        return countryRepository.save(country);
    }


    @Transactional
    public Country update(Country country, Set<String> continents, Long id) {
        Country countryToUpdate = findById(id);
        countryToUpdate.setCountryName(country.getCountryName());
        countryToUpdate.setContinents(continentRepository.findByContinentNameIn(continents));
        return countryToUpdate;
    }


    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }


    public Country findByName(String countryName) {
        return countryRepository.getCountryByCountryName(countryName);
    }


    public Page<Country> getPage(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }


    public Optional<Country> findByCountryName(String countryName) {
        return countryRepository.findCountryByCountryName(countryName);
    }


    public List<Country> getCountriesByContinentName(String continentName) {
        return countryRepository.findAllByContinentsContinentName(continentName);
    }
}

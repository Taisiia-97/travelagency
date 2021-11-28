package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.CountryDto;
import com.taisiia.travelagency.domain.form.CountryForm;
import com.taisiia.travelagency.mapper.CountryMapper;
import com.taisiia.travelagency.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
@PreAuthorize("hasAuthority('user:write')")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @GetMapping("/{id}")
    public CountryDto findContinentById(@PathVariable Long id) {
        return countryMapper.toDto(countryService.findById(id));
    }

    @GetMapping("/name")
    public CountryDto findContinentByName(@RequestParam String countryName) {
        return countryMapper.toDto(countryService.findByName(countryName));
    }

    @GetMapping("/continent")
    public List<CountryDto> findCountriesByContinentName(@RequestParam String continentName) {
        return countryMapper.toListDto(countryService.getCountriesByContinentName(continentName));
    }

    @PostMapping
    public CountryDto saveContinent(@RequestBody @Valid CountryForm countryForm) {
        return countryMapper.toDto(countryService.save(countryMapper.toDao(countryForm), countryForm.getContinents()));
    }

    @PutMapping("/{id}")
    public CountryDto updateContinent(@RequestBody @Valid CountryForm countryForm, @PathVariable Long id) {
        return countryMapper.toDto(countryService.update(countryMapper.toDao(countryForm), countryForm.getContinents(), id));
    }

    @DeleteMapping("/{id}")
    public void deleteContinentById(@PathVariable Long id) {
        countryService.deleteById(id);
    }

    @GetMapping
    public Page<CountryDto> getCountryPage(@RequestParam int page, @RequestParam int size) {
        return countryService.getPage(PageRequest.of(page, size)).map(countryMapper::toDto);
    }

}

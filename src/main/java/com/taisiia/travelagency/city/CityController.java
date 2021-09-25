package com.taisiia.travelagency.city;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping("/{id}")
    public CityDto findCityById(@PathVariable Long id) {
        return cityMapper.toDto(cityService.findById(id));
    }

    @GetMapping("/name")
    public CityDto findCityByName(@RequestParam String cityName) {
        return cityMapper.toDto(cityService.findByCityName(cityName));
    }

    @PostMapping
    public CityDto saveCity(@RequestBody @Valid CityForm cityForm) {
        return cityMapper.toDto(cityService.save(cityMapper.toDao(cityForm), cityForm.getCountry()));
    }

    @PutMapping("/{id}")
    public CityDto updateCity(@RequestBody @Valid CityForm cityForm, @PathVariable Long id) {
        return cityMapper.toDto(cityService.update(cityMapper.toDao(cityForm), cityForm.getCountry(), id));
    }

    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable Long id) {
        cityService.deleteById(id);
    }

    @GetMapping
    public Page<CityDto> getCityPage(@RequestParam int page, @RequestParam int size) {
        return cityService.getPage(PageRequest.of(page, size)).map(cityMapper::toDto);
    }

    @GetMapping("/country/{country}")
    public List<CityDto> getCitiesByCountryName(@PathVariable String country) {
        return cityMapper.toListDto(cityService.findByCountryName(country));
    }
}

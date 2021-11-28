package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.TourDto;
import com.taisiia.travelagency.domain.form.TourSearchForm;
import com.taisiia.travelagency.mapper.TourMapper;
import com.taisiia.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours/main")
@AllArgsConstructor
public class TourUserController {
    private final TourService tourService;
    private final TourMapper tourMapper;

    @GetMapping("/tour_city_purpose")
    public List<TourDto> findToursByCity(@RequestParam String cityName) {
        return  tourMapper.toListDto(tourService.findByCityPurpose(cityName));
    }

    @GetMapping("/tour_continent_purpose")
    public List<TourDto> findToursByContinent(@RequestParam String continentName) {
        return tourMapper.toListDto(tourService.findByContinentPurpose(continentName));
    }

    @GetMapping("/tour_country_purpose")
    public List<TourDto> findToursByCountry(@RequestParam String countryName) {
        return tourMapper.toListDto(tourService.findByCountryPurpose(countryName));
    }

    @GetMapping("/tour_city_departure")
    public List<TourDto> findByDepartureAirport(@RequestParam String cityName) {
        return null;
    }

    @GetMapping("/tour_continent_departure")
    public List<TourDto> findByPurposeAirport(@RequestParam String continentName) {
        return null;
    }

    @GetMapping("/tour_country_departure")
    public List<TourDto> findToursByDepartureCountry(@RequestParam String countryName){
        return null;
    }

    @GetMapping("/tour/promotion_active")
    public List<TourDto> findToursWithPromotions(){
        return null;
    }

    @GetMapping("/tour/form")
    public List<TourDto> findToursByParameters(@RequestBody TourSearchForm form){

        return tourMapper.toListDto(tourService.findByParameters(form));
    }


//    @GetMapping("/tour_departure_after")
//    public List<TourDto> findToursHavingDepartureDateAfter(@RequestParam String departureDate){
//        return tourMapper.toListDto(tourService.findByDepartureDateAfter(departureDate));
//    }
}

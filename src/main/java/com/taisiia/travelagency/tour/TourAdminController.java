package com.taisiia.travelagency.tour;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tours/admin")
public class TourAdminController {

  private final TourService tourService;

    private final TourMapper tourMapper;

    @PostMapping
    public  TourDto saveTour(@RequestBody @Valid TourForm tourForm) {
       return tourMapper.toDto(tourService.save(tourMapper.toDao(tourForm),
                tourForm.getFromAirportUuid(),tourForm.getDepartureAirportUuid(),
                tourForm.getHotelUuid(),tourForm.getPromotionsUuids(),tourForm.getStatus()));

    }
}

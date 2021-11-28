package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.TourDto;
import com.taisiia.travelagency.domain.form.TourForm;
import com.taisiia.travelagency.mapper.TourMapper;
import com.taisiia.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tours/admin")
@PreAuthorize("hasAuthority('user:write')")
public class TourAdminController {

    private final TourService tourService;
    private final TourMapper tourMapper;

    @PostMapping
    public TourDto saveTour(@RequestBody @Valid TourForm tourForm) {
        return tourMapper.toDto(tourService.save(tourMapper.toDao(tourForm),
                tourForm.getFromAirportUuid(), tourForm.getDepartureAirportUuid(),
                tourForm.getHotelUuid(), tourForm.getStatus()));

    }

    @GetMapping("/{id}")
    public TourDto findTourById(@PathVariable Long id) {
        return tourMapper.toDto(tourService.findById(id));
    }

    @GetMapping("/all")
    public List<TourDto> findAllTours() {
        return tourMapper.toListDto(tourService.findAll());
    }

    @PutMapping("/{id}")
    public TourDto updateTour(@RequestBody @Valid TourForm tourForm, @PathVariable Long id) {
        return tourMapper.toDto(tourService.update(tourMapper.toDao(tourForm),
                tourForm.getFromAirportUuid(), tourForm.getDepartureAirportUuid(),
                tourForm.getHotelUuid(), tourForm.getStatus(), id));
    }

    @DeleteMapping("/{id}")
    public void deleteTourById(@PathVariable Long id) {
        tourService.deleteTourById(id);
    }

    @PutMapping("/change_status/{id}/")
    public TourDto changeTourStatusById(@PathVariable Long id, @RequestParam String status) {

        return tourMapper.toDto(tourService.changeTourStatus(id, status));
    }

    @GetMapping
    public TourDto findTourByUuid(@PathVariable String uuid) {
        return tourMapper.toDto(tourService.findTourByUuid(uuid).get());
    }
}

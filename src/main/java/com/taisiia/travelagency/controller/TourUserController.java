package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.TourDto;
import com.taisiia.travelagency.domain.form.TourSearchForm;
import com.taisiia.travelagency.mapper.TourMapper;
import com.taisiia.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tours/main")
@AllArgsConstructor
public class TourUserController {
    private final TourService tourService;
    private final TourMapper tourMapper;

    @GetMapping("/tour/form")
    public List<TourDto> findToursByParameters(@RequestBody TourSearchForm form) {
        return tourMapper.toListDto(tourService.findByParameters(form));
    }


}

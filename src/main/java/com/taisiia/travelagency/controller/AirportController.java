package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.AirportDto;
import com.taisiia.travelagency.domain.form.AirportForm;
import com.taisiia.travelagency.mapper.AirportMapper;
import com.taisiia.travelagency.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
@PreAuthorize("hasAuthority('user:write')")
public class AirportController {
    private final AirportService airportService;
    private final AirportMapper airportMapper;

    @GetMapping("{id}")
    public AirportDto findAirportById(@PathVariable Long id) {
        return airportMapper.toDto(airportService.findById(id));
    }

    @PostMapping
    public AirportDto saveAirport(@RequestBody @Valid AirportForm airportForm) {
        return airportMapper.toDto(airportService.save(airportMapper.toDao(airportForm), airportForm.getAddressForm().getCity()));
    }

    @PutMapping("/{id}")
    public AirportDto updateAirport(@RequestBody @Valid AirportForm airportForm, @PathVariable Long id) {
        return airportMapper.toDto(airportService.update(airportMapper.
                toDao(airportForm), airportForm.getAddressForm().getCity(), id));
    }

    @DeleteMapping("/{id}")
    public void deleteAirportById(@PathVariable Long id) {
        airportService.deleteById(id);
    }

    @GetMapping
    public Page<AirportDto> getAirportPage(@RequestParam int page, @RequestParam int size) {
        return airportService.getPage(PageRequest.of(page, size)).map(airportMapper::toDto);
    }

    @GetMapping("/city")
    public List<AirportDto> findAirportsByCity(@RequestParam String cityName) {
        return airportMapper.toListDto(airportService.findByCityName(cityName));
    }
}

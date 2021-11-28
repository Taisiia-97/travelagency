package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.ContinentDto;
import com.taisiia.travelagency.mapper.ContinentMapper;
import com.taisiia.travelagency.service.ContinentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/continents")
@PreAuthorize("hasAuthority('user:write')")
public class ContinentController {
    private final ContinentService continentService;
    private final ContinentMapper continentMapper;

    @GetMapping("/{id}")
    public ContinentDto findContinentById(@PathVariable Long id) {
        return continentMapper.toDto(continentService.findById(id));
    }

    @GetMapping("/name")
    public ContinentDto findContinentByName(@RequestParam String continentName) {
        return continentMapper.toDto(continentService.findByName(continentName));
    }

    @PostMapping
    public ContinentDto saveContinent(@RequestBody @Valid ContinentDto continentDto) {
        return continentMapper.toDto(continentService.save(continentMapper.toDao(continentDto)));
    }

    @PutMapping("/{id}")
    public ContinentDto updateContinent(@RequestBody @Valid ContinentDto continentDto, @PathVariable Long id) {
        return continentMapper.toDto(continentService.update(continentMapper.toDao(continentDto), id));
    }

    @DeleteMapping("/{id}")
    public void deleteContinentById(@PathVariable Long id) {
        continentService.deleteById(id);
    }

    @GetMapping
    public Page<ContinentDto> getContinentPage(@RequestParam int page, @RequestParam int size) {
        return continentService.getPage(PageRequest.of(page, size)).map(continentMapper::toDto);
    }

}

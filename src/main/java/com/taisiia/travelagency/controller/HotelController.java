package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.HotelDto;
import com.taisiia.travelagency.domain.form.HotelForm;
import com.taisiia.travelagency.mapper.HotelMapper;
import com.taisiia.travelagency.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
@PreAuthorize("hasAuthority('user:write')")
public class HotelController {

    private final HotelService hotelService;

    private final HotelMapper hotelMapper;

    @PostMapping
    public HotelDto saveHotel(@RequestPart("form") @Valid HotelForm hotelForm,
                              @RequestPart("file") MultipartFile multipartFile) {
        return hotelMapper.toDto(hotelService.save(hotelMapper.toDao(hotelForm),
                hotelForm.getAddress().getCity(), hotelForm.getHotelType(), multipartFile));
    }

    @GetMapping("/{id}")
    public HotelDto findHotelById(@PathVariable Long id) {
        return hotelMapper.toDto(hotelService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable Long id) {
        hotelService.deleteById(id);
    }

    @PutMapping("/{id}")
    public HotelDto updateHotel(@RequestPart("from") @Valid HotelForm hotelForm,
                                @RequestPart("file") MultipartFile photo,
                                @PathVariable Long id) {

        return hotelMapper.toDto(hotelService.update(hotelMapper.toDao(hotelForm), hotelForm.getAddress().getCity(), hotelForm.getHotelType(), photo, id));
    }

    @GetMapping("/page")
    public Page<HotelDto> getPage(@RequestParam int page, @RequestParam int size) {

        return hotelService.getPage(PageRequest.of(page, size)).map(hotelMapper::toDto);
    }

    @GetMapping("/hotel_name")
    public List<HotelDto> findByHotelName(@RequestParam String hotelName) {
        return hotelMapper.toListDto(hotelService.findByHotelName(hotelName));

    }

    @GetMapping("/hotel_city")
    public List<HotelDto> findHotelByCityName(@RequestParam String cityName) {
        return hotelMapper.toListDto(hotelService.findByAddressCityCityName(cityName));
    }

    @GetMapping("/hotel_country")
    public List<HotelDto> findHotelByCountryName(@RequestParam String countryName) {
        return hotelMapper.toListDto(hotelService.findByAddressCityCountryCountryName(countryName));
    }


    @GetMapping("/hotel_description")
    public List<HotelDto> findByDescriptionContaining(@RequestParam String description) {
        return hotelMapper.toListDto(hotelService.findByDescriptionContaining(description));
    }

    @GetMapping("/hotel_standard")
    public List<HotelDto> findHotelByStandard(@RequestParam Integer standard) {
        return hotelMapper.toListDto(hotelService.findByStandard(standard));
    }

    @GetMapping("/hotel_type")
    public List<HotelDto> findHotelByHotelType(@RequestParam String hotelType) {
        return hotelMapper.toListDto(hotelService.findByHotelType(hotelType));
    }

    @GetMapping("/hotel_type_standard_city")
    public List<HotelDto> findByHotelTypeAndStandardAndAddressCityCityName(@RequestParam String hotelType,
                                                                           @RequestParam Integer standard,
                                                                           @RequestParam String city) {
        return hotelMapper.toListDto(hotelService.
                findByHotelTypeAndStandardAndAddressCityCityName(hotelType, standard, city));
    }

    @GetMapping("/hotel_country_standard")
    public List<HotelDto> findHotelByCountryNameAndStandard(@RequestParam String countryName,
                                                            @RequestParam Integer standard) {
        return hotelMapper.toListDto(hotelService.findByAddressCityCountryCountryNameAndStandard(countryName, standard));
    }
}


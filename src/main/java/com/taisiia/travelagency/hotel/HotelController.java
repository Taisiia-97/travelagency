package com.taisiia.travelagency.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    private final HotelMapper hotelMapper;

    @PostMapping
    public HotelDto saveHotel(@RequestPart("form") @Valid HotelForm hotelForm, @RequestPart("file") MultipartFile multipartFile) {

        return hotelMapper.toDto(hotelService.save(hotelMapper.toDao(hotelForm),
                hotelForm.getAddress().getCity(), hotelForm.getHotelType(), multipartFile));
    }

    @GetMapping("{id}")
    public HotelDto findHotelById(@PathVariable Long id){
        return hotelMapper.toDto(hotelService.findById(id));
    }
    @DeleteMapping("{id}")
    public void deleteHotelById(@PathVariable Long id){
        hotelService.deleteById(id);
    }
}


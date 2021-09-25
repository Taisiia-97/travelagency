package com.taisiia.travelagency.tour;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tours/main")
/**
 *
 prezentacja promowanych wycieczek
 prezentacja zbliżających się wycieczek (globalnie)
 prezentacja zbliżających się wycieczek (z podziałem na kontynenty)
 prezentacja zbliżających się wycieczek (z podziałem na kraje)
 prezentacja ostatnio zakupionych wycieczek
 (opcjonalnie) prezentacja wycieczek z obniżoną ceną (trzeba dorobić mechanizm)
 (opcjonalnie) prezentacja wycieczek, w których zostało tylko 1 lub 2 miejsca

 */
public class TourUserController {


//    @GetMapping("/promotions")
//    public List<TourDto> findPromotionTours(){
//        return null;
//    }
//
//    @GetMapping("/future_data")
//    public List<TourDto> findByFutureDate(){
//        return null;
//    }
//
//    @GetMapping("/future_city_sorted")
//    public List<TourDto> findByCity(){
//        return null;
//    }
//
//    @GetMapping("/last_minutes")
//    public List<TourDto> findByPlaces(){
//        return null;
//    }
}

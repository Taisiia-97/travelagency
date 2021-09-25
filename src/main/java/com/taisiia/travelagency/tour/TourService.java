package com.taisiia.travelagency.tour;

import com.taisiia.travelagency.airport.Airport;
import com.taisiia.travelagency.airport.AirportService;
import com.taisiia.travelagency.hotel.Hotel;
import com.taisiia.travelagency.hotel.HotelService;
import com.taisiia.travelagency.promotion.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final HotelService hotelService;
    private final AirportService airportService;
    private final PromotionService promotionService;


    public Tour save(Tour tour, String fromAirportUuid, String departureAirportUuid,
                     String hotelUuid, Set<String> promotionsUuids, String status) {
        tour.setStatus(TourStatus.safeValueOf(status));
        tour.setPromotions(promotionService.findPromotionsByUuids(promotionsUuids));
        Optional<Airport> fromAirport = airportService.findByUuid(fromAirportUuid);
        Optional<Airport> departureAirport = airportService.findByUuid(departureAirportUuid);
        Optional<Hotel> hotel = hotelService.findHotelByUuid(hotelUuid);

        if (fromAirport.isPresent() && departureAirport.isPresent() && hotel.isPresent()) {
            tour.setFromAirPort(fromAirport.get());
            tour.setPurposeAirport(departureAirport.get());
            tour.setHotel(hotel.get());
        }
        return tourRepository.save(tour);
    }
}

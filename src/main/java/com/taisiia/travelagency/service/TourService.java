package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.Airport;
import com.taisiia.travelagency.domain.dao.Discount;
import com.taisiia.travelagency.domain.dao.Hotel;
import com.taisiia.travelagency.domain.dao.Tour;
import com.taisiia.travelagency.domain.form.TourSearchForm;
import com.taisiia.travelagency.enums.DiscountStatus;
import com.taisiia.travelagency.enums.TourStatus;
import com.taisiia.travelagency.exception.OrderException;
import com.taisiia.travelagency.exception.TourException;
import com.taisiia.travelagency.repository.TourRepository;
import com.taisiia.travelagency.repository.specification.TourSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final HotelService hotelService;
    private final AirportService airportService;
    private final DiscountService discountService;
    private final TourSpecification tourSpecification;

    public Tour save(Tour tour, String fromAirportUuid, String departureAirportUuid,
                     String hotelUuid, String status) {
        tour.setStatus(TourStatus.safeValueOf(status));
        Optional<Airport> fromAirport = airportService.findByUuid(fromAirportUuid);
        Optional<Airport> departureAirport = airportService.findByUuid(departureAirportUuid);
        Optional<Hotel> hotel = hotelService.findHotelByUuid(hotelUuid);
        if (!(fromAirport.isPresent() && departureAirport.isPresent() && hotel.isPresent())) {
            throw new TourException("Data is not correct");
        }
        tour.setFromAirPort(fromAirport.get());
        tour.setPurposeAirport(departureAirport.get());
        tour.setHotel(hotel.get());
        tour.setAdultDiscountPrice(tour.getAdultRegularPrice());
        if (tour.getAdultDiscountPercent() > 0) {
            tour.setAdultDiscountPrice(calculateDiscountPrice(tour.getAdultRegularPrice(), tour.getAdultDiscountPercent()));
            tour.setActiveDiscount(true);
        }
        return tourRepository.save(tour);
    }

    private Double calculateDiscountPrice(Double adultRegularPrice, Double adultDiscountPercent) {
        return BigDecimal.valueOf(adultRegularPrice).subtract(BigDecimal.valueOf(adultRegularPrice).multiply(BigDecimal.valueOf(adultDiscountPercent)).divide(BigDecimal.valueOf(100))).doubleValue();

    }

    public Optional<Tour> findTourByUuid(String uuid) {
        return tourRepository.findByUuid(uuid);
    }

    @Transactional
    public Tour update(Tour tour, String fromAirportUuid, String departureAirportUuid,
                       String hotelUuid, String status, Long id) {
        Tour tourToUpdate = findById(id);
        tourToUpdate.setStatus(TourStatus.safeValueOf(status));
        Optional<Airport> fromAirport = airportService.findByUuid(fromAirportUuid);
        Optional<Airport> departureAirport = airportService.findByUuid(departureAirportUuid);
        Optional<Hotel> hotel = hotelService.findHotelByUuid(hotelUuid);
        if (!(fromAirport.isPresent() && departureAirport.isPresent() && hotel.isPresent())) {
            throw new TourException("Data is not correct");
        }
        tourToUpdate.setAdultRegularPrice(tour.getAdultRegularPrice());
        tourToUpdate.setAdultDiscountPrice(tour.getAdultRegularPrice());
        tourToUpdate.setAdultDiscountPercent(tour.getAdultDiscountPercent());
        tourToUpdate.setChildPrice(tour.getChildPrice());
        if (tourToUpdate.getAdultDiscountPercent() > 0) {
            tourToUpdate.setAdultDiscountPrice(calculateDiscountPrice(tour.getAdultRegularPrice(), tour.getAdultDiscountPercent()));
            tourToUpdate.setActiveDiscount(true);
        }
        tourToUpdate.setFromAirPort(fromAirport.get());
        tourToUpdate.setPurposeAirport(departureAirport.get());
        tourToUpdate.setHotel(hotel.get());
        tourToUpdate.setPlacesAdults(tour.getPlacesAdults());
        tourToUpdate.setPlacesChild(tour.getPlacesChild());
        return tourToUpdate;
    }

    @Transactional
    public Tour changeTourStatus(Long id, String status) {
        Tour tour = findById(id);
        TourStatus tourStatus = TourStatus.safeValueOf(status);
        tour.setStatus(tourStatus);
        return tour;
    }

    public Tour findById(Long id) {
        return tourRepository.getById(id);
    }

    public void deleteTourById(Long id) {
        tourRepository.deleteById(id);
    }

    public List<Tour> findAll() {
        return tourRepository.findAll();
    }


    public List<Tour> findByCityFrom(String cityName) {
        return tourRepository.findAllByFromAirPortAddressCityCityNameAndStatusIs(cityName, TourStatus.ACTIVE);
    }

    public List<Tour> findAllByDepartureDate(LocalDateTime localDateTime) {
        return tourRepository.findAllByDepartureDateBefore(localDateTime);
    }

    public void dayUpdate(Tour tour) {
        tourRepository.saveAndFlush(tour);

    }

    @Transactional
    public Tour updatePlaces(Tour tour, Integer adultsPlaces, Integer childPlaces) {
        tour.setPlacesAdults(tour.getPlacesAdults() - adultsPlaces);
        tour.setPlacesChild(tour.getPlacesChild() - childPlaces);
        return tour;

    }

    public BigDecimal calculateTotalTourPrice(Tour tour, Integer adultsPlaces, Integer childPlaces) {

        if (tour.getPlacesAdults() >= adultsPlaces && tour.getPlacesChild() >= childPlaces) {
            updatePlaces(tour, adultsPlaces, childPlaces);
            return BigDecimal.valueOf(tour.getAdultRegularPrice()).multiply(BigDecimal.valueOf(adultsPlaces)).add(BigDecimal.valueOf(childPlaces).multiply(BigDecimal.valueOf(tour.getPlacesChild())));
        } else {
            throw new OrderException("This tour doesn't have enough places");
        }

    }

    public BigDecimal calculateTotalDiscount(Tour tour, String discountCode, BigDecimal tourTotalPrice) {
        if (tour.isActiveDiscount()) {
            throw new OrderException("Price of this tour has already discount");
        }
        Discount discount = discountService.findPromotionByCode(discountCode).orElseThrow(() -> new OrderException("Incorrect discount code"));
        if (discount.getStatus() != DiscountStatus.ACTIVE) {
            throw new OrderException("This promotion code is not active or this discount code doesn't active for this tour");
        }
        return BigDecimal.valueOf(discount.getPercent()).multiply(tourTotalPrice).divide(BigDecimal.valueOf(100));

    }


    public BigDecimal calculateDiscount(Tour tour, Integer adultsPlaces, BigDecimal totalTourPrice) {
        return totalTourPrice.subtract(BigDecimal.valueOf(tour.getAdultDiscountPrice()).multiply(BigDecimal.valueOf(adultsPlaces)));
    }


    public List<Tour> findByParameters(TourSearchForm request) {
        return tourRepository.findAll(tourSpecification.createSpecification(request));
    }
}

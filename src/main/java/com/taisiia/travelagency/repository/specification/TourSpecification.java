package com.taisiia.travelagency.repository.specification;

import com.taisiia.travelagency.domain.dao.Tour;
import com.taisiia.travelagency.domain.form.TourSearchForm;
import com.taisiia.travelagency.enums.HotelType;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class TourSpecification {

    public Specification<Tour> createSpecification(TourSearchForm request) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getHotelStandard() != null && !request.getHotelStandard().isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("hotel").get("standard"), request.getHotelStandard()));
            }
            if (request.getDepartureCity() != null && !request.getDepartureCity().isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("fromAirPort").get("address").get("city").get("cityName"), request.getDepartureCity().trim()));
            }
            if (request.getHotelPurposeCity() != null && !request.getHotelPurposeCity().isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("purposeAirport").get("address").get("city").get("cityName"), request.getHotelPurposeCity().trim()));
            }

            if (request.getHotelType() != null && !request.getHotelType().isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("hotel").get("hotelType"), HotelType.safeValueOf(request.getHotelType()).toString()));
            }
            if (request.getMaxPrice() != null && request.getMinPrice() != null) {
                predicates.add(criteriaBuilder.between(root.get("adultRegularPrice"), request.getMaxPrice(), request.getMinPrice()));
            }
            if (request.getMaxPrice() == null || request.getMinPrice() == null) {
                if (request.getMinPrice() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("adultRegularPrice"), request.getMinPrice()));
                }
                if (request.getMaxPrice() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("adultRegularPrice"), request.getMaxPrice()));
                }
            }

            if (request.getDepartureDate() != null && request.getReturnDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("departureDate"), request.getDepartureDate(), request.getReturnDate()));
            }
            if (request.getDepartureDate() == null || request.getReturnDate() == null) {
                if (request.getDepartureDate() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("departureDate"), request.getDepartureDate()));
                }
                if (request.getReturnDate() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("returnDate"), request.getReturnDate()));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });


    }

}

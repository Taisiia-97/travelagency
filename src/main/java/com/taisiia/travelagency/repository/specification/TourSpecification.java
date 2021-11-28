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

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });


    }

}

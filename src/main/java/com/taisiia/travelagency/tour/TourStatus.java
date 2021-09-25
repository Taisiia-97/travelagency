package com.taisiia.travelagency.tour;

import com.taisiia.travelagency.hotel.HotelType;

import java.util.Arrays;
import java.util.Optional;

public  enum  TourStatus {
    ACTIVE,
    ARCHIVAL,
    WAITING,
    COVER;



    public static TourStatus safeValueOf(String status) {
        try {
            return valueOf(status.toUpperCase());
        } catch (Exception e) {
            return COVER;
        }
    }

    public static boolean containsValue(String value) {

        Optional<String> foundedType = Arrays.stream(TourStatus.values()).
                map(TourStatus::toString).
                filter(tourStatus -> tourStatus.equals(value.toUpperCase())).
                findFirst();

        return foundedType.isPresent();
    }

}

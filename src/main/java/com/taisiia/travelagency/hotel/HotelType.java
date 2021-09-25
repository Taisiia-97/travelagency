package com.taisiia.travelagency.hotel;

import java.util.Arrays;
import java.util.Optional;

public enum HotelType {

    BB("bed & breakfast"),

    HB("half board"),

    FB("full board"),

    AI("all inclusive"),

    OV("overnight"),

    SC("self catering"),

    COVER("undefined");

    private final String description;

    HotelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static HotelType safeValueOf(String type) {
        try {
            return valueOf(type.toUpperCase());
        } catch (Exception e) {
            return COVER;
        }
    }

    public static boolean containsValue(String value) {

        Optional<String> foundedType = Arrays.stream(HotelType.values()).
                map(HotelType::toString).
                filter(hotelType -> hotelType.equals(value.toUpperCase())).
                findFirst();

        return foundedType.isPresent();
    }
}

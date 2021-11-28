package com.taisiia.travelagency.enums;

import com.taisiia.travelagency.exception.HotelException;

import java.util.Map;

public enum HotelType {

    BB("bed & breakfast"),

    HB("half board"),

    FB("full board"),

    AI("all inclusive"),

    OV("overnight"),

    SC("self catering");

    private final String description;
    private static final Map<String, HotelType> TYPES = Map.ofEntries(Map.entry("bb", BB),
            Map.entry("hb", HB), Map.entry("fb", FB), Map.entry("ai", AI), Map.entry("ov", OV), Map.entry("sc", SC));

    HotelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static HotelType safeValueOf(String type) {
        HotelType hotelType = TYPES.get(type.toLowerCase());
        if (hotelType == null) {
            throw new HotelException("This hotel's type is not correct");
        }
        return hotelType;
    }

    public static boolean containsValue(String value) {
        return TYPES.containsKey(value.toLowerCase());
    }
}

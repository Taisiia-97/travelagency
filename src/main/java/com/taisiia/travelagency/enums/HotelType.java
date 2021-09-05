package com.taisiia.travelagency.enums;

public enum HotelType {

    BB("bed & breakfast"),

    HB("half board"),

    FB("full board"),

    AI("all inclusive"),

    OV("overnight"),

    SC("self catering");

    private final String description;

    HotelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

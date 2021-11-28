package com.taisiia.travelagency.enums;

import com.taisiia.travelagency.exception.TourStatusException;

import java.util.Map;

public enum TourStatus {
    ACTIVE,
    ARCHIVAL,
    WAITING,
    COVER;
    private static final Map<String, TourStatus> STATUSES = Map.ofEntries(Map.entry("active", ACTIVE),
            Map.entry("archival", ARCHIVAL), Map.entry("waiting", WAITING));

    public static TourStatus safeValueOf(String status) {
        TourStatus tourStatus = STATUSES.get(status.toLowerCase());
        if (tourStatus == null) {
            throw new TourStatusException("This status is not correct");
        }
        return tourStatus;

    }

    public static boolean containsValue(String value) {
        return STATUSES.containsKey(value);
    }

}

package com.taisiia.travelagency.promotion;

import java.util.Arrays;
import java.util.Optional;

public enum PromotionStatus {
    ACTIVE,
    ARCHIVAL,
    COVER;

    public static PromotionStatus safeValueOf(String type) {
        try {
            return valueOf(type.toUpperCase());
        } catch (Exception e) {
            return COVER;
        }
    }

    public static boolean containsValue(String status) {

        Optional<String> foundedStatus = Arrays.stream(PromotionStatus.values()).
                map(PromotionStatus::toString).
                filter(promotionStatus -> promotionStatus.equals(status.toUpperCase())).
                findFirst();

        return foundedStatus.isPresent();
    }

    public static Optional<PromotionStatus> valueOfOrEmpty(String status){
        try {
            return Optional.of(valueOf(status.toUpperCase()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

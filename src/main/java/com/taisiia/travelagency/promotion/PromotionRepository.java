package com.taisiia.travelagency.promotion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    List<Promotion> findAllByStatus(PromotionStatus promotionStatus);

    Promotion findPromotionByPromotionName(String promotionName);

    Optional<Promotion> findByUuid(String uuid);

    Set<Promotion> findPromotionByUuidIn(Set<String> promotions);
}

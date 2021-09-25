package com.taisiia.travelagency.promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public Promotion findById(Long id) {
        return promotionRepository.getById(id);
    }


    public Promotion save(Promotion promotion, String status) {
        promotion.setStatus(PromotionStatus.safeValueOf(status));
        return promotionRepository.save(promotion);
    }

    @Transactional
    public Promotion update(Promotion promotion, String status, Long id) {
        Promotion toUpdate = findById(id);
        toUpdate.setPromotionName(promotion.getPromotionName());
        toUpdate.setPercent(promotion.getPercent());
        toUpdate.setStatus(PromotionStatus.safeValueOf(status));
        return toUpdate;
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id).setStatus(PromotionStatus.ARCHIVAL);
    }

    @Transactional
    public Promotion modifyPromotionStatus(Long id, String status) {
        Promotion promotion = findById(id);
        if (PromotionStatus.containsValue(status)) {
            promotion.setStatus(PromotionStatus.safeValueOf(status));
        }
        return promotion;
    }

    public Promotion findByName(String promotionName) {
        return promotionRepository.findPromotionByPromotionName(promotionName);
    }


    public Page<Promotion> getPage(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }


    public List<Promotion> findByPromotionStatus(String promotionStatus) {
        Optional<PromotionStatus> foundedStatus = PromotionStatus.valueOfOrEmpty(promotionStatus);
        if (foundedStatus.isPresent()) {
            return promotionRepository.findAllByStatus(foundedStatus.get());
        }

        return new ArrayList<>();
    }

    public List<Promotion> getActivePromotions() {
        return promotionRepository.findAllByStatus(PromotionStatus.ACTIVE);
    }

    public Optional<Promotion> findByUuid(String uuid) {
        return promotionRepository.findByUuid(uuid);
    }

    public Set<Promotion> findPromotionsByUuids(Set<String> promotions) {
        return promotionRepository.findPromotionByUuidIn(promotions);
    }
}

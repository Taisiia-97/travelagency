package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.Discount;
import com.taisiia.travelagency.enums.DiscountStatus;
import com.taisiia.travelagency.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository promotionRepository;

    public Discount findById(Long id) {
        return promotionRepository.getById(id);
    }


    public Discount save(Discount promotion, String status) {
        if (promotion.getCode() == null || promotion.getCode().isBlank()) {
            promotion.setCode((UUID.randomUUID().toString()));
        }
        promotion.setStatus(DiscountStatus.safeValueOf(status));
        return promotionRepository.save(promotion);
    }

    @Transactional
    public Discount update(Discount promotion, String status, Long id) {
        Discount toUpdate = findById(id);
        toUpdate.setName(promotion.getName());
        toUpdate.setCode(promotion.getCode());
        toUpdate.setPercent(promotion.getPercent());
        toUpdate.setStatus(DiscountStatus.safeValueOf(status));
        return toUpdate;
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id).setStatus(DiscountStatus.ARCHIVAL);
    }

    @Transactional
    public Discount modifyPromotionStatus(Long id, String status) {
        Discount promotion = findById(id);
        if (DiscountStatus.containsValue(status)) {
            promotion.setStatus(DiscountStatus.safeValueOf(status));
        }
        return promotion;
    }

    @Transactional
    public Discount deactivateDiscountById(Long id) {
        Discount discount = findById(id);
        discount.setStatus(DiscountStatus.ARCHIVAL);
        return discount;

    }

    public Discount findByName(String promotionName) {
        return promotionRepository.findDiscountByName(promotionName);
    }


    public Page<Discount> getPage(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }


    public List<Discount> findByPromotionStatus(String status) {
        Optional<DiscountStatus> foundedStatus = DiscountStatus.valueOfOrEmpty(status);
        if (foundedStatus.isPresent()) {
            return promotionRepository.findAllByStatus(foundedStatus.get());
        }

        return new ArrayList<>();
    }

    public List<Discount> getActivePromotions() {
        return promotionRepository.findAllByStatus(DiscountStatus.ACTIVE);
    }


    public Optional<Discount> findPromotionByCode(String code) {
        return promotionRepository.findDiscountByCode(code);
    }
}

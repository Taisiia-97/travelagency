package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.DiscountDto;
import com.taisiia.travelagency.domain.form.DiscountForm;
import com.taisiia.travelagency.mapper.DiscountMapper;
import com.taisiia.travelagency.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discounts")
@PreAuthorize("hasAuthority('user:write')")
public class DiscountController {
    private final DiscountService discountService;
    private final DiscountMapper discountMapper;

    @GetMapping("/{id}")
    public DiscountDto findPromotionById(@PathVariable Long id) {
        return discountMapper.toDto(discountService.findById(id));
    }

    @GetMapping("/name")
    public DiscountDto findPromotionByName(@RequestParam String promotionName) {
        return discountMapper.toDto(discountService.findByName(promotionName));
    }

    @PostMapping
    public DiscountDto savePromotion(@RequestBody @Valid DiscountForm promotionForm) {
        return discountMapper.toDto(discountService.save(discountMapper.toDao(promotionForm), promotionForm.getStatus()));
    }

    @PutMapping("/{id}")
    public DiscountDto updatePromotion(@RequestBody @Valid DiscountForm promotionForm, @PathVariable Long id) {
        return discountMapper.toDto(discountService.update(discountMapper.toDao(promotionForm), promotionForm.getStatus(), id));
    }

    @DeleteMapping("/{id}")
    public void deletePromotionById(@PathVariable Long id) {
        discountService.deleteById(id);
    }

    @GetMapping
    public Page<DiscountDto> getPromotionPage(@RequestParam int page, @RequestParam int size) {
        return discountService.getPage(PageRequest.of(page, size)).map(discountMapper::toDto);
    }

    @GetMapping("/discount/{status}")
    public List<DiscountDto> getPromotionsByStatus(@PathVariable String status) {
        return discountMapper.toListDto(discountService.findByPromotionStatus(status));
    }

    @PutMapping("/status_modifying/{id}")
    public DiscountDto modifyPromotionStatus(@PathVariable Long id, @RequestParam String promotionStatus) {
        return discountMapper.toDto(discountService.modifyPromotionStatus(id, promotionStatus));
    }

    @GetMapping("status_active")
    public List<DiscountDto> findActivePromotions() {
        return discountMapper.toListDto(discountService.getActivePromotions());
    }
}

package com.taisiia.travelagency.promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promotions")
public class PromotionController {
    private final PromotionService promotionService;
    private final PromotionMapper promotionMapper;

    @GetMapping("/{id}")
    public PromotionDto findPromotionById(@PathVariable Long id) {
        return promotionMapper.toDto(promotionService.findById(id));
    }

    @GetMapping("/name")
    public PromotionDto findPromotionByName(@RequestParam String promotionName) {
        return promotionMapper.toDto(promotionService.findByName(promotionName));
    }

    @PostMapping
    public PromotionDto savePromotion(@RequestBody @Valid PromotionForm promotionForm) {
        return promotionMapper.toDto(promotionService.save(promotionMapper.toDao(promotionForm), promotionForm.getStatus()));
    }

    @PutMapping("/{id}")
    public PromotionDto updatePromotion(@RequestBody @Valid PromotionForm promotionForm, @PathVariable Long id) {
        return promotionMapper.toDto(promotionService.update(promotionMapper.toDao(promotionForm), promotionForm.getStatus(), id));
    }

    @DeleteMapping("/{id}")
    public void deletePromotionById(@PathVariable Long id) {
        promotionService.deleteById(id);
    }

    @GetMapping
    public Page<PromotionDto> getPromotionPage(@RequestParam int page, @RequestParam int size) {
        return promotionService.getPage(PageRequest.of(page, size)).map(promotionMapper::toDto);
    }

    @GetMapping("/promotion/{status}")
    public List<PromotionDto> getPromotionsByStatus(@PathVariable String status) {
        return promotionMapper.toListDto(promotionService.findByPromotionStatus(status));
    }

    @PutMapping("/status_modifying/{id}")
    public PromotionDto modifyPromotionStatus(@PathVariable Long id, @RequestParam String promotionStatus){
        return promotionMapper.toDto(promotionService.modifyPromotionStatus(id,promotionStatus));
    }

    @GetMapping("status_active")
    public List<PromotionDto> findActivePromotions(){
        return promotionMapper.toListDto(promotionService.getActivePromotions());
    }
}

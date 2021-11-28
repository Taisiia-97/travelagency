package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.Discount;
import com.taisiia.travelagency.enums.DiscountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long>, RevisionRepository<Discount, Long, Integer> {

    List<Discount> findAllByStatus(DiscountStatus promotionStatus);

    Discount findDiscountByName(String promotionName);

    Optional<Discount> findDiscountByCode(String code);

}

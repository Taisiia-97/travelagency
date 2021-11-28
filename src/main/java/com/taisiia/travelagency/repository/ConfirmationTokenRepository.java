package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
   Optional <ConfirmationToken> findByConfirmationToken(String confirmationToken);
   ConfirmationToken findByUserId(Long id);


}

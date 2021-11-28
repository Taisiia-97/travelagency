package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {

    Optional<PasswordToken> findByPasswordToken(String token);

    List<PasswordToken> findAllByExpirationDateBefore(LocalDateTime localDateTime);
}

package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.PasswordToken;
import com.taisiia.travelagency.exception.TokenException;
import com.taisiia.travelagency.exception.TourException;
import com.taisiia.travelagency.repository.PasswordTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordTokenService {
    private final UserService userService;
    private final PasswordTokenRepository passwordTokenRepository;

    public void confirmToken(String confirmationToken, String password, String confirmPassword) {
        PasswordToken passwordToken = passwordTokenRepository.findByPasswordToken(confirmationToken)
                .orElseThrow(() -> new TokenException("This token doesn't exists"));
        if (!passwordToken.isActive()) {
            throw new TourException("This token is not active, you can try to generate new token");
        }
        userService.changePassword(passwordToken.getUser().getId(), password, confirmPassword);
    }


    public List<PasswordToken> findTokensByExpirationDateBefore(LocalDateTime localDateTime) {
        return passwordTokenRepository.findAllByExpirationDateBefore(localDateTime);
    }

    @Transactional
    public void updateTokenStatusById(boolean status, Long id) {
        passwordTokenRepository.getById(id).setActive(status);

    }
}

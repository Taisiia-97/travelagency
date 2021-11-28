package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.ConfirmationToken;
import com.taisiia.travelagency.repository.ConfirmationTokenRepository;
import com.taisiia.travelagency.domain.dao.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final UserService userService;
    private final ConfirmationTokenRepository confirmationTokenRepository;


    public String confirmToken(String confirmationToken) {

        Optional<ConfirmationToken> token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token.isPresent()) {
            userService.activateAccount(token.get().getUser().getEmail());

            return "Your account is active";

        }
        return "Account is not active";
    }

    public String findUserToken(User user) {
        return confirmationTokenRepository.findByUserId(user.getId()).getConfirmationToken();
    }

    public ConfirmationToken findTokenByUser(User user) {
        return confirmationTokenRepository.findByUserId(user.getId());
    }

    public void deleteTokenById(Long id) {
        confirmationTokenRepository.deleteById(id);
    }
}

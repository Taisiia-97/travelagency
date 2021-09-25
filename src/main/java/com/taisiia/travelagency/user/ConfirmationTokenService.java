package com.taisiia.travelagency.user;

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
}

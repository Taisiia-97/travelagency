package com.taisiia.travelagency.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public User saveUser(User user) {
        user.setRole(Role.USER);
        user.setActive(false);
        User savedUser = userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(savedUser);
        confirmationTokenRepository.save(confirmationToken);
        emailSenderService.sendConfirmationEmail(savedUser.getEmail(), confirmationToken.getConfirmationToken());


        return savedUser;
    }

    public List<User> loadUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void activateAccount(String email) {
       findUserByEmail(email).setActive(true);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
}

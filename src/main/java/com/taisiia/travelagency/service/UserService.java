package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.ConfirmationToken;
import com.taisiia.travelagency.domain.dao.PasswordToken;
import com.taisiia.travelagency.domain.dao.User;
import com.taisiia.travelagency.domain.dto.UserDto;
import com.taisiia.travelagency.enums.Role;
import com.taisiia.travelagency.exception.UserException;
import com.taisiia.travelagency.mailservice.EmailSenderService;
import com.taisiia.travelagency.repository.ConfirmationTokenRepository;
import com.taisiia.travelagency.repository.PasswordTokenRepository;
import com.taisiia.travelagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordTokenRepository passwordTokenRepository;

    public User saveUser(User user) {
        user.setRole(Role.USER);
        user.setIsActive(Boolean.FALSE);
        user.setPassword(encodePassword(user.getPassword()));
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
        userRepository.getByEmailIgnoreCase(email).setIsActive(Boolean.TRUE);
        emailSenderService.sendWelcomeMail(findUserByEmailRegistration(email));

    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public User findUserByEmailRegistration(String email) {
        return userRepository.getByEmailIgnoreCase(email);
    }

    public List<User> findByAccountFalseStatus(Boolean status) {
        return userRepository.findAllByIsActive(status);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Transactional
    public User changeRoleById(String role, Long id) {
        User toUpdate = findById(id);
        toUpdate.setRole(Role.safeValueOf(role));
        return toUpdate;
    }

    public void remindPassword(String email) {
        findUserByEmail(email).orElseThrow(() -> new UserException("This user does not exists"));
        PasswordToken passwordToken = new PasswordToken(findUserByEmailRegistration(email));
        passwordTokenRepository.save(passwordToken);
        emailSenderService.sendResetPasswordEmail(email, passwordToken.getPasswordToken());
    }

    @Transactional
    public void changePassword(Long id, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UserException("Passwords are not same");
        }
        findById(id).setPassword(encodePassword(password));
        log.warn("Password for user was changed");
    }

    @Transactional
    public User updateUser(UserDto userDto, Long id) {
        User toUpdate = findById(id);
        toUpdate.setFirstName(userDto.getFirstName());
        toUpdate.setLastName(userDto.getLastName());
        toUpdate.setEmail(userDto.getEmail());
        return toUpdate;
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder(12).encode(password);
    }
}

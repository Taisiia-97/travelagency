package com.taisiia.travelagency.tasks;

import com.taisiia.travelagency.domain.dao.Discount;
import com.taisiia.travelagency.enums.TourStatus;
import com.taisiia.travelagency.mailservice.EmailSenderService;
import com.taisiia.travelagency.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DayUpdate {
    private final TourService tourService;
    private final UserService userService;
    private final DiscountService discountService;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordTokenService passwordTokenService;
    private final EmailSenderService emailSenderService;

    @Scheduled(cron = "0 0 10 1/1 * ?")
    public void dayUpdate() {
        tourService.findAllByDepartureDate(LocalDateTime.now())
                .forEach(element -> {
                    element.setStatus(TourStatus.ARCHIVAL);
                    tourService.dayUpdate(element);
                });

        log.info("Day update is done");
    }


    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void deleteNotActiveUsers() {
        userService.findByAccountFalseStatus(Boolean.FALSE)
                .forEach(user -> {
                    LocalDateTime createdDate = confirmationTokenService.findTokenByUser(user).getCreatedDate();
                    System.out.println(createdDate);
                    if ((LocalDateTime.now().getDayOfMonth() - createdDate.getDayOfMonth()) >= 1) {
                        confirmationTokenService.deleteTokenById(confirmationTokenService.findTokenByUser(user).getId());
                        userService.deleteById(user.getId());
                    }
                });
        log.info("User's update was done");
    }


    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void verifyTokenStatus() {
        passwordTokenService.findTokensByExpirationDateBefore(LocalDateTime.now())
                .forEach(passwordToken -> passwordTokenService.updateTokenStatusById(Boolean.FALSE, passwordToken.getId()));
        log.info("Token's expiration were updated at " + LocalDateTime.now());
    }

    @Scheduled(cron = "0 0 10 1/1 * ?")
    public void updateDiscountsStatus() {
        discountService.getActivePromotions().stream().filter(discount -> discount.getEndDate().isBefore(LocalDateTime.now()))
                .forEach(discount -> {
                    discountService.deactivateDiscountById(discount.getId());
                });
        log.info("Discount statuses were updated");
    }


    @Scheduled(cron = "0 0 10 1/1 * ?")
    public void createBirthDayDiscountForUser() {
        userService.loadUsers().stream().filter(user -> user.getBirthDay().getDayOfMonth() == LocalDate.now().getDayOfMonth()
                        && user.getBirthDay().getMonth().equals(LocalDate.now().getMonth()))
                .forEach(user -> {
                    Discount discount = discountService.save(Discount.builder()
                            .code(user.getEmail() + UUID.randomUUID() + LocalDate.now())
                            .endDate(LocalDateTime.now().plusWeeks(1))
                            .name("BirthdayCode" + user.getEmail() + LocalDate.now())
                            .percent(20.0)
                            .build(), "active");
                    emailSenderService.sendBirthDayDiscount(user, discount.getCode());
                });
        log.info("Discount bons were sent to users");
    }

}

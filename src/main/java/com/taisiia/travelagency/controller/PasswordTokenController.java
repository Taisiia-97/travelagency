package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.PasswordResetDto;
import com.taisiia.travelagency.service.PasswordTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PasswordTokenController {

    private final PasswordTokenService passwordTokenService;

    @RequestMapping(value = "/reset_password", method = {RequestMethod.GET, RequestMethod.POST})
    public void resetPassword(@RequestParam("token") String confirmationToken,
                              @RequestBody @Valid PasswordResetDto passwordResetDto) {
        passwordTokenService.confirmToken(confirmationToken, passwordResetDto.getPassword(), passwordResetDto.getConfirmPassword());
    }
}

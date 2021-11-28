package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.UserDto;
import com.taisiia.travelagency.mapper.UserMapper;
import com.taisiia.travelagency.service.UserService;
import com.taisiia.travelagency.validator.group.Create;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping
    @Validated(Create.class)
    public UserDto saveUser(@RequestBody @Valid UserDto userDto) {
        return userMapper.toDto(userService.saveUser(userMapper.toDao(userDto)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:order')")
    public UserDto updateUser(@RequestBody @Valid UserDto userDto, @PathVariable Long id) {
        return userMapper.toDto(userService.updateUser(userDto, id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:write')")
    public List<UserDto> loadUsers() {
        return userMapper.toListDto(userService.loadUsers());
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public UserDto changeUserStatusById(@RequestParam String status, @PathVariable Long id) {
        return userMapper.toDto(userService.changeRoleById(status, id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public UserDto findUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/remind_password")
    public void remindPassword(@RequestParam String email) {
        userService.remindPassword(email);
    }
}

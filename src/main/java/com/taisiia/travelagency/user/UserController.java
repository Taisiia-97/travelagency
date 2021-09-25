package com.taisiia.travelagency.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.saveUser(userMapper.toDao(userDto)));
    }

    @GetMapping
    public List<UserDto> loadUsers() {
        return userService.loadUsers().stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}

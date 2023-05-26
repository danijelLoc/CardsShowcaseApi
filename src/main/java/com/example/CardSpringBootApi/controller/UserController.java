package com.example.CardSpringBootApi.controller;

import com.example.CardSpringBootApi.dto.UserDto;
import com.example.CardSpringBootApi.mapper.IUserMapper;
import com.example.CardSpringBootApi.model.User;
import com.example.CardSpringBootApi.service.ICardGenerationService;
import com.example.CardSpringBootApi.service.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserMapper userMapper;
    private final IUserService userService;
    private final ICardGenerationService cardGenerationService;

    public UserController(IUserMapper userMapper, IUserService userService, ICardGenerationService cardGenerationService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.cardGenerationService = cardGenerationService;
    }

    @GetMapping("/{oib}")
    public UserDto getUser(@PathVariable long oib) {
        return userMapper.userToUserDto(userService.getUserByOib(oib));
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto user) {
        User userModel = userMapper.userDtoToUser(user);
        return userMapper.userToUserDto(userService.saveUser(userModel));
    }

    @DeleteMapping("/{oib}")
    public void deleteUser(@PathVariable long oib) {
        User user = userService.getUserByOib(oib);
        cardGenerationService.inactivateCardRequestFileIfExistent(user);
        userService.deleteUser(user);
    }
}

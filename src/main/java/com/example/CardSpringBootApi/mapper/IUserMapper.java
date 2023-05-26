package com.example.CardSpringBootApi.mapper;

import com.example.CardSpringBootApi.dto.UserDto;
import com.example.CardSpringBootApi.model.User;

public interface IUserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
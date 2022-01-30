package com.java.teaching.blog.dto.mapper;

import com.java.teaching.blog.dto.UserDto;
import com.java.teaching.blog.entity.User;

public class UserMapper {

    public static UserDto userToUserDto (User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }
}

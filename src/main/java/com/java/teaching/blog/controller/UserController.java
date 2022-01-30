package com.java.teaching.blog.controller;

import com.java.teaching.blog.dto.UserDto;
import com.java.teaching.blog.dto.mapper.UserMapper;
import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.response.MessageResponse;
import com.java.teaching.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/save")
    public ResponseEntity<?> save(@RequestBody User user) {
        return userService.saveUser(user).map(u -> new ResponseEntity<Object>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(new MessageResponse("Not save user"), HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        UserDto user = UserMapper.userToUserDto(userService.getCurrentUser(principal));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}

package com.java.teaching.blog.controller;

import com.java.teaching.blog.entity.Role;
import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.repository.RoleRepository;
import com.java.teaching.blog.response.MessageResponse;
import com.java.teaching.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @PostMapping("/users/save")
    public ResponseEntity<?> save(@RequestBody User user) {
        Optional<User> checkUser = getCheckUser(user);
        if (checkUser.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Not save user"), HttpStatus.BAD_REQUEST);
        }
        return userService.saveUser(user).map(u -> new ResponseEntity<Object>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(new MessageResponse("Not save user"), HttpStatus.BAD_REQUEST));
    }

    private Optional<User> getCheckUser(User user) {
        if (CollectionUtils.isNotEmpty(userService.findAllByUsernameOrEmail(user.getUsername(), user.getEmail()))) {
            return Optional.empty();
        }
        if (CollectionUtils.isEmpty(user.getRoles())) {
            Optional<Role> roleUser = roleRepository.getRoleByName("ROLE_USER");
            if (roleUser.isEmpty()) {
                return Optional.empty();
            }
            user.setRoles(Collections.singleton(roleUser.get()));
        }
        return Optional.of(user);
    }
}

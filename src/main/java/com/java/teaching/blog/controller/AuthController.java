package com.java.teaching.blog.controller;

import com.java.teaching.blog.entity.Role;
import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.repository.RoleRepository;
import com.java.teaching.blog.response.JWTTokenSuccessResponse;
import com.java.teaching.blog.response.MessageResponse;
import com.java.teaching.blog.security.JWTTokenProvider;
import com.java.teaching.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthController {

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @PostMapping("/auth/save")
    public ResponseEntity<?> save(@RequestBody User user) {
        Optional<User> checkUser = getCheckUser(user);
        if (checkUser.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Not save user"), HttpStatus.BAD_REQUEST);
        }
        return userService.saveUser(user).map(u -> new ResponseEntity<Object>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(new MessageResponse("Not save user"), HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> getAuthUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
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

    @Data
    private static class LoginRequest {
        private String username;
        private String password;
    }
}

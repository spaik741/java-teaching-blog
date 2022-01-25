package com.java.teaching.blog.controller;

import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.response.JWTTokenSuccessResponse;
import com.java.teaching.blog.response.MessageResponse;
import com.java.teaching.blog.security.JWTTokenProvider;
import com.java.teaching.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/users/save")
    public ResponseEntity<?> save(@RequestBody User user) {
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

    @Data
    private static class LoginRequest {

        private String username;
        private String password;
    }
}

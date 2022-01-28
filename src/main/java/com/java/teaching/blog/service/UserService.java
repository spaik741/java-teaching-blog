package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getCurrentUser(Principal principal);
    List<User> findAllByUsernameOrEmail(String name, String email);
    Optional<User> saveUser(User user);
    Optional<User> getUserById(long id);
}

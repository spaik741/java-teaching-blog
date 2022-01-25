package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.User;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    User getCurrentUser(Principal principal);

    Optional<User> saveUser(User user);
}

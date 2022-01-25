package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface CustomUserDetailsService extends UserDetailsService {
    User getUserById(Long id);

    User build(User user);
}

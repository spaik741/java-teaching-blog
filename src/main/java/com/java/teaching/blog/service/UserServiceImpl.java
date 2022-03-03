package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Post;
import com.java.teaching.blog.entity.Role;
import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.repository.RoleRepository;
import com.java.teaching.blog.repository.UsersRepository;
import com.java.teaching.blog.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    @Override
    @Transactional
    public Optional<User> saveUser(User user) {
        Optional<User> checkUser = getCheckUser(user);
        if (checkUser.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(usersRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByUsernameOrEmail(String name, String email) {
        return usersRepository.findByUsernameOrEmail(name, email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(long id) {
        return usersRepository.findById(id);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    private Optional<User> getCheckUser(User user) {
        if (getUserByUsernameOrEmail(user.getUsername(), user.getEmail()).isPresent()) {
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

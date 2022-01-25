package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    @Override
    @Transactional
    public Optional<User> saveUser(User user) {
        return Optional.of(usersRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByUsernameOrEmail(String name, String email) {
        return usersRepository.findAllByUsernameOrEmail(name, email);
    }


    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}

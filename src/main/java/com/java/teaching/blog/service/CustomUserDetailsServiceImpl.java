package com.java.teaching.blog.service;


import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username not found with username %s", username)));
        return build(user);
    }

    public User getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public User build(User user) {
        return new User(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRoles());
    }

}

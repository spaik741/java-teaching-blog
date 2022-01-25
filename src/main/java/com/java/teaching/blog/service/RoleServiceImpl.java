package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Role;
import com.java.teaching.blog.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}

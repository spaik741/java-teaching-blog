package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> getRoleByName(String name);
}

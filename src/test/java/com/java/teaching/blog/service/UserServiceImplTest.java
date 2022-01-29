package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.repository.RoleRepository;
import com.java.teaching.blog.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImplTest {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TestEntityManager em;
    private UserService service;

    @BeforeEach
    public void setup() throws Exception {
        service = new UserServiceImpl(usersRepository, roleRepository);
    }

    @Test
    public void getUserByName() {
        User user = service.getUserByUsernameOrEmail("admin", "").get();
        assertEquals(user.getEmail(), "admin@mail.ru");
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123");
        user.setEmail("test@test.en");
        user.setRoles(Collections.EMPTY_SET);
        service.saveUser(user);
        User expectedUser = em.find(User.class, 4L);
        assertEquals(user.getEmail(), expectedUser.getEmail());
        assertEquals(Collections.singleton(roleRepository.getRoleByName("ROLE_USER").get()),expectedUser.getRoles());
    }

}
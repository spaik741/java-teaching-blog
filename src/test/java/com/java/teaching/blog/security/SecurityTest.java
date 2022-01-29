package com.java.teaching.blog.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    private static final String POSTS_API = "/posts";
    private static final String POSTS_API_ID = "/posts/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(
            username = "user"
    )
    public void getAllPostsTest() throws Exception {
        mockMvc.perform(get(POSTS_API))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    public void getPostsTest() throws Exception {
        mockMvc.perform(get(POSTS_API_ID, 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_ADMIN"}
    )
    public void deletePostsTest() throws Exception {
        mockMvc.perform(delete(POSTS_API_ID, 2))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    public void deletePostsBadRequestTest() throws Exception {
        mockMvc.perform(delete(POSTS_API_ID, 1))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
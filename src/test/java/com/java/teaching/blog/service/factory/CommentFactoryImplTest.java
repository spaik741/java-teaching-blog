package com.java.teaching.blog.service.factory;

import com.java.teaching.blog.entity.Comment;
import com.java.teaching.blog.entity.Post;
import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.service.PostService;
import com.java.teaching.blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CommentFactoryImplTest {

    private static final Long LONG_VAL = 1L;

    @MockBean
    private PostService postService;
    @MockBean
    private UserService userService;
    private CommentFactory commentFactory;

    @BeforeEach
    void init() {
        postService = mock(PostService.class);
        userService = mock(UserService.class);
        commentFactory = new CommentFactoryImpl(postService, userService);
    }

    @Test
    public void testCreateComment() {
        given(postService.getPostById(LONG_VAL)).willReturn(Optional.of(new Post()));
        given(userService.getUserById(LONG_VAL)).willReturn(Optional.of(new User()));
        Optional<Comment> comment = commentFactory.createComment("message", new Date(), LONG_VAL, LONG_VAL);
        assertThat(comment).get().isNotNull().matches(c -> c.getMessage().equals("message"));
    }
}
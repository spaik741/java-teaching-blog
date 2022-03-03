package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Post;
import com.java.teaching.blog.repository.PostsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostServiceImplTest {

    @Autowired
    private PostsRepository repository;
    @Autowired
    private TestEntityManager em;
    private PostService service;

    @BeforeEach
    public void setup() throws Exception {
        service = new PostServiceImpl(repository);
    }

    @Test
    public void getPostTest() {
        Post post = service.getPostById(1L).get();
        Post expectedPost = em.find(Post.class, 1L);
        assertThat(post).usingRecursiveComparison().isEqualTo(expectedPost);
    }

    @Test
    public void getAllPostsTest() {
        assertThat(service.getAllPosts())
                .allMatch(p -> StringUtils.isNotBlank(p.getTitle()))
                .allMatch(p -> StringUtils.isNotBlank(p.getText()));
    }

    @Test
    public void deletePostTest() {
        service.deletePost(1L);
        assertThat(em.find(Post.class, 1L)).isNull();
    }

    @Test
    public void savePostTest() {
        Post post = service.savePost(new Post(4L, "Title", "text...", new Date())).get();
        Post expectedPost = em.find(Post.class, 4L);
        assertThat(post).usingRecursiveComparison().isEqualTo(expectedPost);
    }
}
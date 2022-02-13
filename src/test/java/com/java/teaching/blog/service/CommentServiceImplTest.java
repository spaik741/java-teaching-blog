package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Comment;
import com.java.teaching.blog.repository.CommentsRepository;
import com.java.teaching.blog.repository.PostsRepository;
import com.java.teaching.blog.repository.UsersRepository;
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
class CommentServiceImplTest {

    @Autowired
    private TestEntityManager em;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    private CommentService commentService;

    @BeforeEach
    public void setup() throws Exception {
        commentService = new CommentServiceImpl(commentsRepository);
    }

    @Test
    public void getCommentTest() {
        Comment comment = commentService.getCommentById(1L).get();
        Comment expectedComment = em.find(Comment.class, 1L);
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    public void getAllCommentTest() {
        assertThat(commentsRepository.findAllByPostId(1L))
                .allMatch(c -> StringUtils.isNotBlank(c.getMessage()))
                .allMatch(c -> c.getMessageDate() != null)
                .allMatch(c -> c.getPost() != null)
                .allMatch(c -> c.getUser() != null);
    }

    @Test
    public void deleteCommentTest() {
        commentsRepository.deleteById(1L);
        assertThat(em.find(Comment.class, 1L)).isNull();
    }

    @Test
    public void saveCommentTest() {
        Comment comment = commentsRepository.save(new Comment(4L, "comment...", new Date(), postsRepository.getById(1L), usersRepository.getById(1L)));
        Comment expectedComment = em.find(Comment.class, 4L);
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

}
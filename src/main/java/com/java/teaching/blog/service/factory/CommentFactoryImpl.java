package com.java.teaching.blog.service.factory;

import com.java.teaching.blog.entity.Comment;
import com.java.teaching.blog.entity.Post;
import com.java.teaching.blog.entity.User;
import com.java.teaching.blog.service.PostService;
import com.java.teaching.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CommentFactoryImpl implements CommentFactory {

    private final PostService postService;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> createComment(String text, Date date, long idPost, long idUser) {
        Comment comment = null;
        Optional<Post> optionalPost = postService.getPostById(idPost);
        Optional<User> optionalUser = userService.getUserById(idUser);
        if (optionalPost.isPresent() && optionalUser.isPresent()) {
            comment = new Comment(null, text, new Date(), optionalPost.get(), optionalUser.get());
        }
        return Optional.ofNullable(comment);
    }
}

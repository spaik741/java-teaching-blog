package com.java.teaching.blog.service.factory;

import com.java.teaching.blog.entity.Comment;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

public interface CommentFactory {
    Optional<Comment> createComment(String text, Date date, long idPost, long idUser);
    Optional<Comment> createCommentWithPrincipal(String text, Date date, long idPost, Principal principal);
}

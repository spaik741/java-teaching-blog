package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments(long idPost);

    Optional<Comment> getCommentById(long id);

    void deleteComment(long id);

    Optional<Comment> saveComment(Comment comment);
}

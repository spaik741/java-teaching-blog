package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Comment;
import com.java.teaching.blog.repository.CommentsRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentsRepository commentsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments(long idPost) {
        List<Comment> comments = commentsRepository.findAllByPostId(idPost);
        return CollectionUtils.isEmpty(comments) ? new ArrayList<>() : comments;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(long id) {
        return commentsRepository.findAll().stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        commentsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public  Optional<Comment> saveComment(Comment comment) {
        return Optional.of(commentsRepository.save(comment));
    }
}

package com.java.teaching.blog.repository;

import com.java.teaching.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(long idBook);
}

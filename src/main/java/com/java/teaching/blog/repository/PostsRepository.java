package com.java.teaching.blog.repository;

import com.java.teaching.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContainsOrTextContains(String title, String text);
}

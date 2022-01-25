package com.java.teaching.blog.repository;

import com.java.teaching.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
}

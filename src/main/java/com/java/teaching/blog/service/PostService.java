package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();

    Optional<Post> getPostById(long id);

    void deletePost(long id);

    Optional<Post> savePost(Post post);
}

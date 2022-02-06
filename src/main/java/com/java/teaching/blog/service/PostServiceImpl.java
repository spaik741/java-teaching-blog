package com.java.teaching.blog.service;

import com.java.teaching.blog.entity.Post;
import com.java.teaching.blog.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostsRepository postsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        List<Post> posts = postsRepository.findAll();
        return CollectionUtils.isEmpty(posts) ? new ArrayList<>() : posts;
    }

    @Override
    public List<Post> getAllPostsWithFilter(String word) {
        List<Post> posts = postsRepository.findAllByTitleContainsOrTextContains(word, word);
        return CollectionUtils.isEmpty(posts) ? new ArrayList<>() : posts;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(long id) {
        return getAllPosts().stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public void deletePost(long id) {
        postsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Post> savePost(Post post) {
        return Optional.of(postsRepository.save(post));
    }
}

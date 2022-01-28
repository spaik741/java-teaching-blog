package com.java.teaching.blog.controller;

import com.java.teaching.blog.entity.Post;
import com.java.teaching.blog.response.MessageResponse;
import com.java.teaching.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostsController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> savePost(@RequestBody Post book) {
        return postService.savePost(book)
                .map(p -> new ResponseEntity<Object>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(new MessageResponse("Not save post"), HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> books = postService.getAllPosts();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") long id) {
        return postService.getPostById(id)
                .map(p -> new ResponseEntity<Object>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(new MessageResponse(String.format("Not found post on id : %s", id)),
                        HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("id") long id) {
        try {
            postService.deletePost(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(String.format("Not found post on id : %s", id)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessageResponse(String.format("Post on id : %s is deleted", id)), HttpStatus.OK);
    }
}

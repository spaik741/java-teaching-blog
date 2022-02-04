package com.java.teaching.blog.controller;

import com.java.teaching.blog.entity.Comment;
import com.java.teaching.blog.response.MessageResponse;
import com.java.teaching.blog.service.CommentService;
import com.java.teaching.blog.service.UserService;
import com.java.teaching.blog.service.factory.CommentFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentFactory commentFactory;

    @PostMapping("/comments")
    public ResponseEntity<?> save(@RequestBody MessageRequest messageRequest, Principal principal) {
        return commentFactory.createCommentWithPrincipal(messageRequest.getMessage(), new Date(), messageRequest.getId(), principal)
                .map(commentService::saveComment)
                .map(c -> new ResponseEntity<Object>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(new MessageResponse("Not save comment"), HttpStatus.BAD_REQUEST));

    }

    @GetMapping("/comments/{idPost}")
    public ResponseEntity<List<Comment>> getAll(@PathVariable("idPost") long idPost) {
        List<Comment> comments = commentService.getAllComments(idPost);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") long id) {
        try {
            commentService.deleteComment(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(String.format("Not found comment on id : %s", id)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessageResponse(String.format("Comment on id : %s is deleted", id)), HttpStatus.OK);
    }

    @Data
    private static class MessageRequest {

        private long id;
        private String message;
    }

}

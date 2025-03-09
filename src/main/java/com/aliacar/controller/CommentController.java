package com.aliacar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliacar.entities.Comment;
import com.aliacar.requests.CommentCreateRequest;
import com.aliacar.requests.CommentUpdateRequest;
import com.aliacar.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComment(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){ 
        return commentService.getAllComment(userId,postId);

    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest newComment){  
        return commentService.createComment(newComment);

    }

    @GetMapping("/{commentId}")
    public Comment getOneCommentById(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneCommentById(@PathVariable Long commentId,@RequestBody CommentUpdateRequest  updateComment){
        return commentService.updateOneCommentById(commentId,updateComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneCommentById(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }



    
}

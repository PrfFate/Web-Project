package com.aliacar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.tokens.CommentToken;

import com.aliacar.entities.Comment;
import com.aliacar.entities.Post;
import com.aliacar.entities.User;
import com.aliacar.repository.CommentRepository;
import com.aliacar.repository.PostRepository;
import com.aliacar.repository.UserRepository;
import com.aliacar.requests.CommentCreateRequest;
import com.aliacar.requests.CommentUpdateRequest;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> getAllComment(Optional<Long> userId, Optional<Long> postId){
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        return commentRepository.findAll();
    }

    public Comment createComment(CommentCreateRequest newComment) {
        User user= userRepository.findById(newComment.getUserId()).orElseThrow();
        Post post = postRepository.findById(newComment.getPostId()).orElseThrow();

        if(user==null && post ==null){
            return null;
        
        }
        Comment toCreate=new Comment();

        BeanUtils.copyProperties(newComment, toCreate);
        toCreate.setUser(user);
        toCreate.setPost(post);

        return commentRepository.save(toCreate);

        
    }

    public Comment getOneCommentById(Long commentId) {
       return commentRepository.findById(commentId).orElse(null);
    }

    public Comment updateOneCommentById(Long commentId,CommentUpdateRequest updateComment) {
        Optional<Comment> comment= commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment toUpdate=comment.get();

            
            BeanUtils.copyProperties(updateComment, toUpdate);

            return commentRepository.save(toUpdate);
            
            

        }
        return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}

package com.aliacar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliacar.entities.Post;
import com.aliacar.entities.User;
import com.aliacar.repository.PostRepository;
import com.aliacar.repository.UserRepository;
import com.aliacar.requests.PostCreateRequest;
import com.aliacar.requests.PostUpdateRequest;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<Post> getAllPosts(Optional<Long> userId) {
      
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
        
    }

    public Post getOnePostById(Long postId) {
      return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest newPostRequest) {
        User user= userService.getOneUser(newPostRequest.getUserId());
        if(user == null){
            return null;
        }
        Post toSave=new Post();

        BeanUtils.copyProperties(newPostRequest, toSave);
        toSave.setUser(user);

        return postRepository.save(toSave);
    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest updatePost) {

        Optional<Post> post=postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate=post.get();
            BeanUtils.copyProperties(updatePost, toUpdate);
            return postRepository.save(toUpdate);
        }
        
        return null;
    }
}

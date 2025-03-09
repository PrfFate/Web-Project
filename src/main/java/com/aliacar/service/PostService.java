package com.aliacar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliacar.entities.Post;
import com.aliacar.entities.User;
import com.aliacar.repository.PostRepository;

import com.aliacar.requests.PostCreateRequest;
import com.aliacar.requests.PostUpdateRequest;
import com.aliacar.response.PostResponse;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()){
            list= postRepository.findByUserId(userId.get());
        }else{
        list= postRepository.findAll();
        }
        return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
        
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

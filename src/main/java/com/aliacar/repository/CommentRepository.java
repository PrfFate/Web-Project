package com.aliacar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliacar.entities.Comment;


@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long>{

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long PostId);
}

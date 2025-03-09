package com.aliacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliacar.entities.Like;

@Repository
public interface LikeRepository  extends JpaRepository<Like,Long>{

}

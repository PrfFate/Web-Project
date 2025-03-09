package com.aliacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliacar.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}

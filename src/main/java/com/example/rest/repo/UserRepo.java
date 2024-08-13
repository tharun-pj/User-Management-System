package com.example.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}

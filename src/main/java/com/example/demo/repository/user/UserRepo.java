package com.example.demo.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.user.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>  {
	
	User findByUsername(String username);

}

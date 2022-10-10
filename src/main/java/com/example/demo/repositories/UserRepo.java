package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>  {

	User findByName(String username);

	User findByNameAndPassword(String username,String password);
	
	User  findByEmail(String email);

	
}

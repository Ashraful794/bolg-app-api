package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer>{
	
	List<Post> findByUser(User user);

}

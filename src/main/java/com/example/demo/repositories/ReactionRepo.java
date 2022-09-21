package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction,Integer>{
	
	Reaction findByUserIdAndPostId(Integer userId,Integer postId);
	
	List<Reaction> findByPostId(Integer postId);
	
	Reaction findByIdAndUserId(Integer id,Integer userId);
	

}

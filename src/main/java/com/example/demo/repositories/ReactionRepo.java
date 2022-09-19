package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;
import com.example.demo.entities.User;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction,Integer>{
	
	User findByUser(User user);

}

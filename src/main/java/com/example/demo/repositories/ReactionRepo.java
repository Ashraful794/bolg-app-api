package com.example.demo.repositories;


import java.util.List;

import com.example.demo.entities.Reacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction,Integer>{
	
	Reaction findByUserIdAndPostId(Integer userId,Integer postId);
	
	List<Reaction> findByPostId(Integer postId);
	
	Reaction findByIdAndUserId(Integer id,Integer userId);


	@Query("Select count(u) from Reaction u where u.post.id= :postId and u.react= :reacts")
	Integer getTotalPostLike(Integer postId, Reacts reacts);

	@Query("Select count(u)  from Reaction u where u.post.id= :postId and u.react= :reacts")
	Integer getTotalPostDisLike(Integer postId, Reacts reacts);


}

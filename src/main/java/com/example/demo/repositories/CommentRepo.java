package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {
	
	List<Comment> findByPostId(Integer postId);

}

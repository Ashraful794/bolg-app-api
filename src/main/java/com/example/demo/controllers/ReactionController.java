package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.entities.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.CommentDto;
import com.example.demo.payloads.ReactionDto;

import com.example.demo.services.ReactionService;

@RestController
@RequestMapping("/api/")
public class ReactionController {

	
	@Autowired
	ReactionService reactionService;
	
	@PostMapping("/post/{postId}/user/{userId}/reacts")
	public ResponseEntity<ReactionDto> createReact(@RequestBody ReactionDto react,@PathVariable Integer userId,@PathVariable Integer postId)
	{
		ReactionDto createReact=this.reactionService.addReact(react,userId, postId);
				
		return new ResponseEntity<ReactionDto>(createReact,HttpStatus.CREATED);
	}
	
	@GetMapping("reactions/post/{postId}")
	
	public ResponseEntity<List<ReactionDto>> getReactionsByPostId(@PathVariable Integer postId)
	{
		return ResponseEntity.ok(this.reactionService.getPostReactions(postId));		
	}

	@GetMapping("posts/{postId}/total-like")
	public Integer getTotalLike(@PathVariable Integer postId)
	{
		return this.reactionService.getTotalLikePost(postId);
	}

	@GetMapping("posts/{postId}/total-Dislike")
	public Integer getTotalDisLike(@PathVariable Integer postId)
	{
		return this.reactionService.getTotalDisLikePost(postId);
	}

	
	
}

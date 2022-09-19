package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

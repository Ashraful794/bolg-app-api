package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.CommentDto;
import com.example.demo.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService; 
		
	@PostMapping("/post/{postId}/user/{userId}/comments")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto comment,@PathVariable Integer postId,@PathVariable Integer userId)
	{
		CommentDto createComment=this.commentService.createComment(comment, postId,userId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("comments/{commentId}/user/{userId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable Integer commentId,@PathVariable Integer userId)
	{
		this.commentService.deleteComment(commentId,userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Succesfully",true),HttpStatus.CREATED);
	}
	
	@GetMapping("comments/post/{postId}")
	
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Integer postId)
	{
		return ResponseEntity.ok(this.commentService.getPostComments(postId));		
	}
	
	


}

package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.UserDto;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("/api/")

public class PostController {
	
	@Autowired 
	private PostService postService;
	
	@PostMapping("/user/{userId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId)
	{
		PostDto createPost=this.postService.createPost(postDto, userId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}



	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost()
	{
		List<PostDto> allPost=this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@PathVariable Integer userId)
	{
		this.postService.deletePost(postId,userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is Successfully Deleted"),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{userId}/getall")
	public ModelAndView getPost(@PathVariable Integer userId)
	{
		ModelAndView mav=new ModelAndView("Post");
		List<PostDto> postlist=this.postService.getAllPost();
		mav.addObject("posts",postlist);
		return mav;
	}
	
	
	
	@GetMapping("/createpost/{userId}")
	public ModelAndView createPost(@PathVariable Integer userId) {

		ModelAndView mav = new ModelAndView("CreatePost");
		mav.addObject("userId", userId);
		return mav;
	}
	
//	@PostMapping("/post/submit")
//	public String savePost(@ModelAttribute PostDto postDto)
//	{		
//		
////		this.postService.createPost(postDto,userId);		
//		return "redirect:/api/posts/{userId}/getall";
//	}

	
	

}

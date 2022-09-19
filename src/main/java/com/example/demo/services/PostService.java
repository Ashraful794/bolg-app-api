package com.example.demo.services;

import java.util.List;

import com.example.demo.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId);

	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	
}

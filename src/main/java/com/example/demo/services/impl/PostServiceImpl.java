package com.example.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.Exceptions;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setUser(user);
		
		Post newPost=this.postRepo.save(post);
			
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		
		Post updatePost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId,Integer userId) {
		// TODO Auto-generated method stub
		
		this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId) );
		
		Post post=this.postRepo.findByIdAndUserId(postId, userId);
		
		if(post==null)
		{
			throw new Exceptions("Can't Delete this Post",HttpStatus.UNAUTHORIZED);
		}
		
		this.postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		List<Post> allPosts=this.postRepo.findAll();		
		
		List<PostDto> allPostDtos= allPosts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPostDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId) );
		
		List<Post> posts=this.postRepo.findByUser(user);
		
		List<PostDto> postDtos=posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

}

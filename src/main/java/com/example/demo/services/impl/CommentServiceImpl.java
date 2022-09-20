package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CommentDto;
import com.example.demo.payloads.PostDto;
import com.example.demo.repositories.CommentRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepo commentRepo;
	
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		// TODO Auto-generated method stub

		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		
		
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		
		comment.setUser(user);
		
		Comment savedComment=this.commentRepo.save(comment);
		
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Comment id",commentId));
		
		this.commentRepo.delete(comment);
		
		
	}

	@Override
	public List<CommentDto> getPostComments(Integer postId) {
		// TODO Auto-generated method stub
		List<Comment> commentlist=this.commentRepo.findByPostId(postId);				
		List<CommentDto> commentDto=commentlist.stream().map(comment->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return commentDto;
	}

}

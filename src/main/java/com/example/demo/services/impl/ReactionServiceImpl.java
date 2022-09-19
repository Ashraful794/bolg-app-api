package com.example.demo.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.ReactionDto;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.ReactionRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.ReactionService;

@Service
public class ReactionServiceImpl implements ReactionService {
	
	@Autowired
	private ReactionRepo reactionRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ReactionDto addReact(ReactionDto reactionDto, Integer userId, Integer postId) {
		// TODO Auto-generated method stub
				
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
		
				
		if(user.getId()==post.getUser().getId())
		{
			return null;
		}
		
		Integer reactId=userReactOrNot(user,post);
		
		if(reactId!=null)
		{						
			return updateReact(reactionDto,reactId,userId,postId);
			
		}
		
		
		Reaction reaction=this.modelMapper.map(reactionDto,Reaction.class);
		
		reaction.setPost(post);
		reaction.setUser(user);
		
		Reaction savedReaction=this.reactionRepo.save(reaction);
		
		return this.modelMapper.map(savedReaction, ReactionDto.class);
				
		
	}
	
	
	public Integer userReactOrNot(User user,Post post)
	{
		
		List<Reaction> reaction =this.reactionRepo.findAll();
				
		for(Reaction react:reaction)
		{
			if(react.getPost().getId()== post.getId() &&  react.getUser().getId()==user.getId())
			{
				return react.getId();
			}
		}
		
		return null;
	}


	@Override
	public ReactionDto updateReact(ReactionDto reactionDto, Integer reactId,Integer userId, Integer postId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
				
		Reaction reaction=this.reactionRepo.findById(reactId).orElseThrow(()-> new ResourceNotFoundException("Reaction","Reaction id",reactId));
				
		if(user.getId()==post.getUser().getId())
		{
			return null;
		}
		
		reaction.setReact(reactionDto.getReact());
		
		Reaction updateReaction=this.reactionRepo.save(reaction);
				
		return this.modelMapper.map(updateReaction, ReactionDto.class);			
		
	}
	
	
	

}

package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.entities.Reacts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;
import com.example.demo.entities.User;
import com.example.demo.exceptions.Exceptions;
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
			throw new Exceptions("Can't Like or Dislike this Post",HttpStatus.UNAUTHORIZED);
		}
		
		//If User React the Post or Not
		Integer reactId=userReactOrNot(userId,postId);
		
		if(reactId!=-1)
		{						
			return updateReact(reactionDto,reactId,userId,postId);
			
		}
		
		
		Reaction reaction=this.modelMapper.map(reactionDto,Reaction.class);
		
		reaction.setPost(post);
		reaction.setUser(user);
		
		Reaction savedReaction=this.reactionRepo.save(reaction);

		return this.modelMapper.map(savedReaction, ReactionDto.class);
				
		
	}
	
	
	public Integer userReactOrNot(Integer userId,Integer postId)
	{
		
		Reaction reaction=this.reactionRepo.findByUserIdAndPostId(userId,postId);
		
		if(reaction!=null)
		{
			return reaction.getId();
		}
		
		return -1;
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


	@Override
	public List<ReactionDto> getPostReactions(Integer postId) {
		// TODO Auto-generated method stub
		List<Reaction> reactionlist=this.reactionRepo.findByPostId(postId);
				
		List<ReactionDto> reactionListDto=reactionlist.stream().map(reaction->this.modelMapper.map(reaction, ReactionDto.class)).collect(Collectors.toList());
		
		return reactionListDto;
	}


	@Override
	public void deleteReact(Integer reactionId, Integer userId) {
		// TODO Auto-generated method stub
		
		this.reactionRepo.findById(reactionId).orElseThrow(()-> new ResourceNotFoundException("Reaction","Reaction id",reactionId) );
		
		Reaction reaction=this.reactionRepo.findByIdAndUserId(reactionId,userId);
		
		if(reaction==null)
		{
			throw new Exceptions("Can't Delete this React",HttpStatus.UNAUTHORIZED);

		}
		
		this.reactionRepo.delete(reaction);
		
	}

	@Override
	public Integer getTotalLikePost(Integer postId) {

		return this.reactionRepo.getTotalPostLike(postId, Reacts.Like);
	}

	@Override
	public Integer getTotalDisLikePost(Integer postId) {
		return this.reactionRepo.getTotalPostDisLike(postId,Reacts.Dislike);
	}


}

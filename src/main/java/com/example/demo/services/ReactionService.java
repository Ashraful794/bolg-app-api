package com.example.demo.services;

import java.util.List;


import com.example.demo.entities.Reaction;
import com.example.demo.entities.Reacts;
import com.example.demo.payloads.ReactionDto;

public interface ReactionService {
	
	ReactionDto addReact(ReactionDto reactionDto, Integer userId, Integer postId);
	
	ReactionDto updateReact(ReactionDto reactionDto, Integer reactId ,Integer userId, Integer postId);
	
	List<ReactionDto> getPostReactions(Integer postId); 
	
	void deleteReact(Integer reactionId,Integer userId);

	Integer getTotalLikePost(Integer postId);

	Integer getTotalDisLikePost(Integer postId);

}

package com.example.demo.services;

import com.example.demo.payloads.ReactionDto;

public interface ReactionService {
	
	ReactionDto addReact(ReactionDto reactionDto, Integer userId, Integer postId);
	
	ReactionDto updateReact(ReactionDto reactionDto, Integer reactId ,Integer userId, Integer postId);

}

package com.example.demo.payloads;

import com.example.demo.entities.Reacts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ReactionDto {
	

	private Integer id;

	private Reacts react;
		
	private UserDto user;
	
	private PostDto post;


}

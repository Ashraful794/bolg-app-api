package com.example.demo.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private Integer postId;

	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	private Date addedDate;
		
//	private UserDto user;
	
//	private Set<CommentDto> comments=new HashSet<>();
//	
//	private Set<ReactionDto> reacts=new HashSet<>();
	
	

}

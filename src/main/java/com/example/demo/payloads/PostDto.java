package com.example.demo.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private Integer postId;

	private String title;
	
	private String content;
	
	private Date addedDate;
		
	private UserDto user;

}

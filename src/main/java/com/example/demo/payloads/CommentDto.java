package com.example.demo.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentDto
{
	
	private Integer id;
	
	@NotEmpty
	private String content;

}


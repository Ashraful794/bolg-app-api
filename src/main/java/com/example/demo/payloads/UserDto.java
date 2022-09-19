package com.example.demo.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter

public class UserDto {
	private Integer id;
	
	@NotEmpty
	private String name;
	
	
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty

	private String password;

	@NotEmpty
	private String about;
}

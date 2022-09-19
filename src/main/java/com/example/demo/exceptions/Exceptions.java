package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class Exceptions extends RuntimeException {

	private HttpStatus errorCode;
	private String errorMessage;
	
	public Exceptions(String errorMessage,HttpStatus errorCode) 	
	{
		super();
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}

}

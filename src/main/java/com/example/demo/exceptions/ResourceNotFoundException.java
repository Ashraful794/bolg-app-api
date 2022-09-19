package com.example.demo.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldvalue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldvalue) 
	
	{
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
	
	

}

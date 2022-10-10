package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	private ObjectError er;
////	private ObjectError objectError;
////	private ObjectError objectError1;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> resp=new HashMap();

		List<ObjectError> err=ex.getBindingResult().getAllErrors();

		for(var er:err )
		{

//			if(er instanceof FieldError)
//			{
//
//			}

//			String fieldname=((FieldError) er).getField();

			FieldError fieldError=(FieldError)er;

			String fieldname=fieldError.getField();

			fieldError.getDefaultMessage();


			String message= er.getDefaultMessage();
			resp.put(fieldname,message);
		}


//		ex.getBindingResult().getAllErrors().forEach((error)->{
//
//			String fieldName=((FieldError) error).getField();
//			String message=error.getDefaultMessage();
//			resp.put(fieldName, message);
//		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exceptions.class)
	
	public ResponseEntity<String> exceptions(Exceptions exceptions)
	{
		return new ResponseEntity<String>(exceptions.getErrorMessage(),exceptions.getErrorCode());
	}
	
	
	
}

package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import com.example.demo.entities.AuthRequest;
import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;
//import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto ){
		
		UserDto createUserDto= this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}


	@PostMapping("/login")
	public void login(@RequestBody UserDto userDto)
	{
		this.userService.login(userDto);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId)
	{
		UserDto updateUser=this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updateUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		this.userService.deleteUSer(userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted"),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userid)
	{
		return ResponseEntity.ok(this.userService.getUserById(userid));
	}


	@GetMapping("/showUsers")
	public ModelAndView showUsers()
	{
		ModelAndView mav=new ModelAndView("UserList");
		List<UserDto> userList=this.userService.getAllUsers();
		mav.addObject("users",userList);
		return mav;
		
	}
	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mav = new ModelAndView("Registration");
		UserDto newUser = new UserDto();
		mav.addObject("user", newUser);
		return mav;
	}
	@PostMapping("/saveUser")
	public String registration(@ModelAttribute UserDto userDto) {
		this.userService.createUser(userDto);
		return "redirect:/api/users/showUsers";
	}
	




}

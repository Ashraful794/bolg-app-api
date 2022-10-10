package com.example.demo.services;

import java.util.List;

import com.example.demo.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUsers();
	void deleteUSer(Integer userId);

	void login(UserDto userDto);

}

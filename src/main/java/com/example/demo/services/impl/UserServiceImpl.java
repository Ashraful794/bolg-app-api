package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
		
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=userRepo.save(this.modelMapper.map(userDto, User.class));
		
		return this.modelMapper.map(user, UserDto.class); 
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		// TODO Auto-generated method stub
		
		User user=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
				
		this.userRepo.save(user);
				
		return this.modelMapper.map(user,UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		
		User user=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userList= users.stream().map(user -> this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
		
		
		return userList;
	}

	@Override
	public void deleteUSer(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		this.userRepo.delete(user);
				
	}

}

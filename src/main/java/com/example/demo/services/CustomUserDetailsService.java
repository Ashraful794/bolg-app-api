//package com.example.demo.services;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.entities.User;
//import com.example.demo.repositories.UserRepo;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService{
//
//	@Autowired
//	private UserRepo userRepo;
//
//	 @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		 	User user = userRepo.findByName(username);
//	        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new ArrayList<>());
//	    }
//}

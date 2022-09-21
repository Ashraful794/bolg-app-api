//package com.example.demo.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.example.demo.payloads.UserDto;
//import com.example.demo.services.UserService;
//
//@Controller
//public class RegistrationLoginController {
//
//	@Autowired
//	UserService userService;
//
//	@GetMapping("/showUsers")
//	public ModelAndView showUsers() {
//		ModelAndView mav = new ModelAndView("UserList");
//		List<UserDto> userList = this.userService.getAllUsers();
//		mav.addObject("users", userList);
//		return mav;
//
//	}
//
//	@GetMapping("/registration")
//	public ModelAndView registration() {
//		ModelAndView mav = new ModelAndView("Registration");
//		UserDto newUser = new UserDto();
//		mav.addObject("user", newUser);
//		return mav;
//	}
//
//	@PostMapping("/saveUser")
//	public String registration(@ModelAttribute UserDto userDto) {
//		this.userService.createUser(userDto);
//		return "redirect:/showUsers";
//	}
//}

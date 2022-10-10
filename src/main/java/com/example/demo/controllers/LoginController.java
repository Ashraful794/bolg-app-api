package com.example.demo.controllers;

import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login()
    {
        return "/login";
    }

    @GetMapping("/")
    public String home()
    {
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.getCredentials());

        System.out.println(authentication.getName().toString());

        return "/home";
    }

    @GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mav = new ModelAndView("Registration");
		UserDto newUser = new UserDto();
		mav.addObject("user", newUser);
		return mav;
	}

	@PostMapping("/registration/saveUser")
	public String registration(@ModelAttribute UserDto userDto) {
		this.userService.createUser(userDto);
		return "redirect:/registration?Success";
	}



}

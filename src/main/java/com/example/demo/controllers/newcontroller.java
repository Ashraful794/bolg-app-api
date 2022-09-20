//package com.example.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
////import com.example.demo.entities.AuthRequest;
//import com.example.demo.entities.JwtRequest;
//import com.example.demo.entities.JwtResponse;
////import com.example.demo.services.CustomUserDetailsService;
////import com.example.demo.utility.JWTUtility;
//
//@RestController
//public class newcontroller {
//
////	@Autowired
////	private JwtUtil jwtUtil; 
////	
////	@Autowired
////	private AuthenticationManager authenticationManager;
//
////	@Autowired
////	private JWTUtility jwtUtility;
//
////	@Autowired
////	private AuthenticationManager authenticationManager;
//
////	@Autowired
////	private CustomUserDetailsService userService;
//
////	@PostMapping("/authenticate")
////	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
////
////		try {
////			authenticationManager.authenticate(
////					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
////		} catch (BadCredentialsException e) {
////			throw new Exception("INVALID_CREDENTIALS", e);
////		}
////
////		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
////
////		final String token = jwtUtility.generateToken(userDetails);
////
////		return new JwtResponse(token);
////	}
//
////	@PostMapping("/authenticate")
////    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
////        try {
////            authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
////            );
////        } catch (Exception ex) {
////            throw new Exception("inavalid username/password");
////        }
////        return jwtUtil.generateToken(authRequest.getUserName());
////    }
//
//}

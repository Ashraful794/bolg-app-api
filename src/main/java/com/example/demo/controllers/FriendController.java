package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.User;
import com.example.demo.services.FriendService;

import javax.validation.Valid;

@Controller
public class FriendController {

	@Autowired
	FriendService friendService;


	@PostMapping("/api/sendFriendRequest/{senderId}/{receiverId}")
	public void sendFriendRequest(@PathVariable Integer senderId, @PathVariable Integer receiverId) {
		this.friendService.sendFriendRequest(senderId, receiverId);

	}


	@GetMapping("/api/seeFriendRequest/{userId}")
	public ResponseEntity<List<User>> getAllFriendRequest(@PathVariable Integer userId) {
		return new ResponseEntity<List<User>>(this.friendService.seeFriendrequest(userId), HttpStatus.OK);
	}

	@PostMapping("/api/acceptFriendRequest/{senderId}/{receiverId}")
	public void acceptFriendRequest(@PathVariable Integer senderId, @PathVariable Integer receiverId) {
		this.friendService.acceptFriendRequest(senderId, receiverId);
	}

	@GetMapping("/api/getAllFriends/{userId}")
	public ResponseEntity<List<User>> getAllFriend(@PathVariable Integer userId) {
		return new ResponseEntity<List<User>>(this.friendService.seeAllFriends(userId), HttpStatus.OK);
	}

	@PostMapping("/api/rejectFriendRequest/{senderId}/{receiverId}")
	public void rejectFriendRequest(@PathVariable Integer senderId, @PathVariable Integer receiverId) {
		this.friendService.rejectFriendRequest(senderId, receiverId);
	}

	@DeleteMapping("/api/deleteFriendRequest/{senderId}/{receiverId}")
	public void deleteFriendRequest(@PathVariable Integer senderId, @PathVariable Integer receiverId) {
		this.friendService.deleteFriendRequest(senderId, receiverId);
	}

//	@GetMapping("/api/allFriend/{userId}")
//	public ResponseEntity<List<Friend>> getallFriend(@PathVariable Integer userId)
//	{
//		return new ResponseEntity<List<Friend>>(this.friendService.getAllfriend(userId),HttpStatus.OK);
//	}

	@GetMapping("/api/friendsuggestions/{userId}")
	public ResponseEntity<List<User>> friendSuggestion(@PathVariable Integer userId)
	{
		return  new ResponseEntity<List<User>>(this.friendService.friendSuggestion(userId),HttpStatus.OK);
	}


}

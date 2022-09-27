package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Friend;
import com.example.demo.entities.Request;
import com.example.demo.entities.User;
import com.example.demo.exceptions.Exceptions;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.FriendRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.FriendService;

@Service
public class FriendRequestImpl implements FriendService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	FriendRepo friendRepo;

	@Override
	public void sendFriendRequest(Integer senderId, Integer recieverId) {
		// TODO Auto-generated method stub

		if (senderId == recieverId) {
			throw new Exceptions("Can't Send Friend Request ", HttpStatus.BAD_REQUEST);
		}

		Friend findbysender = this.friendRepo.findBySenderIdAndReceiverId(senderId, recieverId);
		Friend findbyreceiver = this.friendRepo.findBySenderIdAndReceiverId(recieverId, senderId);

		if (findbysender != null || findbyreceiver != null) {
			throw new Exceptions("Allready Send or Friends", HttpStatus.ALREADY_REPORTED);
		}

		User sender = this.userRepo.findById(senderId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", senderId));

		User receiver = this.userRepo.findById(recieverId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", recieverId));

		Friend friend = new Friend();
		friend.setReceiver(receiver);
		friend.setSender(sender);

		this.friendRepo.save(friend);
		throw new Exceptions("Friend request send ",HttpStatus.OK);
		

	}

	@Override
	public List<User> seeFriendrequest(Integer userId) {
		// TODO Auto-generated method stub

		List<Friend> friendlist = this.friendRepo.findByReceiverIdAndRequest(userId, Request.PENDING);

		List<User> userlist = new ArrayList<>();

		for (Friend friend : friendlist) {
			Optional<User> user = this.userRepo.findById(friend.getSender().getId());

			if (user.isPresent()) {
				userlist.add(user.get());
			}
		}

		return userlist;
	}
	
	
	@Override
	public List<User> seeAllFriends(Integer id) {
		// TODO Auto-generated method stub


		List<Friend> receiverlist = this.friendRepo.findByReceiverIdAndRequest(id, Request.ACCEPTED);

		List<Friend> senderlist = this.friendRepo.findBySenderIdAndRequest(id, Request.ACCEPTED);
		

		List<User> userlist = new ArrayList<>();

		for (Friend friend : receiverlist) {
			Optional<User> user = this.userRepo.findById(friend.getSender().getId());

			if (user.isPresent()) {
				userlist.add(user.get());
			}
		}

		for (Friend friend : senderlist) {
			Optional<User> user = this.userRepo.findById(friend.getReceiver().getId());

			if (user.isPresent()) {
				userlist.add(user.get());
			}
		}

		return userlist;
	}

	@Override
	public void acceptFriendRequest(Integer senderId, Integer receiverId) {
		// TODO Auto-generated method stub

		Friend friend = this.friendRepo.findBySenderIdAndReceiverId(senderId, receiverId);

		if(friend.getRequest()==Request.ACCEPTED)
		{
			throw new Exceptions("Already Friend",HttpStatus.BAD_REQUEST);

		}
		
		friend.setRequest(Request.ACCEPTED);

		this.friendRepo.save(friend);
		
		throw new Exceptions("Friend request Accepted ",HttpStatus.OK);

	}

	@Override
	public void rejectFriendRequest(Integer senderId, Integer receiverId) {
		// TODO Auto-generated method stub

		Friend friend = this.friendRepo.findBySenderIdAndReceiverId(senderId, receiverId);

		friend.setRequest(Request.REJECTED);

		this.friendRepo.save(friend);
		throw new Exceptions("Friend request Rejected ",HttpStatus.OK);

	}

	@Override
	public void deleteFriendRequest(Integer senderId, Integer receiverId) {
		// TODO Auto-generated method stub
		Friend friend = this.friendRepo.findBySenderIdAndReceiverId(senderId, receiverId);
		
		if(friend==null)
		{
			friend = this.friendRepo.findBySenderIdAndReceiverId( receiverId,senderId);

		}
		this.friendRepo.delete(friend);
		throw new Exceptions("Successfully deleted ",HttpStatus.OK);

	}

}

package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Friend;
import com.example.demo.entities.User;

public interface FriendService {
	
	void sendFriendRequest(Integer senderId,Integer recieverId);
	
	List<User> seeFriendrequest(Integer userId);
	
	void acceptFriendRequest(Integer senderId, Integer receiverId);
	
	List<User> seeAllFriends(Integer id);
	
	void rejectFriendRequest(Integer senderId, Integer receiverId);
	
	void deleteFriendRequest(Integer senderId, Integer receiverId);
	List<Friend> getAllfriend(Integer id);
	List<User> friendSuggestion(Integer userId);


}

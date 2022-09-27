package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Friend;
import com.example.demo.entities.Request;

public interface FriendRepo extends JpaRepository<Friend, Integer> {
	
	
	List<Friend> findByReceiverIdAndRequest(Integer receiverId,Request request);
	
	List<Friend> findBySenderIdAndRequest(Integer senderId,Request request);
	
	Friend findBySenderIdAndReceiverId(Integer senderId,Integer receiverId);
	
	List<Friend> findBySenderId(Integer senderId);
	
	List<Friend> findByReceiverId(Integer receiverId);
	

}

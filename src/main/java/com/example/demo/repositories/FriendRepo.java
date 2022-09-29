package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Friend;
import com.example.demo.entities.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepo extends JpaRepository<Friend, Integer> {
	
	
	List<Friend> findByReceiverIdAndRequest(Integer receiverId,Request request);
	
	List<Friend> findBySenderIdAndRequest(Integer senderId,Request request);
	
	Friend findBySenderIdAndReceiverId(Integer senderId,Integer receiverId);
	
	List<Friend> findBySenderId(Integer senderId);
	
	List<Friend> findByReceiverId(Integer receiverId);

	@Query("SELECT u FROM Friend u WHERE u.sender.id = :id or u.receiver.id = :id and u.request= :request")
	List<Friend> allFriends(@Param("id") Integer id, @Param("request") Request request );



	@Query("SELECT u FROM Friend u WHERE u.sender.id = :id or u.receiver.id = :id ")
	List<Friend> allFriends(@Param("id") Integer id);

	@Query("SELECT u FROM Friend u WHERE u.sender.id = :senderId and u.receiver.id = :receiverId  or u.sender.id = :receiverId and u.receiver.id = :senderId")
	Friend getFrind(@Param("senderId") Integer senderId,@Param("receiverId") Integer receiverId);
	

}

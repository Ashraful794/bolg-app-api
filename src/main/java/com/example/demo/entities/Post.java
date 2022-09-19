package com.example.demo.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter

public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private Date addedDate;
	
	@ManyToOne
	private User user;

}

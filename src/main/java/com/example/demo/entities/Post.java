package com.example.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private Integer id;
	
	private String title;
	
	private String content;
	
	private Date addedDate;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	private Set<Reaction> reactions=new HashSet<>();

}

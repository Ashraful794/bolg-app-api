package com.example.demo.entities;

//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	
	@Column(name="user_name" , nullable=false , length=100)
	private String name;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)	
	private String password;

	@Column(nullable=false)
	private String about;
	
//	@OneToMany(mappedBy="user", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private List<Post> posts=new ArrayList<>();
//	
//	
//	@OneToMany(mappedBy="user", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private List<Comment> comments=new ArrayList<>();
//	
//	
//	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
//	private Set<Reaction> reactions=new HashSet<>();
	
	
	
	
}

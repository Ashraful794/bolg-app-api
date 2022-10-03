package com.example.demo.entities;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Friend {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
	private User sender;


	@ManyToOne
	private User receiver;

	@Enumerated(EnumType.STRING)
	private Request request=Request.PENDING;
	
	

}

package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String moodysRating;
	private String sandPRating;
	private String fitchRating;
	private int orderNumber;
}

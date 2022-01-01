package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "Must not be blank")
	@Size(max = 125, message = "Rating length cannot excede 125 characters")
	private String moodysRating;

	@NotBlank(message = "Must not be blank")
	@Size(max = 125, message = "Rating length cannot excede 125 characters")
	private String sandPRating;

	@NotBlank(message = "Must not be blank")
	@Size(max = 125, message = "Rating length cannot excede 125 characters")
	private String fitchRating;

	@NotNull(message = "Must not be null")
	@Min(value = -127, message = "Order Number cannot be below -127")
	@Max(value = 127, message = "Order Number cannot be above 127")
	private Integer orderNumber;

	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}
}

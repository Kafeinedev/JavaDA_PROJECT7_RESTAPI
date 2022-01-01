package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bidlist")
public class BidList {

	@Id
	@Column(name = "BidListId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer BidListId;

	@NotBlank(message = "Account is mandatory")
	@Size(max = 30, message = "Account length cannot excede 30 characters")
	private String account;

	@NotBlank(message = "Type is mandatory")
	@Size(max = 30, message = "Type length cannot excede 30 characters")
	private String type;

	@Min(value = 0, message = "Bid quantity must be above zero")
	private Double bidQuantity;
	private Double askQuantity;
	private Double bid;
	private Double ask;
	private String benchmark;
	private Timestamp bidListDate;
	private String commentary;
	private String security;
	private String status;
	private String trader;
	private String book;
	private String creationName;
	private Timestamp creationDate;
	private String revisionName;
	private Timestamp revisionDate;
	private String dealName;
	private String dealType;
	private String sourceListId;
	private String side;

	public BidList(String account, String type, Double bidQuantity) {
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}
}

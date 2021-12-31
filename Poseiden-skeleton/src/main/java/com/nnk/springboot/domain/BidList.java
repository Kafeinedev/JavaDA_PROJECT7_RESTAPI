package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int BidListId;

	private String account;
	private String type;
	private double bidQuantity;
	private double askQUantity;
	private double bid;
	private double ask;
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
	private Timestamp revisionDate​;
	private String dealName;
	private String dealType;
	private String sourceListId;
	private String side;

	public BidList(String account, String type, double bidQuantity) {
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}
}

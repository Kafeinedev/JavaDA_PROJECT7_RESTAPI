package com.nnk.springboot.domain;

import java.sql.Timestamp;

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
@Table(name = "trade")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tradeId;
	private String account;
	private String type;
	private double buyQuantity;
	private double sellQuantity;
	private double buyPrice;
	private double sellPrice;
	private String benchmark;
	private Timestamp tradeDate;
	private String security;
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

	public Trade(String account, String type) {
		this.account = account;
		this.type = type;
	}
}

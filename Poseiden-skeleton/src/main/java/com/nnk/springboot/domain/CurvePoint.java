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
@Table(name = "curvepoint")
public class CurvePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int curveId;
	private Timestamp asOfDate;
	private double term;
	private double value;
	private Timestamp creationDate;
}

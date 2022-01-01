package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "Must not be null")
	@Min(value = -127, message = "curveId cannot be below -127")
	@Max(value = 127, message = "curveId cannot be above 127")
	private Integer curveId;
	private Timestamp asOfDate;

	@NotNull(message = "Please add a term")
	private Double term;

	@NotNull(message = "Please add a value")
	private Double value;
	private Timestamp creationDate;

	public CurvePoint(Integer curveId, Double term, Double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}
}

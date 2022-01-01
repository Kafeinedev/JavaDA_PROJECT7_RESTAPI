package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "rulename")
public class RuleName {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "Please add a name")
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String name;

	@NotBlank(message = "Please add a description")
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String description;

	@NotBlank(message = "Please add a json")
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String json;

	@NotBlank(message = "Please add a template")
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String template;

	@NotBlank(message = "Please add a sqlStr")
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String sqlStr;

	@NotBlank(message = "Please add a sqlPart")
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String sqlPart;

	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}
}

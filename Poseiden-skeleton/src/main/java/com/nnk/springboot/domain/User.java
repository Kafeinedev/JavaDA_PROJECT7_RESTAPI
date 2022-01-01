package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Username is mandatory")
	private String username;
	@NotBlank(message = "Password is mandatory")
	private String password;
	@NotBlank(message = "FullName is mandatory")
	private String fullname;
	@NotBlank(message = "Role is mandatory")
	private String role;

	public User(String username, String password, String fullname, String role) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}
}

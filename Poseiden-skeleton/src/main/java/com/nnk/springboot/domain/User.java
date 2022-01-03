package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String username;

	@NotBlank(message = "Password is mandatory")
	@Size(max = 125, message = "Maximum number of characters is 125")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "Password must containt at least"
			+ " one upper case letter, one symbol, a number and be at least 8 charaters")
	private String password;

	@NotBlank(message = "FullName is mandatory")
	@Size(max = 125, message = "Maximum number of characters is 125")
	private String fullname;

	@NotBlank(message = "Role is mandatory")
	@Pattern(regexp = "ADMIN|USER", message = "Role must either be 'ADMIN' or 'USER'")
	private String role;

	public User(String username, String password, String fullname, String role) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}
}

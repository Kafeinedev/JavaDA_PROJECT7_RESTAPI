package com.nnk.springboot.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Khang Nguyen. Email: khang.nguyen@banvien.com Date: 09/03/2019
 * Time: 11:26 AM
 * 
 * Updated by Kafeinedev. Email: kafeinedev@protonmail.com Date 01/01/2022
 */
public class PasswordEncodeTest {

	@Test
	public void testPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pw = encoder.encode("123456");
		assertTrue(Pattern.matches("^\\$2[ayb]\\$.{56}$", pw));
	}
}

package com.nnk.springboot.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Khang Nguyen. Email: khang.nguyen@banvien.com Date: 09/03/2019
 * Time: 11:26 AM
 */
public class PasswordEncodeTest {

	@Test
	public void testPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pw = encoder.encode("123456");
		assertThat(pw.length()).isEqualTo(60);
		assertThat(pw).isNotEqualTo("123456");
	}
}

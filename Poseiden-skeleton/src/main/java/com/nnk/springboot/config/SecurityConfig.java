package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Allow custom security configuration
 *
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Lazy // Avoid circular dependency
	private UserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) throws Exception {//@formatter:off
		http.authorizeRequests()
			.antMatchers("/", "/css/**", "/user/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").authenticated()
		.and()
			.formLogin().defaultSuccessUrl("/bidList/list")
		.and()
			.logout().logoutUrl("/app-logout").logoutSuccessUrl("/")
		.and()
			.exceptionHandling().accessDeniedPage("/app/error")
		.and()
			.oauth2Login().defaultSuccessUrl("/bidList/list");
	}//@formatter:on

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}

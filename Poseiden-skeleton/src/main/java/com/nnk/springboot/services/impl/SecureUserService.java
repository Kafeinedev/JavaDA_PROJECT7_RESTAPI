package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@Service
public class SecureUserService implements UserService, UserDetailsService {

	Logger log = LogManager.getLogger("SecureUserService");

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User createUser(User toCreate) {
		log.trace("creating a new User");

		toCreate.setPassword(encoder.encode(toCreate.getPassword()));

		return userRepository.save(toCreate);
	}

	@Override
	public List<User> findAllUsers() {
		log.trace("finding all Users");
		return userRepository.findAll();
	}

	@Override
	public User findUserById(int id) {
		log.trace("finding User by id: " + id);
		return userRepository.findById(id).orElseThrow(() -> {
			log.error("Could not find User with id: " + id);
			return new NoSuchElementException();
		});
	}

	@Override
	public User updateUser(int id, User toUpdate) {
		log.trace("updating User id: " + id);
		userRepository.findById(id).orElseThrow(() -> {
			log.error("Could not update User with id: " + id);
			return new NoSuchElementException();
		});

		toUpdate.setId(id);
		toUpdate.setPassword(encoder.encode(toUpdate.getPassword()));

		return userRepository.save(toUpdate);
	}

	@Override
	public void deleteUserById(int idToDelete) {
		log.trace("deleting User id: " + idToDelete);
		userRepository.findById(idToDelete).orElseThrow(() -> {
			log.error("Could not delete User with id: " + idToDelete);
			return new NoSuchElementException();
		});

		userRepository.deleteById(idToDelete);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.trace("loading UserDetails with username: " + username);
		User user = userRepository.findByUsername(username).orElseThrow(() -> {
			log.error("Could not find user with name: " + username);
			return new UsernameNotFoundException(username);
		});

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				List.of(new SimpleGrantedAuthority(user.getRole())));
	}
}

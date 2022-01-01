package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@Service
public class DefaultUserService implements UserService {

	Logger log = LogManager.getLogger("DefaultUserService");

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User toCreate) {
		log.trace("creating a new User");
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
}

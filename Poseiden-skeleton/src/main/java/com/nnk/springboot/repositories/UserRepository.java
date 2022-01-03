package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	/**
	 * Find by username.
	 *
	 * @param username of the user to find
	 * @return optional of the user
	 */
	public Optional<User> findByUsername(String username);
}

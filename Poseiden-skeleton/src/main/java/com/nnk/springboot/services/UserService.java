package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.User;

/**
 * Interface of UserService handle CRUD for User domain.
 */
public interface UserService {

	/**
	 * Creates a User in database.
	 *
	 * @param toCreate the User to create
	 * @return the created User
	 */
	public User createUser(User toCreate);

	/**
	 * Find all User in database
	 * 
	 * @return a List containing all User
	 */
	public List<User> findAllUsers();

	/**
	 * Find one User in database
	 * 
	 * @param id of the User to find
	 * @return the User with that id
	 */
	public User findUserById(int id);

	/**
	 * Update User.
	 *
	 * @param id       of the User to update
	 * @param toUpdate the updated User
	 * @return the updated User
	 */
	public User updateUser(int id, User toUpdate);

	/**
	 * Delete User by id.
	 *
	 * @param id of the User to delete
	 */
	public void deleteUserById(int idToDelete);
}

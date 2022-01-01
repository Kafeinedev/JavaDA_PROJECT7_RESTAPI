package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.User;

public interface UserService {

	public User createUser(User toCreate);

	public List<User> findAllUsers();

	public User findUserById(int id);

	public User updateUser(int id, User toUpdate);

	public void deleteUserById(int idToDelete);
}

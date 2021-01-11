package com.nnk.springboot.service;

import java.util.Optional;

import com.nnk.springboot.domain.User;

/**
 * 
 * Manage the CRUD operations concerning users.
 *
 */
public interface IUserService {
	public User addUser(User user);

	public Optional<User> findById(Integer id);

	public User updateUser(User user);

	public void deleteUser(Integer id);
}

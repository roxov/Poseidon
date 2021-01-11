package com.nnk.springboot.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		LOGGER.info("Adding new user");
		return userRepository.save(user);
	}

	public Optional<User> findById(Integer id) {
		LOGGER.info("Getting user identified by id");
		return userRepository.findById(id);
	}

	public User updateUser(User user) {
		LOGGER.info("Updating user");
		return userRepository.save(user);
	}

	public void deleteUser(Integer id) {
		LOGGER.info("Deleting user");
		userRepository.deleteById(id);
	}
}

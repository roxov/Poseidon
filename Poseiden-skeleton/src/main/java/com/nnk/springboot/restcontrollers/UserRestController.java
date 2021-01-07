package com.nnk.springboot.restcontrollers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@RestController
public class UserRestController {
	private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/user")
	public User findByUserName(@RequestParam String username) {
		LOGGER.info("Getting user identified by username");
		return userRepository.findByUsername(username);
	}

	@PostMapping(value = "/user")
	public User addUser(@RequestBody User user) {
		LOGGER.info("Adding new user");
		return userRepository.save(user);
	}

	@PutMapping(value = "/user")
	public User updateUser(@RequestBody User user) {
		LOGGER.info("Updating user");
		return userRepository.save(user);
	}

	@DeleteMapping(value = "/user")
	public void deleteUser(@RequestParam String username) {
		LOGGER.info("Deleting user");
		userRepository.deleteByUsername(username);
	}
}

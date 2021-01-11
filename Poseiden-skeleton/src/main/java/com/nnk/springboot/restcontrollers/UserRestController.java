package com.nnk.springboot.restcontrollers;

import java.util.Optional;

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
import com.nnk.springboot.service.UserService;

@RestController
public class UserRestController {
	private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

	@GetMapping(value = "/user")
	public Optional<User> findById(@RequestParam Integer id) {
		LOGGER.info("Getting user identified by id");
		return userService.findById(id);
	}

	@PostMapping(value = "/user")
	public User addUser(@RequestBody User user) {
		LOGGER.info("Adding new user");
		return userService.addUser(user);
	}

	@PutMapping(value = "/user")
	public User updateUser(@RequestBody User user) {
		LOGGER.info("Updating user");
		return userService.updateUser(user);
	}

	@DeleteMapping(value = "/user")
	public void deleteUser(@RequestParam Integer id) {
		LOGGER.info("Deleting user");
		userService.deleteUser(id);
	}
}

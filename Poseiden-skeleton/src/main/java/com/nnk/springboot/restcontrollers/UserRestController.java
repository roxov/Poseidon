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
import com.nnk.springboot.util.RegexValidator;

@RestController
public class UserRestController {
	private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

	@PostMapping(value = "/user")
	public Optional<User> addUser(@RequestBody User user) {
		if (RegexValidator.validatePassword(user.getPassword())) {
			LOGGER.info("Adding new user");
			return Optional.of(userService.addUser(user));
		} else {
			LOGGER.error("The password must contain 8 characters, 1 capital, 1 number and 1 special character ");
			return Optional.empty();
		}
	}

	@GetMapping(value = "/user")
	public Optional<User> findById(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
			return Optional.empty();
		}
		LOGGER.info("Getting user identified by id");
		return userService.findById(id);
	}

	@PutMapping(value = "/user")
	public Optional<User> updateUser(@RequestBody User user) {
		if (user.getId() == null) {
			LOGGER.error("The id is mandatory.");
			return Optional.empty();
		}
		LOGGER.info("Updating user");
		return Optional.of(userService.updateUser(user));
	}

	@DeleteMapping(value = "/user")
	public void deleteUser(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
		} else {
			LOGGER.info("Deleting user");
			userService.deleteUser(id);
		}
	}
}

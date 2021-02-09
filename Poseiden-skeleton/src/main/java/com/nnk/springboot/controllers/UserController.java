package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.util.RegexValidator;

/**
 * Application controller for User entities.
 *
 */
@Controller
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@RequestMapping("/user/list")
	public String home(Model model) {
		LOGGER.info("Getting the users list");
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}

	@GetMapping("/user/add")
	public String addUser(User user) {
		LOGGER.info("Getting the form to add a user");
		return "user/add";
	}

	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors() || !RegexValidator.validatePassword(user.getPassword())) {
			LOGGER.error(
					"There are some incorrect datas. The password must contain 8 characters, 1 capital, 1 number and 1 special character ");
			return "redirect:/user/list";
		}
		LOGGER.info("Adding new user");
		userService.addUser(user);
		model.addAttribute("users", userRepository.findAll());
		return "user/add";
	}

	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setPassword("");
		LOGGER.info("Getting the form to update a user");
		model.addAttribute("user", user);
		return "user/update";
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors() || !RegexValidator.validatePassword(user.getPassword())) {
			LOGGER.error(
					"There are some incorrect datas. The password must contain 8 characters, 1 capital, 1 number and 1 special character ");
			return "user/update";
		}
		user.setId(id);
		LOGGER.info("Updating user");
		userService.updateUser(user);
		model.addAttribute("users", userRepository.findAll());
		return "redirect:/user/list";
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		LOGGER.info("Deleting user");
		userService.deleteUser(id);
		model.addAttribute("users", userRepository.findAll());
		return "redirect:/user/list";
	}
}

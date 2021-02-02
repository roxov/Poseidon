package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;

/**
 * Application controller for login pages.
 *
 */
@Controller
public class LoginController {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		LOGGER.info("Getting login page");
		mav.setViewName("login");
		return mav;
	}

	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userRepository.findAll());
		LOGGER.info("Getting users list");
		mav.setViewName("user/list");
		return mav;
	}

	@GetMapping("error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		LOGGER.info("Getting 403 error page");
		mav.setViewName("403");
		return mav;
	}
}

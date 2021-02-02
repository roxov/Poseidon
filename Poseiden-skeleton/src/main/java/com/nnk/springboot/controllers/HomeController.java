package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	/**
	 * Application controller for Home pages.
	 *
	 */
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(Model model) {
		LOGGER.info("Getting home page");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		LOGGER.info("Getting home page for admin");
		return "redirect:/bidList/list";
	}

}

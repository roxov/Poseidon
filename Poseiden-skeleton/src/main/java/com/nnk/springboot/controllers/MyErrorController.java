package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
	private static final Logger LOGGER = LogManager.getLogger(MyErrorController.class);

	@RequestMapping("/error")
	public String handleError() {
		// ModelAndView mav = new ModelAndView();
		// String errorMessage = "You are not authorized for the requested data.";
		// mav.addObject("errorMsg", errorMessage);
		LOGGER.info("Getting 403 error page");
		// mav.setViewName("403");
		return "403";
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}

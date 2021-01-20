package com.nnk.springboot.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addRedirectViewController("/admin/home", "/bidList/list");

		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/secure/article-details").setViewName("user/list");
		registry.addViewController("/error").setViewName("403");

		registry.addViewController("/bidList/list").setViewName("bidList/list");
		registry.addViewController("/bidList/add").setViewName("bidList/add");
		registry.addViewController("/bidList/validate").setViewName("bidList/add");
		registry.addViewController("/bidList/update/{id}").setViewName("bidList/update");
		// POST ?
		registry.addRedirectViewController("/bidList/delete/{id}", "/bidList/list");

		registry.addViewController("/curvePoint/list").setViewName("curvePoint/list");
		registry.addViewController("/curvePoint/add").setViewName("curvePoint/add");
		registry.addViewController("/curvePoint/validate").setViewName("curvePoint/add");
		registry.addViewController("/curvePoint/update/{id}").setViewName("curvePoint/update");
		// POST ?
		registry.addRedirectViewController("/curvePoint/delete/{id}", "/curvePoint/list");

		registry.addViewController("/rating/list").setViewName("rating/list");
		registry.addViewController("/rating/add").setViewName("rating/add");
		registry.addViewController("/rating/validate").setViewName("rating/add");
		registry.addViewController("/rating/update/{id}").setViewName("rating/update");
		// POST ?
		registry.addRedirectViewController("/curvePoint/delete/{id}", "/rating/list");

	}
}

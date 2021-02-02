package com.nnk.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/**", "/rest/user/**").hasRole("ADMIN")
				.antMatchers("/", "/home", "/error", "/rest/", "/rest/error/").permitAll()
				.antMatchers("/login*", "/rest/login*").permitAll().anyRequest().authenticated().and().formLogin()
				.defaultSuccessUrl("/bidList/list").and().exceptionHandling().accessDeniedPage("/error").and().logout()
				.logoutUrl("/app-logout").logoutSuccessUrl("/").and().httpBasic();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				com.nnk.springboot.domain.User user = userRepository.findByUsername(username);
				return User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getRole()).build();
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// return NoOpPasswordEncoder.getInstance();
	}

}

package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestControllerTest {
	@Autowired
	private UserRestController userRestController;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void givenAUserName_whenFindByUsername_thenReturnTheUser() {
		// GIVEN
		User user = new User("username", "password", "fullname", "role");
		when(userRepository.findByUsername("username")).thenReturn(user);

		// WHEN
		User result = userRestController.findByUserName("username");

		// THEN
		verify(userRepository, Mockito.times(1)).findByUsername("username");
		// TODO : password
		assertEquals("fullname", result.getFullname());
		assertEquals("role", result.getRole());
	}

	@Test
	public void givenAUser_whenAddUser_thenReturnCreatedUser() {
		// GIVEN
		User user = new User("username", "password", "fullname", "role");
		when(userRepository.save(user)).thenReturn(user);

		// WHEN
		User result = userRestController.addUser(user);

		// THEN
		verify(userRepository, Mockito.times(1)).save(any(User.class));
		assertEquals("username", result.getUsername());
		assertEquals("fullname", result.getFullname());
		assertEquals("role", result.getRole());
	}
}

package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestControllerTest {
	@Autowired
	private UserRestController userRestController;

	@MockBean
	private UserService userService;

	@Test
	public void givenACorrectPassword_whenAddUser_thenReturnCreatedUser() {
		// GIVEN
		User user = new User("username", "Password4+", "fullname", "role");
		when(userService.addUser(user)).thenReturn(user);

		// WHEN
		Optional<User> result = userRestController.addUser(user);

		// THEN
		assertEquals("fullname", result.get().getFullname());
	}

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<User> result = userRestController.findById(null);

		// THEN
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateUser_thenReturnEmptyOptional() {
		// GIVEN
		User user = new User(null, "username", "password", "fullname", "role");

		// WHEN
		Optional<User> result = userRestController.updateUser(user);

		// THEN
		assertEquals(Optional.empty(), result);
	}
}

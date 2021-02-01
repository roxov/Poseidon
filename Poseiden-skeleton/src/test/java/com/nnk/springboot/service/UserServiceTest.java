package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void givenAUser_whenAddUser_thenReturnCreatedUser() {
		// GIVEN
		User user = new User("username", "password", "fullname", "role");
		when(userRepository.save(user)).thenReturn(user);

		// WHEN
		User result = userService.addUser(user);

		// THEN
		verify(userRepository, Mockito.times(1)).save(any(User.class));
		assertEquals("username", result.getUsername());
		assertEquals("fullname", result.getFullname());
		assertEquals("role", result.getRole());
	}

	@Test
	public void givenAUser_whenFindById_thenReturnTheUser() {
		// GIVEN
		User user = new User(1, "username", "password", "fullname", "role");
		when(userRepository.findById(1)).thenReturn(Optional.of(user));

		// WHEN
		Optional<User> result = userService.findById(1);

		// THEN
		verify(userRepository, Mockito.times(1)).findById(1);
		// TODO : password
		assertEquals("fullname", result.get().getFullname());
		assertEquals("role", result.get().getRole());
	}

	@Test
	public void givenAUser_whenUpdateUser_thenReturnUpdatedUser() {
		// GIVEN
		User user = new User(1, "username", "password", "fullname", "role");
		when(userRepository.save(user)).thenReturn(user);

		// WHEN
		User result = userService.updateUser(user);

		// THEN
		verify(userRepository, Mockito.times(1)).save(any(User.class));
		assertEquals("username", result.getUsername());
		assertEquals("fullname", result.getFullname());
		assertEquals("role", result.getRole());
	}

	@Test
	public void givenAUser_whenDeleteUser_thenVerifyMethodCalled() {
		// GIVEN
		doNothing().when(userRepository).deleteById(1);

		// WHEN
		userService.deleteUser(1);

		// THEN
		verify(userRepository, Mockito.times(1)).deleteById(any(Integer.class));
	}

}

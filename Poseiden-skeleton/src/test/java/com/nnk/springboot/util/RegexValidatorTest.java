package com.nnk.springboot.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RegexValidatorTest {

	@Test
	public void givenACorrectPassword_whenValidatePassword_thenReturnTrue() {
		// WHEN
		boolean result = RegexValidator.validatePassword("Password4+");

		// THEN
		assertTrue(result);
	}

	@Test
	public void givenAPasswordWithoutCapital_whenValidatePassword_thenReturnFalse() {
		// WHEN
		boolean result = RegexValidator.validatePassword("password4+");

		// THEN
		assertFalse(result);
	}

	@Test
	public void givenAPasswordWithSixCharacters_whenValidatePassword_thenReturnFalse() {
		// WHEN
		boolean result = RegexValidator.validatePassword("Pass4+");

		// THEN
		assertFalse(result);
	}

	@Test
	public void givenAPasswordWithoutSpecialCharacter_whenValidatePassword_thenReturnFalse() {
		// WHEN
		boolean result = RegexValidator.validatePassword("Password4");

		// THEN
		assertFalse(result);
	}

	@Test
	public void givenAPasswordWithoutNumber_whenValidatePassword_thenReturnFalse() {
		// WHEN
		boolean result = RegexValidator.validatePassword("Password+");

		// THEN
		assertFalse(result);
	}

	@Test
	public void givenAStringWithOnlyNumbers_whenValidateNumber_thenReturnTrue() {
		// WHEN
		boolean result = RegexValidator.validateNumber("123456");

		// THEN
		assertTrue(result);
	}

	@Test
	public void givenAStringWithACharacter_whenValidateNumber_thenReturnFalse() {
		// WHEN
		boolean result = RegexValidator.validateNumber("P123456");

		// THEN
		assertFalse(result);
	}

}

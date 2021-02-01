package com.nnk.springboot.util;

import java.util.regex.Pattern;

public class RegexValidator {
	public static boolean validatePassword(String password) {
		return Pattern.matches("^(?=.{8,}$)(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$", password);
	}

}

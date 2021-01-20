package com.nnk.springboot.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegexValidator {
	public static boolean validatePassword(String password) {
		return Pattern.matches("^(?=.{8,}$)(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$", password);
	}

	public static boolean validateNumber(String number) {
		return StringUtils.isNumeric(number);
	}

	public static Double validateDouble(String str) {
		Double result = null;
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return null;
		}
		return result;
	}

}

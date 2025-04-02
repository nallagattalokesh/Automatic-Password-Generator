package com.lokesh.passwordGenerator;

import java.security.SecureRandom;

public class PasswordGenerator {

	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	private static final String DIGITS = "0123456789";
	private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:',.<>?/";
	private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

	private static final SecureRandom random = new SecureRandom();

	public static String generatePassword(int length) {
		if (length < 4) {
			throw new IllegalArgumentException("Password length must be at least 4 characters.");
		}

		StringBuilder password = new StringBuilder(length);

		// Ensure at least one character from each category
		password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
		password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
		password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
		password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

		// Fill the rest of the password with random characters
		for (int i = 4; i < length; i++) {
			password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
		}

		// Shuffle the characters to avoid predictable patterns
		return shuffleString(password.toString());
	}

	private static String shuffleString(String input) {
		char[] characters = input.toCharArray();
		for (int i = characters.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			char temp = characters[i];
			characters[i] = characters[j];
			characters[j] = temp;
		}
		return new String(characters);
	}

	public static void main(String[] args) {
		int passwordLength = 10; // You can change this length as needed
		String password = generatePassword(passwordLength);
		System.out.println("Generated Password: " + password);
	}
}

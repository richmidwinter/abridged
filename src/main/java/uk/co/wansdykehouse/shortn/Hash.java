package uk.co.wansdykehouse.shortn;

import java.util.Random;

public class Hash {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public static String get() {
		final Random r = new Random();
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			sb.append(ALPHABET.charAt(r.nextInt(ALPHABET.length())));
		}
		return sb.toString();
	}
}

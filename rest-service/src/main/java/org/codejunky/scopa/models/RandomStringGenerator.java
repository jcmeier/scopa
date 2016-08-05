package org.codejunky.scopa.models;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomStringGenerator {
	public static String getRandomString() {
		return new BigInteger(130, new SecureRandom()).toString(32);
	}
}

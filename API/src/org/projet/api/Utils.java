package org.projet.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	private final String algorithm = "SHA-256";

	private final char[] hexArray = "0123456789ABCDEF".toCharArray();

	/**
	 * genère une string crypté a partir d'une string
	 * 
	 * @param data string a crypté
	 * @return la string crypté
	 */
	public String generateHash(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.reset();
			byte[] hash = digest.digest(data.getBytes());
			return bytesToStringHex(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String bytesToStringHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}

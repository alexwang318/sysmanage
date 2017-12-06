package com.oracle.sp.security.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User MD5 algorithm to calculate the digest of a string.
 * @author binwan
 *
 */
public class MD5Util {
	
	public static String MD5(String plainString) {
		
		if (plainString != null) {
			try {
				
				MessageDigest msgDigest = MessageDigest.getInstance("MD5");
				msgDigest.update(plainString.getBytes());
				byte[] byteData = msgDigest.digest();
				
				StringBuilder hexString = new StringBuilder();
				for (byte aByte : byteData) {
					String hex = Integer.toHexString(0xff & aByte);
					if (hex.length() == 1) {
						hexString.append('0');
					}
					
					hexString.append(hex);
				}
				
				return hexString.toString();
				
			} catch(NoSuchAlgorithmException e) {
				// Do something here if exception.
			}
		}
		
		return "";
	}

}

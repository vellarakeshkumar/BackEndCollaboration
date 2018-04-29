package com.collaborative.util;

import java.util.UUID;

import org.collaborative.model.User;

public class Util {
	
	public static String getSecurityKey(){
		return UUID.randomUUID().toString();
	}
	
	public static String generateToken(User user){
		String token = user.getId() + "=" + user.getSecurityKey();
		// encrypt
		return Base32.encode(token.getBytes());
	}
	
	public static String decryptToken(String token){
		return Base32.decode(token).toString();
	}
	
	public static User getUserDetailsFromToken(String token){
		try{
			User user = new User();
			String decryptToken = decryptToken(token);
			String[] data = decryptToken.split("=");
			user.setId(Long.parseLong(data[0]));
			user.setSecurityKey(data[1]);
			return user;
		}
		catch(Exception e){
			return null;
		}
	}

}

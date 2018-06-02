
package org.collaborative.util;
import java.util.Base64;
import java.util.UUID;

import org.collaborative.model.User;
public class Util {
	
	public static String getSecurityKey(){
		return UUID.randomUUID().toString();
	}
	
	
	
	public static String generateToken(User user){
		String token = user.getId() + "=" + user.getSecurityKey();
		return Base64.getEncoder().encodeToString(token.getBytes());
	}
	
	public static String decryptToken(String token){
		byte[] decodedBytes = Base64.getDecoder().decode(token);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
	
	public static User getUserDetailsFromToken(String token){
		try{
			User user = new User();
			String decryptToken = decryptToken(token);
			String[] data = decryptToken.split("=");
			user.setId(Long.parseLong(data[0]));//user.setId(1);
			user.setSecurityKey(data[1]);//user.setSecurity(3e4343);
			return user;
		}
		catch(Exception e){
			return null;
		}
	}
	

}
package com.nat.ValueObjects;

import java.net.URI;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayrollClient {

	private static final String allUserbyIdURI = "http://localhost:2345/v1/admin/userid";
	private static final String allUsersURI = "http://localhost:2345/v1/admin/user";
	private static final String deleteUsersURI = "http://localhost:2345/v1/admin/deleteuser";
	
	private RestTemplate restTemplate  = new RestTemplate();

	//get user by id
	public ResponseEntity<User> getUserById(int userid) {
		HttpHeaders authenticationHeaders  = getAuthenticationHeader("Test","test");
		return restTemplate.exchange(allUserbyIdURI +"/{userid}", 
				HttpMethod.GET,new HttpEntity<User>(authenticationHeaders),User.class,userid);
	}
	
	//delete user by id
	public void deleteUserById(int userid) {
		HttpHeaders authenticationHeaders  = getAuthenticationHeader("Test","test");
		restTemplate.exchange(deleteUsersURI +"/{userid}", 
				HttpMethod.GET,new HttpEntity<User>(authenticationHeaders),Void.class,userid);
	}
	
	//get all users
	public List<User> getAllUsers() {
		HttpHeaders authenticationHeaders  = getAuthenticationHeader("Test","test");
		ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {
		};
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(allUsersURI, 
				HttpMethod.GET,new HttpEntity<User>(authenticationHeaders),responseType);
		
		List<User> allUsers = responseEntity.getBody();
		return allUsers;
	}
	
	//create user
	public ResponseEntity<User> createUser(User user) {
		HttpHeaders authenticationHeaders  = getAuthenticationHeader("Test","test");
		return restTemplate.exchange(allUsersURI, 
				HttpMethod.POST,new HttpEntity<User>(authenticationHeaders),User.class);
	}
	
	private HttpHeaders getAuthenticationHeader(String username,String password) {
		String credentials = username + ":" + password;
		byte[] base64CredentialData = Base64.encodeBase64(credentials.getBytes());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization","Basic " + new String(base64CredentialData));
		return headers;
	}
}

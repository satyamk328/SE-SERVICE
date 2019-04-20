package com.erp.controller;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class JWTClientExample {
	static final String URL_LOGIN = "http://localhost:8080/school-user/api/v0/login";
	static final String URL_EMPLOYEES = "http://localhost:8080/employees";

	private static String postLogin(String username, String password) {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		parametersMap.add("username", username);
		parametersMap.add("password", password);

		String base64Credentials = username + ":" + password;
		byte[] plainCredsBytes = base64Credentials.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add("Authorization", "Basic " + base64Creds);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parametersMap, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = restTemplate.exchange(URL_LOGIN, HttpMethod.POST, requestEntity,
				Object.class);
		HttpHeaders responseHeaders = response.getHeaders();
		List<String> list = responseHeaders.get("Authorization");
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	private static void callRESTApi(String restUrl, String authorizationString) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + authorizationString);
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.GET, entity, String.class);
		String result = response.getBody();
		System.out.println(result);
	}

	public static void main(String[] args) {
		String username = "8130787891";
		String password = "smriti@123";
		String authorizationString = postLogin(username, password);
		System.out.println("Authorization String=" + authorizationString);
		callRESTApi(URL_EMPLOYEES, authorizationString);
	}
}
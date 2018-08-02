package cl.test.spotifytestapi.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
	
	@Value("${spotify.credentials.base64}")
	private String credentials;
	
	@Value("${spotify.auth}")
	private String authURL;

	public String getAccessToken() {
		
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	headers.set("Authorization", "Basic " + credentials);
    	
    	MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<>();
    	parametersMap.add("grant_type", "client_credentials");
    	
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
    			parametersMap, headers);
    	
    	RestTemplate restTemplate = new RestTemplate();
    	LinkedHashMap<String, String> response = restTemplate.postForObject(authURL, request, LinkedHashMap.class);
    	
		return response.get("access_token");
		
	}

}

package cl.test.spotifytestapi.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Value("${spotify.credentials.base64}")
	private String credentials;
	
	@Value("${spotify.auth}")
	private String authURL;
	
	@ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public Object getAccessToken() {
		
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.set("Authorization", credentials);      

    	HashMap<String, String> hash = new HashMap<String, String>();
    	hash.put("grant_type", "client_credentials");
    	
    	HttpEntity<HashMap<String, String>> request = new HttpEntity<HashMap<String, String>>(hash, headers);
    	
    	RestTemplate restTemplate = new RestTemplate();
    	Object response = restTemplate.postForObject(authURL, request, Object.class);
    	
		return response;
	}

}

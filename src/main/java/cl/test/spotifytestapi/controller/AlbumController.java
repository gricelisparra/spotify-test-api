package cl.test.spotifytestapi.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cl.test.spotifytestapi.domain.AlbumPageWrapper;

@RestController
@RequestMapping("/album")
public class AlbumController {
	
	@Value("${spotify.access_token}")
	private String accessToken;
	
	@Value("${spotify.base.url}")
	private String baseURL;
    
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public AlbumPageWrapper searchAlbum(@RequestParam String albumName, @RequestParam(required=false) String artistName) {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.set("Authorization", "Bearer " + accessToken);
    	
    	HttpEntity<String> entity = new HttpEntity<String>(headers);
    	 
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<AlbumPageWrapper> response = restTemplate.exchange(
			baseURL + "/search?type=album&q=album:" + albumName + (artistName != null ? " artist:" + artistName : ""),
			HttpMethod.GET,
			entity,
			AlbumPageWrapper.class
		);
		
        return response.getBody();
    }

}
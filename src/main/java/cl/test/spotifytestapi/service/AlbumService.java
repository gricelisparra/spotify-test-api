package cl.test.spotifytestapi.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.test.spotifytestapi.domain.AlbumPageWrapper;

@Service
public class AlbumService {
	
	@Value("${spotify.base.url}")
	private String baseURL;
	
	public ResponseEntity<AlbumPageWrapper> searchAlbums(String accessToken, String albumName, String artistName){
		
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
    	
    	return response;
	}

}

package cl.test.spotifytestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import cl.test.spotifytestapi.domain.AlbumPageWrapper;
import cl.test.spotifytestapi.service.AlbumService;
import cl.test.spotifytestapi.service.AuthService;

@RestController
@RequestMapping("/album")
public class AlbumController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AlbumService albumService;
    
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<AlbumPageWrapper> searchAlbum(@RequestParam String albumName, @RequestParam(required=false) String artistName) {
    	
    	ResponseEntity<AlbumPageWrapper> response = null;
    	
    	try {
    		response = albumService.searchAlbums(authService.getAccessToken(), albumName, artistName);
    	} catch (HttpClientErrorException e) {
    		// TODO: Cambiar a log...
    		System.out.println("Ocurrio un error en la busqueda de albums " + e.getClass() + ": " + e.getMessage());
    		return new ResponseEntity<AlbumPageWrapper>(e.getStatusCode());
    	}
		
    	return new ResponseEntity<AlbumPageWrapper>(response.getBody(), HttpStatus.OK);
    }

}
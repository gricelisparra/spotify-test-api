package cl.test.spotifytestapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.test.spotifytestapi.domain.Album;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object getAlbums() {
    	List<Album> albums = new ArrayList<>();
		
		Album album = new Album();
		album.setName("Prueba");
		albums.add(album);
		
        return albums;
    }

}
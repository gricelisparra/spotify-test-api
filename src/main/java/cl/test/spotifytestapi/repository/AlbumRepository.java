package cl.test.spotifytestapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.test.spotifytestapi.domain.Albums;

public interface AlbumRepository extends MongoRepository<Albums, String> {
	  
}
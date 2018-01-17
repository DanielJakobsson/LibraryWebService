package eagle.library.dao;

import java.util.Optional;

import eagle.library.model.Publication;

public interface PublicationDao extends Dao<Publication> {
	
	Optional<Publication> findByISBN(String isbn);

}
package eagle.library.dao;

import java.util.List;

import eagle.library.model.Book;

public interface BookDao extends Dao<Book> {

	
	
	List<Book> findByTitle(String title);

	List<Book> findByISBN(String isbn);
}
package eagle.library.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import eagle.library.model.Book;

@Stateless
public class BookDaoImpl implements BookDao {

	// Injects a entitymanager to handle entities.
	// The entityManager will scan persistence.xml
	// to figure out the database configuration.
	@Inject
	EntityManager em;

	@Override
	public void create(Book book) {
		em.persist(book);
	}

	@Override
	public void update(Book book) {
		em.merge(book);
	}
	
	@Override
	public Optional<Book> find(long id) {
		try {
			return Optional.of(em.find(Book.class, id));
		} catch (NoResultException ignore) {
		}
		return Optional.empty();
	}
	
	@Override
	public List<Book> findByQuery(TypedQuery<Book> query) {
		return query.getResultList();
	}

	@Override
	public List<Book> findByTitle(String title) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE lower(b.publication.title) LIKE lower(:title)", Book.class);
		query.setParameter("title", title);
		return findByQuery(query);
	}

	@Override
	public List<Book> findByISBN(String isbn) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.publication.isbn = :isbn", Book.class);
		query.setParameter("isbn", isbn);
		return findByQuery(query);
	}

	@Override
	public List<Book> listAll() {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
		return findByQuery(query);
	}

}

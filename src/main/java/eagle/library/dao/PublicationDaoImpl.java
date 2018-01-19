package eagle.library.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import eagle.library.model.Publication;

@Stateless
public class PublicationDaoImpl implements PublicationDao {

	@Inject
	EntityManager em;

	@Override
	public void create(Publication publication) {
		em.persist(publication);
	}

	@Override
	public void update(Publication publication) {
		em.merge(publication);

	}

	@Override
	public Optional<Publication> find(long id) {
		try {
			return Optional.of(em.find(Publication.class, id));
		} catch (NoResultException ignore) {
		}
		return Optional.empty();
	}
	
	@Override
	public List<Publication> findByQuery(TypedQuery<Publication> query) {
		return query.getResultList();
	}

	@Override
	public Optional<Publication> findByISBN(String isbn) {
		TypedQuery<Publication> query = em.createQuery("SELECT p FROM Publication p WHERE p.isbn = :isbn",
				Publication.class);
		query.setParameter("isbn", isbn);
		try {
			return Optional.of(query.getSingleResult());
		} catch (NoResultException ignore) {
		}
		return Optional.empty();
	}

	@Override
	public List<Publication> listAll() {
		TypedQuery<Publication> query = em.createQuery("SELECT p FROM Publication p", Publication.class);
		return findByQuery(query);
	}

}

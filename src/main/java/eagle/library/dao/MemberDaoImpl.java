package eagle.library.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import eagle.library.model.Member;

@Stateless
public class MemberDaoImpl implements MemberDao {

	@Inject
	EntityManager em;

	@Override
	public void create(Member book) {
		em.persist(book);
	}

	@Override
	public void update(Member book) {
		em.merge(book);
	}

	@Override
	public List<Member> findByQuery(TypedQuery<Member> query) {
		return query.getResultList();
	}

	@Override
	public List<Member> listAll() {
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
		return findByQuery(query);
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class);
		query.setParameter("email", email);
		try {
			return Optional.of(query.getSingleResult());
		} catch (NoResultException ignore) {
		}
		return Optional.empty();
	}

	@Override
	public List<Member> findByFirstName(String firstName) {
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE lower(m.firstName) LIKE lower(:firstName)", Member.class);
		query.setParameter("firstName", firstName);
		return findByQuery(query);
	}

	@Override
	public List<Member> findBySurname(String surname) {
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE lower(m.surname) LIKE lower(:surname)",	Member.class);
		query.setParameter("surname", surname);
		return findByQuery(query);
	}

}

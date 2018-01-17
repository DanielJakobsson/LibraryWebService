package eagle.library.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import eagle.library.model.Member;

@Stateless
public class MemberEJB implements MemberInterface {

	//Injects a entitymanager to handle entities.
	//The entityManager will scan persistence.xml
	//to figure out the database configuration.
	@Inject
	EntityManager em;
	
	/* (non-Javadoc)
	 * @see lexicon.rest.MemberInterface#createMember(lexicon.rest.Member)
	 */
	@Override
	public void createMember(Member member) {
		//Calling entityManager to persist entity
		em.persist(member);
	}


 
	/* (non-Javadoc)
	 * @see lexicon.rest.MemberInterface#listMembers()
	 */
	@Override
	public List<Member> listMembers() {
		//Using JPQL to query database
		  TypedQuery<Member> query =
			      em.createQuery("SELECT m FROM Member m", Member.class);
			   List<Member> results = query.getResultList();
			   return results;
	}



	/* (non-Javadoc)
	 * @see lexicon.rest.MemberInterface#searchByLastName(java.lang.String)
	 */
	@Override
	public List<Member> searchByLastName(String name) {
		//Using JPQL to query database
		  TypedQuery<Member> query =
			      em.createQuery("SELECT m FROM Member m WHERE m.lastName= :replace", Member.class);
		  		query.setParameter("replace", name);
			   List<Member> member = query.getResultList();
			   return member;
		
	}
		
}

package eagle.library.dao;

import java.util.List;
import java.util.Optional;

import eagle.library.model.Member;

public interface MemberDao extends Dao<Member> {

	List<Member> findByFirstName(String firstName);

	List<Member> findBySurname(String surname);
	
	Optional<Member> findByEmail(String email);
}
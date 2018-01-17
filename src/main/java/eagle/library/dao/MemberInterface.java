package eagle.library.dao;

import java.util.List;

import eagle.library.model.Member;

public interface MemberInterface {

	void createMember(Member member);

	List<Member> listMembers();

	List<Member> searchByLastName(String name);

}
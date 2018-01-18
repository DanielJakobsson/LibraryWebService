package eagle.library.rest;

import java.net.URISyntaxException;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eagle.library.dao.MemberDao;
import eagle.library.model.Member;


@Path("/member")
public class MemberService {
	
	@Inject
	MemberDao memberDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMember() {
		return Response.ok(memberDao.listAll()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMember(Member member) throws URISyntaxException {
		Optional<Member> result = memberDao.findByEmail(member.getEmail());
		if (result.isPresent()) {
			return Response.status(Response.Status.CONFLICT).build();
		} 
		memberDao.create(member);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	@Path("/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMemberByEmail(@PathParam("email") String email) {
		Optional<Member> result = memberDao.findByEmail(email);
		if (!result.isPresent()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		return Response.ok(result.get()).build();
	}

}

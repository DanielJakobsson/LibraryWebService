package eagle.library.rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	public Response createPeson(Member member) throws URISyntaxException {
		memberDao.create(member);
		return Response.status(Response.Status.CREATED).build();
	}
	
	//Mark this method to handle GET requests on /lastName
	//and defines the content type.
	@GET
	@Path("/surename")
	@Produces(MediaType.APPLICATION_JSON)
	public Response printName(@QueryParam("name") String name) {
		List<Member> person = memberDao.findBySurname(name);
		return Response.ok(person).build();
	}
	
}

package eagle.library.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eagle.library.dao.BookDao;
import eagle.library.dao.PublicationDao;
import eagle.library.model.Book;
import eagle.library.model.Publication;

//The path to the rest service
@Path("/book")
public class BookService {

	// Inject a EJB to handle database logic
	@Inject
	BookDao bookDao;

	@Inject
	PublicationDao publicationDao;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks() {
		return Response.ok(bookDao.listAll()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(Publication publication) throws URISyntaxException {
		Optional<Publication> existingPublication = publicationDao.findByISBN(publication.getIsbn());
		Publication publicationForBook;
		if (existingPublication.isPresent()) {
			publicationForBook = existingPublication.get();
		} else {
			publicationDao.create(publication);
			publicationForBook = publication;
		}
		
		Book book = new Book(publicationForBook);
		bookDao.create(book);
		
		return Response.status(Response.Status.CREATED).build();
	}
	
//	@PUT
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateBook(@PathParam("id") long id, @QueryParam("status") String status) throws URISyntaxException {
//		bookDao.update(book);
//		return Response.status(Response.Status.OK).build();
//	}
	
	@GET
	@Path("/isbn/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksByISBN(@PathParam("isbn") String isbn) {
		List<Book> book = bookDao.findByISBN(isbn);
		return Response.ok(book).build();
	}
	
	@GET
	@Path("/title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksByTitle(@PathParam("title") String title) {
		List<Book> book = bookDao.findByTitle(title);
		return Response.ok(book).build();
	}

	
	@GET
	@Path("/publication/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPublication(@PathParam("isbn") String isbn) {
		Optional<Publication> publication = publicationDao.findByISBN(isbn);
		if (!publication.isPresent()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		return Response.ok(publication.get()).build();
	}

}

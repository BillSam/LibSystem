package webservices.EndPoints;

import Beans.impl.BookBean;
import Entities.Book;
import Entities.BookCategory;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Path("/book")
public class BookEndPoint {
    @EJB
    private BookBean bookBean;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewAdmin(Book book){
        if (bookBean.create(book)){
            return Response
                    .status(200)
                    .entity("Success")
                    .build();
        }
        return Response.status(304).entity("Failed").build();

    }

    @GET
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminById(@PathParam("bookId") long id){
        Book book = bookBean.get(id);
        if (book != null){
            return Response
                    .status(200)
                    .entity(book)
                    .build();
        }
        return Response.status(304).entity("Failed").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks(){
        List list = bookBean.getAll();
        return Response.status(200).entity(list).build();

    }
    @GET
    @Path("/category")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooksByCategory(BookCategory bookCategory){
        List list = bookBean.getBooksByCategory(bookCategory);
        return Response
                .status(200)
                .entity(list)
                .build();

    }

}

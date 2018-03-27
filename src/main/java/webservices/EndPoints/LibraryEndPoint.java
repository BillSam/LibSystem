package webservices.EndPoints;

import Beans.impl.LibraryBean;
import Entities.Book;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Path("/")
public class LibraryEndPoint {

    @EJB
    private LibraryBean libraryBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReaderBorrowHistory(@PathParam("readerId") long id){
        List list = libraryBean.getReaderBorrowHistory(id);
        if (list != null){
            return Response.status(200).entity(list).build();

        }
        return Response.status(304).entity("Failed").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reserveBook(Book book){

        if (libraryBean.placeReservation(book)){
            return Response
                    .status(200)
                    .entity("Reserved")
                    .build();
        }
        return Response
                .status(304)
                .entity("Failed")
                .build();

    }



}

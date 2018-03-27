package webservices.EndPoints;

import Beans.impl.LibrarianBean;
import Entities.Librarian;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Path("/librarian")
public class LibrarianEndPoint {
    @EJB
    private LibrarianBean librarianBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewAdmin(Librarian librarian){
        if (librarianBean.create(librarian)){
            return Response
                    .status(200)
                    .entity("Success")
                    .build();
        }
        return Response.status(304).entity("Failed").build();

    }

    @POST
    @Path("/credentials")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response logInAdmin(@FormParam("name") String name, @FormParam("password") String password){
        if (librarianBean.logInLibrarian(name, password)){
            return Response
                    .status(200)
                    .entity("Credentials okay")
                    .build();
        }
        return Response
                .status(304)
                .entity("Wrong Credentials")
                .build();
    }

    @GET
    @Path("/{librarianId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminById(@PathParam("librarianId") long id){
        Librarian librarian = librarianBean.get(id);
        if (librarian != null){
            return Response
                    .status(200)
                    .entity(librarian)
                    .build();
        }
        return Response.status(304).entity("Failed").build();
    }

    @GET
    @Path("/loggedIn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAdminsLoggedIn(){

        List list = librarianBean.getAllLibrariansLoggedIn();
        return Response.status(200).entity(list).build();
    }

}

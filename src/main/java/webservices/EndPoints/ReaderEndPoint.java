package webservices.EndPoints;

import Beans.impl.ReaderBean;
import Entities.Reader;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by error on 3/27/18.
 */
@Path("/reader")
public class ReaderEndPoint {
    @EJB
    private ReaderBean readerBean;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewAdmin(Reader reader){
        if (readerBean.create(reader)){
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
        if (readerBean.logInReader(name, password)){
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
    @Path("/{readerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminById(@PathParam("readerId") long id){
        Reader reader = readerBean.get(id);
        if (reader != null){
            return Response
                    .status(200)
                    .entity(reader)
                    .build();
        }
        return Response.status(304).entity("Failed").build();
    }

    @Path("/{readerId}/borrowHistory")
    public LibraryEndPoint getReaderHistory(){

        return new LibraryEndPoint();
    }


}

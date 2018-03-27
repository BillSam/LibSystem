package webservices.EndPoints;
import Beans.impl.AdminBean;
import Entities.Admin;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Path("/admin")
public class AdminEndPoint {
    @EJB
    private AdminBean adminBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewAdmin(Admin admin){
        if (adminBean.create(admin)){
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
    public Response logInAdmin(@FormParam("name") String name,@FormParam("password") String password){
        if (adminBean.logInAdmin(name,password)){
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
    @Path("/{adminId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminById(@PathParam("adminId") long id){

        Admin admin = adminBean.get(id);
        if (admin != null){
            return Response
                    .status(200)
                    .entity(admin)
                    .build();
        }
        return Response.status(304).entity("Failed").build();
    }

    @GET
    @Path("/loggedIn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAdminsLoggedIn(){

        List list = adminBean.getAllAdminsLoggedIn();
        return Response.status(200).entity(list).build();
    }




}

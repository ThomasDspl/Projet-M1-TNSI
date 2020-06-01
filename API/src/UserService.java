
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserService {
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser( @PathParam("id") int id ) {
        User user = new User(id, "jdoe", 22);
        return user;
    } 

}

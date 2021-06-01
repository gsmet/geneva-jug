package quarkus.world.tour;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rock")
public class BandResource {

    @GET
    public String hello() {
        return "Hello from Geneva JUG - Updated";
    }
}

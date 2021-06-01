package quarkus.world.tour;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/rock")
public class BandResource {

    @GET
    public List<Band> listBands() {
        return Band.listAll();
    }

    @GET
    @Path("/alive")
    public List<Band> listAlive() {
        return Band.listAlive();
    }

    @POST
    @Transactional
    public void create(@Valid Band band) {
        band.persist();
    }
}

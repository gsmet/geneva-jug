package quarkus.world.tour;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.time.Duration;
import java.util.List;

@Path("/rock")
public class BandResource {

    @GET
    public Multi<Band> listBands() {
        System.out.println("My thread is " + Thread.currentThread().getName());
        return Band.streamAll();
    }

    @GET
    @Path("/blocking")
    @Blocking
    public List<Band> listBandsBlocking() {
        Uni<List<Band>> uni = Band.listAll();
        return uni.await().atMost(Duration.ofSeconds(3));
    }

    @GET
    @Path("/alive")
    public Multi<Band> listAlive() {
        return Band.streamAlive();
    }

    @POST
    public Uni<Void> create(@Valid Band band) {
        return Panache.withTransaction(() -> band.persist());
    }
}

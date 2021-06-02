package quarkus.world.tour;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hibernate.search.mapper.orm.session.SearchSession;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("/rock")
public class BandResource {

    @Inject
    SearchSession searchSession;

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

    @GET
    @Path("/search")
    @Transactional
    public List<Band> search(@QueryParam String pattern) {
        return searchSession.search(Band.class)
                .selectEntity()
                .where(f -> {
                    if (pattern == null || pattern.isBlank()) {
                        return f.matchAll();
                    }

                    return f.simpleQueryString().field("name").matching(pattern);
                })
                .sort(f -> f.field("name_sort"))
                .fetchAllHits();
    }
}

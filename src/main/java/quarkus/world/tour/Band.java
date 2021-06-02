package quarkus.world.tour;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Entity
public class Band extends PanacheEntity {

    @NotBlank
    @Column(unique = true)
    public String name;

    public boolean alive;

    @Min(1945)
    public Integer creationYear;

    public Integer terminationYear;

    public static Multi<Band> streamAlive() {
        return Band.stream("alive", true);
    }
}

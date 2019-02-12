package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.Flux;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Flux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FluxRepository extends JpaRepository<Flux, Long> {

}

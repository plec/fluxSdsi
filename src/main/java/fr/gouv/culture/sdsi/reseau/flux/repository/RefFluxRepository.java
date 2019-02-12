package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefFlux;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefFlux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefFluxRepository extends JpaRepository<RefFlux, Long> {

}

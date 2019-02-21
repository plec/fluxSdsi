package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFlux;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefTypeFlux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefTypeFluxRepository extends JpaRepository<RefTypeFlux, Long> {

}

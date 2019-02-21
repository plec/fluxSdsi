package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefMatriceFlux;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefMatriceFlux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefMatriceFluxRepository extends JpaRepository<RefMatriceFlux, Long> {

}

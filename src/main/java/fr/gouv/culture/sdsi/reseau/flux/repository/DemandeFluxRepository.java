package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.DemandeFlux;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DemandeFlux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeFluxRepository extends JpaRepository<DemandeFlux, Long> {

}

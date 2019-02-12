package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironnement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefEnvironnement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefEnvironnementRepository extends JpaRepository<RefEnvironnement, Long> {

}

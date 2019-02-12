package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefZone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefZone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefZoneRepository extends JpaRepository<RefZone, Long> {

}

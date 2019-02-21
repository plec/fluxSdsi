package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefVlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefVlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefVlanRepository extends JpaRepository<RefVlan, Long> {

}

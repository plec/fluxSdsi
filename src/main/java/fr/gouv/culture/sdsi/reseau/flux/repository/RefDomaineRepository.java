package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefDomaine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefDomaine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefDomaineRepository extends JpaRepository<RefDomaine, Long> {

}

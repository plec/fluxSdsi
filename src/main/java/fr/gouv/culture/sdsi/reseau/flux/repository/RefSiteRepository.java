package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefSite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefSite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefSiteRepository extends JpaRepository<RefSite, Long> {

}

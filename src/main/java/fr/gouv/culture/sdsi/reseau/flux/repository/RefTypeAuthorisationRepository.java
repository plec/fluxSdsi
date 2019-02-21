package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeAuthorisation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefTypeAuthorisation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefTypeAuthorisationRepository extends JpaRepository<RefTypeAuthorisation, Long> {

}

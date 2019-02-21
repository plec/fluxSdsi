package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefEnvironement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefEnvironementRepository extends JpaRepository<RefEnvironement, Long> {

}

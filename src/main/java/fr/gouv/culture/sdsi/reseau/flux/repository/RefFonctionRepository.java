package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefFonction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefFonction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefFonctionRepository extends JpaRepository<RefFonction, Long> {

}

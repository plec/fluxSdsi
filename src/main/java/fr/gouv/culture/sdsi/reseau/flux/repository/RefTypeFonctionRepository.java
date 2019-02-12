package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFonction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefTypeFonction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefTypeFonctionRepository extends JpaRepository<RefTypeFonction, Long> {

}

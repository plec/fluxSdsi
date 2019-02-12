package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefNumero;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefNumero entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefNumeroRepository extends JpaRepository<RefNumero, Long> {

}

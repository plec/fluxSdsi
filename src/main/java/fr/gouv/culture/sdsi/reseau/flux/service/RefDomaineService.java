package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefDomaine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefDomaine.
 */
public interface RefDomaineService {

    /**
     * Save a refDomaine.
     *
     * @param refDomaine the entity to save
     * @return the persisted entity
     */
    RefDomaine save(RefDomaine refDomaine);

    /**
     * Get all the refDomaines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefDomaine> findAll(Pageable pageable);


    /**
     * Get the "id" refDomaine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefDomaine> findOne(Long id);

    /**
     * Delete the "id" refDomaine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

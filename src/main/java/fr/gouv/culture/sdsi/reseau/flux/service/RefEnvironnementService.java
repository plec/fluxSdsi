package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironnement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefEnvironnement.
 */
public interface RefEnvironnementService {

    /**
     * Save a refEnvironnement.
     *
     * @param refEnvironnement the entity to save
     * @return the persisted entity
     */
    RefEnvironnement save(RefEnvironnement refEnvironnement);

    /**
     * Get all the refEnvironnements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefEnvironnement> findAll(Pageable pageable);


    /**
     * Get the "id" refEnvironnement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefEnvironnement> findOne(Long id);

    /**
     * Delete the "id" refEnvironnement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

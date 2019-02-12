package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefZone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefZone.
 */
public interface RefZoneService {

    /**
     * Save a refZone.
     *
     * @param refZone the entity to save
     * @return the persisted entity
     */
    RefZone save(RefZone refZone);

    /**
     * Get all the refZones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefZone> findAll(Pageable pageable);


    /**
     * Get the "id" refZone.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefZone> findOne(Long id);

    /**
     * Delete the "id" refZone.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

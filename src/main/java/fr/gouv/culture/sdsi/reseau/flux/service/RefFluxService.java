package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefFlux;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefFlux.
 */
public interface RefFluxService {

    /**
     * Save a refFlux.
     *
     * @param refFlux the entity to save
     * @return the persisted entity
     */
    RefFlux save(RefFlux refFlux);

    /**
     * Get all the refFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefFlux> findAll(Pageable pageable);


    /**
     * Get the "id" refFlux.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefFlux> findOne(Long id);

    /**
     * Delete the "id" refFlux.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

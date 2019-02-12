package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.Flux;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Flux.
 */
public interface FluxService {

    /**
     * Save a flux.
     *
     * @param flux the entity to save
     * @return the persisted entity
     */
    Flux save(Flux flux);

    /**
     * Get all the fluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Flux> findAll(Pageable pageable);


    /**
     * Get the "id" flux.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Flux> findOne(Long id);

    /**
     * Delete the "id" flux.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

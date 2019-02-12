package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFluxDTO;

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
     * @param refFluxDTO the entity to save
     * @return the persisted entity
     */
    RefFluxDTO save(RefFluxDTO refFluxDTO);

    /**
     * Get all the refFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefFluxDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refFlux.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefFluxDTO> findOne(Long id);

    /**
     * Delete the "id" refFlux.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

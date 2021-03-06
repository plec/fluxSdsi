package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefMatriceFluxDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefMatriceFlux.
 */
public interface RefMatriceFluxService {

    /**
     * Save a refMatriceFlux.
     *
     * @param refMatriceFluxDTO the entity to save
     * @return the persisted entity
     */
    RefMatriceFluxDTO save(RefMatriceFluxDTO refMatriceFluxDTO);

    /**
     * Get all the refMatriceFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefMatriceFluxDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refMatriceFlux.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefMatriceFluxDTO> findOne(Long id);

    /**
     * Delete the "id" refMatriceFlux.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

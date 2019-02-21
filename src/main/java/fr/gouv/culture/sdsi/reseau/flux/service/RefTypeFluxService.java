package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFluxDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefTypeFlux.
 */
public interface RefTypeFluxService {

    /**
     * Save a refTypeFlux.
     *
     * @param refTypeFluxDTO the entity to save
     * @return the persisted entity
     */
    RefTypeFluxDTO save(RefTypeFluxDTO refTypeFluxDTO);

    /**
     * Get all the refTypeFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefTypeFluxDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refTypeFlux.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefTypeFluxDTO> findOne(Long id);

    /**
     * Delete the "id" refTypeFlux.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

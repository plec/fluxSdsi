package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.FluxDTO;

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
     * @param fluxDTO the entity to save
     * @return the persisted entity
     */
    FluxDTO save(FluxDTO fluxDTO);

    /**
     * Get all the fluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FluxDTO> findAll(Pageable pageable);


    /**
     * Get the "id" flux.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FluxDTO> findOne(Long id);

    /**
     * Delete the "id" flux.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

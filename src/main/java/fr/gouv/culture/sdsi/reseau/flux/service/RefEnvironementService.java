package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefEnvironement.
 */
public interface RefEnvironementService {

    /**
     * Save a refEnvironement.
     *
     * @param refEnvironementDTO the entity to save
     * @return the persisted entity
     */
    RefEnvironementDTO save(RefEnvironementDTO refEnvironementDTO);

    /**
     * Get all the refEnvironements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefEnvironementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refEnvironement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefEnvironementDTO> findOne(Long id);

    /**
     * Delete the "id" refEnvironement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

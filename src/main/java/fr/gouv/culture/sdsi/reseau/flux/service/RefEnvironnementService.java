package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironnementDTO;

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
     * @param refEnvironnementDTO the entity to save
     * @return the persisted entity
     */
    RefEnvironnementDTO save(RefEnvironnementDTO refEnvironnementDTO);

    /**
     * Get all the refEnvironnements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefEnvironnementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refEnvironnement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefEnvironnementDTO> findOne(Long id);

    /**
     * Delete the "id" refEnvironnement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

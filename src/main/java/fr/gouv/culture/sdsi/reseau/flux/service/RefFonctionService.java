package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefFonction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefFonction.
 */
public interface RefFonctionService {

    /**
     * Save a refFonction.
     *
     * @param refFonction the entity to save
     * @return the persisted entity
     */
    RefFonction save(RefFonction refFonction);

    /**
     * Get all the refFonctions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefFonction> findAll(Pageable pageable);


    /**
     * Get the "id" refFonction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefFonction> findOne(Long id);

    /**
     * Delete the "id" refFonction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

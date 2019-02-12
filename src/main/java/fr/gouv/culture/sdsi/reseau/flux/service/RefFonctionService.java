package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFonctionDTO;

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
     * @param refFonctionDTO the entity to save
     * @return the persisted entity
     */
    RefFonctionDTO save(RefFonctionDTO refFonctionDTO);

    /**
     * Get all the refFonctions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefFonctionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refFonction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefFonctionDTO> findOne(Long id);

    /**
     * Delete the "id" refFonction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

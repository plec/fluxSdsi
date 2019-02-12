package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFonctionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefTypeFonction.
 */
public interface RefTypeFonctionService {

    /**
     * Save a refTypeFonction.
     *
     * @param refTypeFonctionDTO the entity to save
     * @return the persisted entity
     */
    RefTypeFonctionDTO save(RefTypeFonctionDTO refTypeFonctionDTO);

    /**
     * Get all the refTypeFonctions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefTypeFonctionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refTypeFonction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefTypeFonctionDTO> findOne(Long id);

    /**
     * Delete the "id" refTypeFonction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

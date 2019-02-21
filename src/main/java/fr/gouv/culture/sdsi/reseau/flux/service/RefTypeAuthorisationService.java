package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeAuthorisationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefTypeAuthorisation.
 */
public interface RefTypeAuthorisationService {

    /**
     * Save a refTypeAuthorisation.
     *
     * @param refTypeAuthorisationDTO the entity to save
     * @return the persisted entity
     */
    RefTypeAuthorisationDTO save(RefTypeAuthorisationDTO refTypeAuthorisationDTO);

    /**
     * Get all the refTypeAuthorisations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefTypeAuthorisationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refTypeAuthorisation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefTypeAuthorisationDTO> findOne(Long id);

    /**
     * Delete the "id" refTypeAuthorisation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

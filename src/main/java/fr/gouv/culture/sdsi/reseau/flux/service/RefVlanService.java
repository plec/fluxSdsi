package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefVlanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefVlan.
 */
public interface RefVlanService {

    /**
     * Save a refVlan.
     *
     * @param refVlanDTO the entity to save
     * @return the persisted entity
     */
    RefVlanDTO save(RefVlanDTO refVlanDTO);

    /**
     * Get all the refVlans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefVlanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refVlan.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefVlanDTO> findOne(Long id);

    /**
     * Delete the "id" refVlan.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

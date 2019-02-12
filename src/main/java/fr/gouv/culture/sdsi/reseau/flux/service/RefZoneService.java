package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefZoneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefZone.
 */
public interface RefZoneService {

    /**
     * Save a refZone.
     *
     * @param refZoneDTO the entity to save
     * @return the persisted entity
     */
    RefZoneDTO save(RefZoneDTO refZoneDTO);

    /**
     * Get all the refZones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefZoneDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refZone.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefZoneDTO> findOne(Long id);

    /**
     * Delete the "id" refZone.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

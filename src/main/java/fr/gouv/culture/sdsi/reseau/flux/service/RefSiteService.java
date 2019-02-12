package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefSiteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefSite.
 */
public interface RefSiteService {

    /**
     * Save a refSite.
     *
     * @param refSiteDTO the entity to save
     * @return the persisted entity
     */
    RefSiteDTO save(RefSiteDTO refSiteDTO);

    /**
     * Get all the refSites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefSiteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refSite.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefSiteDTO> findOne(Long id);

    /**
     * Delete the "id" refSite.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

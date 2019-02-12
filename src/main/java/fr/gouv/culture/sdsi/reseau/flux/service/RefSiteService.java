package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefSite;

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
     * @param refSite the entity to save
     * @return the persisted entity
     */
    RefSite save(RefSite refSite);

    /**
     * Get all the refSites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefSite> findAll(Pageable pageable);


    /**
     * Get the "id" refSite.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefSite> findOne(Long id);

    /**
     * Delete the "id" refSite.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefNumero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RefNumero.
 */
public interface RefNumeroService {

    /**
     * Save a refNumero.
     *
     * @param refNumero the entity to save
     * @return the persisted entity
     */
    RefNumero save(RefNumero refNumero);

    /**
     * Get all the refNumeros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefNumero> findAll(Pageable pageable);


    /**
     * Get the "id" refNumero.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefNumero> findOne(Long id);

    /**
     * Delete the "id" refNumero.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

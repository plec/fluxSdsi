package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefNumeroDTO;

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
     * @param refNumeroDTO the entity to save
     * @return the persisted entity
     */
    RefNumeroDTO save(RefNumeroDTO refNumeroDTO);

    /**
     * Get all the refNumeros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RefNumeroDTO> findAll(Pageable pageable);


    /**
     * Get the "id" refNumero.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RefNumeroDTO> findOne(Long id);

    /**
     * Delete the "id" refNumero.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

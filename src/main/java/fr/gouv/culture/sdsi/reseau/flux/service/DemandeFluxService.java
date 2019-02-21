package fr.gouv.culture.sdsi.reseau.flux.service;

import fr.gouv.culture.sdsi.reseau.flux.service.dto.DemandeFluxDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DemandeFlux.
 */
public interface DemandeFluxService {

    /**
     * Save a demandeFlux.
     *
     * @param demandeFluxDTO the entity to save
     * @return the persisted entity
     */
    DemandeFluxDTO save(DemandeFluxDTO demandeFluxDTO);

    /**
     * Get all the demandeFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DemandeFluxDTO> findAll(Pageable pageable);


    /**
     * Get the "id" demandeFlux.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DemandeFluxDTO> findOne(Long id);

    /**
     * Delete the "id" demandeFlux.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

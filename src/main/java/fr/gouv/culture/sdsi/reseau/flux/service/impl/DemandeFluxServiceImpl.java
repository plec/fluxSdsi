package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.DemandeFluxService;
import fr.gouv.culture.sdsi.reseau.flux.domain.DemandeFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.DemandeFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.DemandeFluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.DemandeFluxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DemandeFlux.
 */
@Service
@Transactional
public class DemandeFluxServiceImpl implements DemandeFluxService {

    private final Logger log = LoggerFactory.getLogger(DemandeFluxServiceImpl.class);

    private final DemandeFluxRepository demandeFluxRepository;

    private final DemandeFluxMapper demandeFluxMapper;

    public DemandeFluxServiceImpl(DemandeFluxRepository demandeFluxRepository, DemandeFluxMapper demandeFluxMapper) {
        this.demandeFluxRepository = demandeFluxRepository;
        this.demandeFluxMapper = demandeFluxMapper;
    }

    /**
     * Save a demandeFlux.
     *
     * @param demandeFluxDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DemandeFluxDTO save(DemandeFluxDTO demandeFluxDTO) {
        log.debug("Request to save DemandeFlux : {}", demandeFluxDTO);
        DemandeFlux demandeFlux = demandeFluxMapper.toEntity(demandeFluxDTO);
        demandeFlux = demandeFluxRepository.save(demandeFlux);
        return demandeFluxMapper.toDto(demandeFlux);
    }

    /**
     * Get all the demandeFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DemandeFluxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DemandeFluxes");
        return demandeFluxRepository.findAll(pageable)
            .map(demandeFluxMapper::toDto);
    }


    /**
     * Get one demandeFlux by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemandeFluxDTO> findOne(Long id) {
        log.debug("Request to get DemandeFlux : {}", id);
        return demandeFluxRepository.findById(id)
            .map(demandeFluxMapper::toDto);
    }

    /**
     * Delete the demandeFlux by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DemandeFlux : {}", id);        demandeFluxRepository.deleteById(id);
    }
}

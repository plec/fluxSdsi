package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.FluxService;
import fr.gouv.culture.sdsi.reseau.flux.domain.Flux;
import fr.gouv.culture.sdsi.reseau.flux.repository.FluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.FluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.FluxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Flux.
 */
@Service
@Transactional
public class FluxServiceImpl implements FluxService {

    private final Logger log = LoggerFactory.getLogger(FluxServiceImpl.class);

    private final FluxRepository fluxRepository;

    private final FluxMapper fluxMapper;

    public FluxServiceImpl(FluxRepository fluxRepository, FluxMapper fluxMapper) {
        this.fluxRepository = fluxRepository;
        this.fluxMapper = fluxMapper;
    }

    /**
     * Save a flux.
     *
     * @param fluxDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FluxDTO save(FluxDTO fluxDTO) {
        log.debug("Request to save Flux : {}", fluxDTO);
        Flux flux = fluxMapper.toEntity(fluxDTO);
        flux = fluxRepository.save(flux);
        return fluxMapper.toDto(flux);
    }

    /**
     * Get all the fluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FluxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fluxes");
        return fluxRepository.findAll(pageable)
            .map(fluxMapper::toDto);
    }


    /**
     * Get one flux by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FluxDTO> findOne(Long id) {
        log.debug("Request to get Flux : {}", id);
        return fluxRepository.findById(id)
            .map(fluxMapper::toDto);
    }

    /**
     * Delete the flux by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Flux : {}", id);        fluxRepository.deleteById(id);
    }
}

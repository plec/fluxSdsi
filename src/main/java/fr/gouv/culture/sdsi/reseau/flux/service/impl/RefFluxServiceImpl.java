package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefFluxService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefFluxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefFlux.
 */
@Service
@Transactional
public class RefFluxServiceImpl implements RefFluxService {

    private final Logger log = LoggerFactory.getLogger(RefFluxServiceImpl.class);

    private final RefFluxRepository refFluxRepository;

    private final RefFluxMapper refFluxMapper;

    public RefFluxServiceImpl(RefFluxRepository refFluxRepository, RefFluxMapper refFluxMapper) {
        this.refFluxRepository = refFluxRepository;
        this.refFluxMapper = refFluxMapper;
    }

    /**
     * Save a refFlux.
     *
     * @param refFluxDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefFluxDTO save(RefFluxDTO refFluxDTO) {
        log.debug("Request to save RefFlux : {}", refFluxDTO);
        RefFlux refFlux = refFluxMapper.toEntity(refFluxDTO);
        refFlux = refFluxRepository.save(refFlux);
        return refFluxMapper.toDto(refFlux);
    }

    /**
     * Get all the refFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefFluxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefFluxes");
        return refFluxRepository.findAll(pageable)
            .map(refFluxMapper::toDto);
    }


    /**
     * Get one refFlux by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefFluxDTO> findOne(Long id) {
        log.debug("Request to get RefFlux : {}", id);
        return refFluxRepository.findById(id)
            .map(refFluxMapper::toDto);
    }

    /**
     * Delete the refFlux by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefFlux : {}", id);        refFluxRepository.deleteById(id);
    }
}

package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefMatriceFluxService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefMatriceFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefMatriceFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefMatriceFluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefMatriceFluxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefMatriceFlux.
 */
@Service
@Transactional
public class RefMatriceFluxServiceImpl implements RefMatriceFluxService {

    private final Logger log = LoggerFactory.getLogger(RefMatriceFluxServiceImpl.class);

    private final RefMatriceFluxRepository refMatriceFluxRepository;

    private final RefMatriceFluxMapper refMatriceFluxMapper;

    public RefMatriceFluxServiceImpl(RefMatriceFluxRepository refMatriceFluxRepository, RefMatriceFluxMapper refMatriceFluxMapper) {
        this.refMatriceFluxRepository = refMatriceFluxRepository;
        this.refMatriceFluxMapper = refMatriceFluxMapper;
    }

    /**
     * Save a refMatriceFlux.
     *
     * @param refMatriceFluxDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefMatriceFluxDTO save(RefMatriceFluxDTO refMatriceFluxDTO) {
        log.debug("Request to save RefMatriceFlux : {}", refMatriceFluxDTO);
        RefMatriceFlux refMatriceFlux = refMatriceFluxMapper.toEntity(refMatriceFluxDTO);
        refMatriceFlux = refMatriceFluxRepository.save(refMatriceFlux);
        return refMatriceFluxMapper.toDto(refMatriceFlux);
    }

    /**
     * Get all the refMatriceFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefMatriceFluxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefMatriceFluxes");
        return refMatriceFluxRepository.findAll(pageable)
            .map(refMatriceFluxMapper::toDto);
    }


    /**
     * Get one refMatriceFlux by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefMatriceFluxDTO> findOne(Long id) {
        log.debug("Request to get RefMatriceFlux : {}", id);
        return refMatriceFluxRepository.findById(id)
            .map(refMatriceFluxMapper::toDto);
    }

    /**
     * Delete the refMatriceFlux by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefMatriceFlux : {}", id);        refMatriceFluxRepository.deleteById(id);
    }
}

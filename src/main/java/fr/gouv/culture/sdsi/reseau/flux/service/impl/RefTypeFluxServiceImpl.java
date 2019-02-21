package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFluxService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefTypeFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefTypeFluxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefTypeFlux.
 */
@Service
@Transactional
public class RefTypeFluxServiceImpl implements RefTypeFluxService {

    private final Logger log = LoggerFactory.getLogger(RefTypeFluxServiceImpl.class);

    private final RefTypeFluxRepository refTypeFluxRepository;

    private final RefTypeFluxMapper refTypeFluxMapper;

    public RefTypeFluxServiceImpl(RefTypeFluxRepository refTypeFluxRepository, RefTypeFluxMapper refTypeFluxMapper) {
        this.refTypeFluxRepository = refTypeFluxRepository;
        this.refTypeFluxMapper = refTypeFluxMapper;
    }

    /**
     * Save a refTypeFlux.
     *
     * @param refTypeFluxDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefTypeFluxDTO save(RefTypeFluxDTO refTypeFluxDTO) {
        log.debug("Request to save RefTypeFlux : {}", refTypeFluxDTO);
        RefTypeFlux refTypeFlux = refTypeFluxMapper.toEntity(refTypeFluxDTO);
        refTypeFlux = refTypeFluxRepository.save(refTypeFlux);
        return refTypeFluxMapper.toDto(refTypeFlux);
    }

    /**
     * Get all the refTypeFluxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefTypeFluxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefTypeFluxes");
        return refTypeFluxRepository.findAll(pageable)
            .map(refTypeFluxMapper::toDto);
    }


    /**
     * Get one refTypeFlux by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefTypeFluxDTO> findOne(Long id) {
        log.debug("Request to get RefTypeFlux : {}", id);
        return refTypeFluxRepository.findById(id)
            .map(refTypeFluxMapper::toDto);
    }

    /**
     * Delete the refTypeFlux by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefTypeFlux : {}", id);        refTypeFluxRepository.deleteById(id);
    }
}

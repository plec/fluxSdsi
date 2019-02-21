package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefVlanService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefVlan;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefVlanRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefVlanDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefVlanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefVlan.
 */
@Service
@Transactional
public class RefVlanServiceImpl implements RefVlanService {

    private final Logger log = LoggerFactory.getLogger(RefVlanServiceImpl.class);

    private final RefVlanRepository refVlanRepository;

    private final RefVlanMapper refVlanMapper;

    public RefVlanServiceImpl(RefVlanRepository refVlanRepository, RefVlanMapper refVlanMapper) {
        this.refVlanRepository = refVlanRepository;
        this.refVlanMapper = refVlanMapper;
    }

    /**
     * Save a refVlan.
     *
     * @param refVlanDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefVlanDTO save(RefVlanDTO refVlanDTO) {
        log.debug("Request to save RefVlan : {}", refVlanDTO);
        RefVlan refVlan = refVlanMapper.toEntity(refVlanDTO);
        refVlan = refVlanRepository.save(refVlan);
        return refVlanMapper.toDto(refVlan);
    }

    /**
     * Get all the refVlans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefVlanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefVlans");
        return refVlanRepository.findAll(pageable)
            .map(refVlanMapper::toDto);
    }


    /**
     * Get one refVlan by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefVlanDTO> findOne(Long id) {
        log.debug("Request to get RefVlan : {}", id);
        return refVlanRepository.findById(id)
            .map(refVlanMapper::toDto);
    }

    /**
     * Delete the refVlan by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefVlan : {}", id);        refVlanRepository.deleteById(id);
    }
}

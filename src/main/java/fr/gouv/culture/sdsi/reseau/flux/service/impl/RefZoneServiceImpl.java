package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefZoneService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefZone;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefZoneRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefZoneDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefZoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefZone.
 */
@Service
@Transactional
public class RefZoneServiceImpl implements RefZoneService {

    private final Logger log = LoggerFactory.getLogger(RefZoneServiceImpl.class);

    private final RefZoneRepository refZoneRepository;

    private final RefZoneMapper refZoneMapper;

    public RefZoneServiceImpl(RefZoneRepository refZoneRepository, RefZoneMapper refZoneMapper) {
        this.refZoneRepository = refZoneRepository;
        this.refZoneMapper = refZoneMapper;
    }

    /**
     * Save a refZone.
     *
     * @param refZoneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefZoneDTO save(RefZoneDTO refZoneDTO) {
        log.debug("Request to save RefZone : {}", refZoneDTO);
        RefZone refZone = refZoneMapper.toEntity(refZoneDTO);
        refZone = refZoneRepository.save(refZone);
        return refZoneMapper.toDto(refZone);
    }

    /**
     * Get all the refZones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefZoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefZones");
        return refZoneRepository.findAll(pageable)
            .map(refZoneMapper::toDto);
    }


    /**
     * Get one refZone by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefZoneDTO> findOne(Long id) {
        log.debug("Request to get RefZone : {}", id);
        return refZoneRepository.findById(id)
            .map(refZoneMapper::toDto);
    }

    /**
     * Delete the refZone by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefZone : {}", id);        refZoneRepository.deleteById(id);
    }
}

package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefZoneService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefZone;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefZoneRepository;
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

    public RefZoneServiceImpl(RefZoneRepository refZoneRepository) {
        this.refZoneRepository = refZoneRepository;
    }

    /**
     * Save a refZone.
     *
     * @param refZone the entity to save
     * @return the persisted entity
     */
    @Override
    public RefZone save(RefZone refZone) {
        log.debug("Request to save RefZone : {}", refZone);
        return refZoneRepository.save(refZone);
    }

    /**
     * Get all the refZones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefZone> findAll(Pageable pageable) {
        log.debug("Request to get all RefZones");
        return refZoneRepository.findAll(pageable);
    }


    /**
     * Get one refZone by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefZone> findOne(Long id) {
        log.debug("Request to get RefZone : {}", id);
        return refZoneRepository.findById(id);
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

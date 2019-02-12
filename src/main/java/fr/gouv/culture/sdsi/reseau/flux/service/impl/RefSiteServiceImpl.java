package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefSiteService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefSite;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefSiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefSite.
 */
@Service
@Transactional
public class RefSiteServiceImpl implements RefSiteService {

    private final Logger log = LoggerFactory.getLogger(RefSiteServiceImpl.class);

    private final RefSiteRepository refSiteRepository;

    public RefSiteServiceImpl(RefSiteRepository refSiteRepository) {
        this.refSiteRepository = refSiteRepository;
    }

    /**
     * Save a refSite.
     *
     * @param refSite the entity to save
     * @return the persisted entity
     */
    @Override
    public RefSite save(RefSite refSite) {
        log.debug("Request to save RefSite : {}", refSite);
        return refSiteRepository.save(refSite);
    }

    /**
     * Get all the refSites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefSite> findAll(Pageable pageable) {
        log.debug("Request to get all RefSites");
        return refSiteRepository.findAll(pageable);
    }


    /**
     * Get one refSite by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefSite> findOne(Long id) {
        log.debug("Request to get RefSite : {}", id);
        return refSiteRepository.findById(id);
    }

    /**
     * Delete the refSite by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefSite : {}", id);        refSiteRepository.deleteById(id);
    }
}

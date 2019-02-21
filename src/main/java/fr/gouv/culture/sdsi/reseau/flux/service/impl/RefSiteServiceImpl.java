package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefSiteService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefSite;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefSiteRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefSiteDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefSiteMapper;
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

    private final RefSiteMapper refSiteMapper;

    public RefSiteServiceImpl(RefSiteRepository refSiteRepository, RefSiteMapper refSiteMapper) {
        this.refSiteRepository = refSiteRepository;
        this.refSiteMapper = refSiteMapper;
    }

    /**
     * Save a refSite.
     *
     * @param refSiteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefSiteDTO save(RefSiteDTO refSiteDTO) {
        log.debug("Request to save RefSite : {}", refSiteDTO);
        RefSite refSite = refSiteMapper.toEntity(refSiteDTO);
        refSite = refSiteRepository.save(refSite);
        return refSiteMapper.toDto(refSite);
    }

    /**
     * Get all the refSites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefSiteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefSites");
        return refSiteRepository.findAll(pageable)
            .map(refSiteMapper::toDto);
    }


    /**
     * Get one refSite by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefSiteDTO> findOne(Long id) {
        log.debug("Request to get RefSite : {}", id);
        return refSiteRepository.findById(id)
            .map(refSiteMapper::toDto);
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

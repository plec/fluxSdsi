package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFonctionService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFonction;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefTypeFonctionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefTypeFonction.
 */
@Service
@Transactional
public class RefTypeFonctionServiceImpl implements RefTypeFonctionService {

    private final Logger log = LoggerFactory.getLogger(RefTypeFonctionServiceImpl.class);

    private final RefTypeFonctionRepository refTypeFonctionRepository;

    public RefTypeFonctionServiceImpl(RefTypeFonctionRepository refTypeFonctionRepository) {
        this.refTypeFonctionRepository = refTypeFonctionRepository;
    }

    /**
     * Save a refTypeFonction.
     *
     * @param refTypeFonction the entity to save
     * @return the persisted entity
     */
    @Override
    public RefTypeFonction save(RefTypeFonction refTypeFonction) {
        log.debug("Request to save RefTypeFonction : {}", refTypeFonction);
        return refTypeFonctionRepository.save(refTypeFonction);
    }

    /**
     * Get all the refTypeFonctions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefTypeFonction> findAll(Pageable pageable) {
        log.debug("Request to get all RefTypeFonctions");
        return refTypeFonctionRepository.findAll(pageable);
    }


    /**
     * Get one refTypeFonction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefTypeFonction> findOne(Long id) {
        log.debug("Request to get RefTypeFonction : {}", id);
        return refTypeFonctionRepository.findById(id);
    }

    /**
     * Delete the refTypeFonction by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefTypeFonction : {}", id);        refTypeFonctionRepository.deleteById(id);
    }
}

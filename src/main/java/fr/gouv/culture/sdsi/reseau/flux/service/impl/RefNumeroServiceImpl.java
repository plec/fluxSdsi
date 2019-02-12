package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefNumeroService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefNumero;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefNumeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefNumero.
 */
@Service
@Transactional
public class RefNumeroServiceImpl implements RefNumeroService {

    private final Logger log = LoggerFactory.getLogger(RefNumeroServiceImpl.class);

    private final RefNumeroRepository refNumeroRepository;

    public RefNumeroServiceImpl(RefNumeroRepository refNumeroRepository) {
        this.refNumeroRepository = refNumeroRepository;
    }

    /**
     * Save a refNumero.
     *
     * @param refNumero the entity to save
     * @return the persisted entity
     */
    @Override
    public RefNumero save(RefNumero refNumero) {
        log.debug("Request to save RefNumero : {}", refNumero);
        return refNumeroRepository.save(refNumero);
    }

    /**
     * Get all the refNumeros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefNumero> findAll(Pageable pageable) {
        log.debug("Request to get all RefNumeros");
        return refNumeroRepository.findAll(pageable);
    }


    /**
     * Get one refNumero by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefNumero> findOne(Long id) {
        log.debug("Request to get RefNumero : {}", id);
        return refNumeroRepository.findById(id);
    }

    /**
     * Delete the refNumero by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefNumero : {}", id);        refNumeroRepository.deleteById(id);
    }
}

package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefNumeroService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefNumero;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefNumeroRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefNumeroDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefNumeroMapper;
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

    private final RefNumeroMapper refNumeroMapper;

    public RefNumeroServiceImpl(RefNumeroRepository refNumeroRepository, RefNumeroMapper refNumeroMapper) {
        this.refNumeroRepository = refNumeroRepository;
        this.refNumeroMapper = refNumeroMapper;
    }

    /**
     * Save a refNumero.
     *
     * @param refNumeroDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefNumeroDTO save(RefNumeroDTO refNumeroDTO) {
        log.debug("Request to save RefNumero : {}", refNumeroDTO);
        RefNumero refNumero = refNumeroMapper.toEntity(refNumeroDTO);
        refNumero = refNumeroRepository.save(refNumero);
        return refNumeroMapper.toDto(refNumero);
    }

    /**
     * Get all the refNumeros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefNumeroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefNumeros");
        return refNumeroRepository.findAll(pageable)
            .map(refNumeroMapper::toDto);
    }


    /**
     * Get one refNumero by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefNumeroDTO> findOne(Long id) {
        log.debug("Request to get RefNumero : {}", id);
        return refNumeroRepository.findById(id)
            .map(refNumeroMapper::toDto);
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

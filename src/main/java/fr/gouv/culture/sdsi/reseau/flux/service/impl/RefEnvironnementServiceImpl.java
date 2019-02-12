package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefEnvironnementService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironnement;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefEnvironnementRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironnementDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefEnvironnementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefEnvironnement.
 */
@Service
@Transactional
public class RefEnvironnementServiceImpl implements RefEnvironnementService {

    private final Logger log = LoggerFactory.getLogger(RefEnvironnementServiceImpl.class);

    private final RefEnvironnementRepository refEnvironnementRepository;

    private final RefEnvironnementMapper refEnvironnementMapper;

    public RefEnvironnementServiceImpl(RefEnvironnementRepository refEnvironnementRepository, RefEnvironnementMapper refEnvironnementMapper) {
        this.refEnvironnementRepository = refEnvironnementRepository;
        this.refEnvironnementMapper = refEnvironnementMapper;
    }

    /**
     * Save a refEnvironnement.
     *
     * @param refEnvironnementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefEnvironnementDTO save(RefEnvironnementDTO refEnvironnementDTO) {
        log.debug("Request to save RefEnvironnement : {}", refEnvironnementDTO);
        RefEnvironnement refEnvironnement = refEnvironnementMapper.toEntity(refEnvironnementDTO);
        refEnvironnement = refEnvironnementRepository.save(refEnvironnement);
        return refEnvironnementMapper.toDto(refEnvironnement);
    }

    /**
     * Get all the refEnvironnements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefEnvironnementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefEnvironnements");
        return refEnvironnementRepository.findAll(pageable)
            .map(refEnvironnementMapper::toDto);
    }


    /**
     * Get one refEnvironnement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefEnvironnementDTO> findOne(Long id) {
        log.debug("Request to get RefEnvironnement : {}", id);
        return refEnvironnementRepository.findById(id)
            .map(refEnvironnementMapper::toDto);
    }

    /**
     * Delete the refEnvironnement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefEnvironnement : {}", id);        refEnvironnementRepository.deleteById(id);
    }
}

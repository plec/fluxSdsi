package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefEnvironementService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironement;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefEnvironementRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironementDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefEnvironementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefEnvironement.
 */
@Service
@Transactional
public class RefEnvironementServiceImpl implements RefEnvironementService {

    private final Logger log = LoggerFactory.getLogger(RefEnvironementServiceImpl.class);

    private final RefEnvironementRepository refEnvironementRepository;

    private final RefEnvironementMapper refEnvironementMapper;

    public RefEnvironementServiceImpl(RefEnvironementRepository refEnvironementRepository, RefEnvironementMapper refEnvironementMapper) {
        this.refEnvironementRepository = refEnvironementRepository;
        this.refEnvironementMapper = refEnvironementMapper;
    }

    /**
     * Save a refEnvironement.
     *
     * @param refEnvironementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefEnvironementDTO save(RefEnvironementDTO refEnvironementDTO) {
        log.debug("Request to save RefEnvironement : {}", refEnvironementDTO);
        RefEnvironement refEnvironement = refEnvironementMapper.toEntity(refEnvironementDTO);
        refEnvironement = refEnvironementRepository.save(refEnvironement);
        return refEnvironementMapper.toDto(refEnvironement);
    }

    /**
     * Get all the refEnvironements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefEnvironementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefEnvironements");
        return refEnvironementRepository.findAll(pageable)
            .map(refEnvironementMapper::toDto);
    }


    /**
     * Get one refEnvironement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefEnvironementDTO> findOne(Long id) {
        log.debug("Request to get RefEnvironement : {}", id);
        return refEnvironementRepository.findById(id)
            .map(refEnvironementMapper::toDto);
    }

    /**
     * Delete the refEnvironement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefEnvironement : {}", id);        refEnvironementRepository.deleteById(id);
    }
}

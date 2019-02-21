package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFonctionService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFonction;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefTypeFonctionRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFonctionDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefTypeFonctionMapper;
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

    private final RefTypeFonctionMapper refTypeFonctionMapper;

    public RefTypeFonctionServiceImpl(RefTypeFonctionRepository refTypeFonctionRepository, RefTypeFonctionMapper refTypeFonctionMapper) {
        this.refTypeFonctionRepository = refTypeFonctionRepository;
        this.refTypeFonctionMapper = refTypeFonctionMapper;
    }

    /**
     * Save a refTypeFonction.
     *
     * @param refTypeFonctionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefTypeFonctionDTO save(RefTypeFonctionDTO refTypeFonctionDTO) {
        log.debug("Request to save RefTypeFonction : {}", refTypeFonctionDTO);
        RefTypeFonction refTypeFonction = refTypeFonctionMapper.toEntity(refTypeFonctionDTO);
        refTypeFonction = refTypeFonctionRepository.save(refTypeFonction);
        return refTypeFonctionMapper.toDto(refTypeFonction);
    }

    /**
     * Get all the refTypeFonctions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefTypeFonctionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefTypeFonctions");
        return refTypeFonctionRepository.findAll(pageable)
            .map(refTypeFonctionMapper::toDto);
    }


    /**
     * Get one refTypeFonction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefTypeFonctionDTO> findOne(Long id) {
        log.debug("Request to get RefTypeFonction : {}", id);
        return refTypeFonctionRepository.findById(id)
            .map(refTypeFonctionMapper::toDto);
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

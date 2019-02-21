package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefFonctionService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefFonction;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefFonctionRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFonctionDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefFonctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefFonction.
 */
@Service
@Transactional
public class RefFonctionServiceImpl implements RefFonctionService {

    private final Logger log = LoggerFactory.getLogger(RefFonctionServiceImpl.class);

    private final RefFonctionRepository refFonctionRepository;

    private final RefFonctionMapper refFonctionMapper;

    public RefFonctionServiceImpl(RefFonctionRepository refFonctionRepository, RefFonctionMapper refFonctionMapper) {
        this.refFonctionRepository = refFonctionRepository;
        this.refFonctionMapper = refFonctionMapper;
    }

    /**
     * Save a refFonction.
     *
     * @param refFonctionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefFonctionDTO save(RefFonctionDTO refFonctionDTO) {
        log.debug("Request to save RefFonction : {}", refFonctionDTO);
        RefFonction refFonction = refFonctionMapper.toEntity(refFonctionDTO);
        refFonction = refFonctionRepository.save(refFonction);
        return refFonctionMapper.toDto(refFonction);
    }

    /**
     * Get all the refFonctions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefFonctionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefFonctions");
        return refFonctionRepository.findAll(pageable)
            .map(refFonctionMapper::toDto);
    }


    /**
     * Get one refFonction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefFonctionDTO> findOne(Long id) {
        log.debug("Request to get RefFonction : {}", id);
        return refFonctionRepository.findById(id)
            .map(refFonctionMapper::toDto);
    }

    /**
     * Delete the refFonction by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefFonction : {}", id);        refFonctionRepository.deleteById(id);
    }
}

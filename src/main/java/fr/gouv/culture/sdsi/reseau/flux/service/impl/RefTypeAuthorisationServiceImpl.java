package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeAuthorisationService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeAuthorisation;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefTypeAuthorisationRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeAuthorisationDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefTypeAuthorisationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefTypeAuthorisation.
 */
@Service
@Transactional
public class RefTypeAuthorisationServiceImpl implements RefTypeAuthorisationService {

    private final Logger log = LoggerFactory.getLogger(RefTypeAuthorisationServiceImpl.class);

    private final RefTypeAuthorisationRepository refTypeAuthorisationRepository;

    private final RefTypeAuthorisationMapper refTypeAuthorisationMapper;

    public RefTypeAuthorisationServiceImpl(RefTypeAuthorisationRepository refTypeAuthorisationRepository, RefTypeAuthorisationMapper refTypeAuthorisationMapper) {
        this.refTypeAuthorisationRepository = refTypeAuthorisationRepository;
        this.refTypeAuthorisationMapper = refTypeAuthorisationMapper;
    }

    /**
     * Save a refTypeAuthorisation.
     *
     * @param refTypeAuthorisationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefTypeAuthorisationDTO save(RefTypeAuthorisationDTO refTypeAuthorisationDTO) {
        log.debug("Request to save RefTypeAuthorisation : {}", refTypeAuthorisationDTO);
        RefTypeAuthorisation refTypeAuthorisation = refTypeAuthorisationMapper.toEntity(refTypeAuthorisationDTO);
        refTypeAuthorisation = refTypeAuthorisationRepository.save(refTypeAuthorisation);
        return refTypeAuthorisationMapper.toDto(refTypeAuthorisation);
    }

    /**
     * Get all the refTypeAuthorisations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefTypeAuthorisationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefTypeAuthorisations");
        return refTypeAuthorisationRepository.findAll(pageable)
            .map(refTypeAuthorisationMapper::toDto);
    }


    /**
     * Get one refTypeAuthorisation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefTypeAuthorisationDTO> findOne(Long id) {
        log.debug("Request to get RefTypeAuthorisation : {}", id);
        return refTypeAuthorisationRepository.findById(id)
            .map(refTypeAuthorisationMapper::toDto);
    }

    /**
     * Delete the refTypeAuthorisation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefTypeAuthorisation : {}", id);        refTypeAuthorisationRepository.deleteById(id);
    }
}

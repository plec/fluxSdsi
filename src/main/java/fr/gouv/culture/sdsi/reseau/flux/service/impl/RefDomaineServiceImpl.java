package fr.gouv.culture.sdsi.reseau.flux.service.impl;

import fr.gouv.culture.sdsi.reseau.flux.service.RefDomaineService;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefDomaine;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefDomaineRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefDomaineDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefDomaineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RefDomaine.
 */
@Service
@Transactional
public class RefDomaineServiceImpl implements RefDomaineService {

    private final Logger log = LoggerFactory.getLogger(RefDomaineServiceImpl.class);

    private final RefDomaineRepository refDomaineRepository;

    private final RefDomaineMapper refDomaineMapper;

    public RefDomaineServiceImpl(RefDomaineRepository refDomaineRepository, RefDomaineMapper refDomaineMapper) {
        this.refDomaineRepository = refDomaineRepository;
        this.refDomaineMapper = refDomaineMapper;
    }

    /**
     * Save a refDomaine.
     *
     * @param refDomaineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RefDomaineDTO save(RefDomaineDTO refDomaineDTO) {
        log.debug("Request to save RefDomaine : {}", refDomaineDTO);
        RefDomaine refDomaine = refDomaineMapper.toEntity(refDomaineDTO);
        refDomaine = refDomaineRepository.save(refDomaine);
        return refDomaineMapper.toDto(refDomaine);
    }

    /**
     * Get all the refDomaines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefDomaineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefDomaines");
        return refDomaineRepository.findAll(pageable)
            .map(refDomaineMapper::toDto);
    }


    /**
     * Get one refDomaine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RefDomaineDTO> findOne(Long id) {
        log.debug("Request to get RefDomaine : {}", id);
        return refDomaineRepository.findById(id)
            .map(refDomaineMapper::toDto);
    }

    /**
     * Delete the refDomaine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefDomaine : {}", id);        refDomaineRepository.deleteById(id);
    }
}

package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeAuthorisationService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeAuthorisationDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RefTypeAuthorisation.
 */
@RestController
@RequestMapping("/api")
public class RefTypeAuthorisationResource {

    private final Logger log = LoggerFactory.getLogger(RefTypeAuthorisationResource.class);

    private static final String ENTITY_NAME = "refTypeAuthorisation";

    private final RefTypeAuthorisationService refTypeAuthorisationService;

    public RefTypeAuthorisationResource(RefTypeAuthorisationService refTypeAuthorisationService) {
        this.refTypeAuthorisationService = refTypeAuthorisationService;
    }

    /**
     * POST  /ref-type-authorisations : Create a new refTypeAuthorisation.
     *
     * @param refTypeAuthorisationDTO the refTypeAuthorisationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refTypeAuthorisationDTO, or with status 400 (Bad Request) if the refTypeAuthorisation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-type-authorisations")
    public ResponseEntity<RefTypeAuthorisationDTO> createRefTypeAuthorisation(@Valid @RequestBody RefTypeAuthorisationDTO refTypeAuthorisationDTO) throws URISyntaxException {
        log.debug("REST request to save RefTypeAuthorisation : {}", refTypeAuthorisationDTO);
        if (refTypeAuthorisationDTO.getId() != null) {
            throw new BadRequestAlertException("A new refTypeAuthorisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefTypeAuthorisationDTO result = refTypeAuthorisationService.save(refTypeAuthorisationDTO);
        return ResponseEntity.created(new URI("/api/ref-type-authorisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-type-authorisations : Updates an existing refTypeAuthorisation.
     *
     * @param refTypeAuthorisationDTO the refTypeAuthorisationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refTypeAuthorisationDTO,
     * or with status 400 (Bad Request) if the refTypeAuthorisationDTO is not valid,
     * or with status 500 (Internal Server Error) if the refTypeAuthorisationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-type-authorisations")
    public ResponseEntity<RefTypeAuthorisationDTO> updateRefTypeAuthorisation(@Valid @RequestBody RefTypeAuthorisationDTO refTypeAuthorisationDTO) throws URISyntaxException {
        log.debug("REST request to update RefTypeAuthorisation : {}", refTypeAuthorisationDTO);
        if (refTypeAuthorisationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefTypeAuthorisationDTO result = refTypeAuthorisationService.save(refTypeAuthorisationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refTypeAuthorisationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-type-authorisations : get all the refTypeAuthorisations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refTypeAuthorisations in body
     */
    @GetMapping("/ref-type-authorisations")
    public ResponseEntity<List<RefTypeAuthorisationDTO>> getAllRefTypeAuthorisations(Pageable pageable) {
        log.debug("REST request to get a page of RefTypeAuthorisations");
        Page<RefTypeAuthorisationDTO> page = refTypeAuthorisationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-type-authorisations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-type-authorisations/:id : get the "id" refTypeAuthorisation.
     *
     * @param id the id of the refTypeAuthorisationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refTypeAuthorisationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-type-authorisations/{id}")
    public ResponseEntity<RefTypeAuthorisationDTO> getRefTypeAuthorisation(@PathVariable Long id) {
        log.debug("REST request to get RefTypeAuthorisation : {}", id);
        Optional<RefTypeAuthorisationDTO> refTypeAuthorisationDTO = refTypeAuthorisationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refTypeAuthorisationDTO);
    }

    /**
     * DELETE  /ref-type-authorisations/:id : delete the "id" refTypeAuthorisation.
     *
     * @param id the id of the refTypeAuthorisationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-type-authorisations/{id}")
    public ResponseEntity<Void> deleteRefTypeAuthorisation(@PathVariable Long id) {
        log.debug("REST request to delete RefTypeAuthorisation : {}", id);
        refTypeAuthorisationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

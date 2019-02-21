package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefDomaineService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefDomaineDTO;
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
 * REST controller for managing RefDomaine.
 */
@RestController
@RequestMapping("/api")
public class RefDomaineResource {

    private final Logger log = LoggerFactory.getLogger(RefDomaineResource.class);

    private static final String ENTITY_NAME = "refDomaine";

    private final RefDomaineService refDomaineService;

    public RefDomaineResource(RefDomaineService refDomaineService) {
        this.refDomaineService = refDomaineService;
    }

    /**
     * POST  /ref-domaines : Create a new refDomaine.
     *
     * @param refDomaineDTO the refDomaineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refDomaineDTO, or with status 400 (Bad Request) if the refDomaine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-domaines")
    public ResponseEntity<RefDomaineDTO> createRefDomaine(@Valid @RequestBody RefDomaineDTO refDomaineDTO) throws URISyntaxException {
        log.debug("REST request to save RefDomaine : {}", refDomaineDTO);
        if (refDomaineDTO.getId() != null) {
            throw new BadRequestAlertException("A new refDomaine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefDomaineDTO result = refDomaineService.save(refDomaineDTO);
        return ResponseEntity.created(new URI("/api/ref-domaines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-domaines : Updates an existing refDomaine.
     *
     * @param refDomaineDTO the refDomaineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refDomaineDTO,
     * or with status 400 (Bad Request) if the refDomaineDTO is not valid,
     * or with status 500 (Internal Server Error) if the refDomaineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-domaines")
    public ResponseEntity<RefDomaineDTO> updateRefDomaine(@Valid @RequestBody RefDomaineDTO refDomaineDTO) throws URISyntaxException {
        log.debug("REST request to update RefDomaine : {}", refDomaineDTO);
        if (refDomaineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefDomaineDTO result = refDomaineService.save(refDomaineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refDomaineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-domaines : get all the refDomaines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refDomaines in body
     */
    @GetMapping("/ref-domaines")
    public ResponseEntity<List<RefDomaineDTO>> getAllRefDomaines(Pageable pageable) {
        log.debug("REST request to get a page of RefDomaines");
        Page<RefDomaineDTO> page = refDomaineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-domaines");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-domaines/:id : get the "id" refDomaine.
     *
     * @param id the id of the refDomaineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refDomaineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-domaines/{id}")
    public ResponseEntity<RefDomaineDTO> getRefDomaine(@PathVariable Long id) {
        log.debug("REST request to get RefDomaine : {}", id);
        Optional<RefDomaineDTO> refDomaineDTO = refDomaineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refDomaineDTO);
    }

    /**
     * DELETE  /ref-domaines/:id : delete the "id" refDomaine.
     *
     * @param id the id of the refDomaineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-domaines/{id}")
    public ResponseEntity<Void> deleteRefDomaine(@PathVariable Long id) {
        log.debug("REST request to delete RefDomaine : {}", id);
        refDomaineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

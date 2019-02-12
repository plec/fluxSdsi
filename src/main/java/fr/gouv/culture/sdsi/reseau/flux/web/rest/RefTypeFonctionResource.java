package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFonctionService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFonctionDTO;
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
 * REST controller for managing RefTypeFonction.
 */
@RestController
@RequestMapping("/api")
public class RefTypeFonctionResource {

    private final Logger log = LoggerFactory.getLogger(RefTypeFonctionResource.class);

    private static final String ENTITY_NAME = "refTypeFonction";

    private final RefTypeFonctionService refTypeFonctionService;

    public RefTypeFonctionResource(RefTypeFonctionService refTypeFonctionService) {
        this.refTypeFonctionService = refTypeFonctionService;
    }

    /**
     * POST  /ref-type-fonctions : Create a new refTypeFonction.
     *
     * @param refTypeFonctionDTO the refTypeFonctionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refTypeFonctionDTO, or with status 400 (Bad Request) if the refTypeFonction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-type-fonctions")
    public ResponseEntity<RefTypeFonctionDTO> createRefTypeFonction(@Valid @RequestBody RefTypeFonctionDTO refTypeFonctionDTO) throws URISyntaxException {
        log.debug("REST request to save RefTypeFonction : {}", refTypeFonctionDTO);
        if (refTypeFonctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new refTypeFonction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefTypeFonctionDTO result = refTypeFonctionService.save(refTypeFonctionDTO);
        return ResponseEntity.created(new URI("/api/ref-type-fonctions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-type-fonctions : Updates an existing refTypeFonction.
     *
     * @param refTypeFonctionDTO the refTypeFonctionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refTypeFonctionDTO,
     * or with status 400 (Bad Request) if the refTypeFonctionDTO is not valid,
     * or with status 500 (Internal Server Error) if the refTypeFonctionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-type-fonctions")
    public ResponseEntity<RefTypeFonctionDTO> updateRefTypeFonction(@Valid @RequestBody RefTypeFonctionDTO refTypeFonctionDTO) throws URISyntaxException {
        log.debug("REST request to update RefTypeFonction : {}", refTypeFonctionDTO);
        if (refTypeFonctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefTypeFonctionDTO result = refTypeFonctionService.save(refTypeFonctionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refTypeFonctionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-type-fonctions : get all the refTypeFonctions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refTypeFonctions in body
     */
    @GetMapping("/ref-type-fonctions")
    public ResponseEntity<List<RefTypeFonctionDTO>> getAllRefTypeFonctions(Pageable pageable) {
        log.debug("REST request to get a page of RefTypeFonctions");
        Page<RefTypeFonctionDTO> page = refTypeFonctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-type-fonctions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-type-fonctions/:id : get the "id" refTypeFonction.
     *
     * @param id the id of the refTypeFonctionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refTypeFonctionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-type-fonctions/{id}")
    public ResponseEntity<RefTypeFonctionDTO> getRefTypeFonction(@PathVariable Long id) {
        log.debug("REST request to get RefTypeFonction : {}", id);
        Optional<RefTypeFonctionDTO> refTypeFonctionDTO = refTypeFonctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refTypeFonctionDTO);
    }

    /**
     * DELETE  /ref-type-fonctions/:id : delete the "id" refTypeFonction.
     *
     * @param id the id of the refTypeFonctionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-type-fonctions/{id}")
    public ResponseEntity<Void> deleteRefTypeFonction(@PathVariable Long id) {
        log.debug("REST request to delete RefTypeFonction : {}", id);
        refTypeFonctionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFonction;
import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFonctionService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
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
     * @param refTypeFonction the refTypeFonction to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refTypeFonction, or with status 400 (Bad Request) if the refTypeFonction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-type-fonctions")
    public ResponseEntity<RefTypeFonction> createRefTypeFonction(@Valid @RequestBody RefTypeFonction refTypeFonction) throws URISyntaxException {
        log.debug("REST request to save RefTypeFonction : {}", refTypeFonction);
        if (refTypeFonction.getId() != null) {
            throw new BadRequestAlertException("A new refTypeFonction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefTypeFonction result = refTypeFonctionService.save(refTypeFonction);
        return ResponseEntity.created(new URI("/api/ref-type-fonctions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-type-fonctions : Updates an existing refTypeFonction.
     *
     * @param refTypeFonction the refTypeFonction to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refTypeFonction,
     * or with status 400 (Bad Request) if the refTypeFonction is not valid,
     * or with status 500 (Internal Server Error) if the refTypeFonction couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-type-fonctions")
    public ResponseEntity<RefTypeFonction> updateRefTypeFonction(@Valid @RequestBody RefTypeFonction refTypeFonction) throws URISyntaxException {
        log.debug("REST request to update RefTypeFonction : {}", refTypeFonction);
        if (refTypeFonction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefTypeFonction result = refTypeFonctionService.save(refTypeFonction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refTypeFonction.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-type-fonctions : get all the refTypeFonctions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refTypeFonctions in body
     */
    @GetMapping("/ref-type-fonctions")
    public ResponseEntity<List<RefTypeFonction>> getAllRefTypeFonctions(Pageable pageable) {
        log.debug("REST request to get a page of RefTypeFonctions");
        Page<RefTypeFonction> page = refTypeFonctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-type-fonctions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-type-fonctions/:id : get the "id" refTypeFonction.
     *
     * @param id the id of the refTypeFonction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refTypeFonction, or with status 404 (Not Found)
     */
    @GetMapping("/ref-type-fonctions/{id}")
    public ResponseEntity<RefTypeFonction> getRefTypeFonction(@PathVariable Long id) {
        log.debug("REST request to get RefTypeFonction : {}", id);
        Optional<RefTypeFonction> refTypeFonction = refTypeFonctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refTypeFonction);
    }

    /**
     * DELETE  /ref-type-fonctions/:id : delete the "id" refTypeFonction.
     *
     * @param id the id of the refTypeFonction to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-type-fonctions/{id}")
    public ResponseEntity<Void> deleteRefTypeFonction(@PathVariable Long id) {
        log.debug("REST request to delete RefTypeFonction : {}", id);
        refTypeFonctionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

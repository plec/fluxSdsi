package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefFonction;
import fr.gouv.culture.sdsi.reseau.flux.service.RefFonctionService;
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
 * REST controller for managing RefFonction.
 */
@RestController
@RequestMapping("/api")
public class RefFonctionResource {

    private final Logger log = LoggerFactory.getLogger(RefFonctionResource.class);

    private static final String ENTITY_NAME = "refFonction";

    private final RefFonctionService refFonctionService;

    public RefFonctionResource(RefFonctionService refFonctionService) {
        this.refFonctionService = refFonctionService;
    }

    /**
     * POST  /ref-fonctions : Create a new refFonction.
     *
     * @param refFonction the refFonction to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refFonction, or with status 400 (Bad Request) if the refFonction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-fonctions")
    public ResponseEntity<RefFonction> createRefFonction(@Valid @RequestBody RefFonction refFonction) throws URISyntaxException {
        log.debug("REST request to save RefFonction : {}", refFonction);
        if (refFonction.getId() != null) {
            throw new BadRequestAlertException("A new refFonction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefFonction result = refFonctionService.save(refFonction);
        return ResponseEntity.created(new URI("/api/ref-fonctions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-fonctions : Updates an existing refFonction.
     *
     * @param refFonction the refFonction to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refFonction,
     * or with status 400 (Bad Request) if the refFonction is not valid,
     * or with status 500 (Internal Server Error) if the refFonction couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-fonctions")
    public ResponseEntity<RefFonction> updateRefFonction(@Valid @RequestBody RefFonction refFonction) throws URISyntaxException {
        log.debug("REST request to update RefFonction : {}", refFonction);
        if (refFonction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefFonction result = refFonctionService.save(refFonction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refFonction.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-fonctions : get all the refFonctions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refFonctions in body
     */
    @GetMapping("/ref-fonctions")
    public ResponseEntity<List<RefFonction>> getAllRefFonctions(Pageable pageable) {
        log.debug("REST request to get a page of RefFonctions");
        Page<RefFonction> page = refFonctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-fonctions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-fonctions/:id : get the "id" refFonction.
     *
     * @param id the id of the refFonction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refFonction, or with status 404 (Not Found)
     */
    @GetMapping("/ref-fonctions/{id}")
    public ResponseEntity<RefFonction> getRefFonction(@PathVariable Long id) {
        log.debug("REST request to get RefFonction : {}", id);
        Optional<RefFonction> refFonction = refFonctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refFonction);
    }

    /**
     * DELETE  /ref-fonctions/:id : delete the "id" refFonction.
     *
     * @param id the id of the refFonction to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-fonctions/{id}")
    public ResponseEntity<Void> deleteRefFonction(@PathVariable Long id) {
        log.debug("REST request to delete RefFonction : {}", id);
        refFonctionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

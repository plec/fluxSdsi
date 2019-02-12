package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefNumero;
import fr.gouv.culture.sdsi.reseau.flux.service.RefNumeroService;
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
 * REST controller for managing RefNumero.
 */
@RestController
@RequestMapping("/api")
public class RefNumeroResource {

    private final Logger log = LoggerFactory.getLogger(RefNumeroResource.class);

    private static final String ENTITY_NAME = "refNumero";

    private final RefNumeroService refNumeroService;

    public RefNumeroResource(RefNumeroService refNumeroService) {
        this.refNumeroService = refNumeroService;
    }

    /**
     * POST  /ref-numeros : Create a new refNumero.
     *
     * @param refNumero the refNumero to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refNumero, or with status 400 (Bad Request) if the refNumero has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-numeros")
    public ResponseEntity<RefNumero> createRefNumero(@Valid @RequestBody RefNumero refNumero) throws URISyntaxException {
        log.debug("REST request to save RefNumero : {}", refNumero);
        if (refNumero.getId() != null) {
            throw new BadRequestAlertException("A new refNumero cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefNumero result = refNumeroService.save(refNumero);
        return ResponseEntity.created(new URI("/api/ref-numeros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-numeros : Updates an existing refNumero.
     *
     * @param refNumero the refNumero to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refNumero,
     * or with status 400 (Bad Request) if the refNumero is not valid,
     * or with status 500 (Internal Server Error) if the refNumero couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-numeros")
    public ResponseEntity<RefNumero> updateRefNumero(@Valid @RequestBody RefNumero refNumero) throws URISyntaxException {
        log.debug("REST request to update RefNumero : {}", refNumero);
        if (refNumero.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefNumero result = refNumeroService.save(refNumero);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refNumero.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-numeros : get all the refNumeros.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refNumeros in body
     */
    @GetMapping("/ref-numeros")
    public ResponseEntity<List<RefNumero>> getAllRefNumeros(Pageable pageable) {
        log.debug("REST request to get a page of RefNumeros");
        Page<RefNumero> page = refNumeroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-numeros");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-numeros/:id : get the "id" refNumero.
     *
     * @param id the id of the refNumero to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refNumero, or with status 404 (Not Found)
     */
    @GetMapping("/ref-numeros/{id}")
    public ResponseEntity<RefNumero> getRefNumero(@PathVariable Long id) {
        log.debug("REST request to get RefNumero : {}", id);
        Optional<RefNumero> refNumero = refNumeroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refNumero);
    }

    /**
     * DELETE  /ref-numeros/:id : delete the "id" refNumero.
     *
     * @param id the id of the refNumero to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-numeros/{id}")
    public ResponseEntity<Void> deleteRefNumero(@PathVariable Long id) {
        log.debug("REST request to delete RefNumero : {}", id);
        refNumeroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

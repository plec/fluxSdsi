package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefFlux;
import fr.gouv.culture.sdsi.reseau.flux.service.RefFluxService;
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
 * REST controller for managing RefFlux.
 */
@RestController
@RequestMapping("/api")
public class RefFluxResource {

    private final Logger log = LoggerFactory.getLogger(RefFluxResource.class);

    private static final String ENTITY_NAME = "refFlux";

    private final RefFluxService refFluxService;

    public RefFluxResource(RefFluxService refFluxService) {
        this.refFluxService = refFluxService;
    }

    /**
     * POST  /ref-fluxes : Create a new refFlux.
     *
     * @param refFlux the refFlux to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refFlux, or with status 400 (Bad Request) if the refFlux has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-fluxes")
    public ResponseEntity<RefFlux> createRefFlux(@Valid @RequestBody RefFlux refFlux) throws URISyntaxException {
        log.debug("REST request to save RefFlux : {}", refFlux);
        if (refFlux.getId() != null) {
            throw new BadRequestAlertException("A new refFlux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefFlux result = refFluxService.save(refFlux);
        return ResponseEntity.created(new URI("/api/ref-fluxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-fluxes : Updates an existing refFlux.
     *
     * @param refFlux the refFlux to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refFlux,
     * or with status 400 (Bad Request) if the refFlux is not valid,
     * or with status 500 (Internal Server Error) if the refFlux couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-fluxes")
    public ResponseEntity<RefFlux> updateRefFlux(@Valid @RequestBody RefFlux refFlux) throws URISyntaxException {
        log.debug("REST request to update RefFlux : {}", refFlux);
        if (refFlux.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefFlux result = refFluxService.save(refFlux);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refFlux.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-fluxes : get all the refFluxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refFluxes in body
     */
    @GetMapping("/ref-fluxes")
    public ResponseEntity<List<RefFlux>> getAllRefFluxes(Pageable pageable) {
        log.debug("REST request to get a page of RefFluxes");
        Page<RefFlux> page = refFluxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-fluxes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-fluxes/:id : get the "id" refFlux.
     *
     * @param id the id of the refFlux to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refFlux, or with status 404 (Not Found)
     */
    @GetMapping("/ref-fluxes/{id}")
    public ResponseEntity<RefFlux> getRefFlux(@PathVariable Long id) {
        log.debug("REST request to get RefFlux : {}", id);
        Optional<RefFlux> refFlux = refFluxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refFlux);
    }

    /**
     * DELETE  /ref-fluxes/:id : delete the "id" refFlux.
     *
     * @param id the id of the refFlux to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-fluxes/{id}")
    public ResponseEntity<Void> deleteRefFlux(@PathVariable Long id) {
        log.debug("REST request to delete RefFlux : {}", id);
        refFluxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

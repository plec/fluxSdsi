package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.domain.RefSite;
import fr.gouv.culture.sdsi.reseau.flux.service.RefSiteService;
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
 * REST controller for managing RefSite.
 */
@RestController
@RequestMapping("/api")
public class RefSiteResource {

    private final Logger log = LoggerFactory.getLogger(RefSiteResource.class);

    private static final String ENTITY_NAME = "refSite";

    private final RefSiteService refSiteService;

    public RefSiteResource(RefSiteService refSiteService) {
        this.refSiteService = refSiteService;
    }

    /**
     * POST  /ref-sites : Create a new refSite.
     *
     * @param refSite the refSite to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refSite, or with status 400 (Bad Request) if the refSite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-sites")
    public ResponseEntity<RefSite> createRefSite(@Valid @RequestBody RefSite refSite) throws URISyntaxException {
        log.debug("REST request to save RefSite : {}", refSite);
        if (refSite.getId() != null) {
            throw new BadRequestAlertException("A new refSite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefSite result = refSiteService.save(refSite);
        return ResponseEntity.created(new URI("/api/ref-sites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-sites : Updates an existing refSite.
     *
     * @param refSite the refSite to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refSite,
     * or with status 400 (Bad Request) if the refSite is not valid,
     * or with status 500 (Internal Server Error) if the refSite couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-sites")
    public ResponseEntity<RefSite> updateRefSite(@Valid @RequestBody RefSite refSite) throws URISyntaxException {
        log.debug("REST request to update RefSite : {}", refSite);
        if (refSite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefSite result = refSiteService.save(refSite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refSite.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-sites : get all the refSites.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refSites in body
     */
    @GetMapping("/ref-sites")
    public ResponseEntity<List<RefSite>> getAllRefSites(Pageable pageable) {
        log.debug("REST request to get a page of RefSites");
        Page<RefSite> page = refSiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-sites");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-sites/:id : get the "id" refSite.
     *
     * @param id the id of the refSite to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refSite, or with status 404 (Not Found)
     */
    @GetMapping("/ref-sites/{id}")
    public ResponseEntity<RefSite> getRefSite(@PathVariable Long id) {
        log.debug("REST request to get RefSite : {}", id);
        Optional<RefSite> refSite = refSiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refSite);
    }

    /**
     * DELETE  /ref-sites/:id : delete the "id" refSite.
     *
     * @param id the id of the refSite to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-sites/{id}")
    public ResponseEntity<Void> deleteRefSite(@PathVariable Long id) {
        log.debug("REST request to delete RefSite : {}", id);
        refSiteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

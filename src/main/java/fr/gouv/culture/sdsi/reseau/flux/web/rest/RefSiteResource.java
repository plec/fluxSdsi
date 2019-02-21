package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefSiteService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefSiteDTO;
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
     * @param refSiteDTO the refSiteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refSiteDTO, or with status 400 (Bad Request) if the refSite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-sites")
    public ResponseEntity<RefSiteDTO> createRefSite(@Valid @RequestBody RefSiteDTO refSiteDTO) throws URISyntaxException {
        log.debug("REST request to save RefSite : {}", refSiteDTO);
        if (refSiteDTO.getId() != null) {
            throw new BadRequestAlertException("A new refSite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefSiteDTO result = refSiteService.save(refSiteDTO);
        return ResponseEntity.created(new URI("/api/ref-sites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-sites : Updates an existing refSite.
     *
     * @param refSiteDTO the refSiteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refSiteDTO,
     * or with status 400 (Bad Request) if the refSiteDTO is not valid,
     * or with status 500 (Internal Server Error) if the refSiteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-sites")
    public ResponseEntity<RefSiteDTO> updateRefSite(@Valid @RequestBody RefSiteDTO refSiteDTO) throws URISyntaxException {
        log.debug("REST request to update RefSite : {}", refSiteDTO);
        if (refSiteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefSiteDTO result = refSiteService.save(refSiteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refSiteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-sites : get all the refSites.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refSites in body
     */
    @GetMapping("/ref-sites")
    public ResponseEntity<List<RefSiteDTO>> getAllRefSites(Pageable pageable) {
        log.debug("REST request to get a page of RefSites");
        Page<RefSiteDTO> page = refSiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-sites");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-sites/:id : get the "id" refSite.
     *
     * @param id the id of the refSiteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refSiteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-sites/{id}")
    public ResponseEntity<RefSiteDTO> getRefSite(@PathVariable Long id) {
        log.debug("REST request to get RefSite : {}", id);
        Optional<RefSiteDTO> refSiteDTO = refSiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refSiteDTO);
    }

    /**
     * DELETE  /ref-sites/:id : delete the "id" refSite.
     *
     * @param id the id of the refSiteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-sites/{id}")
    public ResponseEntity<Void> deleteRefSite(@PathVariable Long id) {
        log.debug("REST request to delete RefSite : {}", id);
        refSiteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

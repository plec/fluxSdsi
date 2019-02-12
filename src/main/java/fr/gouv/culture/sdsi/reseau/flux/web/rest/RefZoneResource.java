package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefZoneService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefZoneDTO;
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
 * REST controller for managing RefZone.
 */
@RestController
@RequestMapping("/api")
public class RefZoneResource {

    private final Logger log = LoggerFactory.getLogger(RefZoneResource.class);

    private static final String ENTITY_NAME = "refZone";

    private final RefZoneService refZoneService;

    public RefZoneResource(RefZoneService refZoneService) {
        this.refZoneService = refZoneService;
    }

    /**
     * POST  /ref-zones : Create a new refZone.
     *
     * @param refZoneDTO the refZoneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refZoneDTO, or with status 400 (Bad Request) if the refZone has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-zones")
    public ResponseEntity<RefZoneDTO> createRefZone(@Valid @RequestBody RefZoneDTO refZoneDTO) throws URISyntaxException {
        log.debug("REST request to save RefZone : {}", refZoneDTO);
        if (refZoneDTO.getId() != null) {
            throw new BadRequestAlertException("A new refZone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefZoneDTO result = refZoneService.save(refZoneDTO);
        return ResponseEntity.created(new URI("/api/ref-zones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-zones : Updates an existing refZone.
     *
     * @param refZoneDTO the refZoneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refZoneDTO,
     * or with status 400 (Bad Request) if the refZoneDTO is not valid,
     * or with status 500 (Internal Server Error) if the refZoneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-zones")
    public ResponseEntity<RefZoneDTO> updateRefZone(@Valid @RequestBody RefZoneDTO refZoneDTO) throws URISyntaxException {
        log.debug("REST request to update RefZone : {}", refZoneDTO);
        if (refZoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefZoneDTO result = refZoneService.save(refZoneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refZoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-zones : get all the refZones.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refZones in body
     */
    @GetMapping("/ref-zones")
    public ResponseEntity<List<RefZoneDTO>> getAllRefZones(Pageable pageable) {
        log.debug("REST request to get a page of RefZones");
        Page<RefZoneDTO> page = refZoneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-zones");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-zones/:id : get the "id" refZone.
     *
     * @param id the id of the refZoneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refZoneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-zones/{id}")
    public ResponseEntity<RefZoneDTO> getRefZone(@PathVariable Long id) {
        log.debug("REST request to get RefZone : {}", id);
        Optional<RefZoneDTO> refZoneDTO = refZoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refZoneDTO);
    }

    /**
     * DELETE  /ref-zones/:id : delete the "id" refZone.
     *
     * @param id the id of the refZoneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-zones/{id}")
    public ResponseEntity<Void> deleteRefZone(@PathVariable Long id) {
        log.debug("REST request to delete RefZone : {}", id);
        refZoneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

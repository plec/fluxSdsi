package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefVlanService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefVlanDTO;
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
 * REST controller for managing RefVlan.
 */
@RestController
@RequestMapping("/api")
public class RefVlanResource {

    private final Logger log = LoggerFactory.getLogger(RefVlanResource.class);

    private static final String ENTITY_NAME = "refVlan";

    private final RefVlanService refVlanService;

    public RefVlanResource(RefVlanService refVlanService) {
        this.refVlanService = refVlanService;
    }

    /**
     * POST  /ref-vlans : Create a new refVlan.
     *
     * @param refVlanDTO the refVlanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refVlanDTO, or with status 400 (Bad Request) if the refVlan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-vlans")
    public ResponseEntity<RefVlanDTO> createRefVlan(@Valid @RequestBody RefVlanDTO refVlanDTO) throws URISyntaxException {
        log.debug("REST request to save RefVlan : {}", refVlanDTO);
        if (refVlanDTO.getId() != null) {
            throw new BadRequestAlertException("A new refVlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefVlanDTO result = refVlanService.save(refVlanDTO);
        return ResponseEntity.created(new URI("/api/ref-vlans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-vlans : Updates an existing refVlan.
     *
     * @param refVlanDTO the refVlanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refVlanDTO,
     * or with status 400 (Bad Request) if the refVlanDTO is not valid,
     * or with status 500 (Internal Server Error) if the refVlanDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-vlans")
    public ResponseEntity<RefVlanDTO> updateRefVlan(@Valid @RequestBody RefVlanDTO refVlanDTO) throws URISyntaxException {
        log.debug("REST request to update RefVlan : {}", refVlanDTO);
        if (refVlanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefVlanDTO result = refVlanService.save(refVlanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refVlanDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-vlans : get all the refVlans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refVlans in body
     */
    @GetMapping("/ref-vlans")
    public ResponseEntity<List<RefVlanDTO>> getAllRefVlans(Pageable pageable) {
        log.debug("REST request to get a page of RefVlans");
        Page<RefVlanDTO> page = refVlanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-vlans");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-vlans/:id : get the "id" refVlan.
     *
     * @param id the id of the refVlanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refVlanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-vlans/{id}")
    public ResponseEntity<RefVlanDTO> getRefVlan(@PathVariable Long id) {
        log.debug("REST request to get RefVlan : {}", id);
        Optional<RefVlanDTO> refVlanDTO = refVlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refVlanDTO);
    }

    /**
     * DELETE  /ref-vlans/:id : delete the "id" refVlan.
     *
     * @param id the id of the refVlanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-vlans/{id}")
    public ResponseEntity<Void> deleteRefVlan(@PathVariable Long id) {
        log.debug("REST request to delete RefVlan : {}", id);
        refVlanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

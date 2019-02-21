package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefEnvironementService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironementDTO;
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
 * REST controller for managing RefEnvironement.
 */
@RestController
@RequestMapping("/api")
public class RefEnvironementResource {

    private final Logger log = LoggerFactory.getLogger(RefEnvironementResource.class);

    private static final String ENTITY_NAME = "refEnvironement";

    private final RefEnvironementService refEnvironementService;

    public RefEnvironementResource(RefEnvironementService refEnvironementService) {
        this.refEnvironementService = refEnvironementService;
    }

    /**
     * POST  /ref-environements : Create a new refEnvironement.
     *
     * @param refEnvironementDTO the refEnvironementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refEnvironementDTO, or with status 400 (Bad Request) if the refEnvironement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-environements")
    public ResponseEntity<RefEnvironementDTO> createRefEnvironement(@Valid @RequestBody RefEnvironementDTO refEnvironementDTO) throws URISyntaxException {
        log.debug("REST request to save RefEnvironement : {}", refEnvironementDTO);
        if (refEnvironementDTO.getId() != null) {
            throw new BadRequestAlertException("A new refEnvironement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefEnvironementDTO result = refEnvironementService.save(refEnvironementDTO);
        return ResponseEntity.created(new URI("/api/ref-environements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-environements : Updates an existing refEnvironement.
     *
     * @param refEnvironementDTO the refEnvironementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refEnvironementDTO,
     * or with status 400 (Bad Request) if the refEnvironementDTO is not valid,
     * or with status 500 (Internal Server Error) if the refEnvironementDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-environements")
    public ResponseEntity<RefEnvironementDTO> updateRefEnvironement(@Valid @RequestBody RefEnvironementDTO refEnvironementDTO) throws URISyntaxException {
        log.debug("REST request to update RefEnvironement : {}", refEnvironementDTO);
        if (refEnvironementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefEnvironementDTO result = refEnvironementService.save(refEnvironementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refEnvironementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-environements : get all the refEnvironements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refEnvironements in body
     */
    @GetMapping("/ref-environements")
    public ResponseEntity<List<RefEnvironementDTO>> getAllRefEnvironements(Pageable pageable) {
        log.debug("REST request to get a page of RefEnvironements");
        Page<RefEnvironementDTO> page = refEnvironementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-environements");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-environements/:id : get the "id" refEnvironement.
     *
     * @param id the id of the refEnvironementDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refEnvironementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-environements/{id}")
    public ResponseEntity<RefEnvironementDTO> getRefEnvironement(@PathVariable Long id) {
        log.debug("REST request to get RefEnvironement : {}", id);
        Optional<RefEnvironementDTO> refEnvironementDTO = refEnvironementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refEnvironementDTO);
    }

    /**
     * DELETE  /ref-environements/:id : delete the "id" refEnvironement.
     *
     * @param id the id of the refEnvironementDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-environements/{id}")
    public ResponseEntity<Void> deleteRefEnvironement(@PathVariable Long id) {
        log.debug("REST request to delete RefEnvironement : {}", id);
        refEnvironementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

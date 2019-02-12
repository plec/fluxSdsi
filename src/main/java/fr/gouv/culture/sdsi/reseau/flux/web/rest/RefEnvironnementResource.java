package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefEnvironnementService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironnementDTO;
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
 * REST controller for managing RefEnvironnement.
 */
@RestController
@RequestMapping("/api")
public class RefEnvironnementResource {

    private final Logger log = LoggerFactory.getLogger(RefEnvironnementResource.class);

    private static final String ENTITY_NAME = "refEnvironnement";

    private final RefEnvironnementService refEnvironnementService;

    public RefEnvironnementResource(RefEnvironnementService refEnvironnementService) {
        this.refEnvironnementService = refEnvironnementService;
    }

    /**
     * POST  /ref-environnements : Create a new refEnvironnement.
     *
     * @param refEnvironnementDTO the refEnvironnementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refEnvironnementDTO, or with status 400 (Bad Request) if the refEnvironnement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-environnements")
    public ResponseEntity<RefEnvironnementDTO> createRefEnvironnement(@Valid @RequestBody RefEnvironnementDTO refEnvironnementDTO) throws URISyntaxException {
        log.debug("REST request to save RefEnvironnement : {}", refEnvironnementDTO);
        if (refEnvironnementDTO.getId() != null) {
            throw new BadRequestAlertException("A new refEnvironnement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefEnvironnementDTO result = refEnvironnementService.save(refEnvironnementDTO);
        return ResponseEntity.created(new URI("/api/ref-environnements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-environnements : Updates an existing refEnvironnement.
     *
     * @param refEnvironnementDTO the refEnvironnementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refEnvironnementDTO,
     * or with status 400 (Bad Request) if the refEnvironnementDTO is not valid,
     * or with status 500 (Internal Server Error) if the refEnvironnementDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-environnements")
    public ResponseEntity<RefEnvironnementDTO> updateRefEnvironnement(@Valid @RequestBody RefEnvironnementDTO refEnvironnementDTO) throws URISyntaxException {
        log.debug("REST request to update RefEnvironnement : {}", refEnvironnementDTO);
        if (refEnvironnementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefEnvironnementDTO result = refEnvironnementService.save(refEnvironnementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refEnvironnementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-environnements : get all the refEnvironnements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refEnvironnements in body
     */
    @GetMapping("/ref-environnements")
    public ResponseEntity<List<RefEnvironnementDTO>> getAllRefEnvironnements(Pageable pageable) {
        log.debug("REST request to get a page of RefEnvironnements");
        Page<RefEnvironnementDTO> page = refEnvironnementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-environnements");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-environnements/:id : get the "id" refEnvironnement.
     *
     * @param id the id of the refEnvironnementDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refEnvironnementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-environnements/{id}")
    public ResponseEntity<RefEnvironnementDTO> getRefEnvironnement(@PathVariable Long id) {
        log.debug("REST request to get RefEnvironnement : {}", id);
        Optional<RefEnvironnementDTO> refEnvironnementDTO = refEnvironnementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refEnvironnementDTO);
    }

    /**
     * DELETE  /ref-environnements/:id : delete the "id" refEnvironnement.
     *
     * @param id the id of the refEnvironnementDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-environnements/{id}")
    public ResponseEntity<Void> deleteRefEnvironnement(@PathVariable Long id) {
        log.debug("REST request to delete RefEnvironnement : {}", id);
        refEnvironnementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

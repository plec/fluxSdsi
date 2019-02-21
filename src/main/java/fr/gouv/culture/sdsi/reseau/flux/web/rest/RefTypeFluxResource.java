package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFluxService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFluxDTO;
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
 * REST controller for managing RefTypeFlux.
 */
@RestController
@RequestMapping("/api")
public class RefTypeFluxResource {

    private final Logger log = LoggerFactory.getLogger(RefTypeFluxResource.class);

    private static final String ENTITY_NAME = "refTypeFlux";

    private final RefTypeFluxService refTypeFluxService;

    public RefTypeFluxResource(RefTypeFluxService refTypeFluxService) {
        this.refTypeFluxService = refTypeFluxService;
    }

    /**
     * POST  /ref-type-fluxes : Create a new refTypeFlux.
     *
     * @param refTypeFluxDTO the refTypeFluxDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refTypeFluxDTO, or with status 400 (Bad Request) if the refTypeFlux has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-type-fluxes")
    public ResponseEntity<RefTypeFluxDTO> createRefTypeFlux(@Valid @RequestBody RefTypeFluxDTO refTypeFluxDTO) throws URISyntaxException {
        log.debug("REST request to save RefTypeFlux : {}", refTypeFluxDTO);
        if (refTypeFluxDTO.getId() != null) {
            throw new BadRequestAlertException("A new refTypeFlux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefTypeFluxDTO result = refTypeFluxService.save(refTypeFluxDTO);
        return ResponseEntity.created(new URI("/api/ref-type-fluxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-type-fluxes : Updates an existing refTypeFlux.
     *
     * @param refTypeFluxDTO the refTypeFluxDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refTypeFluxDTO,
     * or with status 400 (Bad Request) if the refTypeFluxDTO is not valid,
     * or with status 500 (Internal Server Error) if the refTypeFluxDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-type-fluxes")
    public ResponseEntity<RefTypeFluxDTO> updateRefTypeFlux(@Valid @RequestBody RefTypeFluxDTO refTypeFluxDTO) throws URISyntaxException {
        log.debug("REST request to update RefTypeFlux : {}", refTypeFluxDTO);
        if (refTypeFluxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefTypeFluxDTO result = refTypeFluxService.save(refTypeFluxDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refTypeFluxDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-type-fluxes : get all the refTypeFluxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refTypeFluxes in body
     */
    @GetMapping("/ref-type-fluxes")
    public ResponseEntity<List<RefTypeFluxDTO>> getAllRefTypeFluxes(Pageable pageable) {
        log.debug("REST request to get a page of RefTypeFluxes");
        Page<RefTypeFluxDTO> page = refTypeFluxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-type-fluxes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-type-fluxes/:id : get the "id" refTypeFlux.
     *
     * @param id the id of the refTypeFluxDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refTypeFluxDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-type-fluxes/{id}")
    public ResponseEntity<RefTypeFluxDTO> getRefTypeFlux(@PathVariable Long id) {
        log.debug("REST request to get RefTypeFlux : {}", id);
        Optional<RefTypeFluxDTO> refTypeFluxDTO = refTypeFluxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refTypeFluxDTO);
    }

    /**
     * DELETE  /ref-type-fluxes/:id : delete the "id" refTypeFlux.
     *
     * @param id the id of the refTypeFluxDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-type-fluxes/{id}")
    public ResponseEntity<Void> deleteRefTypeFlux(@PathVariable Long id) {
        log.debug("REST request to delete RefTypeFlux : {}", id);
        refTypeFluxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

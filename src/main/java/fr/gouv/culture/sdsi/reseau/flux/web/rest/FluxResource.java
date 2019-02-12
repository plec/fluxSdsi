package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.FluxService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.FluxDTO;
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
 * REST controller for managing Flux.
 */
@RestController
@RequestMapping("/api")
public class FluxResource {

    private final Logger log = LoggerFactory.getLogger(FluxResource.class);

    private static final String ENTITY_NAME = "flux";

    private final FluxService fluxService;

    public FluxResource(FluxService fluxService) {
        this.fluxService = fluxService;
    }

    /**
     * POST  /fluxes : Create a new flux.
     *
     * @param fluxDTO the fluxDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fluxDTO, or with status 400 (Bad Request) if the flux has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fluxes")
    public ResponseEntity<FluxDTO> createFlux(@Valid @RequestBody FluxDTO fluxDTO) throws URISyntaxException {
        log.debug("REST request to save Flux : {}", fluxDTO);
        if (fluxDTO.getId() != null) {
            throw new BadRequestAlertException("A new flux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FluxDTO result = fluxService.save(fluxDTO);
        return ResponseEntity.created(new URI("/api/fluxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fluxes : Updates an existing flux.
     *
     * @param fluxDTO the fluxDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fluxDTO,
     * or with status 400 (Bad Request) if the fluxDTO is not valid,
     * or with status 500 (Internal Server Error) if the fluxDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fluxes")
    public ResponseEntity<FluxDTO> updateFlux(@Valid @RequestBody FluxDTO fluxDTO) throws URISyntaxException {
        log.debug("REST request to update Flux : {}", fluxDTO);
        if (fluxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FluxDTO result = fluxService.save(fluxDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fluxDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fluxes : get all the fluxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fluxes in body
     */
    @GetMapping("/fluxes")
    public ResponseEntity<List<FluxDTO>> getAllFluxes(Pageable pageable) {
        log.debug("REST request to get a page of Fluxes");
        Page<FluxDTO> page = fluxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fluxes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fluxes/:id : get the "id" flux.
     *
     * @param id the id of the fluxDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fluxDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fluxes/{id}")
    public ResponseEntity<FluxDTO> getFlux(@PathVariable Long id) {
        log.debug("REST request to get Flux : {}", id);
        Optional<FluxDTO> fluxDTO = fluxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fluxDTO);
    }

    /**
     * DELETE  /fluxes/:id : delete the "id" flux.
     *
     * @param id the id of the fluxDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fluxes/{id}")
    public ResponseEntity<Void> deleteFlux(@PathVariable Long id) {
        log.debug("REST request to delete Flux : {}", id);
        fluxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

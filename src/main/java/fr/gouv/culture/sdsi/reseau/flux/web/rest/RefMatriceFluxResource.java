package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefMatriceFluxService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefMatriceFluxDTO;
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
 * REST controller for managing RefMatriceFlux.
 */
@RestController
@RequestMapping("/api")
public class RefMatriceFluxResource {

    private final Logger log = LoggerFactory.getLogger(RefMatriceFluxResource.class);

    private static final String ENTITY_NAME = "refMatriceFlux";

    private final RefMatriceFluxService refMatriceFluxService;

    public RefMatriceFluxResource(RefMatriceFluxService refMatriceFluxService) {
        this.refMatriceFluxService = refMatriceFluxService;
    }

    /**
     * POST  /ref-matrice-fluxes : Create a new refMatriceFlux.
     *
     * @param refMatriceFluxDTO the refMatriceFluxDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refMatriceFluxDTO, or with status 400 (Bad Request) if the refMatriceFlux has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-matrice-fluxes")
    public ResponseEntity<RefMatriceFluxDTO> createRefMatriceFlux(@Valid @RequestBody RefMatriceFluxDTO refMatriceFluxDTO) throws URISyntaxException {
        log.debug("REST request to save RefMatriceFlux : {}", refMatriceFluxDTO);
        if (refMatriceFluxDTO.getId() != null) {
            throw new BadRequestAlertException("A new refMatriceFlux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefMatriceFluxDTO result = refMatriceFluxService.save(refMatriceFluxDTO);
        return ResponseEntity.created(new URI("/api/ref-matrice-fluxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-matrice-fluxes : Updates an existing refMatriceFlux.
     *
     * @param refMatriceFluxDTO the refMatriceFluxDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refMatriceFluxDTO,
     * or with status 400 (Bad Request) if the refMatriceFluxDTO is not valid,
     * or with status 500 (Internal Server Error) if the refMatriceFluxDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-matrice-fluxes")
    public ResponseEntity<RefMatriceFluxDTO> updateRefMatriceFlux(@Valid @RequestBody RefMatriceFluxDTO refMatriceFluxDTO) throws URISyntaxException {
        log.debug("REST request to update RefMatriceFlux : {}", refMatriceFluxDTO);
        if (refMatriceFluxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefMatriceFluxDTO result = refMatriceFluxService.save(refMatriceFluxDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refMatriceFluxDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-matrice-fluxes : get all the refMatriceFluxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refMatriceFluxes in body
     */
    @GetMapping("/ref-matrice-fluxes")
    public ResponseEntity<List<RefMatriceFluxDTO>> getAllRefMatriceFluxes(Pageable pageable) {
        log.debug("REST request to get a page of RefMatriceFluxes");
        Page<RefMatriceFluxDTO> page = refMatriceFluxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-matrice-fluxes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-matrice-fluxes/:id : get the "id" refMatriceFlux.
     *
     * @param id the id of the refMatriceFluxDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refMatriceFluxDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-matrice-fluxes/{id}")
    public ResponseEntity<RefMatriceFluxDTO> getRefMatriceFlux(@PathVariable Long id) {
        log.debug("REST request to get RefMatriceFlux : {}", id);
        Optional<RefMatriceFluxDTO> refMatriceFluxDTO = refMatriceFluxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refMatriceFluxDTO);
    }

    /**
     * DELETE  /ref-matrice-fluxes/:id : delete the "id" refMatriceFlux.
     *
     * @param id the id of the refMatriceFluxDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-matrice-fluxes/{id}")
    public ResponseEntity<Void> deleteRefMatriceFlux(@PathVariable Long id) {
        log.debug("REST request to delete RefMatriceFlux : {}", id);
        refMatriceFluxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

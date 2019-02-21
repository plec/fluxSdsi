package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.DemandeFluxService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.DemandeFluxDTO;
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
 * REST controller for managing DemandeFlux.
 */
@RestController
@RequestMapping("/api")
public class DemandeFluxResource {

    private final Logger log = LoggerFactory.getLogger(DemandeFluxResource.class);

    private static final String ENTITY_NAME = "demandeFlux";

    private final DemandeFluxService demandeFluxService;

    public DemandeFluxResource(DemandeFluxService demandeFluxService) {
        this.demandeFluxService = demandeFluxService;
    }

    /**
     * POST  /demande-fluxes : Create a new demandeFlux.
     *
     * @param demandeFluxDTO the demandeFluxDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new demandeFluxDTO, or with status 400 (Bad Request) if the demandeFlux has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/demande-fluxes")
    public ResponseEntity<DemandeFluxDTO> createDemandeFlux(@Valid @RequestBody DemandeFluxDTO demandeFluxDTO) throws URISyntaxException {
        log.debug("REST request to save DemandeFlux : {}", demandeFluxDTO);
        if (demandeFluxDTO.getId() != null) {
            throw new BadRequestAlertException("A new demandeFlux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemandeFluxDTO result = demandeFluxService.save(demandeFluxDTO);
        return ResponseEntity.created(new URI("/api/demande-fluxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demande-fluxes : Updates an existing demandeFlux.
     *
     * @param demandeFluxDTO the demandeFluxDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated demandeFluxDTO,
     * or with status 400 (Bad Request) if the demandeFluxDTO is not valid,
     * or with status 500 (Internal Server Error) if the demandeFluxDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/demande-fluxes")
    public ResponseEntity<DemandeFluxDTO> updateDemandeFlux(@Valid @RequestBody DemandeFluxDTO demandeFluxDTO) throws URISyntaxException {
        log.debug("REST request to update DemandeFlux : {}", demandeFluxDTO);
        if (demandeFluxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemandeFluxDTO result = demandeFluxService.save(demandeFluxDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, demandeFluxDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demande-fluxes : get all the demandeFluxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of demandeFluxes in body
     */
    @GetMapping("/demande-fluxes")
    public ResponseEntity<List<DemandeFluxDTO>> getAllDemandeFluxes(Pageable pageable) {
        log.debug("REST request to get a page of DemandeFluxes");
        Page<DemandeFluxDTO> page = demandeFluxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/demande-fluxes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /demande-fluxes/:id : get the "id" demandeFlux.
     *
     * @param id the id of the demandeFluxDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the demandeFluxDTO, or with status 404 (Not Found)
     */
    @GetMapping("/demande-fluxes/{id}")
    public ResponseEntity<DemandeFluxDTO> getDemandeFlux(@PathVariable Long id) {
        log.debug("REST request to get DemandeFlux : {}", id);
        Optional<DemandeFluxDTO> demandeFluxDTO = demandeFluxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandeFluxDTO);
    }

    /**
     * DELETE  /demande-fluxes/:id : delete the "id" demandeFlux.
     *
     * @param id the id of the demandeFluxDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/demande-fluxes/{id}")
    public ResponseEntity<Void> deleteDemandeFlux(@PathVariable Long id) {
        log.debug("REST request to delete DemandeFlux : {}", id);
        demandeFluxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

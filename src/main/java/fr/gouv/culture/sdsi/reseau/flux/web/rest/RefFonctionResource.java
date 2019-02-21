package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefFonctionService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFonctionDTO;
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
 * REST controller for managing RefFonction.
 */
@RestController
@RequestMapping("/api")
public class RefFonctionResource {

    private final Logger log = LoggerFactory.getLogger(RefFonctionResource.class);

    private static final String ENTITY_NAME = "refFonction";

    private final RefFonctionService refFonctionService;

    public RefFonctionResource(RefFonctionService refFonctionService) {
        this.refFonctionService = refFonctionService;
    }

    /**
     * POST  /ref-fonctions : Create a new refFonction.
     *
     * @param refFonctionDTO the refFonctionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refFonctionDTO, or with status 400 (Bad Request) if the refFonction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-fonctions")
    public ResponseEntity<RefFonctionDTO> createRefFonction(@Valid @RequestBody RefFonctionDTO refFonctionDTO) throws URISyntaxException {
        log.debug("REST request to save RefFonction : {}", refFonctionDTO);
        if (refFonctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new refFonction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefFonctionDTO result = refFonctionService.save(refFonctionDTO);
        return ResponseEntity.created(new URI("/api/ref-fonctions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-fonctions : Updates an existing refFonction.
     *
     * @param refFonctionDTO the refFonctionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refFonctionDTO,
     * or with status 400 (Bad Request) if the refFonctionDTO is not valid,
     * or with status 500 (Internal Server Error) if the refFonctionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-fonctions")
    public ResponseEntity<RefFonctionDTO> updateRefFonction(@Valid @RequestBody RefFonctionDTO refFonctionDTO) throws URISyntaxException {
        log.debug("REST request to update RefFonction : {}", refFonctionDTO);
        if (refFonctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefFonctionDTO result = refFonctionService.save(refFonctionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refFonctionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-fonctions : get all the refFonctions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refFonctions in body
     */
    @GetMapping("/ref-fonctions")
    public ResponseEntity<List<RefFonctionDTO>> getAllRefFonctions(Pageable pageable) {
        log.debug("REST request to get a page of RefFonctions");
        Page<RefFonctionDTO> page = refFonctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-fonctions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-fonctions/:id : get the "id" refFonction.
     *
     * @param id the id of the refFonctionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refFonctionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-fonctions/{id}")
    public ResponseEntity<RefFonctionDTO> getRefFonction(@PathVariable Long id) {
        log.debug("REST request to get RefFonction : {}", id);
        Optional<RefFonctionDTO> refFonctionDTO = refFonctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refFonctionDTO);
    }

    /**
     * DELETE  /ref-fonctions/:id : delete the "id" refFonction.
     *
     * @param id the id of the refFonctionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-fonctions/{id}")
    public ResponseEntity<Void> deleteRefFonction(@PathVariable Long id) {
        log.debug("REST request to delete RefFonction : {}", id);
        refFonctionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

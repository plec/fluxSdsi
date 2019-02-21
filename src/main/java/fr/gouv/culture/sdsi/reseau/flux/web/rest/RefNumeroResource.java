package fr.gouv.culture.sdsi.reseau.flux.web.rest;
import fr.gouv.culture.sdsi.reseau.flux.service.RefNumeroService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.BadRequestAlertException;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.HeaderUtil;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.util.PaginationUtil;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefNumeroDTO;
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
 * REST controller for managing RefNumero.
 */
@RestController
@RequestMapping("/api")
public class RefNumeroResource {

    private final Logger log = LoggerFactory.getLogger(RefNumeroResource.class);

    private static final String ENTITY_NAME = "refNumero";

    private final RefNumeroService refNumeroService;

    public RefNumeroResource(RefNumeroService refNumeroService) {
        this.refNumeroService = refNumeroService;
    }

    /**
     * POST  /ref-numeros : Create a new refNumero.
     *
     * @param refNumeroDTO the refNumeroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refNumeroDTO, or with status 400 (Bad Request) if the refNumero has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-numeros")
    public ResponseEntity<RefNumeroDTO> createRefNumero(@Valid @RequestBody RefNumeroDTO refNumeroDTO) throws URISyntaxException {
        log.debug("REST request to save RefNumero : {}", refNumeroDTO);
        if (refNumeroDTO.getId() != null) {
            throw new BadRequestAlertException("A new refNumero cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefNumeroDTO result = refNumeroService.save(refNumeroDTO);
        return ResponseEntity.created(new URI("/api/ref-numeros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-numeros : Updates an existing refNumero.
     *
     * @param refNumeroDTO the refNumeroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refNumeroDTO,
     * or with status 400 (Bad Request) if the refNumeroDTO is not valid,
     * or with status 500 (Internal Server Error) if the refNumeroDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-numeros")
    public ResponseEntity<RefNumeroDTO> updateRefNumero(@Valid @RequestBody RefNumeroDTO refNumeroDTO) throws URISyntaxException {
        log.debug("REST request to update RefNumero : {}", refNumeroDTO);
        if (refNumeroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefNumeroDTO result = refNumeroService.save(refNumeroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refNumeroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-numeros : get all the refNumeros.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refNumeros in body
     */
    @GetMapping("/ref-numeros")
    public ResponseEntity<List<RefNumeroDTO>> getAllRefNumeros(Pageable pageable) {
        log.debug("REST request to get a page of RefNumeros");
        Page<RefNumeroDTO> page = refNumeroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ref-numeros");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ref-numeros/:id : get the "id" refNumero.
     *
     * @param id the id of the refNumeroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refNumeroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref-numeros/{id}")
    public ResponseEntity<RefNumeroDTO> getRefNumero(@PathVariable Long id) {
        log.debug("REST request to get RefNumero : {}", id);
        Optional<RefNumeroDTO> refNumeroDTO = refNumeroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refNumeroDTO);
    }

    /**
     * DELETE  /ref-numeros/:id : delete the "id" refNumero.
     *
     * @param id the id of the refNumeroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-numeros/{id}")
    public ResponseEntity<Void> deleteRefNumero(@PathVariable Long id) {
        log.debug("REST request to delete RefNumero : {}", id);
        refNumeroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

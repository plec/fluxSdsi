package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.Flux;
import fr.gouv.culture.sdsi.reseau.flux.repository.FluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.FluxService;
import fr.gouv.culture.sdsi.reseau.flux.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static fr.gouv.culture.sdsi.reseau.flux.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FluxResource REST controller.
 *
 * @see FluxResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class FluxResourceIntTest {

    private static final String DEFAULT_ENVIRONNEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ENVIRONNEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_IP = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_IP = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_PORT = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_IP = "AAAAAAAAAA";
    private static final String UPDATED_DEST_IP = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_PORT = "AAAAAAAAAA";
    private static final String UPDATED_DEST_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_DEST_ZONE = "BBBBBBBBBB";

    @Autowired
    private FluxRepository fluxRepository;

    @Autowired
    private FluxService fluxService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFluxMockMvc;

    private Flux flux;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FluxResource fluxResource = new FluxResource(fluxService);
        this.restFluxMockMvc = MockMvcBuilders.standaloneSetup(fluxResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Flux createEntity(EntityManager em) {
        Flux flux = new Flux()
            .environnement(DEFAULT_ENVIRONNEMENT)
            .type(DEFAULT_TYPE)
            .sourceIP(DEFAULT_SOURCE_IP)
            .sourcePort(DEFAULT_SOURCE_PORT)
            .sourceZone(DEFAULT_SOURCE_ZONE)
            .destIP(DEFAULT_DEST_IP)
            .destPort(DEFAULT_DEST_PORT)
            .destZone(DEFAULT_DEST_ZONE);
        return flux;
    }

    @Before
    public void initTest() {
        flux = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlux() throws Exception {
        int databaseSizeBeforeCreate = fluxRepository.findAll().size();

        // Create the Flux
        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isCreated());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeCreate + 1);
        Flux testFlux = fluxList.get(fluxList.size() - 1);
        assertThat(testFlux.getEnvironnement()).isEqualTo(DEFAULT_ENVIRONNEMENT);
        assertThat(testFlux.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFlux.getSourceIP()).isEqualTo(DEFAULT_SOURCE_IP);
        assertThat(testFlux.getSourcePort()).isEqualTo(DEFAULT_SOURCE_PORT);
        assertThat(testFlux.getSourceZone()).isEqualTo(DEFAULT_SOURCE_ZONE);
        assertThat(testFlux.getDestIP()).isEqualTo(DEFAULT_DEST_IP);
        assertThat(testFlux.getDestPort()).isEqualTo(DEFAULT_DEST_PORT);
        assertThat(testFlux.getDestZone()).isEqualTo(DEFAULT_DEST_ZONE);
    }

    @Test
    @Transactional
    public void createFluxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fluxRepository.findAll().size();

        // Create the Flux with an existing ID
        flux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEnvironnementIsRequired() throws Exception {
        int databaseSizeBeforeTest = fluxRepository.findAll().size();
        // set the field null
        flux.setEnvironnement(null);

        // Create the Flux, which fails.

        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fluxRepository.findAll().size();
        // set the field null
        flux.setType(null);

        // Create the Flux, which fails.

        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceIPIsRequired() throws Exception {
        int databaseSizeBeforeTest = fluxRepository.findAll().size();
        // set the field null
        flux.setSourceIP(null);

        // Create the Flux, which fails.

        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceZoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = fluxRepository.findAll().size();
        // set the field null
        flux.setSourceZone(null);

        // Create the Flux, which fails.

        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestIPIsRequired() throws Exception {
        int databaseSizeBeforeTest = fluxRepository.findAll().size();
        // set the field null
        flux.setDestIP(null);

        // Create the Flux, which fails.

        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestZoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = fluxRepository.findAll().size();
        // set the field null
        flux.setDestZone(null);

        // Create the Flux, which fails.

        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFluxes() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList
        restFluxMockMvc.perform(get("/api/fluxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flux.getId().intValue())))
            .andExpect(jsonPath("$.[*].environnement").value(hasItem(DEFAULT_ENVIRONNEMENT.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].sourceIP").value(hasItem(DEFAULT_SOURCE_IP.toString())))
            .andExpect(jsonPath("$.[*].sourcePort").value(hasItem(DEFAULT_SOURCE_PORT.toString())))
            .andExpect(jsonPath("$.[*].sourceZone").value(hasItem(DEFAULT_SOURCE_ZONE.toString())))
            .andExpect(jsonPath("$.[*].destIP").value(hasItem(DEFAULT_DEST_IP.toString())))
            .andExpect(jsonPath("$.[*].destPort").value(hasItem(DEFAULT_DEST_PORT.toString())))
            .andExpect(jsonPath("$.[*].destZone").value(hasItem(DEFAULT_DEST_ZONE.toString())));
    }
    
    @Test
    @Transactional
    public void getFlux() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get the flux
        restFluxMockMvc.perform(get("/api/fluxes/{id}", flux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(flux.getId().intValue()))
            .andExpect(jsonPath("$.environnement").value(DEFAULT_ENVIRONNEMENT.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.sourceIP").value(DEFAULT_SOURCE_IP.toString()))
            .andExpect(jsonPath("$.sourcePort").value(DEFAULT_SOURCE_PORT.toString()))
            .andExpect(jsonPath("$.sourceZone").value(DEFAULT_SOURCE_ZONE.toString()))
            .andExpect(jsonPath("$.destIP").value(DEFAULT_DEST_IP.toString()))
            .andExpect(jsonPath("$.destPort").value(DEFAULT_DEST_PORT.toString()))
            .andExpect(jsonPath("$.destZone").value(DEFAULT_DEST_ZONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFlux() throws Exception {
        // Get the flux
        restFluxMockMvc.perform(get("/api/fluxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlux() throws Exception {
        // Initialize the database
        fluxService.save(flux);

        int databaseSizeBeforeUpdate = fluxRepository.findAll().size();

        // Update the flux
        Flux updatedFlux = fluxRepository.findById(flux.getId()).get();
        // Disconnect from session so that the updates on updatedFlux are not directly saved in db
        em.detach(updatedFlux);
        updatedFlux
            .environnement(UPDATED_ENVIRONNEMENT)
            .type(UPDATED_TYPE)
            .sourceIP(UPDATED_SOURCE_IP)
            .sourcePort(UPDATED_SOURCE_PORT)
            .sourceZone(UPDATED_SOURCE_ZONE)
            .destIP(UPDATED_DEST_IP)
            .destPort(UPDATED_DEST_PORT)
            .destZone(UPDATED_DEST_ZONE);

        restFluxMockMvc.perform(put("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFlux)))
            .andExpect(status().isOk());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeUpdate);
        Flux testFlux = fluxList.get(fluxList.size() - 1);
        assertThat(testFlux.getEnvironnement()).isEqualTo(UPDATED_ENVIRONNEMENT);
        assertThat(testFlux.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFlux.getSourceIP()).isEqualTo(UPDATED_SOURCE_IP);
        assertThat(testFlux.getSourcePort()).isEqualTo(UPDATED_SOURCE_PORT);
        assertThat(testFlux.getSourceZone()).isEqualTo(UPDATED_SOURCE_ZONE);
        assertThat(testFlux.getDestIP()).isEqualTo(UPDATED_DEST_IP);
        assertThat(testFlux.getDestPort()).isEqualTo(UPDATED_DEST_PORT);
        assertThat(testFlux.getDestZone()).isEqualTo(UPDATED_DEST_ZONE);
    }

    @Test
    @Transactional
    public void updateNonExistingFlux() throws Exception {
        int databaseSizeBeforeUpdate = fluxRepository.findAll().size();

        // Create the Flux

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFluxMockMvc.perform(put("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlux() throws Exception {
        // Initialize the database
        fluxService.save(flux);

        int databaseSizeBeforeDelete = fluxRepository.findAll().size();

        // Delete the flux
        restFluxMockMvc.perform(delete("/api/fluxes/{id}", flux.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Flux.class);
        Flux flux1 = new Flux();
        flux1.setId(1L);
        Flux flux2 = new Flux();
        flux2.setId(flux1.getId());
        assertThat(flux1).isEqualTo(flux2);
        flux2.setId(2L);
        assertThat(flux1).isNotEqualTo(flux2);
        flux1.setId(null);
        assertThat(flux1).isNotEqualTo(flux2);
    }
}

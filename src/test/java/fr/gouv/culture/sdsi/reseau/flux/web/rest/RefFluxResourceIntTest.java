package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefFluxService;
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

import fr.gouv.culture.sdsi.reseau.flux.domain.enumeration.TypeFlux;
/**
 * Test class for the RefFluxResource REST controller.
 *
 * @see RefFluxResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefFluxResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final TypeFlux DEFAULT_TYPE = TypeFlux.TCP;
    private static final TypeFlux UPDATED_TYPE = TypeFlux.UDP;

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefFluxRepository refFluxRepository;

    @Autowired
    private RefFluxService refFluxService;

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

    private MockMvc restRefFluxMockMvc;

    private RefFlux refFlux;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefFluxResource refFluxResource = new RefFluxResource(refFluxService);
        this.restRefFluxMockMvc = MockMvcBuilders.standaloneSetup(refFluxResource)
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
    public static RefFlux createEntity(EntityManager em) {
        RefFlux refFlux = new RefFlux()
            .code(DEFAULT_CODE)
            .type(DEFAULT_TYPE)
            .libelle(DEFAULT_LIBELLE);
        return refFlux;
    }

    @Before
    public void initTest() {
        refFlux = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefFlux() throws Exception {
        int databaseSizeBeforeCreate = refFluxRepository.findAll().size();

        // Create the RefFlux
        restRefFluxMockMvc.perform(post("/api/ref-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFlux)))
            .andExpect(status().isCreated());

        // Validate the RefFlux in the database
        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeCreate + 1);
        RefFlux testRefFlux = refFluxList.get(refFluxList.size() - 1);
        assertThat(testRefFlux.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefFlux.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testRefFlux.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefFluxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refFluxRepository.findAll().size();

        // Create the RefFlux with an existing ID
        refFlux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefFluxMockMvc.perform(post("/api/ref-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFlux)))
            .andExpect(status().isBadRequest());

        // Validate the RefFlux in the database
        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refFluxRepository.findAll().size();
        // set the field null
        refFlux.setCode(null);

        // Create the RefFlux, which fails.

        restRefFluxMockMvc.perform(post("/api/ref-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFlux)))
            .andExpect(status().isBadRequest());

        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refFluxRepository.findAll().size();
        // set the field null
        refFlux.setType(null);

        // Create the RefFlux, which fails.

        restRefFluxMockMvc.perform(post("/api/ref-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFlux)))
            .andExpect(status().isBadRequest());

        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refFluxRepository.findAll().size();
        // set the field null
        refFlux.setLibelle(null);

        // Create the RefFlux, which fails.

        restRefFluxMockMvc.perform(post("/api/ref-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFlux)))
            .andExpect(status().isBadRequest());

        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefFluxes() throws Exception {
        // Initialize the database
        refFluxRepository.saveAndFlush(refFlux);

        // Get all the refFluxList
        restRefFluxMockMvc.perform(get("/api/ref-fluxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refFlux.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefFlux() throws Exception {
        // Initialize the database
        refFluxRepository.saveAndFlush(refFlux);

        // Get the refFlux
        restRefFluxMockMvc.perform(get("/api/ref-fluxes/{id}", refFlux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refFlux.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefFlux() throws Exception {
        // Get the refFlux
        restRefFluxMockMvc.perform(get("/api/ref-fluxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefFlux() throws Exception {
        // Initialize the database
        refFluxService.save(refFlux);

        int databaseSizeBeforeUpdate = refFluxRepository.findAll().size();

        // Update the refFlux
        RefFlux updatedRefFlux = refFluxRepository.findById(refFlux.getId()).get();
        // Disconnect from session so that the updates on updatedRefFlux are not directly saved in db
        em.detach(updatedRefFlux);
        updatedRefFlux
            .code(UPDATED_CODE)
            .type(UPDATED_TYPE)
            .libelle(UPDATED_LIBELLE);

        restRefFluxMockMvc.perform(put("/api/ref-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefFlux)))
            .andExpect(status().isOk());

        // Validate the RefFlux in the database
        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeUpdate);
        RefFlux testRefFlux = refFluxList.get(refFluxList.size() - 1);
        assertThat(testRefFlux.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefFlux.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testRefFlux.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefFlux() throws Exception {
        int databaseSizeBeforeUpdate = refFluxRepository.findAll().size();

        // Create the RefFlux

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefFluxMockMvc.perform(put("/api/ref-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFlux)))
            .andExpect(status().isBadRequest());

        // Validate the RefFlux in the database
        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefFlux() throws Exception {
        // Initialize the database
        refFluxService.save(refFlux);

        int databaseSizeBeforeDelete = refFluxRepository.findAll().size();

        // Delete the refFlux
        restRefFluxMockMvc.perform(delete("/api/ref-fluxes/{id}", refFlux.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefFlux> refFluxList = refFluxRepository.findAll();
        assertThat(refFluxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefFlux.class);
        RefFlux refFlux1 = new RefFlux();
        refFlux1.setId(1L);
        RefFlux refFlux2 = new RefFlux();
        refFlux2.setId(refFlux1.getId());
        assertThat(refFlux1).isEqualTo(refFlux2);
        refFlux2.setId(2L);
        assertThat(refFlux1).isNotEqualTo(refFlux2);
        refFlux1.setId(null);
        assertThat(refFlux1).isNotEqualTo(refFlux2);
    }
}

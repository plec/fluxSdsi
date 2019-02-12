package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefZone;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefZoneRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefZoneService;
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
 * Test class for the RefZoneResource REST controller.
 *
 * @see RefZoneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefZoneResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefZoneRepository refZoneRepository;

    @Autowired
    private RefZoneService refZoneService;

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

    private MockMvc restRefZoneMockMvc;

    private RefZone refZone;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefZoneResource refZoneResource = new RefZoneResource(refZoneService);
        this.restRefZoneMockMvc = MockMvcBuilders.standaloneSetup(refZoneResource)
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
    public static RefZone createEntity(EntityManager em) {
        RefZone refZone = new RefZone()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refZone;
    }

    @Before
    public void initTest() {
        refZone = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefZone() throws Exception {
        int databaseSizeBeforeCreate = refZoneRepository.findAll().size();

        // Create the RefZone
        restRefZoneMockMvc.perform(post("/api/ref-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refZone)))
            .andExpect(status().isCreated());

        // Validate the RefZone in the database
        List<RefZone> refZoneList = refZoneRepository.findAll();
        assertThat(refZoneList).hasSize(databaseSizeBeforeCreate + 1);
        RefZone testRefZone = refZoneList.get(refZoneList.size() - 1);
        assertThat(testRefZone.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefZone.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefZoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refZoneRepository.findAll().size();

        // Create the RefZone with an existing ID
        refZone.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefZoneMockMvc.perform(post("/api/ref-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refZone)))
            .andExpect(status().isBadRequest());

        // Validate the RefZone in the database
        List<RefZone> refZoneList = refZoneRepository.findAll();
        assertThat(refZoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refZoneRepository.findAll().size();
        // set the field null
        refZone.setCode(null);

        // Create the RefZone, which fails.

        restRefZoneMockMvc.perform(post("/api/ref-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refZone)))
            .andExpect(status().isBadRequest());

        List<RefZone> refZoneList = refZoneRepository.findAll();
        assertThat(refZoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refZoneRepository.findAll().size();
        // set the field null
        refZone.setLibelle(null);

        // Create the RefZone, which fails.

        restRefZoneMockMvc.perform(post("/api/ref-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refZone)))
            .andExpect(status().isBadRequest());

        List<RefZone> refZoneList = refZoneRepository.findAll();
        assertThat(refZoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefZones() throws Exception {
        // Initialize the database
        refZoneRepository.saveAndFlush(refZone);

        // Get all the refZoneList
        restRefZoneMockMvc.perform(get("/api/ref-zones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refZone.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefZone() throws Exception {
        // Initialize the database
        refZoneRepository.saveAndFlush(refZone);

        // Get the refZone
        restRefZoneMockMvc.perform(get("/api/ref-zones/{id}", refZone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refZone.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefZone() throws Exception {
        // Get the refZone
        restRefZoneMockMvc.perform(get("/api/ref-zones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefZone() throws Exception {
        // Initialize the database
        refZoneService.save(refZone);

        int databaseSizeBeforeUpdate = refZoneRepository.findAll().size();

        // Update the refZone
        RefZone updatedRefZone = refZoneRepository.findById(refZone.getId()).get();
        // Disconnect from session so that the updates on updatedRefZone are not directly saved in db
        em.detach(updatedRefZone);
        updatedRefZone
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restRefZoneMockMvc.perform(put("/api/ref-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefZone)))
            .andExpect(status().isOk());

        // Validate the RefZone in the database
        List<RefZone> refZoneList = refZoneRepository.findAll();
        assertThat(refZoneList).hasSize(databaseSizeBeforeUpdate);
        RefZone testRefZone = refZoneList.get(refZoneList.size() - 1);
        assertThat(testRefZone.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefZone.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefZone() throws Exception {
        int databaseSizeBeforeUpdate = refZoneRepository.findAll().size();

        // Create the RefZone

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefZoneMockMvc.perform(put("/api/ref-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refZone)))
            .andExpect(status().isBadRequest());

        // Validate the RefZone in the database
        List<RefZone> refZoneList = refZoneRepository.findAll();
        assertThat(refZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefZone() throws Exception {
        // Initialize the database
        refZoneService.save(refZone);

        int databaseSizeBeforeDelete = refZoneRepository.findAll().size();

        // Delete the refZone
        restRefZoneMockMvc.perform(delete("/api/ref-zones/{id}", refZone.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefZone> refZoneList = refZoneRepository.findAll();
        assertThat(refZoneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefZone.class);
        RefZone refZone1 = new RefZone();
        refZone1.setId(1L);
        RefZone refZone2 = new RefZone();
        refZone2.setId(refZone1.getId());
        assertThat(refZone1).isEqualTo(refZone2);
        refZone2.setId(2L);
        assertThat(refZone1).isNotEqualTo(refZone2);
        refZone1.setId(null);
        assertThat(refZone1).isNotEqualTo(refZone2);
    }
}

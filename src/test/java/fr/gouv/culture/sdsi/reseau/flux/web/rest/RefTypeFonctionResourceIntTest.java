package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFonction;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefTypeFonctionRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFonctionService;
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
 * Test class for the RefTypeFonctionResource REST controller.
 *
 * @see RefTypeFonctionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefTypeFonctionResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefTypeFonctionRepository refTypeFonctionRepository;

    @Autowired
    private RefTypeFonctionService refTypeFonctionService;

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

    private MockMvc restRefTypeFonctionMockMvc;

    private RefTypeFonction refTypeFonction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefTypeFonctionResource refTypeFonctionResource = new RefTypeFonctionResource(refTypeFonctionService);
        this.restRefTypeFonctionMockMvc = MockMvcBuilders.standaloneSetup(refTypeFonctionResource)
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
    public static RefTypeFonction createEntity(EntityManager em) {
        RefTypeFonction refTypeFonction = new RefTypeFonction()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refTypeFonction;
    }

    @Before
    public void initTest() {
        refTypeFonction = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefTypeFonction() throws Exception {
        int databaseSizeBeforeCreate = refTypeFonctionRepository.findAll().size();

        // Create the RefTypeFonction
        restRefTypeFonctionMockMvc.perform(post("/api/ref-type-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFonction)))
            .andExpect(status().isCreated());

        // Validate the RefTypeFonction in the database
        List<RefTypeFonction> refTypeFonctionList = refTypeFonctionRepository.findAll();
        assertThat(refTypeFonctionList).hasSize(databaseSizeBeforeCreate + 1);
        RefTypeFonction testRefTypeFonction = refTypeFonctionList.get(refTypeFonctionList.size() - 1);
        assertThat(testRefTypeFonction.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefTypeFonction.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefTypeFonctionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refTypeFonctionRepository.findAll().size();

        // Create the RefTypeFonction with an existing ID
        refTypeFonction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefTypeFonctionMockMvc.perform(post("/api/ref-type-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFonction)))
            .andExpect(status().isBadRequest());

        // Validate the RefTypeFonction in the database
        List<RefTypeFonction> refTypeFonctionList = refTypeFonctionRepository.findAll();
        assertThat(refTypeFonctionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refTypeFonctionRepository.findAll().size();
        // set the field null
        refTypeFonction.setCode(null);

        // Create the RefTypeFonction, which fails.

        restRefTypeFonctionMockMvc.perform(post("/api/ref-type-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFonction)))
            .andExpect(status().isBadRequest());

        List<RefTypeFonction> refTypeFonctionList = refTypeFonctionRepository.findAll();
        assertThat(refTypeFonctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refTypeFonctionRepository.findAll().size();
        // set the field null
        refTypeFonction.setLibelle(null);

        // Create the RefTypeFonction, which fails.

        restRefTypeFonctionMockMvc.perform(post("/api/ref-type-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFonction)))
            .andExpect(status().isBadRequest());

        List<RefTypeFonction> refTypeFonctionList = refTypeFonctionRepository.findAll();
        assertThat(refTypeFonctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefTypeFonctions() throws Exception {
        // Initialize the database
        refTypeFonctionRepository.saveAndFlush(refTypeFonction);

        // Get all the refTypeFonctionList
        restRefTypeFonctionMockMvc.perform(get("/api/ref-type-fonctions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refTypeFonction.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefTypeFonction() throws Exception {
        // Initialize the database
        refTypeFonctionRepository.saveAndFlush(refTypeFonction);

        // Get the refTypeFonction
        restRefTypeFonctionMockMvc.perform(get("/api/ref-type-fonctions/{id}", refTypeFonction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refTypeFonction.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefTypeFonction() throws Exception {
        // Get the refTypeFonction
        restRefTypeFonctionMockMvc.perform(get("/api/ref-type-fonctions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefTypeFonction() throws Exception {
        // Initialize the database
        refTypeFonctionService.save(refTypeFonction);

        int databaseSizeBeforeUpdate = refTypeFonctionRepository.findAll().size();

        // Update the refTypeFonction
        RefTypeFonction updatedRefTypeFonction = refTypeFonctionRepository.findById(refTypeFonction.getId()).get();
        // Disconnect from session so that the updates on updatedRefTypeFonction are not directly saved in db
        em.detach(updatedRefTypeFonction);
        updatedRefTypeFonction
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restRefTypeFonctionMockMvc.perform(put("/api/ref-type-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefTypeFonction)))
            .andExpect(status().isOk());

        // Validate the RefTypeFonction in the database
        List<RefTypeFonction> refTypeFonctionList = refTypeFonctionRepository.findAll();
        assertThat(refTypeFonctionList).hasSize(databaseSizeBeforeUpdate);
        RefTypeFonction testRefTypeFonction = refTypeFonctionList.get(refTypeFonctionList.size() - 1);
        assertThat(testRefTypeFonction.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefTypeFonction.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefTypeFonction() throws Exception {
        int databaseSizeBeforeUpdate = refTypeFonctionRepository.findAll().size();

        // Create the RefTypeFonction

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefTypeFonctionMockMvc.perform(put("/api/ref-type-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFonction)))
            .andExpect(status().isBadRequest());

        // Validate the RefTypeFonction in the database
        List<RefTypeFonction> refTypeFonctionList = refTypeFonctionRepository.findAll();
        assertThat(refTypeFonctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefTypeFonction() throws Exception {
        // Initialize the database
        refTypeFonctionService.save(refTypeFonction);

        int databaseSizeBeforeDelete = refTypeFonctionRepository.findAll().size();

        // Delete the refTypeFonction
        restRefTypeFonctionMockMvc.perform(delete("/api/ref-type-fonctions/{id}", refTypeFonction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefTypeFonction> refTypeFonctionList = refTypeFonctionRepository.findAll();
        assertThat(refTypeFonctionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefTypeFonction.class);
        RefTypeFonction refTypeFonction1 = new RefTypeFonction();
        refTypeFonction1.setId(1L);
        RefTypeFonction refTypeFonction2 = new RefTypeFonction();
        refTypeFonction2.setId(refTypeFonction1.getId());
        assertThat(refTypeFonction1).isEqualTo(refTypeFonction2);
        refTypeFonction2.setId(2L);
        assertThat(refTypeFonction1).isNotEqualTo(refTypeFonction2);
        refTypeFonction1.setId(null);
        assertThat(refTypeFonction1).isNotEqualTo(refTypeFonction2);
    }
}

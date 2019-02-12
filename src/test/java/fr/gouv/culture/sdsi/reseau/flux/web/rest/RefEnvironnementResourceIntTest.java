package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironnement;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefEnvironnementRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefEnvironnementService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironnementDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefEnvironnementMapper;
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
 * Test class for the RefEnvironnementResource REST controller.
 *
 * @see RefEnvironnementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefEnvironnementResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefEnvironnementRepository refEnvironnementRepository;

    @Autowired
    private RefEnvironnementMapper refEnvironnementMapper;

    @Autowired
    private RefEnvironnementService refEnvironnementService;

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

    private MockMvc restRefEnvironnementMockMvc;

    private RefEnvironnement refEnvironnement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefEnvironnementResource refEnvironnementResource = new RefEnvironnementResource(refEnvironnementService);
        this.restRefEnvironnementMockMvc = MockMvcBuilders.standaloneSetup(refEnvironnementResource)
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
    public static RefEnvironnement createEntity(EntityManager em) {
        RefEnvironnement refEnvironnement = new RefEnvironnement()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refEnvironnement;
    }

    @Before
    public void initTest() {
        refEnvironnement = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefEnvironnement() throws Exception {
        int databaseSizeBeforeCreate = refEnvironnementRepository.findAll().size();

        // Create the RefEnvironnement
        RefEnvironnementDTO refEnvironnementDTO = refEnvironnementMapper.toDto(refEnvironnement);
        restRefEnvironnementMockMvc.perform(post("/api/ref-environnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironnementDTO)))
            .andExpect(status().isCreated());

        // Validate the RefEnvironnement in the database
        List<RefEnvironnement> refEnvironnementList = refEnvironnementRepository.findAll();
        assertThat(refEnvironnementList).hasSize(databaseSizeBeforeCreate + 1);
        RefEnvironnement testRefEnvironnement = refEnvironnementList.get(refEnvironnementList.size() - 1);
        assertThat(testRefEnvironnement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefEnvironnement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefEnvironnementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refEnvironnementRepository.findAll().size();

        // Create the RefEnvironnement with an existing ID
        refEnvironnement.setId(1L);
        RefEnvironnementDTO refEnvironnementDTO = refEnvironnementMapper.toDto(refEnvironnement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefEnvironnementMockMvc.perform(post("/api/ref-environnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironnementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefEnvironnement in the database
        List<RefEnvironnement> refEnvironnementList = refEnvironnementRepository.findAll();
        assertThat(refEnvironnementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refEnvironnementRepository.findAll().size();
        // set the field null
        refEnvironnement.setCode(null);

        // Create the RefEnvironnement, which fails.
        RefEnvironnementDTO refEnvironnementDTO = refEnvironnementMapper.toDto(refEnvironnement);

        restRefEnvironnementMockMvc.perform(post("/api/ref-environnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironnementDTO)))
            .andExpect(status().isBadRequest());

        List<RefEnvironnement> refEnvironnementList = refEnvironnementRepository.findAll();
        assertThat(refEnvironnementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refEnvironnementRepository.findAll().size();
        // set the field null
        refEnvironnement.setLibelle(null);

        // Create the RefEnvironnement, which fails.
        RefEnvironnementDTO refEnvironnementDTO = refEnvironnementMapper.toDto(refEnvironnement);

        restRefEnvironnementMockMvc.perform(post("/api/ref-environnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironnementDTO)))
            .andExpect(status().isBadRequest());

        List<RefEnvironnement> refEnvironnementList = refEnvironnementRepository.findAll();
        assertThat(refEnvironnementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefEnvironnements() throws Exception {
        // Initialize the database
        refEnvironnementRepository.saveAndFlush(refEnvironnement);

        // Get all the refEnvironnementList
        restRefEnvironnementMockMvc.perform(get("/api/ref-environnements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refEnvironnement.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefEnvironnement() throws Exception {
        // Initialize the database
        refEnvironnementRepository.saveAndFlush(refEnvironnement);

        // Get the refEnvironnement
        restRefEnvironnementMockMvc.perform(get("/api/ref-environnements/{id}", refEnvironnement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refEnvironnement.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefEnvironnement() throws Exception {
        // Get the refEnvironnement
        restRefEnvironnementMockMvc.perform(get("/api/ref-environnements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefEnvironnement() throws Exception {
        // Initialize the database
        refEnvironnementRepository.saveAndFlush(refEnvironnement);

        int databaseSizeBeforeUpdate = refEnvironnementRepository.findAll().size();

        // Update the refEnvironnement
        RefEnvironnement updatedRefEnvironnement = refEnvironnementRepository.findById(refEnvironnement.getId()).get();
        // Disconnect from session so that the updates on updatedRefEnvironnement are not directly saved in db
        em.detach(updatedRefEnvironnement);
        updatedRefEnvironnement
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RefEnvironnementDTO refEnvironnementDTO = refEnvironnementMapper.toDto(updatedRefEnvironnement);

        restRefEnvironnementMockMvc.perform(put("/api/ref-environnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironnementDTO)))
            .andExpect(status().isOk());

        // Validate the RefEnvironnement in the database
        List<RefEnvironnement> refEnvironnementList = refEnvironnementRepository.findAll();
        assertThat(refEnvironnementList).hasSize(databaseSizeBeforeUpdate);
        RefEnvironnement testRefEnvironnement = refEnvironnementList.get(refEnvironnementList.size() - 1);
        assertThat(testRefEnvironnement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefEnvironnement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefEnvironnement() throws Exception {
        int databaseSizeBeforeUpdate = refEnvironnementRepository.findAll().size();

        // Create the RefEnvironnement
        RefEnvironnementDTO refEnvironnementDTO = refEnvironnementMapper.toDto(refEnvironnement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefEnvironnementMockMvc.perform(put("/api/ref-environnements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironnementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefEnvironnement in the database
        List<RefEnvironnement> refEnvironnementList = refEnvironnementRepository.findAll();
        assertThat(refEnvironnementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefEnvironnement() throws Exception {
        // Initialize the database
        refEnvironnementRepository.saveAndFlush(refEnvironnement);

        int databaseSizeBeforeDelete = refEnvironnementRepository.findAll().size();

        // Delete the refEnvironnement
        restRefEnvironnementMockMvc.perform(delete("/api/ref-environnements/{id}", refEnvironnement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefEnvironnement> refEnvironnementList = refEnvironnementRepository.findAll();
        assertThat(refEnvironnementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefEnvironnement.class);
        RefEnvironnement refEnvironnement1 = new RefEnvironnement();
        refEnvironnement1.setId(1L);
        RefEnvironnement refEnvironnement2 = new RefEnvironnement();
        refEnvironnement2.setId(refEnvironnement1.getId());
        assertThat(refEnvironnement1).isEqualTo(refEnvironnement2);
        refEnvironnement2.setId(2L);
        assertThat(refEnvironnement1).isNotEqualTo(refEnvironnement2);
        refEnvironnement1.setId(null);
        assertThat(refEnvironnement1).isNotEqualTo(refEnvironnement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefEnvironnementDTO.class);
        RefEnvironnementDTO refEnvironnementDTO1 = new RefEnvironnementDTO();
        refEnvironnementDTO1.setId(1L);
        RefEnvironnementDTO refEnvironnementDTO2 = new RefEnvironnementDTO();
        assertThat(refEnvironnementDTO1).isNotEqualTo(refEnvironnementDTO2);
        refEnvironnementDTO2.setId(refEnvironnementDTO1.getId());
        assertThat(refEnvironnementDTO1).isEqualTo(refEnvironnementDTO2);
        refEnvironnementDTO2.setId(2L);
        assertThat(refEnvironnementDTO1).isNotEqualTo(refEnvironnementDTO2);
        refEnvironnementDTO1.setId(null);
        assertThat(refEnvironnementDTO1).isNotEqualTo(refEnvironnementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refEnvironnementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refEnvironnementMapper.fromId(null)).isNull();
    }
}

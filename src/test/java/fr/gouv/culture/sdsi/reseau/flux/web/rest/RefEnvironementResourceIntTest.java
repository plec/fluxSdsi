package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefEnvironement;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefEnvironementRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefEnvironementService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironementDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefEnvironementMapper;
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
 * Test class for the RefEnvironementResource REST controller.
 *
 * @see RefEnvironementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefEnvironementResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefEnvironementRepository refEnvironementRepository;

    @Autowired
    private RefEnvironementMapper refEnvironementMapper;

    @Autowired
    private RefEnvironementService refEnvironementService;

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

    private MockMvc restRefEnvironementMockMvc;

    private RefEnvironement refEnvironement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefEnvironementResource refEnvironementResource = new RefEnvironementResource(refEnvironementService);
        this.restRefEnvironementMockMvc = MockMvcBuilders.standaloneSetup(refEnvironementResource)
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
    public static RefEnvironement createEntity(EntityManager em) {
        RefEnvironement refEnvironement = new RefEnvironement()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refEnvironement;
    }

    @Before
    public void initTest() {
        refEnvironement = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefEnvironement() throws Exception {
        int databaseSizeBeforeCreate = refEnvironementRepository.findAll().size();

        // Create the RefEnvironement
        RefEnvironementDTO refEnvironementDTO = refEnvironementMapper.toDto(refEnvironement);
        restRefEnvironementMockMvc.perform(post("/api/ref-environements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironementDTO)))
            .andExpect(status().isCreated());

        // Validate the RefEnvironement in the database
        List<RefEnvironement> refEnvironementList = refEnvironementRepository.findAll();
        assertThat(refEnvironementList).hasSize(databaseSizeBeforeCreate + 1);
        RefEnvironement testRefEnvironement = refEnvironementList.get(refEnvironementList.size() - 1);
        assertThat(testRefEnvironement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefEnvironement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefEnvironementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refEnvironementRepository.findAll().size();

        // Create the RefEnvironement with an existing ID
        refEnvironement.setId(1L);
        RefEnvironementDTO refEnvironementDTO = refEnvironementMapper.toDto(refEnvironement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefEnvironementMockMvc.perform(post("/api/ref-environements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefEnvironement in the database
        List<RefEnvironement> refEnvironementList = refEnvironementRepository.findAll();
        assertThat(refEnvironementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refEnvironementRepository.findAll().size();
        // set the field null
        refEnvironement.setCode(null);

        // Create the RefEnvironement, which fails.
        RefEnvironementDTO refEnvironementDTO = refEnvironementMapper.toDto(refEnvironement);

        restRefEnvironementMockMvc.perform(post("/api/ref-environements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironementDTO)))
            .andExpect(status().isBadRequest());

        List<RefEnvironement> refEnvironementList = refEnvironementRepository.findAll();
        assertThat(refEnvironementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refEnvironementRepository.findAll().size();
        // set the field null
        refEnvironement.setLibelle(null);

        // Create the RefEnvironement, which fails.
        RefEnvironementDTO refEnvironementDTO = refEnvironementMapper.toDto(refEnvironement);

        restRefEnvironementMockMvc.perform(post("/api/ref-environements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironementDTO)))
            .andExpect(status().isBadRequest());

        List<RefEnvironement> refEnvironementList = refEnvironementRepository.findAll();
        assertThat(refEnvironementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefEnvironements() throws Exception {
        // Initialize the database
        refEnvironementRepository.saveAndFlush(refEnvironement);

        // Get all the refEnvironementList
        restRefEnvironementMockMvc.perform(get("/api/ref-environements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refEnvironement.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefEnvironement() throws Exception {
        // Initialize the database
        refEnvironementRepository.saveAndFlush(refEnvironement);

        // Get the refEnvironement
        restRefEnvironementMockMvc.perform(get("/api/ref-environements/{id}", refEnvironement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refEnvironement.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefEnvironement() throws Exception {
        // Get the refEnvironement
        restRefEnvironementMockMvc.perform(get("/api/ref-environements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefEnvironement() throws Exception {
        // Initialize the database
        refEnvironementRepository.saveAndFlush(refEnvironement);

        int databaseSizeBeforeUpdate = refEnvironementRepository.findAll().size();

        // Update the refEnvironement
        RefEnvironement updatedRefEnvironement = refEnvironementRepository.findById(refEnvironement.getId()).get();
        // Disconnect from session so that the updates on updatedRefEnvironement are not directly saved in db
        em.detach(updatedRefEnvironement);
        updatedRefEnvironement
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RefEnvironementDTO refEnvironementDTO = refEnvironementMapper.toDto(updatedRefEnvironement);

        restRefEnvironementMockMvc.perform(put("/api/ref-environements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironementDTO)))
            .andExpect(status().isOk());

        // Validate the RefEnvironement in the database
        List<RefEnvironement> refEnvironementList = refEnvironementRepository.findAll();
        assertThat(refEnvironementList).hasSize(databaseSizeBeforeUpdate);
        RefEnvironement testRefEnvironement = refEnvironementList.get(refEnvironementList.size() - 1);
        assertThat(testRefEnvironement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefEnvironement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefEnvironement() throws Exception {
        int databaseSizeBeforeUpdate = refEnvironementRepository.findAll().size();

        // Create the RefEnvironement
        RefEnvironementDTO refEnvironementDTO = refEnvironementMapper.toDto(refEnvironement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefEnvironementMockMvc.perform(put("/api/ref-environements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refEnvironementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefEnvironement in the database
        List<RefEnvironement> refEnvironementList = refEnvironementRepository.findAll();
        assertThat(refEnvironementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefEnvironement() throws Exception {
        // Initialize the database
        refEnvironementRepository.saveAndFlush(refEnvironement);

        int databaseSizeBeforeDelete = refEnvironementRepository.findAll().size();

        // Delete the refEnvironement
        restRefEnvironementMockMvc.perform(delete("/api/ref-environements/{id}", refEnvironement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefEnvironement> refEnvironementList = refEnvironementRepository.findAll();
        assertThat(refEnvironementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefEnvironement.class);
        RefEnvironement refEnvironement1 = new RefEnvironement();
        refEnvironement1.setId(1L);
        RefEnvironement refEnvironement2 = new RefEnvironement();
        refEnvironement2.setId(refEnvironement1.getId());
        assertThat(refEnvironement1).isEqualTo(refEnvironement2);
        refEnvironement2.setId(2L);
        assertThat(refEnvironement1).isNotEqualTo(refEnvironement2);
        refEnvironement1.setId(null);
        assertThat(refEnvironement1).isNotEqualTo(refEnvironement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefEnvironementDTO.class);
        RefEnvironementDTO refEnvironementDTO1 = new RefEnvironementDTO();
        refEnvironementDTO1.setId(1L);
        RefEnvironementDTO refEnvironementDTO2 = new RefEnvironementDTO();
        assertThat(refEnvironementDTO1).isNotEqualTo(refEnvironementDTO2);
        refEnvironementDTO2.setId(refEnvironementDTO1.getId());
        assertThat(refEnvironementDTO1).isEqualTo(refEnvironementDTO2);
        refEnvironementDTO2.setId(2L);
        assertThat(refEnvironementDTO1).isNotEqualTo(refEnvironementDTO2);
        refEnvironementDTO1.setId(null);
        assertThat(refEnvironementDTO1).isNotEqualTo(refEnvironementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refEnvironementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refEnvironementMapper.fromId(null)).isNull();
    }
}

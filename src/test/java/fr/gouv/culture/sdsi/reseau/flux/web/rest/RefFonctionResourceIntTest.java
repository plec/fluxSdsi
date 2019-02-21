package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefFonction;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefFonctionRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefFonctionService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFonctionDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefFonctionMapper;
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
 * Test class for the RefFonctionResource REST controller.
 *
 * @see RefFonctionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefFonctionResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefFonctionRepository refFonctionRepository;

    @Autowired
    private RefFonctionMapper refFonctionMapper;

    @Autowired
    private RefFonctionService refFonctionService;

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

    private MockMvc restRefFonctionMockMvc;

    private RefFonction refFonction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefFonctionResource refFonctionResource = new RefFonctionResource(refFonctionService);
        this.restRefFonctionMockMvc = MockMvcBuilders.standaloneSetup(refFonctionResource)
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
    public static RefFonction createEntity(EntityManager em) {
        RefFonction refFonction = new RefFonction()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refFonction;
    }

    @Before
    public void initTest() {
        refFonction = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefFonction() throws Exception {
        int databaseSizeBeforeCreate = refFonctionRepository.findAll().size();

        // Create the RefFonction
        RefFonctionDTO refFonctionDTO = refFonctionMapper.toDto(refFonction);
        restRefFonctionMockMvc.perform(post("/api/ref-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFonctionDTO)))
            .andExpect(status().isCreated());

        // Validate the RefFonction in the database
        List<RefFonction> refFonctionList = refFonctionRepository.findAll();
        assertThat(refFonctionList).hasSize(databaseSizeBeforeCreate + 1);
        RefFonction testRefFonction = refFonctionList.get(refFonctionList.size() - 1);
        assertThat(testRefFonction.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefFonction.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefFonctionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refFonctionRepository.findAll().size();

        // Create the RefFonction with an existing ID
        refFonction.setId(1L);
        RefFonctionDTO refFonctionDTO = refFonctionMapper.toDto(refFonction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefFonctionMockMvc.perform(post("/api/ref-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFonctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefFonction in the database
        List<RefFonction> refFonctionList = refFonctionRepository.findAll();
        assertThat(refFonctionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refFonctionRepository.findAll().size();
        // set the field null
        refFonction.setCode(null);

        // Create the RefFonction, which fails.
        RefFonctionDTO refFonctionDTO = refFonctionMapper.toDto(refFonction);

        restRefFonctionMockMvc.perform(post("/api/ref-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFonctionDTO)))
            .andExpect(status().isBadRequest());

        List<RefFonction> refFonctionList = refFonctionRepository.findAll();
        assertThat(refFonctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refFonctionRepository.findAll().size();
        // set the field null
        refFonction.setLibelle(null);

        // Create the RefFonction, which fails.
        RefFonctionDTO refFonctionDTO = refFonctionMapper.toDto(refFonction);

        restRefFonctionMockMvc.perform(post("/api/ref-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFonctionDTO)))
            .andExpect(status().isBadRequest());

        List<RefFonction> refFonctionList = refFonctionRepository.findAll();
        assertThat(refFonctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefFonctions() throws Exception {
        // Initialize the database
        refFonctionRepository.saveAndFlush(refFonction);

        // Get all the refFonctionList
        restRefFonctionMockMvc.perform(get("/api/ref-fonctions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refFonction.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefFonction() throws Exception {
        // Initialize the database
        refFonctionRepository.saveAndFlush(refFonction);

        // Get the refFonction
        restRefFonctionMockMvc.perform(get("/api/ref-fonctions/{id}", refFonction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refFonction.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefFonction() throws Exception {
        // Get the refFonction
        restRefFonctionMockMvc.perform(get("/api/ref-fonctions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefFonction() throws Exception {
        // Initialize the database
        refFonctionRepository.saveAndFlush(refFonction);

        int databaseSizeBeforeUpdate = refFonctionRepository.findAll().size();

        // Update the refFonction
        RefFonction updatedRefFonction = refFonctionRepository.findById(refFonction.getId()).get();
        // Disconnect from session so that the updates on updatedRefFonction are not directly saved in db
        em.detach(updatedRefFonction);
        updatedRefFonction
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RefFonctionDTO refFonctionDTO = refFonctionMapper.toDto(updatedRefFonction);

        restRefFonctionMockMvc.perform(put("/api/ref-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFonctionDTO)))
            .andExpect(status().isOk());

        // Validate the RefFonction in the database
        List<RefFonction> refFonctionList = refFonctionRepository.findAll();
        assertThat(refFonctionList).hasSize(databaseSizeBeforeUpdate);
        RefFonction testRefFonction = refFonctionList.get(refFonctionList.size() - 1);
        assertThat(testRefFonction.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefFonction.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefFonction() throws Exception {
        int databaseSizeBeforeUpdate = refFonctionRepository.findAll().size();

        // Create the RefFonction
        RefFonctionDTO refFonctionDTO = refFonctionMapper.toDto(refFonction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefFonctionMockMvc.perform(put("/api/ref-fonctions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFonctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefFonction in the database
        List<RefFonction> refFonctionList = refFonctionRepository.findAll();
        assertThat(refFonctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefFonction() throws Exception {
        // Initialize the database
        refFonctionRepository.saveAndFlush(refFonction);

        int databaseSizeBeforeDelete = refFonctionRepository.findAll().size();

        // Delete the refFonction
        restRefFonctionMockMvc.perform(delete("/api/ref-fonctions/{id}", refFonction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefFonction> refFonctionList = refFonctionRepository.findAll();
        assertThat(refFonctionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefFonction.class);
        RefFonction refFonction1 = new RefFonction();
        refFonction1.setId(1L);
        RefFonction refFonction2 = new RefFonction();
        refFonction2.setId(refFonction1.getId());
        assertThat(refFonction1).isEqualTo(refFonction2);
        refFonction2.setId(2L);
        assertThat(refFonction1).isNotEqualTo(refFonction2);
        refFonction1.setId(null);
        assertThat(refFonction1).isNotEqualTo(refFonction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefFonctionDTO.class);
        RefFonctionDTO refFonctionDTO1 = new RefFonctionDTO();
        refFonctionDTO1.setId(1L);
        RefFonctionDTO refFonctionDTO2 = new RefFonctionDTO();
        assertThat(refFonctionDTO1).isNotEqualTo(refFonctionDTO2);
        refFonctionDTO2.setId(refFonctionDTO1.getId());
        assertThat(refFonctionDTO1).isEqualTo(refFonctionDTO2);
        refFonctionDTO2.setId(2L);
        assertThat(refFonctionDTO1).isNotEqualTo(refFonctionDTO2);
        refFonctionDTO1.setId(null);
        assertThat(refFonctionDTO1).isNotEqualTo(refFonctionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refFonctionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refFonctionMapper.fromId(null)).isNull();
    }
}

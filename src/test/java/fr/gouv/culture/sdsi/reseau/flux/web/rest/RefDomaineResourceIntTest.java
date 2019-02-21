package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefDomaine;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefDomaineRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefDomaineService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefDomaineDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefDomaineMapper;
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
 * Test class for the RefDomaineResource REST controller.
 *
 * @see RefDomaineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefDomaineResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefDomaineRepository refDomaineRepository;

    @Autowired
    private RefDomaineMapper refDomaineMapper;

    @Autowired
    private RefDomaineService refDomaineService;

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

    private MockMvc restRefDomaineMockMvc;

    private RefDomaine refDomaine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefDomaineResource refDomaineResource = new RefDomaineResource(refDomaineService);
        this.restRefDomaineMockMvc = MockMvcBuilders.standaloneSetup(refDomaineResource)
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
    public static RefDomaine createEntity(EntityManager em) {
        RefDomaine refDomaine = new RefDomaine()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refDomaine;
    }

    @Before
    public void initTest() {
        refDomaine = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefDomaine() throws Exception {
        int databaseSizeBeforeCreate = refDomaineRepository.findAll().size();

        // Create the RefDomaine
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);
        restRefDomaineMockMvc.perform(post("/api/ref-domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isCreated());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeCreate + 1);
        RefDomaine testRefDomaine = refDomaineList.get(refDomaineList.size() - 1);
        assertThat(testRefDomaine.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefDomaine.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefDomaineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refDomaineRepository.findAll().size();

        // Create the RefDomaine with an existing ID
        refDomaine.setId(1L);
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefDomaineMockMvc.perform(post("/api/ref-domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refDomaineRepository.findAll().size();
        // set the field null
        refDomaine.setCode(null);

        // Create the RefDomaine, which fails.
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);

        restRefDomaineMockMvc.perform(post("/api/ref-domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isBadRequest());

        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refDomaineRepository.findAll().size();
        // set the field null
        refDomaine.setLibelle(null);

        // Create the RefDomaine, which fails.
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);

        restRefDomaineMockMvc.perform(post("/api/ref-domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isBadRequest());

        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefDomaines() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        // Get all the refDomaineList
        restRefDomaineMockMvc.perform(get("/api/ref-domaines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refDomaine.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefDomaine() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        // Get the refDomaine
        restRefDomaineMockMvc.perform(get("/api/ref-domaines/{id}", refDomaine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refDomaine.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefDomaine() throws Exception {
        // Get the refDomaine
        restRefDomaineMockMvc.perform(get("/api/ref-domaines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefDomaine() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        int databaseSizeBeforeUpdate = refDomaineRepository.findAll().size();

        // Update the refDomaine
        RefDomaine updatedRefDomaine = refDomaineRepository.findById(refDomaine.getId()).get();
        // Disconnect from session so that the updates on updatedRefDomaine are not directly saved in db
        em.detach(updatedRefDomaine);
        updatedRefDomaine
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(updatedRefDomaine);

        restRefDomaineMockMvc.perform(put("/api/ref-domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isOk());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeUpdate);
        RefDomaine testRefDomaine = refDomaineList.get(refDomaineList.size() - 1);
        assertThat(testRefDomaine.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefDomaine.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefDomaine() throws Exception {
        int databaseSizeBeforeUpdate = refDomaineRepository.findAll().size();

        // Create the RefDomaine
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefDomaineMockMvc.perform(put("/api/ref-domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefDomaine() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        int databaseSizeBeforeDelete = refDomaineRepository.findAll().size();

        // Delete the refDomaine
        restRefDomaineMockMvc.perform(delete("/api/ref-domaines/{id}", refDomaine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefDomaine.class);
        RefDomaine refDomaine1 = new RefDomaine();
        refDomaine1.setId(1L);
        RefDomaine refDomaine2 = new RefDomaine();
        refDomaine2.setId(refDomaine1.getId());
        assertThat(refDomaine1).isEqualTo(refDomaine2);
        refDomaine2.setId(2L);
        assertThat(refDomaine1).isNotEqualTo(refDomaine2);
        refDomaine1.setId(null);
        assertThat(refDomaine1).isNotEqualTo(refDomaine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefDomaineDTO.class);
        RefDomaineDTO refDomaineDTO1 = new RefDomaineDTO();
        refDomaineDTO1.setId(1L);
        RefDomaineDTO refDomaineDTO2 = new RefDomaineDTO();
        assertThat(refDomaineDTO1).isNotEqualTo(refDomaineDTO2);
        refDomaineDTO2.setId(refDomaineDTO1.getId());
        assertThat(refDomaineDTO1).isEqualTo(refDomaineDTO2);
        refDomaineDTO2.setId(2L);
        assertThat(refDomaineDTO1).isNotEqualTo(refDomaineDTO2);
        refDomaineDTO1.setId(null);
        assertThat(refDomaineDTO1).isNotEqualTo(refDomaineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refDomaineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refDomaineMapper.fromId(null)).isNull();
    }
}

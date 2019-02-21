package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefVlan;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefVlanRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefVlanService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefVlanDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefVlanMapper;
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
 * Test class for the RefVlanResource REST controller.
 *
 * @see RefVlanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefVlanResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefVlanRepository refVlanRepository;

    @Autowired
    private RefVlanMapper refVlanMapper;

    @Autowired
    private RefVlanService refVlanService;

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

    private MockMvc restRefVlanMockMvc;

    private RefVlan refVlan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefVlanResource refVlanResource = new RefVlanResource(refVlanService);
        this.restRefVlanMockMvc = MockMvcBuilders.standaloneSetup(refVlanResource)
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
    public static RefVlan createEntity(EntityManager em) {
        RefVlan refVlan = new RefVlan()
            .code(DEFAULT_CODE)
            .codeZone(DEFAULT_CODE_ZONE)
            .libelle(DEFAULT_LIBELLE);
        return refVlan;
    }

    @Before
    public void initTest() {
        refVlan = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefVlan() throws Exception {
        int databaseSizeBeforeCreate = refVlanRepository.findAll().size();

        // Create the RefVlan
        RefVlanDTO refVlanDTO = refVlanMapper.toDto(refVlan);
        restRefVlanMockMvc.perform(post("/api/ref-vlans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refVlanDTO)))
            .andExpect(status().isCreated());

        // Validate the RefVlan in the database
        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeCreate + 1);
        RefVlan testRefVlan = refVlanList.get(refVlanList.size() - 1);
        assertThat(testRefVlan.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefVlan.getCodeZone()).isEqualTo(DEFAULT_CODE_ZONE);
        assertThat(testRefVlan.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefVlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refVlanRepository.findAll().size();

        // Create the RefVlan with an existing ID
        refVlan.setId(1L);
        RefVlanDTO refVlanDTO = refVlanMapper.toDto(refVlan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefVlanMockMvc.perform(post("/api/ref-vlans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refVlanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefVlan in the database
        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refVlanRepository.findAll().size();
        // set the field null
        refVlan.setCode(null);

        // Create the RefVlan, which fails.
        RefVlanDTO refVlanDTO = refVlanMapper.toDto(refVlan);

        restRefVlanMockMvc.perform(post("/api/ref-vlans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refVlanDTO)))
            .andExpect(status().isBadRequest());

        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeZoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = refVlanRepository.findAll().size();
        // set the field null
        refVlan.setCodeZone(null);

        // Create the RefVlan, which fails.
        RefVlanDTO refVlanDTO = refVlanMapper.toDto(refVlan);

        restRefVlanMockMvc.perform(post("/api/ref-vlans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refVlanDTO)))
            .andExpect(status().isBadRequest());

        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refVlanRepository.findAll().size();
        // set the field null
        refVlan.setLibelle(null);

        // Create the RefVlan, which fails.
        RefVlanDTO refVlanDTO = refVlanMapper.toDto(refVlan);

        restRefVlanMockMvc.perform(post("/api/ref-vlans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refVlanDTO)))
            .andExpect(status().isBadRequest());

        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefVlans() throws Exception {
        // Initialize the database
        refVlanRepository.saveAndFlush(refVlan);

        // Get all the refVlanList
        restRefVlanMockMvc.perform(get("/api/ref-vlans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refVlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].codeZone").value(hasItem(DEFAULT_CODE_ZONE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefVlan() throws Exception {
        // Initialize the database
        refVlanRepository.saveAndFlush(refVlan);

        // Get the refVlan
        restRefVlanMockMvc.perform(get("/api/ref-vlans/{id}", refVlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refVlan.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.codeZone").value(DEFAULT_CODE_ZONE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefVlan() throws Exception {
        // Get the refVlan
        restRefVlanMockMvc.perform(get("/api/ref-vlans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefVlan() throws Exception {
        // Initialize the database
        refVlanRepository.saveAndFlush(refVlan);

        int databaseSizeBeforeUpdate = refVlanRepository.findAll().size();

        // Update the refVlan
        RefVlan updatedRefVlan = refVlanRepository.findById(refVlan.getId()).get();
        // Disconnect from session so that the updates on updatedRefVlan are not directly saved in db
        em.detach(updatedRefVlan);
        updatedRefVlan
            .code(UPDATED_CODE)
            .codeZone(UPDATED_CODE_ZONE)
            .libelle(UPDATED_LIBELLE);
        RefVlanDTO refVlanDTO = refVlanMapper.toDto(updatedRefVlan);

        restRefVlanMockMvc.perform(put("/api/ref-vlans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refVlanDTO)))
            .andExpect(status().isOk());

        // Validate the RefVlan in the database
        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeUpdate);
        RefVlan testRefVlan = refVlanList.get(refVlanList.size() - 1);
        assertThat(testRefVlan.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefVlan.getCodeZone()).isEqualTo(UPDATED_CODE_ZONE);
        assertThat(testRefVlan.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefVlan() throws Exception {
        int databaseSizeBeforeUpdate = refVlanRepository.findAll().size();

        // Create the RefVlan
        RefVlanDTO refVlanDTO = refVlanMapper.toDto(refVlan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefVlanMockMvc.perform(put("/api/ref-vlans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refVlanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefVlan in the database
        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefVlan() throws Exception {
        // Initialize the database
        refVlanRepository.saveAndFlush(refVlan);

        int databaseSizeBeforeDelete = refVlanRepository.findAll().size();

        // Delete the refVlan
        restRefVlanMockMvc.perform(delete("/api/ref-vlans/{id}", refVlan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefVlan> refVlanList = refVlanRepository.findAll();
        assertThat(refVlanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefVlan.class);
        RefVlan refVlan1 = new RefVlan();
        refVlan1.setId(1L);
        RefVlan refVlan2 = new RefVlan();
        refVlan2.setId(refVlan1.getId());
        assertThat(refVlan1).isEqualTo(refVlan2);
        refVlan2.setId(2L);
        assertThat(refVlan1).isNotEqualTo(refVlan2);
        refVlan1.setId(null);
        assertThat(refVlan1).isNotEqualTo(refVlan2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefVlanDTO.class);
        RefVlanDTO refVlanDTO1 = new RefVlanDTO();
        refVlanDTO1.setId(1L);
        RefVlanDTO refVlanDTO2 = new RefVlanDTO();
        assertThat(refVlanDTO1).isNotEqualTo(refVlanDTO2);
        refVlanDTO2.setId(refVlanDTO1.getId());
        assertThat(refVlanDTO1).isEqualTo(refVlanDTO2);
        refVlanDTO2.setId(2L);
        assertThat(refVlanDTO1).isNotEqualTo(refVlanDTO2);
        refVlanDTO1.setId(null);
        assertThat(refVlanDTO1).isNotEqualTo(refVlanDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refVlanMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refVlanMapper.fromId(null)).isNull();
    }
}

package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefTypeFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeFluxService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefTypeFluxMapper;
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
 * Test class for the RefTypeFluxResource REST controller.
 *
 * @see RefTypeFluxResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefTypeFluxResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefTypeFluxRepository refTypeFluxRepository;

    @Autowired
    private RefTypeFluxMapper refTypeFluxMapper;

    @Autowired
    private RefTypeFluxService refTypeFluxService;

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

    private MockMvc restRefTypeFluxMockMvc;

    private RefTypeFlux refTypeFlux;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefTypeFluxResource refTypeFluxResource = new RefTypeFluxResource(refTypeFluxService);
        this.restRefTypeFluxMockMvc = MockMvcBuilders.standaloneSetup(refTypeFluxResource)
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
    public static RefTypeFlux createEntity(EntityManager em) {
        RefTypeFlux refTypeFlux = new RefTypeFlux()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refTypeFlux;
    }

    @Before
    public void initTest() {
        refTypeFlux = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefTypeFlux() throws Exception {
        int databaseSizeBeforeCreate = refTypeFluxRepository.findAll().size();

        // Create the RefTypeFlux
        RefTypeFluxDTO refTypeFluxDTO = refTypeFluxMapper.toDto(refTypeFlux);
        restRefTypeFluxMockMvc.perform(post("/api/ref-type-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFluxDTO)))
            .andExpect(status().isCreated());

        // Validate the RefTypeFlux in the database
        List<RefTypeFlux> refTypeFluxList = refTypeFluxRepository.findAll();
        assertThat(refTypeFluxList).hasSize(databaseSizeBeforeCreate + 1);
        RefTypeFlux testRefTypeFlux = refTypeFluxList.get(refTypeFluxList.size() - 1);
        assertThat(testRefTypeFlux.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefTypeFlux.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefTypeFluxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refTypeFluxRepository.findAll().size();

        // Create the RefTypeFlux with an existing ID
        refTypeFlux.setId(1L);
        RefTypeFluxDTO refTypeFluxDTO = refTypeFluxMapper.toDto(refTypeFlux);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefTypeFluxMockMvc.perform(post("/api/ref-type-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFluxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefTypeFlux in the database
        List<RefTypeFlux> refTypeFluxList = refTypeFluxRepository.findAll();
        assertThat(refTypeFluxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refTypeFluxRepository.findAll().size();
        // set the field null
        refTypeFlux.setCode(null);

        // Create the RefTypeFlux, which fails.
        RefTypeFluxDTO refTypeFluxDTO = refTypeFluxMapper.toDto(refTypeFlux);

        restRefTypeFluxMockMvc.perform(post("/api/ref-type-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefTypeFlux> refTypeFluxList = refTypeFluxRepository.findAll();
        assertThat(refTypeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refTypeFluxRepository.findAll().size();
        // set the field null
        refTypeFlux.setLibelle(null);

        // Create the RefTypeFlux, which fails.
        RefTypeFluxDTO refTypeFluxDTO = refTypeFluxMapper.toDto(refTypeFlux);

        restRefTypeFluxMockMvc.perform(post("/api/ref-type-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefTypeFlux> refTypeFluxList = refTypeFluxRepository.findAll();
        assertThat(refTypeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefTypeFluxes() throws Exception {
        // Initialize the database
        refTypeFluxRepository.saveAndFlush(refTypeFlux);

        // Get all the refTypeFluxList
        restRefTypeFluxMockMvc.perform(get("/api/ref-type-fluxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refTypeFlux.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefTypeFlux() throws Exception {
        // Initialize the database
        refTypeFluxRepository.saveAndFlush(refTypeFlux);

        // Get the refTypeFlux
        restRefTypeFluxMockMvc.perform(get("/api/ref-type-fluxes/{id}", refTypeFlux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refTypeFlux.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefTypeFlux() throws Exception {
        // Get the refTypeFlux
        restRefTypeFluxMockMvc.perform(get("/api/ref-type-fluxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefTypeFlux() throws Exception {
        // Initialize the database
        refTypeFluxRepository.saveAndFlush(refTypeFlux);

        int databaseSizeBeforeUpdate = refTypeFluxRepository.findAll().size();

        // Update the refTypeFlux
        RefTypeFlux updatedRefTypeFlux = refTypeFluxRepository.findById(refTypeFlux.getId()).get();
        // Disconnect from session so that the updates on updatedRefTypeFlux are not directly saved in db
        em.detach(updatedRefTypeFlux);
        updatedRefTypeFlux
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RefTypeFluxDTO refTypeFluxDTO = refTypeFluxMapper.toDto(updatedRefTypeFlux);

        restRefTypeFluxMockMvc.perform(put("/api/ref-type-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFluxDTO)))
            .andExpect(status().isOk());

        // Validate the RefTypeFlux in the database
        List<RefTypeFlux> refTypeFluxList = refTypeFluxRepository.findAll();
        assertThat(refTypeFluxList).hasSize(databaseSizeBeforeUpdate);
        RefTypeFlux testRefTypeFlux = refTypeFluxList.get(refTypeFluxList.size() - 1);
        assertThat(testRefTypeFlux.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefTypeFlux.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefTypeFlux() throws Exception {
        int databaseSizeBeforeUpdate = refTypeFluxRepository.findAll().size();

        // Create the RefTypeFlux
        RefTypeFluxDTO refTypeFluxDTO = refTypeFluxMapper.toDto(refTypeFlux);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefTypeFluxMockMvc.perform(put("/api/ref-type-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeFluxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefTypeFlux in the database
        List<RefTypeFlux> refTypeFluxList = refTypeFluxRepository.findAll();
        assertThat(refTypeFluxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefTypeFlux() throws Exception {
        // Initialize the database
        refTypeFluxRepository.saveAndFlush(refTypeFlux);

        int databaseSizeBeforeDelete = refTypeFluxRepository.findAll().size();

        // Delete the refTypeFlux
        restRefTypeFluxMockMvc.perform(delete("/api/ref-type-fluxes/{id}", refTypeFlux.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefTypeFlux> refTypeFluxList = refTypeFluxRepository.findAll();
        assertThat(refTypeFluxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefTypeFlux.class);
        RefTypeFlux refTypeFlux1 = new RefTypeFlux();
        refTypeFlux1.setId(1L);
        RefTypeFlux refTypeFlux2 = new RefTypeFlux();
        refTypeFlux2.setId(refTypeFlux1.getId());
        assertThat(refTypeFlux1).isEqualTo(refTypeFlux2);
        refTypeFlux2.setId(2L);
        assertThat(refTypeFlux1).isNotEqualTo(refTypeFlux2);
        refTypeFlux1.setId(null);
        assertThat(refTypeFlux1).isNotEqualTo(refTypeFlux2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefTypeFluxDTO.class);
        RefTypeFluxDTO refTypeFluxDTO1 = new RefTypeFluxDTO();
        refTypeFluxDTO1.setId(1L);
        RefTypeFluxDTO refTypeFluxDTO2 = new RefTypeFluxDTO();
        assertThat(refTypeFluxDTO1).isNotEqualTo(refTypeFluxDTO2);
        refTypeFluxDTO2.setId(refTypeFluxDTO1.getId());
        assertThat(refTypeFluxDTO1).isEqualTo(refTypeFluxDTO2);
        refTypeFluxDTO2.setId(2L);
        assertThat(refTypeFluxDTO1).isNotEqualTo(refTypeFluxDTO2);
        refTypeFluxDTO1.setId(null);
        assertThat(refTypeFluxDTO1).isNotEqualTo(refTypeFluxDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refTypeFluxMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refTypeFluxMapper.fromId(null)).isNull();
    }
}

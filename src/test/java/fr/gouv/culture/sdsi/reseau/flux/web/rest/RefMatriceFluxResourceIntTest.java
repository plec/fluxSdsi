package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefMatriceFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefMatriceFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefMatriceFluxService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefMatriceFluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefMatriceFluxMapper;
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
 * Test class for the RefMatriceFluxResource REST controller.
 *
 * @see RefMatriceFluxResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefMatriceFluxResourceIntTest {

    private static final String DEFAULT_ENVIRONNEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ENVIRONNEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_FLUX = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FLUX = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_VLAN = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_VLAN = "BBBBBBBBBB";

    private static final String DEFAULT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_VLAN = "AAAAAAAAAA";
    private static final String UPDATED_DEST_VLAN = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_AUTHORISATION = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_AUTHORISATION = "BBBBBBBBBB";

    @Autowired
    private RefMatriceFluxRepository refMatriceFluxRepository;

    @Autowired
    private RefMatriceFluxMapper refMatriceFluxMapper;

    @Autowired
    private RefMatriceFluxService refMatriceFluxService;

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

    private MockMvc restRefMatriceFluxMockMvc;

    private RefMatriceFlux refMatriceFlux;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefMatriceFluxResource refMatriceFluxResource = new RefMatriceFluxResource(refMatriceFluxService);
        this.restRefMatriceFluxMockMvc = MockMvcBuilders.standaloneSetup(refMatriceFluxResource)
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
    public static RefMatriceFlux createEntity(EntityManager em) {
        RefMatriceFlux refMatriceFlux = new RefMatriceFlux()
            .environnement(DEFAULT_ENVIRONNEMENT)
            .typeFlux(DEFAULT_TYPE_FLUX)
            .sourceVlan(DEFAULT_SOURCE_VLAN)
            .port(DEFAULT_PORT)
            .destVlan(DEFAULT_DEST_VLAN)
            .typeAuthorisation(DEFAULT_TYPE_AUTHORISATION);
        return refMatriceFlux;
    }

    @Before
    public void initTest() {
        refMatriceFlux = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefMatriceFlux() throws Exception {
        int databaseSizeBeforeCreate = refMatriceFluxRepository.findAll().size();

        // Create the RefMatriceFlux
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);
        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isCreated());

        // Validate the RefMatriceFlux in the database
        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeCreate + 1);
        RefMatriceFlux testRefMatriceFlux = refMatriceFluxList.get(refMatriceFluxList.size() - 1);
        assertThat(testRefMatriceFlux.getEnvironnement()).isEqualTo(DEFAULT_ENVIRONNEMENT);
        assertThat(testRefMatriceFlux.getTypeFlux()).isEqualTo(DEFAULT_TYPE_FLUX);
        assertThat(testRefMatriceFlux.getSourceVlan()).isEqualTo(DEFAULT_SOURCE_VLAN);
        assertThat(testRefMatriceFlux.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testRefMatriceFlux.getDestVlan()).isEqualTo(DEFAULT_DEST_VLAN);
        assertThat(testRefMatriceFlux.getTypeAuthorisation()).isEqualTo(DEFAULT_TYPE_AUTHORISATION);
    }

    @Test
    @Transactional
    public void createRefMatriceFluxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refMatriceFluxRepository.findAll().size();

        // Create the RefMatriceFlux with an existing ID
        refMatriceFlux.setId(1L);
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefMatriceFlux in the database
        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEnvironnementIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMatriceFluxRepository.findAll().size();
        // set the field null
        refMatriceFlux.setEnvironnement(null);

        // Create the RefMatriceFlux, which fails.
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeFluxIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMatriceFluxRepository.findAll().size();
        // set the field null
        refMatriceFlux.setTypeFlux(null);

        // Create the RefMatriceFlux, which fails.
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceVlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMatriceFluxRepository.findAll().size();
        // set the field null
        refMatriceFlux.setSourceVlan(null);

        // Create the RefMatriceFlux, which fails.
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPortIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMatriceFluxRepository.findAll().size();
        // set the field null
        refMatriceFlux.setPort(null);

        // Create the RefMatriceFlux, which fails.
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestVlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMatriceFluxRepository.findAll().size();
        // set the field null
        refMatriceFlux.setDestVlan(null);

        // Create the RefMatriceFlux, which fails.
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeAuthorisationIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMatriceFluxRepository.findAll().size();
        // set the field null
        refMatriceFlux.setTypeAuthorisation(null);

        // Create the RefMatriceFlux, which fails.
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        restRefMatriceFluxMockMvc.perform(post("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefMatriceFluxes() throws Exception {
        // Initialize the database
        refMatriceFluxRepository.saveAndFlush(refMatriceFlux);

        // Get all the refMatriceFluxList
        restRefMatriceFluxMockMvc.perform(get("/api/ref-matrice-fluxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refMatriceFlux.getId().intValue())))
            .andExpect(jsonPath("$.[*].environnement").value(hasItem(DEFAULT_ENVIRONNEMENT.toString())))
            .andExpect(jsonPath("$.[*].typeFlux").value(hasItem(DEFAULT_TYPE_FLUX.toString())))
            .andExpect(jsonPath("$.[*].sourceVlan").value(hasItem(DEFAULT_SOURCE_VLAN.toString())))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT.toString())))
            .andExpect(jsonPath("$.[*].destVlan").value(hasItem(DEFAULT_DEST_VLAN.toString())))
            .andExpect(jsonPath("$.[*].typeAuthorisation").value(hasItem(DEFAULT_TYPE_AUTHORISATION.toString())));
    }
    
    @Test
    @Transactional
    public void getRefMatriceFlux() throws Exception {
        // Initialize the database
        refMatriceFluxRepository.saveAndFlush(refMatriceFlux);

        // Get the refMatriceFlux
        restRefMatriceFluxMockMvc.perform(get("/api/ref-matrice-fluxes/{id}", refMatriceFlux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refMatriceFlux.getId().intValue()))
            .andExpect(jsonPath("$.environnement").value(DEFAULT_ENVIRONNEMENT.toString()))
            .andExpect(jsonPath("$.typeFlux").value(DEFAULT_TYPE_FLUX.toString()))
            .andExpect(jsonPath("$.sourceVlan").value(DEFAULT_SOURCE_VLAN.toString()))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT.toString()))
            .andExpect(jsonPath("$.destVlan").value(DEFAULT_DEST_VLAN.toString()))
            .andExpect(jsonPath("$.typeAuthorisation").value(DEFAULT_TYPE_AUTHORISATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefMatriceFlux() throws Exception {
        // Get the refMatriceFlux
        restRefMatriceFluxMockMvc.perform(get("/api/ref-matrice-fluxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefMatriceFlux() throws Exception {
        // Initialize the database
        refMatriceFluxRepository.saveAndFlush(refMatriceFlux);

        int databaseSizeBeforeUpdate = refMatriceFluxRepository.findAll().size();

        // Update the refMatriceFlux
        RefMatriceFlux updatedRefMatriceFlux = refMatriceFluxRepository.findById(refMatriceFlux.getId()).get();
        // Disconnect from session so that the updates on updatedRefMatriceFlux are not directly saved in db
        em.detach(updatedRefMatriceFlux);
        updatedRefMatriceFlux
            .environnement(UPDATED_ENVIRONNEMENT)
            .typeFlux(UPDATED_TYPE_FLUX)
            .sourceVlan(UPDATED_SOURCE_VLAN)
            .port(UPDATED_PORT)
            .destVlan(UPDATED_DEST_VLAN)
            .typeAuthorisation(UPDATED_TYPE_AUTHORISATION);
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(updatedRefMatriceFlux);

        restRefMatriceFluxMockMvc.perform(put("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isOk());

        // Validate the RefMatriceFlux in the database
        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeUpdate);
        RefMatriceFlux testRefMatriceFlux = refMatriceFluxList.get(refMatriceFluxList.size() - 1);
        assertThat(testRefMatriceFlux.getEnvironnement()).isEqualTo(UPDATED_ENVIRONNEMENT);
        assertThat(testRefMatriceFlux.getTypeFlux()).isEqualTo(UPDATED_TYPE_FLUX);
        assertThat(testRefMatriceFlux.getSourceVlan()).isEqualTo(UPDATED_SOURCE_VLAN);
        assertThat(testRefMatriceFlux.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testRefMatriceFlux.getDestVlan()).isEqualTo(UPDATED_DEST_VLAN);
        assertThat(testRefMatriceFlux.getTypeAuthorisation()).isEqualTo(UPDATED_TYPE_AUTHORISATION);
    }

    @Test
    @Transactional
    public void updateNonExistingRefMatriceFlux() throws Exception {
        int databaseSizeBeforeUpdate = refMatriceFluxRepository.findAll().size();

        // Create the RefMatriceFlux
        RefMatriceFluxDTO refMatriceFluxDTO = refMatriceFluxMapper.toDto(refMatriceFlux);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefMatriceFluxMockMvc.perform(put("/api/ref-matrice-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMatriceFluxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefMatriceFlux in the database
        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefMatriceFlux() throws Exception {
        // Initialize the database
        refMatriceFluxRepository.saveAndFlush(refMatriceFlux);

        int databaseSizeBeforeDelete = refMatriceFluxRepository.findAll().size();

        // Delete the refMatriceFlux
        restRefMatriceFluxMockMvc.perform(delete("/api/ref-matrice-fluxes/{id}", refMatriceFlux.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefMatriceFlux> refMatriceFluxList = refMatriceFluxRepository.findAll();
        assertThat(refMatriceFluxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefMatriceFlux.class);
        RefMatriceFlux refMatriceFlux1 = new RefMatriceFlux();
        refMatriceFlux1.setId(1L);
        RefMatriceFlux refMatriceFlux2 = new RefMatriceFlux();
        refMatriceFlux2.setId(refMatriceFlux1.getId());
        assertThat(refMatriceFlux1).isEqualTo(refMatriceFlux2);
        refMatriceFlux2.setId(2L);
        assertThat(refMatriceFlux1).isNotEqualTo(refMatriceFlux2);
        refMatriceFlux1.setId(null);
        assertThat(refMatriceFlux1).isNotEqualTo(refMatriceFlux2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefMatriceFluxDTO.class);
        RefMatriceFluxDTO refMatriceFluxDTO1 = new RefMatriceFluxDTO();
        refMatriceFluxDTO1.setId(1L);
        RefMatriceFluxDTO refMatriceFluxDTO2 = new RefMatriceFluxDTO();
        assertThat(refMatriceFluxDTO1).isNotEqualTo(refMatriceFluxDTO2);
        refMatriceFluxDTO2.setId(refMatriceFluxDTO1.getId());
        assertThat(refMatriceFluxDTO1).isEqualTo(refMatriceFluxDTO2);
        refMatriceFluxDTO2.setId(2L);
        assertThat(refMatriceFluxDTO1).isNotEqualTo(refMatriceFluxDTO2);
        refMatriceFluxDTO1.setId(null);
        assertThat(refMatriceFluxDTO1).isNotEqualTo(refMatriceFluxDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refMatriceFluxMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refMatriceFluxMapper.fromId(null)).isNull();
    }
}

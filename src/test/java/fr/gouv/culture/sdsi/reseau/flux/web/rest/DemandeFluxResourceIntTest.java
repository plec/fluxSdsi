package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.DemandeFlux;
import fr.gouv.culture.sdsi.reseau.flux.repository.DemandeFluxRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.DemandeFluxService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.DemandeFluxDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.DemandeFluxMapper;
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
 * Test class for the DemandeFluxResource REST controller.
 *
 * @see DemandeFluxResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class DemandeFluxResourceIntTest {

    private static final String DEFAULT_ENVIRONNEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ENVIRONNEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_FLUX = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FLUX = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_IP = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_IP = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_PORT = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_VLAN = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_VLAN = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_IP = "AAAAAAAAAA";
    private static final String UPDATED_DEST_IP = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_PORT = "AAAAAAAAAA";
    private static final String UPDATED_DEST_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_DEST_VLAN = "AAAAAAAAAA";
    private static final String UPDATED_DEST_VLAN = "BBBBBBBBBB";

    @Autowired
    private DemandeFluxRepository demandeFluxRepository;

    @Autowired
    private DemandeFluxMapper demandeFluxMapper;

    @Autowired
    private DemandeFluxService demandeFluxService;

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

    private MockMvc restDemandeFluxMockMvc;

    private DemandeFlux demandeFlux;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DemandeFluxResource demandeFluxResource = new DemandeFluxResource(demandeFluxService);
        this.restDemandeFluxMockMvc = MockMvcBuilders.standaloneSetup(demandeFluxResource)
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
    public static DemandeFlux createEntity(EntityManager em) {
        DemandeFlux demandeFlux = new DemandeFlux()
            .environnement(DEFAULT_ENVIRONNEMENT)
            .typeFlux(DEFAULT_TYPE_FLUX)
            .sourceIP(DEFAULT_SOURCE_IP)
            .sourcePort(DEFAULT_SOURCE_PORT)
            .sourceVlan(DEFAULT_SOURCE_VLAN)
            .destIP(DEFAULT_DEST_IP)
            .destPort(DEFAULT_DEST_PORT)
            .destVlan(DEFAULT_DEST_VLAN);
        return demandeFlux;
    }

    @Before
    public void initTest() {
        demandeFlux = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemandeFlux() throws Exception {
        int databaseSizeBeforeCreate = demandeFluxRepository.findAll().size();

        // Create the DemandeFlux
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);
        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isCreated());

        // Validate the DemandeFlux in the database
        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeCreate + 1);
        DemandeFlux testDemandeFlux = demandeFluxList.get(demandeFluxList.size() - 1);
        assertThat(testDemandeFlux.getEnvironnement()).isEqualTo(DEFAULT_ENVIRONNEMENT);
        assertThat(testDemandeFlux.getTypeFlux()).isEqualTo(DEFAULT_TYPE_FLUX);
        assertThat(testDemandeFlux.getSourceIP()).isEqualTo(DEFAULT_SOURCE_IP);
        assertThat(testDemandeFlux.getSourcePort()).isEqualTo(DEFAULT_SOURCE_PORT);
        assertThat(testDemandeFlux.getSourceVlan()).isEqualTo(DEFAULT_SOURCE_VLAN);
        assertThat(testDemandeFlux.getDestIP()).isEqualTo(DEFAULT_DEST_IP);
        assertThat(testDemandeFlux.getDestPort()).isEqualTo(DEFAULT_DEST_PORT);
        assertThat(testDemandeFlux.getDestVlan()).isEqualTo(DEFAULT_DEST_VLAN);
    }

    @Test
    @Transactional
    public void createDemandeFluxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeFluxRepository.findAll().size();

        // Create the DemandeFlux with an existing ID
        demandeFlux.setId(1L);
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeFlux in the database
        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEnvironnementIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeFluxRepository.findAll().size();
        // set the field null
        demandeFlux.setEnvironnement(null);

        // Create the DemandeFlux, which fails.
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeFluxIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeFluxRepository.findAll().size();
        // set the field null
        demandeFlux.setTypeFlux(null);

        // Create the DemandeFlux, which fails.
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceIPIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeFluxRepository.findAll().size();
        // set the field null
        demandeFlux.setSourceIP(null);

        // Create the DemandeFlux, which fails.
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceVlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeFluxRepository.findAll().size();
        // set the field null
        demandeFlux.setSourceVlan(null);

        // Create the DemandeFlux, which fails.
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestIPIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeFluxRepository.findAll().size();
        // set the field null
        demandeFlux.setDestIP(null);

        // Create the DemandeFlux, which fails.
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestVlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeFluxRepository.findAll().size();
        // set the field null
        demandeFlux.setDestVlan(null);

        // Create the DemandeFlux, which fails.
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        restDemandeFluxMockMvc.perform(post("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDemandeFluxes() throws Exception {
        // Initialize the database
        demandeFluxRepository.saveAndFlush(demandeFlux);

        // Get all the demandeFluxList
        restDemandeFluxMockMvc.perform(get("/api/demande-fluxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeFlux.getId().intValue())))
            .andExpect(jsonPath("$.[*].environnement").value(hasItem(DEFAULT_ENVIRONNEMENT.toString())))
            .andExpect(jsonPath("$.[*].typeFlux").value(hasItem(DEFAULT_TYPE_FLUX.toString())))
            .andExpect(jsonPath("$.[*].sourceIP").value(hasItem(DEFAULT_SOURCE_IP.toString())))
            .andExpect(jsonPath("$.[*].sourcePort").value(hasItem(DEFAULT_SOURCE_PORT.toString())))
            .andExpect(jsonPath("$.[*].sourceVlan").value(hasItem(DEFAULT_SOURCE_VLAN.toString())))
            .andExpect(jsonPath("$.[*].destIP").value(hasItem(DEFAULT_DEST_IP.toString())))
            .andExpect(jsonPath("$.[*].destPort").value(hasItem(DEFAULT_DEST_PORT.toString())))
            .andExpect(jsonPath("$.[*].destVlan").value(hasItem(DEFAULT_DEST_VLAN.toString())));
    }
    
    @Test
    @Transactional
    public void getDemandeFlux() throws Exception {
        // Initialize the database
        demandeFluxRepository.saveAndFlush(demandeFlux);

        // Get the demandeFlux
        restDemandeFluxMockMvc.perform(get("/api/demande-fluxes/{id}", demandeFlux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(demandeFlux.getId().intValue()))
            .andExpect(jsonPath("$.environnement").value(DEFAULT_ENVIRONNEMENT.toString()))
            .andExpect(jsonPath("$.typeFlux").value(DEFAULT_TYPE_FLUX.toString()))
            .andExpect(jsonPath("$.sourceIP").value(DEFAULT_SOURCE_IP.toString()))
            .andExpect(jsonPath("$.sourcePort").value(DEFAULT_SOURCE_PORT.toString()))
            .andExpect(jsonPath("$.sourceVlan").value(DEFAULT_SOURCE_VLAN.toString()))
            .andExpect(jsonPath("$.destIP").value(DEFAULT_DEST_IP.toString()))
            .andExpect(jsonPath("$.destPort").value(DEFAULT_DEST_PORT.toString()))
            .andExpect(jsonPath("$.destVlan").value(DEFAULT_DEST_VLAN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemandeFlux() throws Exception {
        // Get the demandeFlux
        restDemandeFluxMockMvc.perform(get("/api/demande-fluxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemandeFlux() throws Exception {
        // Initialize the database
        demandeFluxRepository.saveAndFlush(demandeFlux);

        int databaseSizeBeforeUpdate = demandeFluxRepository.findAll().size();

        // Update the demandeFlux
        DemandeFlux updatedDemandeFlux = demandeFluxRepository.findById(demandeFlux.getId()).get();
        // Disconnect from session so that the updates on updatedDemandeFlux are not directly saved in db
        em.detach(updatedDemandeFlux);
        updatedDemandeFlux
            .environnement(UPDATED_ENVIRONNEMENT)
            .typeFlux(UPDATED_TYPE_FLUX)
            .sourceIP(UPDATED_SOURCE_IP)
            .sourcePort(UPDATED_SOURCE_PORT)
            .sourceVlan(UPDATED_SOURCE_VLAN)
            .destIP(UPDATED_DEST_IP)
            .destPort(UPDATED_DEST_PORT)
            .destVlan(UPDATED_DEST_VLAN);
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(updatedDemandeFlux);

        restDemandeFluxMockMvc.perform(put("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isOk());

        // Validate the DemandeFlux in the database
        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeUpdate);
        DemandeFlux testDemandeFlux = demandeFluxList.get(demandeFluxList.size() - 1);
        assertThat(testDemandeFlux.getEnvironnement()).isEqualTo(UPDATED_ENVIRONNEMENT);
        assertThat(testDemandeFlux.getTypeFlux()).isEqualTo(UPDATED_TYPE_FLUX);
        assertThat(testDemandeFlux.getSourceIP()).isEqualTo(UPDATED_SOURCE_IP);
        assertThat(testDemandeFlux.getSourcePort()).isEqualTo(UPDATED_SOURCE_PORT);
        assertThat(testDemandeFlux.getSourceVlan()).isEqualTo(UPDATED_SOURCE_VLAN);
        assertThat(testDemandeFlux.getDestIP()).isEqualTo(UPDATED_DEST_IP);
        assertThat(testDemandeFlux.getDestPort()).isEqualTo(UPDATED_DEST_PORT);
        assertThat(testDemandeFlux.getDestVlan()).isEqualTo(UPDATED_DEST_VLAN);
    }

    @Test
    @Transactional
    public void updateNonExistingDemandeFlux() throws Exception {
        int databaseSizeBeforeUpdate = demandeFluxRepository.findAll().size();

        // Create the DemandeFlux
        DemandeFluxDTO demandeFluxDTO = demandeFluxMapper.toDto(demandeFlux);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeFluxMockMvc.perform(put("/api/demande-fluxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeFluxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DemandeFlux in the database
        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemandeFlux() throws Exception {
        // Initialize the database
        demandeFluxRepository.saveAndFlush(demandeFlux);

        int databaseSizeBeforeDelete = demandeFluxRepository.findAll().size();

        // Delete the demandeFlux
        restDemandeFluxMockMvc.perform(delete("/api/demande-fluxes/{id}", demandeFlux.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DemandeFlux> demandeFluxList = demandeFluxRepository.findAll();
        assertThat(demandeFluxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandeFlux.class);
        DemandeFlux demandeFlux1 = new DemandeFlux();
        demandeFlux1.setId(1L);
        DemandeFlux demandeFlux2 = new DemandeFlux();
        demandeFlux2.setId(demandeFlux1.getId());
        assertThat(demandeFlux1).isEqualTo(demandeFlux2);
        demandeFlux2.setId(2L);
        assertThat(demandeFlux1).isNotEqualTo(demandeFlux2);
        demandeFlux1.setId(null);
        assertThat(demandeFlux1).isNotEqualTo(demandeFlux2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandeFluxDTO.class);
        DemandeFluxDTO demandeFluxDTO1 = new DemandeFluxDTO();
        demandeFluxDTO1.setId(1L);
        DemandeFluxDTO demandeFluxDTO2 = new DemandeFluxDTO();
        assertThat(demandeFluxDTO1).isNotEqualTo(demandeFluxDTO2);
        demandeFluxDTO2.setId(demandeFluxDTO1.getId());
        assertThat(demandeFluxDTO1).isEqualTo(demandeFluxDTO2);
        demandeFluxDTO2.setId(2L);
        assertThat(demandeFluxDTO1).isNotEqualTo(demandeFluxDTO2);
        demandeFluxDTO1.setId(null);
        assertThat(demandeFluxDTO1).isNotEqualTo(demandeFluxDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(demandeFluxMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(demandeFluxMapper.fromId(null)).isNull();
    }
}

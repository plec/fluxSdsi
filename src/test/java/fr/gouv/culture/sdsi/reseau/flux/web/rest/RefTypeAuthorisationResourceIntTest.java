package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefTypeAuthorisation;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefTypeAuthorisationRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefTypeAuthorisationService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeAuthorisationDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefTypeAuthorisationMapper;
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
 * Test class for the RefTypeAuthorisationResource REST controller.
 *
 * @see RefTypeAuthorisationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefTypeAuthorisationResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefTypeAuthorisationRepository refTypeAuthorisationRepository;

    @Autowired
    private RefTypeAuthorisationMapper refTypeAuthorisationMapper;

    @Autowired
    private RefTypeAuthorisationService refTypeAuthorisationService;

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

    private MockMvc restRefTypeAuthorisationMockMvc;

    private RefTypeAuthorisation refTypeAuthorisation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefTypeAuthorisationResource refTypeAuthorisationResource = new RefTypeAuthorisationResource(refTypeAuthorisationService);
        this.restRefTypeAuthorisationMockMvc = MockMvcBuilders.standaloneSetup(refTypeAuthorisationResource)
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
    public static RefTypeAuthorisation createEntity(EntityManager em) {
        RefTypeAuthorisation refTypeAuthorisation = new RefTypeAuthorisation()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refTypeAuthorisation;
    }

    @Before
    public void initTest() {
        refTypeAuthorisation = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefTypeAuthorisation() throws Exception {
        int databaseSizeBeforeCreate = refTypeAuthorisationRepository.findAll().size();

        // Create the RefTypeAuthorisation
        RefTypeAuthorisationDTO refTypeAuthorisationDTO = refTypeAuthorisationMapper.toDto(refTypeAuthorisation);
        restRefTypeAuthorisationMockMvc.perform(post("/api/ref-type-authorisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeAuthorisationDTO)))
            .andExpect(status().isCreated());

        // Validate the RefTypeAuthorisation in the database
        List<RefTypeAuthorisation> refTypeAuthorisationList = refTypeAuthorisationRepository.findAll();
        assertThat(refTypeAuthorisationList).hasSize(databaseSizeBeforeCreate + 1);
        RefTypeAuthorisation testRefTypeAuthorisation = refTypeAuthorisationList.get(refTypeAuthorisationList.size() - 1);
        assertThat(testRefTypeAuthorisation.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefTypeAuthorisation.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefTypeAuthorisationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refTypeAuthorisationRepository.findAll().size();

        // Create the RefTypeAuthorisation with an existing ID
        refTypeAuthorisation.setId(1L);
        RefTypeAuthorisationDTO refTypeAuthorisationDTO = refTypeAuthorisationMapper.toDto(refTypeAuthorisation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefTypeAuthorisationMockMvc.perform(post("/api/ref-type-authorisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeAuthorisationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefTypeAuthorisation in the database
        List<RefTypeAuthorisation> refTypeAuthorisationList = refTypeAuthorisationRepository.findAll();
        assertThat(refTypeAuthorisationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refTypeAuthorisationRepository.findAll().size();
        // set the field null
        refTypeAuthorisation.setCode(null);

        // Create the RefTypeAuthorisation, which fails.
        RefTypeAuthorisationDTO refTypeAuthorisationDTO = refTypeAuthorisationMapper.toDto(refTypeAuthorisation);

        restRefTypeAuthorisationMockMvc.perform(post("/api/ref-type-authorisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeAuthorisationDTO)))
            .andExpect(status().isBadRequest());

        List<RefTypeAuthorisation> refTypeAuthorisationList = refTypeAuthorisationRepository.findAll();
        assertThat(refTypeAuthorisationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refTypeAuthorisationRepository.findAll().size();
        // set the field null
        refTypeAuthorisation.setLibelle(null);

        // Create the RefTypeAuthorisation, which fails.
        RefTypeAuthorisationDTO refTypeAuthorisationDTO = refTypeAuthorisationMapper.toDto(refTypeAuthorisation);

        restRefTypeAuthorisationMockMvc.perform(post("/api/ref-type-authorisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeAuthorisationDTO)))
            .andExpect(status().isBadRequest());

        List<RefTypeAuthorisation> refTypeAuthorisationList = refTypeAuthorisationRepository.findAll();
        assertThat(refTypeAuthorisationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefTypeAuthorisations() throws Exception {
        // Initialize the database
        refTypeAuthorisationRepository.saveAndFlush(refTypeAuthorisation);

        // Get all the refTypeAuthorisationList
        restRefTypeAuthorisationMockMvc.perform(get("/api/ref-type-authorisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refTypeAuthorisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefTypeAuthorisation() throws Exception {
        // Initialize the database
        refTypeAuthorisationRepository.saveAndFlush(refTypeAuthorisation);

        // Get the refTypeAuthorisation
        restRefTypeAuthorisationMockMvc.perform(get("/api/ref-type-authorisations/{id}", refTypeAuthorisation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refTypeAuthorisation.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefTypeAuthorisation() throws Exception {
        // Get the refTypeAuthorisation
        restRefTypeAuthorisationMockMvc.perform(get("/api/ref-type-authorisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefTypeAuthorisation() throws Exception {
        // Initialize the database
        refTypeAuthorisationRepository.saveAndFlush(refTypeAuthorisation);

        int databaseSizeBeforeUpdate = refTypeAuthorisationRepository.findAll().size();

        // Update the refTypeAuthorisation
        RefTypeAuthorisation updatedRefTypeAuthorisation = refTypeAuthorisationRepository.findById(refTypeAuthorisation.getId()).get();
        // Disconnect from session so that the updates on updatedRefTypeAuthorisation are not directly saved in db
        em.detach(updatedRefTypeAuthorisation);
        updatedRefTypeAuthorisation
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RefTypeAuthorisationDTO refTypeAuthorisationDTO = refTypeAuthorisationMapper.toDto(updatedRefTypeAuthorisation);

        restRefTypeAuthorisationMockMvc.perform(put("/api/ref-type-authorisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeAuthorisationDTO)))
            .andExpect(status().isOk());

        // Validate the RefTypeAuthorisation in the database
        List<RefTypeAuthorisation> refTypeAuthorisationList = refTypeAuthorisationRepository.findAll();
        assertThat(refTypeAuthorisationList).hasSize(databaseSizeBeforeUpdate);
        RefTypeAuthorisation testRefTypeAuthorisation = refTypeAuthorisationList.get(refTypeAuthorisationList.size() - 1);
        assertThat(testRefTypeAuthorisation.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefTypeAuthorisation.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefTypeAuthorisation() throws Exception {
        int databaseSizeBeforeUpdate = refTypeAuthorisationRepository.findAll().size();

        // Create the RefTypeAuthorisation
        RefTypeAuthorisationDTO refTypeAuthorisationDTO = refTypeAuthorisationMapper.toDto(refTypeAuthorisation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefTypeAuthorisationMockMvc.perform(put("/api/ref-type-authorisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeAuthorisationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefTypeAuthorisation in the database
        List<RefTypeAuthorisation> refTypeAuthorisationList = refTypeAuthorisationRepository.findAll();
        assertThat(refTypeAuthorisationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefTypeAuthorisation() throws Exception {
        // Initialize the database
        refTypeAuthorisationRepository.saveAndFlush(refTypeAuthorisation);

        int databaseSizeBeforeDelete = refTypeAuthorisationRepository.findAll().size();

        // Delete the refTypeAuthorisation
        restRefTypeAuthorisationMockMvc.perform(delete("/api/ref-type-authorisations/{id}", refTypeAuthorisation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefTypeAuthorisation> refTypeAuthorisationList = refTypeAuthorisationRepository.findAll();
        assertThat(refTypeAuthorisationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefTypeAuthorisation.class);
        RefTypeAuthorisation refTypeAuthorisation1 = new RefTypeAuthorisation();
        refTypeAuthorisation1.setId(1L);
        RefTypeAuthorisation refTypeAuthorisation2 = new RefTypeAuthorisation();
        refTypeAuthorisation2.setId(refTypeAuthorisation1.getId());
        assertThat(refTypeAuthorisation1).isEqualTo(refTypeAuthorisation2);
        refTypeAuthorisation2.setId(2L);
        assertThat(refTypeAuthorisation1).isNotEqualTo(refTypeAuthorisation2);
        refTypeAuthorisation1.setId(null);
        assertThat(refTypeAuthorisation1).isNotEqualTo(refTypeAuthorisation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefTypeAuthorisationDTO.class);
        RefTypeAuthorisationDTO refTypeAuthorisationDTO1 = new RefTypeAuthorisationDTO();
        refTypeAuthorisationDTO1.setId(1L);
        RefTypeAuthorisationDTO refTypeAuthorisationDTO2 = new RefTypeAuthorisationDTO();
        assertThat(refTypeAuthorisationDTO1).isNotEqualTo(refTypeAuthorisationDTO2);
        refTypeAuthorisationDTO2.setId(refTypeAuthorisationDTO1.getId());
        assertThat(refTypeAuthorisationDTO1).isEqualTo(refTypeAuthorisationDTO2);
        refTypeAuthorisationDTO2.setId(2L);
        assertThat(refTypeAuthorisationDTO1).isNotEqualTo(refTypeAuthorisationDTO2);
        refTypeAuthorisationDTO1.setId(null);
        assertThat(refTypeAuthorisationDTO1).isNotEqualTo(refTypeAuthorisationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refTypeAuthorisationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refTypeAuthorisationMapper.fromId(null)).isNull();
    }
}

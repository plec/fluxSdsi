package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefSite;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefSiteRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefSiteService;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefSiteDTO;
import fr.gouv.culture.sdsi.reseau.flux.service.mapper.RefSiteMapper;
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
 * Test class for the RefSiteResource REST controller.
 *
 * @see RefSiteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefSiteResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefSiteRepository refSiteRepository;

    @Autowired
    private RefSiteMapper refSiteMapper;

    @Autowired
    private RefSiteService refSiteService;

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

    private MockMvc restRefSiteMockMvc;

    private RefSite refSite;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefSiteResource refSiteResource = new RefSiteResource(refSiteService);
        this.restRefSiteMockMvc = MockMvcBuilders.standaloneSetup(refSiteResource)
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
    public static RefSite createEntity(EntityManager em) {
        RefSite refSite = new RefSite()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refSite;
    }

    @Before
    public void initTest() {
        refSite = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefSite() throws Exception {
        int databaseSizeBeforeCreate = refSiteRepository.findAll().size();

        // Create the RefSite
        RefSiteDTO refSiteDTO = refSiteMapper.toDto(refSite);
        restRefSiteMockMvc.perform(post("/api/ref-sites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSiteDTO)))
            .andExpect(status().isCreated());

        // Validate the RefSite in the database
        List<RefSite> refSiteList = refSiteRepository.findAll();
        assertThat(refSiteList).hasSize(databaseSizeBeforeCreate + 1);
        RefSite testRefSite = refSiteList.get(refSiteList.size() - 1);
        assertThat(testRefSite.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefSite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefSiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refSiteRepository.findAll().size();

        // Create the RefSite with an existing ID
        refSite.setId(1L);
        RefSiteDTO refSiteDTO = refSiteMapper.toDto(refSite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefSiteMockMvc.perform(post("/api/ref-sites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefSite in the database
        List<RefSite> refSiteList = refSiteRepository.findAll();
        assertThat(refSiteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refSiteRepository.findAll().size();
        // set the field null
        refSite.setCode(null);

        // Create the RefSite, which fails.
        RefSiteDTO refSiteDTO = refSiteMapper.toDto(refSite);

        restRefSiteMockMvc.perform(post("/api/ref-sites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSiteDTO)))
            .andExpect(status().isBadRequest());

        List<RefSite> refSiteList = refSiteRepository.findAll();
        assertThat(refSiteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refSiteRepository.findAll().size();
        // set the field null
        refSite.setLibelle(null);

        // Create the RefSite, which fails.
        RefSiteDTO refSiteDTO = refSiteMapper.toDto(refSite);

        restRefSiteMockMvc.perform(post("/api/ref-sites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSiteDTO)))
            .andExpect(status().isBadRequest());

        List<RefSite> refSiteList = refSiteRepository.findAll();
        assertThat(refSiteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefSites() throws Exception {
        // Initialize the database
        refSiteRepository.saveAndFlush(refSite);

        // Get all the refSiteList
        restRefSiteMockMvc.perform(get("/api/ref-sites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refSite.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefSite() throws Exception {
        // Initialize the database
        refSiteRepository.saveAndFlush(refSite);

        // Get the refSite
        restRefSiteMockMvc.perform(get("/api/ref-sites/{id}", refSite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refSite.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefSite() throws Exception {
        // Get the refSite
        restRefSiteMockMvc.perform(get("/api/ref-sites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefSite() throws Exception {
        // Initialize the database
        refSiteRepository.saveAndFlush(refSite);

        int databaseSizeBeforeUpdate = refSiteRepository.findAll().size();

        // Update the refSite
        RefSite updatedRefSite = refSiteRepository.findById(refSite.getId()).get();
        // Disconnect from session so that the updates on updatedRefSite are not directly saved in db
        em.detach(updatedRefSite);
        updatedRefSite
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RefSiteDTO refSiteDTO = refSiteMapper.toDto(updatedRefSite);

        restRefSiteMockMvc.perform(put("/api/ref-sites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSiteDTO)))
            .andExpect(status().isOk());

        // Validate the RefSite in the database
        List<RefSite> refSiteList = refSiteRepository.findAll();
        assertThat(refSiteList).hasSize(databaseSizeBeforeUpdate);
        RefSite testRefSite = refSiteList.get(refSiteList.size() - 1);
        assertThat(testRefSite.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefSite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefSite() throws Exception {
        int databaseSizeBeforeUpdate = refSiteRepository.findAll().size();

        // Create the RefSite
        RefSiteDTO refSiteDTO = refSiteMapper.toDto(refSite);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefSiteMockMvc.perform(put("/api/ref-sites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefSite in the database
        List<RefSite> refSiteList = refSiteRepository.findAll();
        assertThat(refSiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefSite() throws Exception {
        // Initialize the database
        refSiteRepository.saveAndFlush(refSite);

        int databaseSizeBeforeDelete = refSiteRepository.findAll().size();

        // Delete the refSite
        restRefSiteMockMvc.perform(delete("/api/ref-sites/{id}", refSite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefSite> refSiteList = refSiteRepository.findAll();
        assertThat(refSiteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefSite.class);
        RefSite refSite1 = new RefSite();
        refSite1.setId(1L);
        RefSite refSite2 = new RefSite();
        refSite2.setId(refSite1.getId());
        assertThat(refSite1).isEqualTo(refSite2);
        refSite2.setId(2L);
        assertThat(refSite1).isNotEqualTo(refSite2);
        refSite1.setId(null);
        assertThat(refSite1).isNotEqualTo(refSite2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefSiteDTO.class);
        RefSiteDTO refSiteDTO1 = new RefSiteDTO();
        refSiteDTO1.setId(1L);
        RefSiteDTO refSiteDTO2 = new RefSiteDTO();
        assertThat(refSiteDTO1).isNotEqualTo(refSiteDTO2);
        refSiteDTO2.setId(refSiteDTO1.getId());
        assertThat(refSiteDTO1).isEqualTo(refSiteDTO2);
        refSiteDTO2.setId(2L);
        assertThat(refSiteDTO1).isNotEqualTo(refSiteDTO2);
        refSiteDTO1.setId(null);
        assertThat(refSiteDTO1).isNotEqualTo(refSiteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(refSiteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(refSiteMapper.fromId(null)).isNull();
    }
}

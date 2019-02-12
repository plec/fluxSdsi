package fr.gouv.culture.sdsi.reseau.flux.web.rest;

import fr.gouv.culture.sdsi.reseau.flux.FluxSdsiApp;

import fr.gouv.culture.sdsi.reseau.flux.domain.RefNumero;
import fr.gouv.culture.sdsi.reseau.flux.repository.RefNumeroRepository;
import fr.gouv.culture.sdsi.reseau.flux.service.RefNumeroService;
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
 * Test class for the RefNumeroResource REST controller.
 *
 * @see RefNumeroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FluxSdsiApp.class)
public class RefNumeroResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefNumeroRepository refNumeroRepository;

    @Autowired
    private RefNumeroService refNumeroService;

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

    private MockMvc restRefNumeroMockMvc;

    private RefNumero refNumero;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefNumeroResource refNumeroResource = new RefNumeroResource(refNumeroService);
        this.restRefNumeroMockMvc = MockMvcBuilders.standaloneSetup(refNumeroResource)
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
    public static RefNumero createEntity(EntityManager em) {
        RefNumero refNumero = new RefNumero()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return refNumero;
    }

    @Before
    public void initTest() {
        refNumero = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefNumero() throws Exception {
        int databaseSizeBeforeCreate = refNumeroRepository.findAll().size();

        // Create the RefNumero
        restRefNumeroMockMvc.perform(post("/api/ref-numeros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refNumero)))
            .andExpect(status().isCreated());

        // Validate the RefNumero in the database
        List<RefNumero> refNumeroList = refNumeroRepository.findAll();
        assertThat(refNumeroList).hasSize(databaseSizeBeforeCreate + 1);
        RefNumero testRefNumero = refNumeroList.get(refNumeroList.size() - 1);
        assertThat(testRefNumero.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRefNumero.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefNumeroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refNumeroRepository.findAll().size();

        // Create the RefNumero with an existing ID
        refNumero.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefNumeroMockMvc.perform(post("/api/ref-numeros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refNumero)))
            .andExpect(status().isBadRequest());

        // Validate the RefNumero in the database
        List<RefNumero> refNumeroList = refNumeroRepository.findAll();
        assertThat(refNumeroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refNumeroRepository.findAll().size();
        // set the field null
        refNumero.setCode(null);

        // Create the RefNumero, which fails.

        restRefNumeroMockMvc.perform(post("/api/ref-numeros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refNumero)))
            .andExpect(status().isBadRequest());

        List<RefNumero> refNumeroList = refNumeroRepository.findAll();
        assertThat(refNumeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refNumeroRepository.findAll().size();
        // set the field null
        refNumero.setLibelle(null);

        // Create the RefNumero, which fails.

        restRefNumeroMockMvc.perform(post("/api/ref-numeros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refNumero)))
            .andExpect(status().isBadRequest());

        List<RefNumero> refNumeroList = refNumeroRepository.findAll();
        assertThat(refNumeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefNumeros() throws Exception {
        // Initialize the database
        refNumeroRepository.saveAndFlush(refNumero);

        // Get all the refNumeroList
        restRefNumeroMockMvc.perform(get("/api/ref-numeros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refNumero.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getRefNumero() throws Exception {
        // Initialize the database
        refNumeroRepository.saveAndFlush(refNumero);

        // Get the refNumero
        restRefNumeroMockMvc.perform(get("/api/ref-numeros/{id}", refNumero.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refNumero.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefNumero() throws Exception {
        // Get the refNumero
        restRefNumeroMockMvc.perform(get("/api/ref-numeros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefNumero() throws Exception {
        // Initialize the database
        refNumeroService.save(refNumero);

        int databaseSizeBeforeUpdate = refNumeroRepository.findAll().size();

        // Update the refNumero
        RefNumero updatedRefNumero = refNumeroRepository.findById(refNumero.getId()).get();
        // Disconnect from session so that the updates on updatedRefNumero are not directly saved in db
        em.detach(updatedRefNumero);
        updatedRefNumero
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restRefNumeroMockMvc.perform(put("/api/ref-numeros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefNumero)))
            .andExpect(status().isOk());

        // Validate the RefNumero in the database
        List<RefNumero> refNumeroList = refNumeroRepository.findAll();
        assertThat(refNumeroList).hasSize(databaseSizeBeforeUpdate);
        RefNumero testRefNumero = refNumeroList.get(refNumeroList.size() - 1);
        assertThat(testRefNumero.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRefNumero.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefNumero() throws Exception {
        int databaseSizeBeforeUpdate = refNumeroRepository.findAll().size();

        // Create the RefNumero

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefNumeroMockMvc.perform(put("/api/ref-numeros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refNumero)))
            .andExpect(status().isBadRequest());

        // Validate the RefNumero in the database
        List<RefNumero> refNumeroList = refNumeroRepository.findAll();
        assertThat(refNumeroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefNumero() throws Exception {
        // Initialize the database
        refNumeroService.save(refNumero);

        int databaseSizeBeforeDelete = refNumeroRepository.findAll().size();

        // Delete the refNumero
        restRefNumeroMockMvc.perform(delete("/api/ref-numeros/{id}", refNumero.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefNumero> refNumeroList = refNumeroRepository.findAll();
        assertThat(refNumeroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefNumero.class);
        RefNumero refNumero1 = new RefNumero();
        refNumero1.setId(1L);
        RefNumero refNumero2 = new RefNumero();
        refNumero2.setId(refNumero1.getId());
        assertThat(refNumero1).isEqualTo(refNumero2);
        refNumero2.setId(2L);
        assertThat(refNumero1).isNotEqualTo(refNumero2);
        refNumero1.setId(null);
        assertThat(refNumero1).isNotEqualTo(refNumero2);
    }
}

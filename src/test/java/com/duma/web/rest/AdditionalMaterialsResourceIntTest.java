package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.AdditionalMaterials;
import com.duma.repository.AdditionalMaterialsRepository;
import com.duma.service.AdditionalMaterialsService;
import com.duma.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AdditionalMaterialsResource REST controller.
 *
 * @see AdditionalMaterialsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class AdditionalMaterialsResourceIntTest {

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final String DEFAULT_REGISTERED = "AAAAAAAAAA";
    private static final String UPDATED_REGISTERED = "BBBBBBBBBB";

    private static final String DEFAULT_MARRIAGE = "AAAAAAAAAA";
    private static final String UPDATED_MARRIAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNICATE = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATE = "BBBBBBBBBB";

    private static final String DEFAULT_AGREEMENT = "AAAAAAAAAA";
    private static final String UPDATED_AGREEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_CARD = "AAAAAAAAAA";
    private static final String UPDATED_BANK_CARD = "BBBBBBBBBB";

    @Autowired
    private AdditionalMaterialsRepository additionalMaterialsRepository;

    @Autowired
    private AdditionalMaterialsService additionalMaterialsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdditionalMaterialsMockMvc;

    private AdditionalMaterials additionalMaterials;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AdditionalMaterialsResource additionalMaterialsResource = new AdditionalMaterialsResource(additionalMaterialsService);
        this.restAdditionalMaterialsMockMvc = MockMvcBuilders.standaloneSetup(additionalMaterialsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdditionalMaterials createEntity(EntityManager em) {
        AdditionalMaterials additionalMaterials = new AdditionalMaterials()
                .orderId(DEFAULT_ORDER_ID)
                .registered(DEFAULT_REGISTERED)
                .marriage(DEFAULT_MARRIAGE)
                .site(DEFAULT_SITE)
                .communicate(DEFAULT_COMMUNICATE)
                .agreement(DEFAULT_AGREEMENT)
                .bankCard(DEFAULT_BANK_CARD);
        return additionalMaterials;
    }

    @Before
    public void initTest() {
        additionalMaterials = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdditionalMaterials() throws Exception {
        int databaseSizeBeforeCreate = additionalMaterialsRepository.findAll().size();

        // Create the AdditionalMaterials

        restAdditionalMaterialsMockMvc.perform(post("/api/additional-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(additionalMaterials)))
            .andExpect(status().isCreated());

        // Validate the AdditionalMaterials in the database
        List<AdditionalMaterials> additionalMaterialsList = additionalMaterialsRepository.findAll();
        assertThat(additionalMaterialsList).hasSize(databaseSizeBeforeCreate + 1);
        AdditionalMaterials testAdditionalMaterials = additionalMaterialsList.get(additionalMaterialsList.size() - 1);
        assertThat(testAdditionalMaterials.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testAdditionalMaterials.getRegistered()).isEqualTo(DEFAULT_REGISTERED);
        assertThat(testAdditionalMaterials.getMarriage()).isEqualTo(DEFAULT_MARRIAGE);
        assertThat(testAdditionalMaterials.getSite()).isEqualTo(DEFAULT_SITE);
        assertThat(testAdditionalMaterials.getCommunicate()).isEqualTo(DEFAULT_COMMUNICATE);
        assertThat(testAdditionalMaterials.getAgreement()).isEqualTo(DEFAULT_AGREEMENT);
        assertThat(testAdditionalMaterials.getBankCard()).isEqualTo(DEFAULT_BANK_CARD);
    }

    @Test
    @Transactional
    public void createAdditionalMaterialsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = additionalMaterialsRepository.findAll().size();

        // Create the AdditionalMaterials with an existing ID
        AdditionalMaterials existingAdditionalMaterials = new AdditionalMaterials();
        existingAdditionalMaterials.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdditionalMaterialsMockMvc.perform(post("/api/additional-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAdditionalMaterials)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AdditionalMaterials> additionalMaterialsList = additionalMaterialsRepository.findAll();
        assertThat(additionalMaterialsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdditionalMaterials() throws Exception {
        // Initialize the database
        additionalMaterialsRepository.saveAndFlush(additionalMaterials);

        // Get all the additionalMaterialsList
        restAdditionalMaterialsMockMvc.perform(get("/api/additional-materials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(additionalMaterials.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].registered").value(hasItem(DEFAULT_REGISTERED.toString())))
            .andExpect(jsonPath("$.[*].marriage").value(hasItem(DEFAULT_MARRIAGE.toString())))
            .andExpect(jsonPath("$.[*].site").value(hasItem(DEFAULT_SITE.toString())))
            .andExpect(jsonPath("$.[*].communicate").value(hasItem(DEFAULT_COMMUNICATE.toString())))
            .andExpect(jsonPath("$.[*].agreement").value(hasItem(DEFAULT_AGREEMENT.toString())))
            .andExpect(jsonPath("$.[*].bankCard").value(hasItem(DEFAULT_BANK_CARD.toString())));
    }

    @Test
    @Transactional
    public void getAdditionalMaterials() throws Exception {
        // Initialize the database
        additionalMaterialsRepository.saveAndFlush(additionalMaterials);

        // Get the additionalMaterials
        restAdditionalMaterialsMockMvc.perform(get("/api/additional-materials/{id}", additionalMaterials.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(additionalMaterials.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.registered").value(DEFAULT_REGISTERED.toString()))
            .andExpect(jsonPath("$.marriage").value(DEFAULT_MARRIAGE.toString()))
            .andExpect(jsonPath("$.site").value(DEFAULT_SITE.toString()))
            .andExpect(jsonPath("$.communicate").value(DEFAULT_COMMUNICATE.toString()))
            .andExpect(jsonPath("$.agreement").value(DEFAULT_AGREEMENT.toString()))
            .andExpect(jsonPath("$.bankCard").value(DEFAULT_BANK_CARD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdditionalMaterials() throws Exception {
        // Get the additionalMaterials
        restAdditionalMaterialsMockMvc.perform(get("/api/additional-materials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdditionalMaterials() throws Exception {
        // Initialize the database
        additionalMaterialsService.save(additionalMaterials);

        int databaseSizeBeforeUpdate = additionalMaterialsRepository.findAll().size();

        // Update the additionalMaterials
        AdditionalMaterials updatedAdditionalMaterials = additionalMaterialsRepository.findOne(additionalMaterials.getId());
        updatedAdditionalMaterials
                .orderId(UPDATED_ORDER_ID)
                .registered(UPDATED_REGISTERED)
                .marriage(UPDATED_MARRIAGE)
                .site(UPDATED_SITE)
                .communicate(UPDATED_COMMUNICATE)
                .agreement(UPDATED_AGREEMENT)
                .bankCard(UPDATED_BANK_CARD);

        restAdditionalMaterialsMockMvc.perform(put("/api/additional-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdditionalMaterials)))
            .andExpect(status().isOk());

        // Validate the AdditionalMaterials in the database
        List<AdditionalMaterials> additionalMaterialsList = additionalMaterialsRepository.findAll();
        assertThat(additionalMaterialsList).hasSize(databaseSizeBeforeUpdate);
        AdditionalMaterials testAdditionalMaterials = additionalMaterialsList.get(additionalMaterialsList.size() - 1);
        assertThat(testAdditionalMaterials.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testAdditionalMaterials.getRegistered()).isEqualTo(UPDATED_REGISTERED);
        assertThat(testAdditionalMaterials.getMarriage()).isEqualTo(UPDATED_MARRIAGE);
        assertThat(testAdditionalMaterials.getSite()).isEqualTo(UPDATED_SITE);
        assertThat(testAdditionalMaterials.getCommunicate()).isEqualTo(UPDATED_COMMUNICATE);
        assertThat(testAdditionalMaterials.getAgreement()).isEqualTo(UPDATED_AGREEMENT);
        assertThat(testAdditionalMaterials.getBankCard()).isEqualTo(UPDATED_BANK_CARD);
    }

    @Test
    @Transactional
    public void updateNonExistingAdditionalMaterials() throws Exception {
        int databaseSizeBeforeUpdate = additionalMaterialsRepository.findAll().size();

        // Create the AdditionalMaterials

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdditionalMaterialsMockMvc.perform(put("/api/additional-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(additionalMaterials)))
            .andExpect(status().isCreated());

        // Validate the AdditionalMaterials in the database
        List<AdditionalMaterials> additionalMaterialsList = additionalMaterialsRepository.findAll();
        assertThat(additionalMaterialsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAdditionalMaterials() throws Exception {
        // Initialize the database
        additionalMaterialsService.save(additionalMaterials);

        int databaseSizeBeforeDelete = additionalMaterialsRepository.findAll().size();

        // Get the additionalMaterials
        restAdditionalMaterialsMockMvc.perform(delete("/api/additional-materials/{id}", additionalMaterials.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdditionalMaterials> additionalMaterialsList = additionalMaterialsRepository.findAll();
        assertThat(additionalMaterialsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdditionalMaterials.class);
    }
}

package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.PurchaseTax;
import com.duma.repository.PurchaseTaxRepository;
import com.duma.service.PurchaseTaxService;
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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PurchaseTaxResource REST controller.
 *
 * @see PurchaseTaxResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class PurchaseTaxResourceIntTest {

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final BigDecimal DEFAULT_PURCHASE_TAXMONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_PURCHASE_TAXMONEY = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_INVOICE_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_PICTURE = "BBBBBBBBBB";

    private static final Long DEFAULT_PURCHASE_TIME = 1L;
    private static final Long UPDATED_PURCHASE_TIME = 2L;

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    @Autowired
    private PurchaseTaxRepository purchaseTaxRepository;

    @Autowired
    private PurchaseTaxService purchaseTaxService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPurchaseTaxMockMvc;

    private PurchaseTax purchaseTax;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PurchaseTaxResource purchaseTaxResource = new PurchaseTaxResource(purchaseTaxService);
        this.restPurchaseTaxMockMvc = MockMvcBuilders.standaloneSetup(purchaseTaxResource)
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
    public static PurchaseTax createEntity(EntityManager em) {
        PurchaseTax purchaseTax = new PurchaseTax()
                .vehicleId(DEFAULT_VEHICLE_ID)
                .purchaseTaxmoney(DEFAULT_PURCHASE_TAXMONEY)
                .remark(DEFAULT_REMARK)
                .invoicePicture(DEFAULT_INVOICE_PICTURE)
                .purchaseTime(DEFAULT_PURCHASE_TIME)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME);
        return purchaseTax;
    }

    @Before
    public void initTest() {
        purchaseTax = createEntity(em);
    }

    @Test
    @Transactional
    public void createPurchaseTax() throws Exception {
        int databaseSizeBeforeCreate = purchaseTaxRepository.findAll().size();

        // Create the PurchaseTax

        restPurchaseTaxMockMvc.perform(post("/api/purchase-taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseTax)))
            .andExpect(status().isCreated());

        // Validate the PurchaseTax in the database
        List<PurchaseTax> purchaseTaxList = purchaseTaxRepository.findAll();
        assertThat(purchaseTaxList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseTax testPurchaseTax = purchaseTaxList.get(purchaseTaxList.size() - 1);
        assertThat(testPurchaseTax.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testPurchaseTax.getPurchaseTaxmoney()).isEqualTo(DEFAULT_PURCHASE_TAXMONEY);
        assertThat(testPurchaseTax.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testPurchaseTax.getInvoicePicture()).isEqualTo(DEFAULT_INVOICE_PICTURE);
        assertThat(testPurchaseTax.getPurchaseTime()).isEqualTo(DEFAULT_PURCHASE_TIME);
        assertThat(testPurchaseTax.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testPurchaseTax.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testPurchaseTax.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testPurchaseTax.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void createPurchaseTaxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = purchaseTaxRepository.findAll().size();

        // Create the PurchaseTax with an existing ID
        PurchaseTax existingPurchaseTax = new PurchaseTax();
        existingPurchaseTax.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseTaxMockMvc.perform(post("/api/purchase-taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPurchaseTax)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PurchaseTax> purchaseTaxList = purchaseTaxRepository.findAll();
        assertThat(purchaseTaxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPurchaseTaxes() throws Exception {
        // Initialize the database
        purchaseTaxRepository.saveAndFlush(purchaseTax);

        // Get all the purchaseTaxList
        restPurchaseTaxMockMvc.perform(get("/api/purchase-taxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseTax.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].purchaseTaxmoney").value(hasItem(DEFAULT_PURCHASE_TAXMONEY.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].invoicePicture").value(hasItem(DEFAULT_INVOICE_PICTURE.toString())))
            .andExpect(jsonPath("$.[*].purchaseTime").value(hasItem(DEFAULT_PURCHASE_TIME.intValue())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())));
    }

    @Test
    @Transactional
    public void getPurchaseTax() throws Exception {
        // Initialize the database
        purchaseTaxRepository.saveAndFlush(purchaseTax);

        // Get the purchaseTax
        restPurchaseTaxMockMvc.perform(get("/api/purchase-taxes/{id}", purchaseTax.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseTax.getId().intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.purchaseTaxmoney").value(DEFAULT_PURCHASE_TAXMONEY.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.invoicePicture").value(DEFAULT_INVOICE_PICTURE.toString()))
            .andExpect(jsonPath("$.purchaseTime").value(DEFAULT_PURCHASE_TIME.intValue()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPurchaseTax() throws Exception {
        // Get the purchaseTax
        restPurchaseTaxMockMvc.perform(get("/api/purchase-taxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePurchaseTax() throws Exception {
        // Initialize the database
        purchaseTaxService.save(purchaseTax);

        int databaseSizeBeforeUpdate = purchaseTaxRepository.findAll().size();

        // Update the purchaseTax
        PurchaseTax updatedPurchaseTax = purchaseTaxRepository.findOne(purchaseTax.getId());
        updatedPurchaseTax
                .vehicleId(UPDATED_VEHICLE_ID)
                .purchaseTaxmoney(UPDATED_PURCHASE_TAXMONEY)
                .remark(UPDATED_REMARK)
                .invoicePicture(UPDATED_INVOICE_PICTURE)
                .purchaseTime(UPDATED_PURCHASE_TIME)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME);

        restPurchaseTaxMockMvc.perform(put("/api/purchase-taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPurchaseTax)))
            .andExpect(status().isOk());

        // Validate the PurchaseTax in the database
        List<PurchaseTax> purchaseTaxList = purchaseTaxRepository.findAll();
        assertThat(purchaseTaxList).hasSize(databaseSizeBeforeUpdate);
        PurchaseTax testPurchaseTax = purchaseTaxList.get(purchaseTaxList.size() - 1);
        assertThat(testPurchaseTax.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testPurchaseTax.getPurchaseTaxmoney()).isEqualTo(UPDATED_PURCHASE_TAXMONEY);
        assertThat(testPurchaseTax.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testPurchaseTax.getInvoicePicture()).isEqualTo(UPDATED_INVOICE_PICTURE);
        assertThat(testPurchaseTax.getPurchaseTime()).isEqualTo(UPDATED_PURCHASE_TIME);
        assertThat(testPurchaseTax.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testPurchaseTax.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testPurchaseTax.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testPurchaseTax.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingPurchaseTax() throws Exception {
        int databaseSizeBeforeUpdate = purchaseTaxRepository.findAll().size();

        // Create the PurchaseTax

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPurchaseTaxMockMvc.perform(put("/api/purchase-taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseTax)))
            .andExpect(status().isCreated());

        // Validate the PurchaseTax in the database
        List<PurchaseTax> purchaseTaxList = purchaseTaxRepository.findAll();
        assertThat(purchaseTaxList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePurchaseTax() throws Exception {
        // Initialize the database
        purchaseTaxService.save(purchaseTax);

        int databaseSizeBeforeDelete = purchaseTaxRepository.findAll().size();

        // Get the purchaseTax
        restPurchaseTaxMockMvc.perform(delete("/api/purchase-taxes/{id}", purchaseTax.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PurchaseTax> purchaseTaxList = purchaseTaxRepository.findAll();
        assertThat(purchaseTaxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseTax.class);
    }
}

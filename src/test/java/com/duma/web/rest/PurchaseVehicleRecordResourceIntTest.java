package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.PurchaseVehicleRecord;
import com.duma.repository.PurchaseVehicleRecordRepository;
import com.duma.service.PurchaseVehicleRecordService;
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
 * Test class for the PurchaseVehicleRecordResource REST controller.
 *
 * @see PurchaseVehicleRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class PurchaseVehicleRecordResourceIntTest {

    @Autowired
    private PurchaseVehicleRecordRepository purchaseVehicleRecordRepository;

    @Autowired
    private PurchaseVehicleRecordService purchaseVehicleRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPurchaseVehicleRecordMockMvc;

    private PurchaseVehicleRecord purchaseVehicleRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PurchaseVehicleRecordResource purchaseVehicleRecordResource = new PurchaseVehicleRecordResource(purchaseVehicleRecordService);
        this.restPurchaseVehicleRecordMockMvc = MockMvcBuilders.standaloneSetup(purchaseVehicleRecordResource)
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
    public static PurchaseVehicleRecord createEntity(EntityManager em) {
        PurchaseVehicleRecord purchaseVehicleRecord = new PurchaseVehicleRecord();
        return purchaseVehicleRecord;
    }

    @Before
    public void initTest() {
        purchaseVehicleRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createPurchaseVehicleRecord() throws Exception {
        int databaseSizeBeforeCreate = purchaseVehicleRecordRepository.findAll().size();

        // Create the PurchaseVehicleRecord

        restPurchaseVehicleRecordMockMvc.perform(post("/api/purchase-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseVehicleRecord)))
            .andExpect(status().isCreated());

        // Validate the PurchaseVehicleRecord in the database
        List<PurchaseVehicleRecord> purchaseVehicleRecordList = purchaseVehicleRecordRepository.findAll();
        assertThat(purchaseVehicleRecordList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseVehicleRecord testPurchaseVehicleRecord = purchaseVehicleRecordList.get(purchaseVehicleRecordList.size() - 1);
    }

    @Test
    @Transactional
    public void createPurchaseVehicleRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = purchaseVehicleRecordRepository.findAll().size();

        // Create the PurchaseVehicleRecord with an existing ID
        PurchaseVehicleRecord existingPurchaseVehicleRecord = new PurchaseVehicleRecord();
        existingPurchaseVehicleRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseVehicleRecordMockMvc.perform(post("/api/purchase-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPurchaseVehicleRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PurchaseVehicleRecord> purchaseVehicleRecordList = purchaseVehicleRecordRepository.findAll();
        assertThat(purchaseVehicleRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPurchaseVehicleRecords() throws Exception {
        // Initialize the database
        purchaseVehicleRecordRepository.saveAndFlush(purchaseVehicleRecord);

        // Get all the purchaseVehicleRecordList
        restPurchaseVehicleRecordMockMvc.perform(get("/api/purchase-vehicle-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseVehicleRecord.getId().intValue())));
    }

    @Test
    @Transactional
    public void getPurchaseVehicleRecord() throws Exception {
        // Initialize the database
        purchaseVehicleRecordRepository.saveAndFlush(purchaseVehicleRecord);

        // Get the purchaseVehicleRecord
        restPurchaseVehicleRecordMockMvc.perform(get("/api/purchase-vehicle-records/{id}", purchaseVehicleRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseVehicleRecord.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPurchaseVehicleRecord() throws Exception {
        // Get the purchaseVehicleRecord
        restPurchaseVehicleRecordMockMvc.perform(get("/api/purchase-vehicle-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePurchaseVehicleRecord() throws Exception {
        // Initialize the database
        purchaseVehicleRecordService.save(purchaseVehicleRecord);

        int databaseSizeBeforeUpdate = purchaseVehicleRecordRepository.findAll().size();

        // Update the purchaseVehicleRecord
        PurchaseVehicleRecord updatedPurchaseVehicleRecord = purchaseVehicleRecordRepository.findOne(purchaseVehicleRecord.getId());

        restPurchaseVehicleRecordMockMvc.perform(put("/api/purchase-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPurchaseVehicleRecord)))
            .andExpect(status().isOk());

        // Validate the PurchaseVehicleRecord in the database
        List<PurchaseVehicleRecord> purchaseVehicleRecordList = purchaseVehicleRecordRepository.findAll();
        assertThat(purchaseVehicleRecordList).hasSize(databaseSizeBeforeUpdate);
        PurchaseVehicleRecord testPurchaseVehicleRecord = purchaseVehicleRecordList.get(purchaseVehicleRecordList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingPurchaseVehicleRecord() throws Exception {
        int databaseSizeBeforeUpdate = purchaseVehicleRecordRepository.findAll().size();

        // Create the PurchaseVehicleRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPurchaseVehicleRecordMockMvc.perform(put("/api/purchase-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseVehicleRecord)))
            .andExpect(status().isCreated());

        // Validate the PurchaseVehicleRecord in the database
        List<PurchaseVehicleRecord> purchaseVehicleRecordList = purchaseVehicleRecordRepository.findAll();
        assertThat(purchaseVehicleRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePurchaseVehicleRecord() throws Exception {
        // Initialize the database
        purchaseVehicleRecordService.save(purchaseVehicleRecord);

        int databaseSizeBeforeDelete = purchaseVehicleRecordRepository.findAll().size();

        // Get the purchaseVehicleRecord
        restPurchaseVehicleRecordMockMvc.perform(delete("/api/purchase-vehicle-records/{id}", purchaseVehicleRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PurchaseVehicleRecord> purchaseVehicleRecordList = purchaseVehicleRecordRepository.findAll();
        assertThat(purchaseVehicleRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseVehicleRecord.class);
    }
}

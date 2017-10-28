package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.PolicyRecord;
import com.duma.repository.PolicyRecordRepository;
import com.duma.service.PolicyRecordService;
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
 * Test class for the PolicyRecordResource REST controller.
 *
 * @see PolicyRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class PolicyRecordResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_VI_BEGIN_DATE = 1L;
    private static final Long UPDATED_VI_BEGIN_DATE = 2L;

    private static final Long DEFAULT_VI_FINISH_DATE = 1L;
    private static final Long UPDATED_VI_FINISH_DATE = 2L;

    private static final Long DEFAULT_CI_BEGIN_DATE = 1L;
    private static final Long UPDATED_CI_BEGIN_DATE = 2L;

    private static final Long DEFAULT_CI_FINISH_DATE = 1L;
    private static final Long UPDATED_CI_FINISH_DATE = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private PolicyRecordRepository policyRecordRepository;

    @Autowired
    private PolicyRecordService policyRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPolicyRecordMockMvc;

    private PolicyRecord policyRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PolicyRecordResource policyRecordResource = new PolicyRecordResource(policyRecordService);
        this.restPolicyRecordMockMvc = MockMvcBuilders.standaloneSetup(policyRecordResource)
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
    public static PolicyRecord createEntity(EntityManager em) {
        PolicyRecord policyRecord = new PolicyRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .viBeginDate(DEFAULT_VI_BEGIN_DATE)
                .viFinishDate(DEFAULT_VI_FINISH_DATE)
                .ciBeginDate(DEFAULT_CI_BEGIN_DATE)
                .ciFinishDate(DEFAULT_CI_FINISH_DATE)
                .remark(DEFAULT_REMARK);
        return policyRecord;
    }

    @Before
    public void initTest() {
        policyRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createPolicyRecord() throws Exception {
        int databaseSizeBeforeCreate = policyRecordRepository.findAll().size();

        // Create the PolicyRecord

        restPolicyRecordMockMvc.perform(post("/api/policy-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(policyRecord)))
            .andExpect(status().isCreated());

        // Validate the PolicyRecord in the database
        List<PolicyRecord> policyRecordList = policyRecordRepository.findAll();
        assertThat(policyRecordList).hasSize(databaseSizeBeforeCreate + 1);
        PolicyRecord testPolicyRecord = policyRecordList.get(policyRecordList.size() - 1);
        assertThat(testPolicyRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testPolicyRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testPolicyRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testPolicyRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testPolicyRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testPolicyRecord.getViBeginDate()).isEqualTo(DEFAULT_VI_BEGIN_DATE);
        assertThat(testPolicyRecord.getViFinishDate()).isEqualTo(DEFAULT_VI_FINISH_DATE);
        assertThat(testPolicyRecord.getCiBeginDate()).isEqualTo(DEFAULT_CI_BEGIN_DATE);
        assertThat(testPolicyRecord.getCiFinishDate()).isEqualTo(DEFAULT_CI_FINISH_DATE);
        assertThat(testPolicyRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createPolicyRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = policyRecordRepository.findAll().size();

        // Create the PolicyRecord with an existing ID
        PolicyRecord existingPolicyRecord = new PolicyRecord();
        existingPolicyRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPolicyRecordMockMvc.perform(post("/api/policy-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPolicyRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PolicyRecord> policyRecordList = policyRecordRepository.findAll();
        assertThat(policyRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPolicyRecords() throws Exception {
        // Initialize the database
        policyRecordRepository.saveAndFlush(policyRecord);

        // Get all the policyRecordList
        restPolicyRecordMockMvc.perform(get("/api/policy-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(policyRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].viBeginDate").value(hasItem(DEFAULT_VI_BEGIN_DATE.intValue())))
            .andExpect(jsonPath("$.[*].viFinishDate").value(hasItem(DEFAULT_VI_FINISH_DATE.intValue())))
            .andExpect(jsonPath("$.[*].ciBeginDate").value(hasItem(DEFAULT_CI_BEGIN_DATE.intValue())))
            .andExpect(jsonPath("$.[*].ciFinishDate").value(hasItem(DEFAULT_CI_FINISH_DATE.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getPolicyRecord() throws Exception {
        // Initialize the database
        policyRecordRepository.saveAndFlush(policyRecord);

        // Get the policyRecord
        restPolicyRecordMockMvc.perform(get("/api/policy-records/{id}", policyRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(policyRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.viBeginDate").value(DEFAULT_VI_BEGIN_DATE.intValue()))
            .andExpect(jsonPath("$.viFinishDate").value(DEFAULT_VI_FINISH_DATE.intValue()))
            .andExpect(jsonPath("$.ciBeginDate").value(DEFAULT_CI_BEGIN_DATE.intValue()))
            .andExpect(jsonPath("$.ciFinishDate").value(DEFAULT_CI_FINISH_DATE.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPolicyRecord() throws Exception {
        // Get the policyRecord
        restPolicyRecordMockMvc.perform(get("/api/policy-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePolicyRecord() throws Exception {
        // Initialize the database
        policyRecordService.save(policyRecord);

        int databaseSizeBeforeUpdate = policyRecordRepository.findAll().size();

        // Update the policyRecord
        PolicyRecord updatedPolicyRecord = policyRecordRepository.findOne(policyRecord.getId());
        updatedPolicyRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .viBeginDate(UPDATED_VI_BEGIN_DATE)
                .viFinishDate(UPDATED_VI_FINISH_DATE)
                .ciBeginDate(UPDATED_CI_BEGIN_DATE)
                .ciFinishDate(UPDATED_CI_FINISH_DATE)
                .remark(UPDATED_REMARK);

        restPolicyRecordMockMvc.perform(put("/api/policy-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPolicyRecord)))
            .andExpect(status().isOk());

        // Validate the PolicyRecord in the database
        List<PolicyRecord> policyRecordList = policyRecordRepository.findAll();
        assertThat(policyRecordList).hasSize(databaseSizeBeforeUpdate);
        PolicyRecord testPolicyRecord = policyRecordList.get(policyRecordList.size() - 1);
        assertThat(testPolicyRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testPolicyRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testPolicyRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testPolicyRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testPolicyRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testPolicyRecord.getViBeginDate()).isEqualTo(UPDATED_VI_BEGIN_DATE);
        assertThat(testPolicyRecord.getViFinishDate()).isEqualTo(UPDATED_VI_FINISH_DATE);
        assertThat(testPolicyRecord.getCiBeginDate()).isEqualTo(UPDATED_CI_BEGIN_DATE);
        assertThat(testPolicyRecord.getCiFinishDate()).isEqualTo(UPDATED_CI_FINISH_DATE);
        assertThat(testPolicyRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingPolicyRecord() throws Exception {
        int databaseSizeBeforeUpdate = policyRecordRepository.findAll().size();

        // Create the PolicyRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPolicyRecordMockMvc.perform(put("/api/policy-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(policyRecord)))
            .andExpect(status().isCreated());

        // Validate the PolicyRecord in the database
        List<PolicyRecord> policyRecordList = policyRecordRepository.findAll();
        assertThat(policyRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePolicyRecord() throws Exception {
        // Initialize the database
        policyRecordService.save(policyRecord);

        int databaseSizeBeforeDelete = policyRecordRepository.findAll().size();

        // Get the policyRecord
        restPolicyRecordMockMvc.perform(delete("/api/policy-records/{id}", policyRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PolicyRecord> policyRecordList = policyRecordRepository.findAll();
        assertThat(policyRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PolicyRecord.class);
    }
}

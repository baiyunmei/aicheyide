package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.ContractMarginRecord;
import com.duma.repository.ContractMarginRecordRepository;
import com.duma.service.ContractMarginRecordService;
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
 * Test class for the ContractMarginRecordResource REST controller.
 *
 * @see ContractMarginRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class ContractMarginRecordResourceIntTest {

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

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PAYMENT_TIME = 1L;
    private static final Long UPDATED_PAYMENT_TIME = 2L;

    private static final Integer DEFAULT_PAYMENT_WAY = 1;
    private static final Integer UPDATED_PAYMENT_WAY = 2;

    private static final BigDecimal DEFAULT_PAYMENT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAYMENT_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_PAYMENT_STATUS = 1;
    private static final Integer UPDATED_PAYMENT_STATUS = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private ContractMarginRecordRepository contractMarginRecordRepository;

    @Autowired
    private ContractMarginRecordService contractMarginRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContractMarginRecordMockMvc;

    private ContractMarginRecord contractMarginRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContractMarginRecordResource contractMarginRecordResource = new ContractMarginRecordResource(contractMarginRecordService);
        this.restContractMarginRecordMockMvc = MockMvcBuilders.standaloneSetup(contractMarginRecordResource)
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
    public static ContractMarginRecord createEntity(EntityManager em) {
        ContractMarginRecord contractMarginRecord = new ContractMarginRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .paymentTime(DEFAULT_PAYMENT_TIME)
                .paymentWay(DEFAULT_PAYMENT_WAY)
                .paymentMoney(DEFAULT_PAYMENT_MONEY)
                .paymentStatus(DEFAULT_PAYMENT_STATUS)
                .remark(DEFAULT_REMARK);
        return contractMarginRecord;
    }

    @Before
    public void initTest() {
        contractMarginRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createContractMarginRecord() throws Exception {
        int databaseSizeBeforeCreate = contractMarginRecordRepository.findAll().size();

        // Create the ContractMarginRecord

        restContractMarginRecordMockMvc.perform(post("/api/contract-margin-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contractMarginRecord)))
            .andExpect(status().isCreated());

        // Validate the ContractMarginRecord in the database
        List<ContractMarginRecord> contractMarginRecordList = contractMarginRecordRepository.findAll();
        assertThat(contractMarginRecordList).hasSize(databaseSizeBeforeCreate + 1);
        ContractMarginRecord testContractMarginRecord = contractMarginRecordList.get(contractMarginRecordList.size() - 1);
        assertThat(testContractMarginRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testContractMarginRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testContractMarginRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testContractMarginRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testContractMarginRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testContractMarginRecord.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testContractMarginRecord.getPaymentTime()).isEqualTo(DEFAULT_PAYMENT_TIME);
        assertThat(testContractMarginRecord.getPaymentWay()).isEqualTo(DEFAULT_PAYMENT_WAY);
        assertThat(testContractMarginRecord.getPaymentMoney()).isEqualTo(DEFAULT_PAYMENT_MONEY);
        assertThat(testContractMarginRecord.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testContractMarginRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createContractMarginRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contractMarginRecordRepository.findAll().size();

        // Create the ContractMarginRecord with an existing ID
        ContractMarginRecord existingContractMarginRecord = new ContractMarginRecord();
        existingContractMarginRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractMarginRecordMockMvc.perform(post("/api/contract-margin-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingContractMarginRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ContractMarginRecord> contractMarginRecordList = contractMarginRecordRepository.findAll();
        assertThat(contractMarginRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContractMarginRecords() throws Exception {
        // Initialize the database
        contractMarginRecordRepository.saveAndFlush(contractMarginRecord);

        // Get all the contractMarginRecordList
        restContractMarginRecordMockMvc.perform(get("/api/contract-margin-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractMarginRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].paymentTime").value(hasItem(DEFAULT_PAYMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].paymentWay").value(hasItem(DEFAULT_PAYMENT_WAY)))
            .andExpect(jsonPath("$.[*].paymentMoney").value(hasItem(DEFAULT_PAYMENT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].paymentStatus").value(hasItem(DEFAULT_PAYMENT_STATUS)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getContractMarginRecord() throws Exception {
        // Initialize the database
        contractMarginRecordRepository.saveAndFlush(contractMarginRecord);

        // Get the contractMarginRecord
        restContractMarginRecordMockMvc.perform(get("/api/contract-margin-records/{id}", contractMarginRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contractMarginRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.paymentTime").value(DEFAULT_PAYMENT_TIME.intValue()))
            .andExpect(jsonPath("$.paymentWay").value(DEFAULT_PAYMENT_WAY))
            .andExpect(jsonPath("$.paymentMoney").value(DEFAULT_PAYMENT_MONEY.intValue()))
            .andExpect(jsonPath("$.paymentStatus").value(DEFAULT_PAYMENT_STATUS))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContractMarginRecord() throws Exception {
        // Get the contractMarginRecord
        restContractMarginRecordMockMvc.perform(get("/api/contract-margin-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContractMarginRecord() throws Exception {
        // Initialize the database
        contractMarginRecordService.save(contractMarginRecord);

        int databaseSizeBeforeUpdate = contractMarginRecordRepository.findAll().size();

        // Update the contractMarginRecord
        ContractMarginRecord updatedContractMarginRecord = contractMarginRecordRepository.findOne(contractMarginRecord.getId());
        updatedContractMarginRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .paymentTime(UPDATED_PAYMENT_TIME)
                .paymentWay(UPDATED_PAYMENT_WAY)
                .paymentMoney(UPDATED_PAYMENT_MONEY)
                .paymentStatus(UPDATED_PAYMENT_STATUS)
                .remark(UPDATED_REMARK);

        restContractMarginRecordMockMvc.perform(put("/api/contract-margin-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedContractMarginRecord)))
            .andExpect(status().isOk());

        // Validate the ContractMarginRecord in the database
        List<ContractMarginRecord> contractMarginRecordList = contractMarginRecordRepository.findAll();
        assertThat(contractMarginRecordList).hasSize(databaseSizeBeforeUpdate);
        ContractMarginRecord testContractMarginRecord = contractMarginRecordList.get(contractMarginRecordList.size() - 1);
        assertThat(testContractMarginRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testContractMarginRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testContractMarginRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testContractMarginRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testContractMarginRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testContractMarginRecord.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testContractMarginRecord.getPaymentTime()).isEqualTo(UPDATED_PAYMENT_TIME);
        assertThat(testContractMarginRecord.getPaymentWay()).isEqualTo(UPDATED_PAYMENT_WAY);
        assertThat(testContractMarginRecord.getPaymentMoney()).isEqualTo(UPDATED_PAYMENT_MONEY);
        assertThat(testContractMarginRecord.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testContractMarginRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingContractMarginRecord() throws Exception {
        int databaseSizeBeforeUpdate = contractMarginRecordRepository.findAll().size();

        // Create the ContractMarginRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContractMarginRecordMockMvc.perform(put("/api/contract-margin-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contractMarginRecord)))
            .andExpect(status().isCreated());

        // Validate the ContractMarginRecord in the database
        List<ContractMarginRecord> contractMarginRecordList = contractMarginRecordRepository.findAll();
        assertThat(contractMarginRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContractMarginRecord() throws Exception {
        // Initialize the database
        contractMarginRecordService.save(contractMarginRecord);

        int databaseSizeBeforeDelete = contractMarginRecordRepository.findAll().size();

        // Get the contractMarginRecord
        restContractMarginRecordMockMvc.perform(delete("/api/contract-margin-records/{id}", contractMarginRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ContractMarginRecord> contractMarginRecordList = contractMarginRecordRepository.findAll();
        assertThat(contractMarginRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractMarginRecord.class);
    }
}

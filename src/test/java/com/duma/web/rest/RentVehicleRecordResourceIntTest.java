package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.RentVehicleRecord;
import com.duma.repository.RentVehicleRecordRepository;
import com.duma.service.RentVehicleRecordService;
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
 * Test class for the RentVehicleRecordResource REST controller.
 *
 * @see RentVehicleRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class RentVehicleRecordResourceIntTest {

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

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Long DEFAULT_CONTRACT_START_DATE = 1L;
    private static final Long UPDATED_CONTRACT_START_DATE = 2L;

    private static final Long DEFAULT_CONTRACT_END_DATE = 1L;
    private static final Long UPDATED_CONTRACT_END_DATE = 2L;

    private static final BigDecimal DEFAULT_MONTHLY_RENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTHLY_RENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CASH_PLEDGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASH_PLEDGE = new BigDecimal(2);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private RentVehicleRecordRepository rentVehicleRecordRepository;

    @Autowired
    private RentVehicleRecordService rentVehicleRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRentVehicleRecordMockMvc;

    private RentVehicleRecord rentVehicleRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RentVehicleRecordResource rentVehicleRecordResource = new RentVehicleRecordResource(rentVehicleRecordService);
        this.restRentVehicleRecordMockMvc = MockMvcBuilders.standaloneSetup(rentVehicleRecordResource)
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
    public static RentVehicleRecord createEntity(EntityManager em) {
        RentVehicleRecord rentVehicleRecord = new RentVehicleRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .phone(DEFAULT_PHONE)
                .contractStartDate(DEFAULT_CONTRACT_START_DATE)
                .contractEndDate(DEFAULT_CONTRACT_END_DATE)
                .monthlyRent(DEFAULT_MONTHLY_RENT)
                .cashPledge(DEFAULT_CASH_PLEDGE)
                .status(DEFAULT_STATUS);
        return rentVehicleRecord;
    }

    @Before
    public void initTest() {
        rentVehicleRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createRentVehicleRecord() throws Exception {
        int databaseSizeBeforeCreate = rentVehicleRecordRepository.findAll().size();

        // Create the RentVehicleRecord

        restRentVehicleRecordMockMvc.perform(post("/api/rent-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rentVehicleRecord)))
            .andExpect(status().isCreated());

        // Validate the RentVehicleRecord in the database
        List<RentVehicleRecord> rentVehicleRecordList = rentVehicleRecordRepository.findAll();
        assertThat(rentVehicleRecordList).hasSize(databaseSizeBeforeCreate + 1);
        RentVehicleRecord testRentVehicleRecord = rentVehicleRecordList.get(rentVehicleRecordList.size() - 1);
        assertThat(testRentVehicleRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testRentVehicleRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testRentVehicleRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testRentVehicleRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testRentVehicleRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testRentVehicleRecord.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testRentVehicleRecord.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testRentVehicleRecord.getContractStartDate()).isEqualTo(DEFAULT_CONTRACT_START_DATE);
        assertThat(testRentVehicleRecord.getContractEndDate()).isEqualTo(DEFAULT_CONTRACT_END_DATE);
        assertThat(testRentVehicleRecord.getMonthlyRent()).isEqualTo(DEFAULT_MONTHLY_RENT);
        assertThat(testRentVehicleRecord.getCashPledge()).isEqualTo(DEFAULT_CASH_PLEDGE);
        assertThat(testRentVehicleRecord.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createRentVehicleRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rentVehicleRecordRepository.findAll().size();

        // Create the RentVehicleRecord with an existing ID
        RentVehicleRecord existingRentVehicleRecord = new RentVehicleRecord();
        existingRentVehicleRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRentVehicleRecordMockMvc.perform(post("/api/rent-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRentVehicleRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RentVehicleRecord> rentVehicleRecordList = rentVehicleRecordRepository.findAll();
        assertThat(rentVehicleRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRentVehicleRecords() throws Exception {
        // Initialize the database
        rentVehicleRecordRepository.saveAndFlush(rentVehicleRecord);

        // Get all the rentVehicleRecordList
        restRentVehicleRecordMockMvc.perform(get("/api/rent-vehicle-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rentVehicleRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].contractStartDate").value(hasItem(DEFAULT_CONTRACT_START_DATE.intValue())))
            .andExpect(jsonPath("$.[*].contractEndDate").value(hasItem(DEFAULT_CONTRACT_END_DATE.intValue())))
            .andExpect(jsonPath("$.[*].monthlyRent").value(hasItem(DEFAULT_MONTHLY_RENT.intValue())))
            .andExpect(jsonPath("$.[*].cashPledge").value(hasItem(DEFAULT_CASH_PLEDGE.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getRentVehicleRecord() throws Exception {
        // Initialize the database
        rentVehicleRecordRepository.saveAndFlush(rentVehicleRecord);

        // Get the rentVehicleRecord
        restRentVehicleRecordMockMvc.perform(get("/api/rent-vehicle-records/{id}", rentVehicleRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rentVehicleRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.contractStartDate").value(DEFAULT_CONTRACT_START_DATE.intValue()))
            .andExpect(jsonPath("$.contractEndDate").value(DEFAULT_CONTRACT_END_DATE.intValue()))
            .andExpect(jsonPath("$.monthlyRent").value(DEFAULT_MONTHLY_RENT.intValue()))
            .andExpect(jsonPath("$.cashPledge").value(DEFAULT_CASH_PLEDGE.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingRentVehicleRecord() throws Exception {
        // Get the rentVehicleRecord
        restRentVehicleRecordMockMvc.perform(get("/api/rent-vehicle-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRentVehicleRecord() throws Exception {
        // Initialize the database
        rentVehicleRecordService.save(rentVehicleRecord);

        int databaseSizeBeforeUpdate = rentVehicleRecordRepository.findAll().size();

        // Update the rentVehicleRecord
        RentVehicleRecord updatedRentVehicleRecord = rentVehicleRecordRepository.findOne(rentVehicleRecord.getId());
        updatedRentVehicleRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .phone(UPDATED_PHONE)
                .contractStartDate(UPDATED_CONTRACT_START_DATE)
                .contractEndDate(UPDATED_CONTRACT_END_DATE)
                .monthlyRent(UPDATED_MONTHLY_RENT)
                .cashPledge(UPDATED_CASH_PLEDGE)
                .status(UPDATED_STATUS);

        restRentVehicleRecordMockMvc.perform(put("/api/rent-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRentVehicleRecord)))
            .andExpect(status().isOk());

        // Validate the RentVehicleRecord in the database
        List<RentVehicleRecord> rentVehicleRecordList = rentVehicleRecordRepository.findAll();
        assertThat(rentVehicleRecordList).hasSize(databaseSizeBeforeUpdate);
        RentVehicleRecord testRentVehicleRecord = rentVehicleRecordList.get(rentVehicleRecordList.size() - 1);
        assertThat(testRentVehicleRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testRentVehicleRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testRentVehicleRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testRentVehicleRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testRentVehicleRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testRentVehicleRecord.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testRentVehicleRecord.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testRentVehicleRecord.getContractStartDate()).isEqualTo(UPDATED_CONTRACT_START_DATE);
        assertThat(testRentVehicleRecord.getContractEndDate()).isEqualTo(UPDATED_CONTRACT_END_DATE);
        assertThat(testRentVehicleRecord.getMonthlyRent()).isEqualTo(UPDATED_MONTHLY_RENT);
        assertThat(testRentVehicleRecord.getCashPledge()).isEqualTo(UPDATED_CASH_PLEDGE);
        assertThat(testRentVehicleRecord.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRentVehicleRecord() throws Exception {
        int databaseSizeBeforeUpdate = rentVehicleRecordRepository.findAll().size();

        // Create the RentVehicleRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRentVehicleRecordMockMvc.perform(put("/api/rent-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rentVehicleRecord)))
            .andExpect(status().isCreated());

        // Validate the RentVehicleRecord in the database
        List<RentVehicleRecord> rentVehicleRecordList = rentVehicleRecordRepository.findAll();
        assertThat(rentVehicleRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRentVehicleRecord() throws Exception {
        // Initialize the database
        rentVehicleRecordService.save(rentVehicleRecord);

        int databaseSizeBeforeDelete = rentVehicleRecordRepository.findAll().size();

        // Get the rentVehicleRecord
        restRentVehicleRecordMockMvc.perform(delete("/api/rent-vehicle-records/{id}", rentVehicleRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RentVehicleRecord> rentVehicleRecordList = rentVehicleRecordRepository.findAll();
        assertThat(rentVehicleRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RentVehicleRecord.class);
    }
}

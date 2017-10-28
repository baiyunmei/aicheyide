package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.BuyVehicleRecord;
import com.duma.repository.BuyVehicleRecordRepository;
import com.duma.service.BuyVehicleRecordService;
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
 * Test class for the BuyVehicleRecordResource REST controller.
 *
 * @see BuyVehicleRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class BuyVehicleRecordResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_BUY_VEHICLE_DATE = 1L;
    private static final Long UPDATED_BUY_VEHICLE_DATE = 2L;

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_SHELF = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_SHELF = "BBBBBBBBBB";

    private static final String DEFAULT_ENGINE = "AAAAAAAAAA";
    private static final String UPDATED_ENGINE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DOWN_PAYMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DOWN_PAYMENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CASH_PLEDGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASH_PLEDGE = new BigDecimal(2);

    private static final Integer DEFAULT_PERIODS = 1;
    private static final Integer UPDATED_PERIODS = 2;

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final Integer DEFAULT_NOT_PERIODS = 1;
    private static final Integer UPDATED_NOT_PERIODS = 2;

    private static final BigDecimal DEFAULT_NOT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NOT_AMOUNT = new BigDecimal(2);

    private static final Integer DEFAULT_POSTPONE_TIME = 1;
    private static final Integer UPDATED_POSTPONE_TIME = 2;

    private static final Integer DEFAULT_OVERDUE_TIEM = 1;
    private static final Integer UPDATED_OVERDUE_TIEM = 2;

    @Autowired
    private BuyVehicleRecordRepository buyVehicleRecordRepository;

    @Autowired
    private BuyVehicleRecordService buyVehicleRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBuyVehicleRecordMockMvc;

    private BuyVehicleRecord buyVehicleRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BuyVehicleRecordResource buyVehicleRecordResource = new BuyVehicleRecordResource(buyVehicleRecordService);
        this.restBuyVehicleRecordMockMvc = MockMvcBuilders.standaloneSetup(buyVehicleRecordResource)
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
    public static BuyVehicleRecord createEntity(EntityManager em) {
        BuyVehicleRecord buyVehicleRecord = new BuyVehicleRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .buyVehicleDate(DEFAULT_BUY_VEHICLE_DATE)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .vehicleShelf(DEFAULT_VEHICLE_SHELF)
                .engine(DEFAULT_ENGINE)
                .downPayment(DEFAULT_DOWN_PAYMENT)
                .cashPledge(DEFAULT_CASH_PLEDGE)
                .periods(DEFAULT_PERIODS)
                .amount(DEFAULT_AMOUNT)
                .notPeriods(DEFAULT_NOT_PERIODS)
                .notAmount(DEFAULT_NOT_AMOUNT)
                .postponeTime(DEFAULT_POSTPONE_TIME)
                .overdueTiem(DEFAULT_OVERDUE_TIEM);
        return buyVehicleRecord;
    }

    @Before
    public void initTest() {
        buyVehicleRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuyVehicleRecord() throws Exception {
        int databaseSizeBeforeCreate = buyVehicleRecordRepository.findAll().size();

        // Create the BuyVehicleRecord

        restBuyVehicleRecordMockMvc.perform(post("/api/buy-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyVehicleRecord)))
            .andExpect(status().isCreated());

        // Validate the BuyVehicleRecord in the database
        List<BuyVehicleRecord> buyVehicleRecordList = buyVehicleRecordRepository.findAll();
        assertThat(buyVehicleRecordList).hasSize(databaseSizeBeforeCreate + 1);
        BuyVehicleRecord testBuyVehicleRecord = buyVehicleRecordList.get(buyVehicleRecordList.size() - 1);
        assertThat(testBuyVehicleRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testBuyVehicleRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testBuyVehicleRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testBuyVehicleRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testBuyVehicleRecord.getBuyVehicleDate()).isEqualTo(DEFAULT_BUY_VEHICLE_DATE);
        assertThat(testBuyVehicleRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testBuyVehicleRecord.getVehicleShelf()).isEqualTo(DEFAULT_VEHICLE_SHELF);
        assertThat(testBuyVehicleRecord.getEngine()).isEqualTo(DEFAULT_ENGINE);
        assertThat(testBuyVehicleRecord.getDownPayment()).isEqualTo(DEFAULT_DOWN_PAYMENT);
        assertThat(testBuyVehicleRecord.getCashPledge()).isEqualTo(DEFAULT_CASH_PLEDGE);
        assertThat(testBuyVehicleRecord.getPeriods()).isEqualTo(DEFAULT_PERIODS);
        assertThat(testBuyVehicleRecord.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testBuyVehicleRecord.getNotPeriods()).isEqualTo(DEFAULT_NOT_PERIODS);
        assertThat(testBuyVehicleRecord.getNotAmount()).isEqualTo(DEFAULT_NOT_AMOUNT);
        assertThat(testBuyVehicleRecord.getPostponeTime()).isEqualTo(DEFAULT_POSTPONE_TIME);
        assertThat(testBuyVehicleRecord.getOverdueTiem()).isEqualTo(DEFAULT_OVERDUE_TIEM);
    }

    @Test
    @Transactional
    public void createBuyVehicleRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buyVehicleRecordRepository.findAll().size();

        // Create the BuyVehicleRecord with an existing ID
        BuyVehicleRecord existingBuyVehicleRecord = new BuyVehicleRecord();
        existingBuyVehicleRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuyVehicleRecordMockMvc.perform(post("/api/buy-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBuyVehicleRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<BuyVehicleRecord> buyVehicleRecordList = buyVehicleRecordRepository.findAll();
        assertThat(buyVehicleRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBuyVehicleRecords() throws Exception {
        // Initialize the database
        buyVehicleRecordRepository.saveAndFlush(buyVehicleRecord);

        // Get all the buyVehicleRecordList
        restBuyVehicleRecordMockMvc.perform(get("/api/buy-vehicle-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buyVehicleRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].buyVehicleDate").value(hasItem(DEFAULT_BUY_VEHICLE_DATE.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].vehicleShelf").value(hasItem(DEFAULT_VEHICLE_SHELF.toString())))
            .andExpect(jsonPath("$.[*].engine").value(hasItem(DEFAULT_ENGINE.toString())))
            .andExpect(jsonPath("$.[*].downPayment").value(hasItem(DEFAULT_DOWN_PAYMENT.intValue())))
            .andExpect(jsonPath("$.[*].cashPledge").value(hasItem(DEFAULT_CASH_PLEDGE.intValue())))
            .andExpect(jsonPath("$.[*].periods").value(hasItem(DEFAULT_PERIODS)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].notPeriods").value(hasItem(DEFAULT_NOT_PERIODS)))
            .andExpect(jsonPath("$.[*].notAmount").value(hasItem(DEFAULT_NOT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].postponeTime").value(hasItem(DEFAULT_POSTPONE_TIME)))
            .andExpect(jsonPath("$.[*].overdueTiem").value(hasItem(DEFAULT_OVERDUE_TIEM)));
    }

    @Test
    @Transactional
    public void getBuyVehicleRecord() throws Exception {
        // Initialize the database
        buyVehicleRecordRepository.saveAndFlush(buyVehicleRecord);

        // Get the buyVehicleRecord
        restBuyVehicleRecordMockMvc.perform(get("/api/buy-vehicle-records/{id}", buyVehicleRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buyVehicleRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.buyVehicleDate").value(DEFAULT_BUY_VEHICLE_DATE.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.vehicleShelf").value(DEFAULT_VEHICLE_SHELF.toString()))
            .andExpect(jsonPath("$.engine").value(DEFAULT_ENGINE.toString()))
            .andExpect(jsonPath("$.downPayment").value(DEFAULT_DOWN_PAYMENT.intValue()))
            .andExpect(jsonPath("$.cashPledge").value(DEFAULT_CASH_PLEDGE.intValue()))
            .andExpect(jsonPath("$.periods").value(DEFAULT_PERIODS))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.notPeriods").value(DEFAULT_NOT_PERIODS))
            .andExpect(jsonPath("$.notAmount").value(DEFAULT_NOT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.postponeTime").value(DEFAULT_POSTPONE_TIME))
            .andExpect(jsonPath("$.overdueTiem").value(DEFAULT_OVERDUE_TIEM));
    }

    @Test
    @Transactional
    public void getNonExistingBuyVehicleRecord() throws Exception {
        // Get the buyVehicleRecord
        restBuyVehicleRecordMockMvc.perform(get("/api/buy-vehicle-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuyVehicleRecord() throws Exception {
        // Initialize the database
        buyVehicleRecordService.save(buyVehicleRecord);

        int databaseSizeBeforeUpdate = buyVehicleRecordRepository.findAll().size();

        // Update the buyVehicleRecord
        BuyVehicleRecord updatedBuyVehicleRecord = buyVehicleRecordRepository.findOne(buyVehicleRecord.getId());
        updatedBuyVehicleRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .buyVehicleDate(UPDATED_BUY_VEHICLE_DATE)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .vehicleShelf(UPDATED_VEHICLE_SHELF)
                .engine(UPDATED_ENGINE)
                .downPayment(UPDATED_DOWN_PAYMENT)
                .cashPledge(UPDATED_CASH_PLEDGE)
                .periods(UPDATED_PERIODS)
                .amount(UPDATED_AMOUNT)
                .notPeriods(UPDATED_NOT_PERIODS)
                .notAmount(UPDATED_NOT_AMOUNT)
                .postponeTime(UPDATED_POSTPONE_TIME)
                .overdueTiem(UPDATED_OVERDUE_TIEM);

        restBuyVehicleRecordMockMvc.perform(put("/api/buy-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBuyVehicleRecord)))
            .andExpect(status().isOk());

        // Validate the BuyVehicleRecord in the database
        List<BuyVehicleRecord> buyVehicleRecordList = buyVehicleRecordRepository.findAll();
        assertThat(buyVehicleRecordList).hasSize(databaseSizeBeforeUpdate);
        BuyVehicleRecord testBuyVehicleRecord = buyVehicleRecordList.get(buyVehicleRecordList.size() - 1);
        assertThat(testBuyVehicleRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testBuyVehicleRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testBuyVehicleRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testBuyVehicleRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testBuyVehicleRecord.getBuyVehicleDate()).isEqualTo(UPDATED_BUY_VEHICLE_DATE);
        assertThat(testBuyVehicleRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testBuyVehicleRecord.getVehicleShelf()).isEqualTo(UPDATED_VEHICLE_SHELF);
        assertThat(testBuyVehicleRecord.getEngine()).isEqualTo(UPDATED_ENGINE);
        assertThat(testBuyVehicleRecord.getDownPayment()).isEqualTo(UPDATED_DOWN_PAYMENT);
        assertThat(testBuyVehicleRecord.getCashPledge()).isEqualTo(UPDATED_CASH_PLEDGE);
        assertThat(testBuyVehicleRecord.getPeriods()).isEqualTo(UPDATED_PERIODS);
        assertThat(testBuyVehicleRecord.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testBuyVehicleRecord.getNotPeriods()).isEqualTo(UPDATED_NOT_PERIODS);
        assertThat(testBuyVehicleRecord.getNotAmount()).isEqualTo(UPDATED_NOT_AMOUNT);
        assertThat(testBuyVehicleRecord.getPostponeTime()).isEqualTo(UPDATED_POSTPONE_TIME);
        assertThat(testBuyVehicleRecord.getOverdueTiem()).isEqualTo(UPDATED_OVERDUE_TIEM);
    }

    @Test
    @Transactional
    public void updateNonExistingBuyVehicleRecord() throws Exception {
        int databaseSizeBeforeUpdate = buyVehicleRecordRepository.findAll().size();

        // Create the BuyVehicleRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBuyVehicleRecordMockMvc.perform(put("/api/buy-vehicle-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyVehicleRecord)))
            .andExpect(status().isCreated());

        // Validate the BuyVehicleRecord in the database
        List<BuyVehicleRecord> buyVehicleRecordList = buyVehicleRecordRepository.findAll();
        assertThat(buyVehicleRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBuyVehicleRecord() throws Exception {
        // Initialize the database
        buyVehicleRecordService.save(buyVehicleRecord);

        int databaseSizeBeforeDelete = buyVehicleRecordRepository.findAll().size();

        // Get the buyVehicleRecord
        restBuyVehicleRecordMockMvc.perform(delete("/api/buy-vehicle-records/{id}", buyVehicleRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BuyVehicleRecord> buyVehicleRecordList = buyVehicleRecordRepository.findAll();
        assertThat(buyVehicleRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyVehicleRecord.class);
    }
}

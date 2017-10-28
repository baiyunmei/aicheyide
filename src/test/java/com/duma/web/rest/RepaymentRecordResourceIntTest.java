package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.RepaymentRecord;
import com.duma.repository.RepaymentRecordRepository;
import com.duma.service.RepaymentRecordService;
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
 * Test class for the RepaymentRecordResource REST controller.
 *
 * @see RepaymentRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class RepaymentRecordResourceIntTest {

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

    private static final Long DEFAULT_REPAYMENT_TIME = 1L;
    private static final Long UPDATED_REPAYMENT_TIME = 2L;

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_OVERDUE = 1;
    private static final Integer UPDATED_OVERDUE = 2;

    private static final Integer DEFAULT_PERIODS = 1;
    private static final Integer UPDATED_PERIODS = 2;

    private static final BigDecimal DEFAULT_NEXT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_NEXT_MONEY = new BigDecimal(2);

    private static final Long DEFAULT_NEXT_DATE = 1L;
    private static final Long UPDATED_NEXT_DATE = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Long DEFAULT_OPERATION_TIME = 1L;
    private static final Long UPDATED_OPERATION_TIME = 2L;

    @Autowired
    private RepaymentRecordRepository repaymentRecordRepository;

    @Autowired
    private RepaymentRecordService repaymentRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRepaymentRecordMockMvc;

    private RepaymentRecord repaymentRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RepaymentRecordResource repaymentRecordResource = new RepaymentRecordResource(repaymentRecordService);
        this.restRepaymentRecordMockMvc = MockMvcBuilders.standaloneSetup(repaymentRecordResource)
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
    public static RepaymentRecord createEntity(EntityManager em) {
        RepaymentRecord repaymentRecord = new RepaymentRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .repaymentTime(DEFAULT_REPAYMENT_TIME)
                .money(DEFAULT_MONEY)
                .overdue(DEFAULT_OVERDUE)
                .periods(DEFAULT_PERIODS)
                .nextMoney(DEFAULT_NEXT_MONEY)
                .nextDate(DEFAULT_NEXT_DATE)
                .remark(DEFAULT_REMARK)
                .operationTime(DEFAULT_OPERATION_TIME);
        return repaymentRecord;
    }

    @Before
    public void initTest() {
        repaymentRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createRepaymentRecord() throws Exception {
        int databaseSizeBeforeCreate = repaymentRecordRepository.findAll().size();

        // Create the RepaymentRecord

        restRepaymentRecordMockMvc.perform(post("/api/repayment-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(repaymentRecord)))
            .andExpect(status().isCreated());

        // Validate the RepaymentRecord in the database
        List<RepaymentRecord> repaymentRecordList = repaymentRecordRepository.findAll();
        assertThat(repaymentRecordList).hasSize(databaseSizeBeforeCreate + 1);
        RepaymentRecord testRepaymentRecord = repaymentRecordList.get(repaymentRecordList.size() - 1);
        assertThat(testRepaymentRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testRepaymentRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testRepaymentRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testRepaymentRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testRepaymentRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testRepaymentRecord.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testRepaymentRecord.getRepaymentTime()).isEqualTo(DEFAULT_REPAYMENT_TIME);
        assertThat(testRepaymentRecord.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testRepaymentRecord.getOverdue()).isEqualTo(DEFAULT_OVERDUE);
        assertThat(testRepaymentRecord.getPeriods()).isEqualTo(DEFAULT_PERIODS);
        assertThat(testRepaymentRecord.getNextMoney()).isEqualTo(DEFAULT_NEXT_MONEY);
        assertThat(testRepaymentRecord.getNextDate()).isEqualTo(DEFAULT_NEXT_DATE);
        assertThat(testRepaymentRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testRepaymentRecord.getOperationTime()).isEqualTo(DEFAULT_OPERATION_TIME);
    }

    @Test
    @Transactional
    public void createRepaymentRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = repaymentRecordRepository.findAll().size();

        // Create the RepaymentRecord with an existing ID
        RepaymentRecord existingRepaymentRecord = new RepaymentRecord();
        existingRepaymentRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRepaymentRecordMockMvc.perform(post("/api/repayment-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRepaymentRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RepaymentRecord> repaymentRecordList = repaymentRecordRepository.findAll();
        assertThat(repaymentRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRepaymentRecords() throws Exception {
        // Initialize the database
        repaymentRecordRepository.saveAndFlush(repaymentRecord);

        // Get all the repaymentRecordList
        restRepaymentRecordMockMvc.perform(get("/api/repayment-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(repaymentRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].repaymentTime").value(hasItem(DEFAULT_REPAYMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].overdue").value(hasItem(DEFAULT_OVERDUE)))
            .andExpect(jsonPath("$.[*].periods").value(hasItem(DEFAULT_PERIODS)))
            .andExpect(jsonPath("$.[*].nextMoney").value(hasItem(DEFAULT_NEXT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].nextDate").value(hasItem(DEFAULT_NEXT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].operationTime").value(hasItem(DEFAULT_OPERATION_TIME.intValue())));
    }

    @Test
    @Transactional
    public void getRepaymentRecord() throws Exception {
        // Initialize the database
        repaymentRecordRepository.saveAndFlush(repaymentRecord);

        // Get the repaymentRecord
        restRepaymentRecordMockMvc.perform(get("/api/repayment-records/{id}", repaymentRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(repaymentRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.repaymentTime").value(DEFAULT_REPAYMENT_TIME.intValue()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.overdue").value(DEFAULT_OVERDUE))
            .andExpect(jsonPath("$.periods").value(DEFAULT_PERIODS))
            .andExpect(jsonPath("$.nextMoney").value(DEFAULT_NEXT_MONEY.intValue()))
            .andExpect(jsonPath("$.nextDate").value(DEFAULT_NEXT_DATE.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.operationTime").value(DEFAULT_OPERATION_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRepaymentRecord() throws Exception {
        // Get the repaymentRecord
        restRepaymentRecordMockMvc.perform(get("/api/repayment-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRepaymentRecord() throws Exception {
        // Initialize the database
        repaymentRecordService.save(repaymentRecord);

        int databaseSizeBeforeUpdate = repaymentRecordRepository.findAll().size();

        // Update the repaymentRecord
        RepaymentRecord updatedRepaymentRecord = repaymentRecordRepository.findOne(repaymentRecord.getId());
        updatedRepaymentRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .repaymentTime(UPDATED_REPAYMENT_TIME)
                .money(UPDATED_MONEY)
                .overdue(UPDATED_OVERDUE)
                .periods(UPDATED_PERIODS)
                .nextMoney(UPDATED_NEXT_MONEY)
                .nextDate(UPDATED_NEXT_DATE)
                .remark(UPDATED_REMARK)
                .operationTime(UPDATED_OPERATION_TIME);

        restRepaymentRecordMockMvc.perform(put("/api/repayment-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRepaymentRecord)))
            .andExpect(status().isOk());

        // Validate the RepaymentRecord in the database
        List<RepaymentRecord> repaymentRecordList = repaymentRecordRepository.findAll();
        assertThat(repaymentRecordList).hasSize(databaseSizeBeforeUpdate);
        RepaymentRecord testRepaymentRecord = repaymentRecordList.get(repaymentRecordList.size() - 1);
        assertThat(testRepaymentRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testRepaymentRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testRepaymentRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testRepaymentRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testRepaymentRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testRepaymentRecord.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testRepaymentRecord.getRepaymentTime()).isEqualTo(UPDATED_REPAYMENT_TIME);
        assertThat(testRepaymentRecord.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testRepaymentRecord.getOverdue()).isEqualTo(UPDATED_OVERDUE);
        assertThat(testRepaymentRecord.getPeriods()).isEqualTo(UPDATED_PERIODS);
        assertThat(testRepaymentRecord.getNextMoney()).isEqualTo(UPDATED_NEXT_MONEY);
        assertThat(testRepaymentRecord.getNextDate()).isEqualTo(UPDATED_NEXT_DATE);
        assertThat(testRepaymentRecord.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testRepaymentRecord.getOperationTime()).isEqualTo(UPDATED_OPERATION_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingRepaymentRecord() throws Exception {
        int databaseSizeBeforeUpdate = repaymentRecordRepository.findAll().size();

        // Create the RepaymentRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRepaymentRecordMockMvc.perform(put("/api/repayment-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(repaymentRecord)))
            .andExpect(status().isCreated());

        // Validate the RepaymentRecord in the database
        List<RepaymentRecord> repaymentRecordList = repaymentRecordRepository.findAll();
        assertThat(repaymentRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRepaymentRecord() throws Exception {
        // Initialize the database
        repaymentRecordService.save(repaymentRecord);

        int databaseSizeBeforeDelete = repaymentRecordRepository.findAll().size();

        // Get the repaymentRecord
        restRepaymentRecordMockMvc.perform(delete("/api/repayment-records/{id}", repaymentRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RepaymentRecord> repaymentRecordList = repaymentRecordRepository.findAll();
        assertThat(repaymentRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RepaymentRecord.class);
    }
}

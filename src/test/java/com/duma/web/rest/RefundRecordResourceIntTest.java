package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.RefundRecord;
import com.duma.repository.RefundRecordRepository;
import com.duma.service.RefundRecordService;
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
 * Test class for the RefundRecordResource REST controller.
 *
 * @see RefundRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class RefundRecordResourceIntTest {

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

    private static final Long DEFAULT_REFUND_TIME = 1L;
    private static final Long UPDATED_REFUND_TIME = 2L;

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEPOSIT_STATUS = 1;
    private static final Integer UPDATED_DEPOSIT_STATUS = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private RefundRecordRepository refundRecordRepository;

    @Autowired
    private RefundRecordService refundRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefundRecordMockMvc;

    private RefundRecord refundRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RefundRecordResource refundRecordResource = new RefundRecordResource(refundRecordService);
        this.restRefundRecordMockMvc = MockMvcBuilders.standaloneSetup(refundRecordResource)
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
    public static RefundRecord createEntity(EntityManager em) {
        RefundRecord refundRecord = new RefundRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .refundTime(DEFAULT_REFUND_TIME)
                .money(DEFAULT_MONEY)
                .source(DEFAULT_SOURCE)
                .depositStatus(DEFAULT_DEPOSIT_STATUS)
                .remark(DEFAULT_REMARK);
        return refundRecord;
    }

    @Before
    public void initTest() {
        refundRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefundRecord() throws Exception {
        int databaseSizeBeforeCreate = refundRecordRepository.findAll().size();

        // Create the RefundRecord

        restRefundRecordMockMvc.perform(post("/api/refund-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refundRecord)))
            .andExpect(status().isCreated());

        // Validate the RefundRecord in the database
        List<RefundRecord> refundRecordList = refundRecordRepository.findAll();
        assertThat(refundRecordList).hasSize(databaseSizeBeforeCreate + 1);
        RefundRecord testRefundRecord = refundRecordList.get(refundRecordList.size() - 1);
        assertThat(testRefundRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testRefundRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testRefundRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testRefundRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testRefundRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testRefundRecord.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testRefundRecord.getRefundTime()).isEqualTo(DEFAULT_REFUND_TIME);
        assertThat(testRefundRecord.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testRefundRecord.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testRefundRecord.getDepositStatus()).isEqualTo(DEFAULT_DEPOSIT_STATUS);
        assertThat(testRefundRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createRefundRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refundRecordRepository.findAll().size();

        // Create the RefundRecord with an existing ID
        RefundRecord existingRefundRecord = new RefundRecord();
        existingRefundRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefundRecordMockMvc.perform(post("/api/refund-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRefundRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RefundRecord> refundRecordList = refundRecordRepository.findAll();
        assertThat(refundRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefundRecords() throws Exception {
        // Initialize the database
        refundRecordRepository.saveAndFlush(refundRecord);

        // Get all the refundRecordList
        restRefundRecordMockMvc.perform(get("/api/refund-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refundRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].refundTime").value(hasItem(DEFAULT_REFUND_TIME.intValue())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].depositStatus").value(hasItem(DEFAULT_DEPOSIT_STATUS)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getRefundRecord() throws Exception {
        // Initialize the database
        refundRecordRepository.saveAndFlush(refundRecord);

        // Get the refundRecord
        restRefundRecordMockMvc.perform(get("/api/refund-records/{id}", refundRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refundRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.refundTime").value(DEFAULT_REFUND_TIME.intValue()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.depositStatus").value(DEFAULT_DEPOSIT_STATUS))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefundRecord() throws Exception {
        // Get the refundRecord
        restRefundRecordMockMvc.perform(get("/api/refund-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefundRecord() throws Exception {
        // Initialize the database
        refundRecordService.save(refundRecord);

        int databaseSizeBeforeUpdate = refundRecordRepository.findAll().size();

        // Update the refundRecord
        RefundRecord updatedRefundRecord = refundRecordRepository.findOne(refundRecord.getId());
        updatedRefundRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .refundTime(UPDATED_REFUND_TIME)
                .money(UPDATED_MONEY)
                .source(UPDATED_SOURCE)
                .depositStatus(UPDATED_DEPOSIT_STATUS)
                .remark(UPDATED_REMARK);

        restRefundRecordMockMvc.perform(put("/api/refund-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefundRecord)))
            .andExpect(status().isOk());

        // Validate the RefundRecord in the database
        List<RefundRecord> refundRecordList = refundRecordRepository.findAll();
        assertThat(refundRecordList).hasSize(databaseSizeBeforeUpdate);
        RefundRecord testRefundRecord = refundRecordList.get(refundRecordList.size() - 1);
        assertThat(testRefundRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testRefundRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testRefundRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testRefundRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testRefundRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testRefundRecord.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testRefundRecord.getRefundTime()).isEqualTo(UPDATED_REFUND_TIME);
        assertThat(testRefundRecord.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testRefundRecord.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testRefundRecord.getDepositStatus()).isEqualTo(UPDATED_DEPOSIT_STATUS);
        assertThat(testRefundRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingRefundRecord() throws Exception {
        int databaseSizeBeforeUpdate = refundRecordRepository.findAll().size();

        // Create the RefundRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRefundRecordMockMvc.perform(put("/api/refund-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refundRecord)))
            .andExpect(status().isCreated());

        // Validate the RefundRecord in the database
        List<RefundRecord> refundRecordList = refundRecordRepository.findAll();
        assertThat(refundRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRefundRecord() throws Exception {
        // Initialize the database
        refundRecordService.save(refundRecord);

        int databaseSizeBeforeDelete = refundRecordRepository.findAll().size();

        // Get the refundRecord
        restRefundRecordMockMvc.perform(delete("/api/refund-records/{id}", refundRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefundRecord> refundRecordList = refundRecordRepository.findAll();
        assertThat(refundRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefundRecord.class);
    }
}

package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.OutDangerRecord;
import com.duma.repository.OutDangerRecordRepository;
import com.duma.service.OutDangerRecordService;
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
 * Test class for the OutDangerRecordResource REST controller.
 *
 * @see OutDangerRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class OutDangerRecordResourceIntTest {

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

    private static final Long DEFAULT_COME_TIME = 1L;
    private static final Long UPDATED_COME_TIME = 2L;

    private static final Long DEFAULT_RESPONSIBLE_PARTY = 1L;
    private static final Long UPDATED_RESPONSIBLE_PARTY = 2L;

    private static final BigDecimal DEFAULT_ONE_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_ONE_MONEY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_THREE_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_THREE_MONEY = new BigDecimal(2);

    private static final String DEFAULT_REPAIR_FACTORY = "AAAAAAAAAA";
    private static final String UPDATED_REPAIR_FACTORY = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private OutDangerRecordRepository outDangerRecordRepository;

    @Autowired
    private OutDangerRecordService outDangerRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOutDangerRecordMockMvc;

    private OutDangerRecord outDangerRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OutDangerRecordResource outDangerRecordResource = new OutDangerRecordResource(outDangerRecordService);
        this.restOutDangerRecordMockMvc = MockMvcBuilders.standaloneSetup(outDangerRecordResource)
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
    public static OutDangerRecord createEntity(EntityManager em) {
        OutDangerRecord outDangerRecord = new OutDangerRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .comeTime(DEFAULT_COME_TIME)
                .responsibleParty(DEFAULT_RESPONSIBLE_PARTY)
                .oneMoney(DEFAULT_ONE_MONEY)
                .threeMoney(DEFAULT_THREE_MONEY)
                .repairFactory(DEFAULT_REPAIR_FACTORY)
                .remark(DEFAULT_REMARK);
        return outDangerRecord;
    }

    @Before
    public void initTest() {
        outDangerRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createOutDangerRecord() throws Exception {
        int databaseSizeBeforeCreate = outDangerRecordRepository.findAll().size();

        // Create the OutDangerRecord

        restOutDangerRecordMockMvc.perform(post("/api/out-danger-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(outDangerRecord)))
            .andExpect(status().isCreated());

        // Validate the OutDangerRecord in the database
        List<OutDangerRecord> outDangerRecordList = outDangerRecordRepository.findAll();
        assertThat(outDangerRecordList).hasSize(databaseSizeBeforeCreate + 1);
        OutDangerRecord testOutDangerRecord = outDangerRecordList.get(outDangerRecordList.size() - 1);
        assertThat(testOutDangerRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testOutDangerRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testOutDangerRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testOutDangerRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testOutDangerRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testOutDangerRecord.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testOutDangerRecord.getComeTime()).isEqualTo(DEFAULT_COME_TIME);
        assertThat(testOutDangerRecord.getResponsibleParty()).isEqualTo(DEFAULT_RESPONSIBLE_PARTY);
        assertThat(testOutDangerRecord.getOneMoney()).isEqualTo(DEFAULT_ONE_MONEY);
        assertThat(testOutDangerRecord.getThreeMoney()).isEqualTo(DEFAULT_THREE_MONEY);
        assertThat(testOutDangerRecord.getRepairFactory()).isEqualTo(DEFAULT_REPAIR_FACTORY);
        assertThat(testOutDangerRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createOutDangerRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = outDangerRecordRepository.findAll().size();

        // Create the OutDangerRecord with an existing ID
        OutDangerRecord existingOutDangerRecord = new OutDangerRecord();
        existingOutDangerRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutDangerRecordMockMvc.perform(post("/api/out-danger-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingOutDangerRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<OutDangerRecord> outDangerRecordList = outDangerRecordRepository.findAll();
        assertThat(outDangerRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOutDangerRecords() throws Exception {
        // Initialize the database
        outDangerRecordRepository.saveAndFlush(outDangerRecord);

        // Get all the outDangerRecordList
        restOutDangerRecordMockMvc.perform(get("/api/out-danger-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outDangerRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].comeTime").value(hasItem(DEFAULT_COME_TIME.intValue())))
            .andExpect(jsonPath("$.[*].responsibleParty").value(hasItem(DEFAULT_RESPONSIBLE_PARTY.intValue())))
            .andExpect(jsonPath("$.[*].oneMoney").value(hasItem(DEFAULT_ONE_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].threeMoney").value(hasItem(DEFAULT_THREE_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].repairFactory").value(hasItem(DEFAULT_REPAIR_FACTORY.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getOutDangerRecord() throws Exception {
        // Initialize the database
        outDangerRecordRepository.saveAndFlush(outDangerRecord);

        // Get the outDangerRecord
        restOutDangerRecordMockMvc.perform(get("/api/out-danger-records/{id}", outDangerRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(outDangerRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.comeTime").value(DEFAULT_COME_TIME.intValue()))
            .andExpect(jsonPath("$.responsibleParty").value(DEFAULT_RESPONSIBLE_PARTY.intValue()))
            .andExpect(jsonPath("$.oneMoney").value(DEFAULT_ONE_MONEY.intValue()))
            .andExpect(jsonPath("$.threeMoney").value(DEFAULT_THREE_MONEY.intValue()))
            .andExpect(jsonPath("$.repairFactory").value(DEFAULT_REPAIR_FACTORY.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOutDangerRecord() throws Exception {
        // Get the outDangerRecord
        restOutDangerRecordMockMvc.perform(get("/api/out-danger-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOutDangerRecord() throws Exception {
        // Initialize the database
        outDangerRecordService.save(outDangerRecord);

        int databaseSizeBeforeUpdate = outDangerRecordRepository.findAll().size();

        // Update the outDangerRecord
        OutDangerRecord updatedOutDangerRecord = outDangerRecordRepository.findOne(outDangerRecord.getId());
        updatedOutDangerRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .comeTime(UPDATED_COME_TIME)
                .responsibleParty(UPDATED_RESPONSIBLE_PARTY)
                .oneMoney(UPDATED_ONE_MONEY)
                .threeMoney(UPDATED_THREE_MONEY)
                .repairFactory(UPDATED_REPAIR_FACTORY)
                .remark(UPDATED_REMARK);

        restOutDangerRecordMockMvc.perform(put("/api/out-danger-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOutDangerRecord)))
            .andExpect(status().isOk());

        // Validate the OutDangerRecord in the database
        List<OutDangerRecord> outDangerRecordList = outDangerRecordRepository.findAll();
        assertThat(outDangerRecordList).hasSize(databaseSizeBeforeUpdate);
        OutDangerRecord testOutDangerRecord = outDangerRecordList.get(outDangerRecordList.size() - 1);
        assertThat(testOutDangerRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testOutDangerRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testOutDangerRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testOutDangerRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testOutDangerRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testOutDangerRecord.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testOutDangerRecord.getComeTime()).isEqualTo(UPDATED_COME_TIME);
        assertThat(testOutDangerRecord.getResponsibleParty()).isEqualTo(UPDATED_RESPONSIBLE_PARTY);
        assertThat(testOutDangerRecord.getOneMoney()).isEqualTo(UPDATED_ONE_MONEY);
        assertThat(testOutDangerRecord.getThreeMoney()).isEqualTo(UPDATED_THREE_MONEY);
        assertThat(testOutDangerRecord.getRepairFactory()).isEqualTo(UPDATED_REPAIR_FACTORY);
        assertThat(testOutDangerRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingOutDangerRecord() throws Exception {
        int databaseSizeBeforeUpdate = outDangerRecordRepository.findAll().size();

        // Create the OutDangerRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOutDangerRecordMockMvc.perform(put("/api/out-danger-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(outDangerRecord)))
            .andExpect(status().isCreated());

        // Validate the OutDangerRecord in the database
        List<OutDangerRecord> outDangerRecordList = outDangerRecordRepository.findAll();
        assertThat(outDangerRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOutDangerRecord() throws Exception {
        // Initialize the database
        outDangerRecordService.save(outDangerRecord);

        int databaseSizeBeforeDelete = outDangerRecordRepository.findAll().size();

        // Get the outDangerRecord
        restOutDangerRecordMockMvc.perform(delete("/api/out-danger-records/{id}", outDangerRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OutDangerRecord> outDangerRecordList = outDangerRecordRepository.findAll();
        assertThat(outDangerRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutDangerRecord.class);
    }
}

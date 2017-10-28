package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.IllegalRecord;
import com.duma.repository.IllegalRecordRepository;
import com.duma.service.IllegalRecordService;
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
 * Test class for the IllegalRecordResource REST controller.
 *
 * @see IllegalRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class IllegalRecordResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_ILLEGAL_DATE = 1L;
    private static final Long UPDATED_ILLEGAL_DATE = 2L;

    private static final String DEFAULT_ILLEGAL_SITE = "AAAAAAAAAA";
    private static final String UPDATED_ILLEGAL_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ILLEGAL_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_ILLEGAL_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_ILLEGAL_DEDUCT = 1;
    private static final Integer UPDATED_ILLEGAL_DEDUCT = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private IllegalRecordRepository illegalRecordRepository;

    @Autowired
    private IllegalRecordService illegalRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIllegalRecordMockMvc;

    private IllegalRecord illegalRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IllegalRecordResource illegalRecordResource = new IllegalRecordResource(illegalRecordService);
        this.restIllegalRecordMockMvc = MockMvcBuilders.standaloneSetup(illegalRecordResource)
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
    public static IllegalRecord createEntity(EntityManager em) {
        IllegalRecord illegalRecord = new IllegalRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .illegalDate(DEFAULT_ILLEGAL_DATE)
                .illegalSite(DEFAULT_ILLEGAL_SITE)
                .detail(DEFAULT_DETAIL)
                .illegalMoney(DEFAULT_ILLEGAL_MONEY)
                .illegalDeduct(DEFAULT_ILLEGAL_DEDUCT)
                .status(DEFAULT_STATUS);
        return illegalRecord;
    }

    @Before
    public void initTest() {
        illegalRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createIllegalRecord() throws Exception {
        int databaseSizeBeforeCreate = illegalRecordRepository.findAll().size();

        // Create the IllegalRecord

        restIllegalRecordMockMvc.perform(post("/api/illegal-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(illegalRecord)))
            .andExpect(status().isCreated());

        // Validate the IllegalRecord in the database
        List<IllegalRecord> illegalRecordList = illegalRecordRepository.findAll();
        assertThat(illegalRecordList).hasSize(databaseSizeBeforeCreate + 1);
        IllegalRecord testIllegalRecord = illegalRecordList.get(illegalRecordList.size() - 1);
        assertThat(testIllegalRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testIllegalRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testIllegalRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testIllegalRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testIllegalRecord.getIllegalDate()).isEqualTo(DEFAULT_ILLEGAL_DATE);
        assertThat(testIllegalRecord.getIllegalSite()).isEqualTo(DEFAULT_ILLEGAL_SITE);
        assertThat(testIllegalRecord.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testIllegalRecord.getIllegalMoney()).isEqualTo(DEFAULT_ILLEGAL_MONEY);
        assertThat(testIllegalRecord.getIllegalDeduct()).isEqualTo(DEFAULT_ILLEGAL_DEDUCT);
        assertThat(testIllegalRecord.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createIllegalRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = illegalRecordRepository.findAll().size();

        // Create the IllegalRecord with an existing ID
        IllegalRecord existingIllegalRecord = new IllegalRecord();
        existingIllegalRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIllegalRecordMockMvc.perform(post("/api/illegal-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingIllegalRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<IllegalRecord> illegalRecordList = illegalRecordRepository.findAll();
        assertThat(illegalRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIllegalRecords() throws Exception {
        // Initialize the database
        illegalRecordRepository.saveAndFlush(illegalRecord);

        // Get all the illegalRecordList
        restIllegalRecordMockMvc.perform(get("/api/illegal-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(illegalRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].illegalDate").value(hasItem(DEFAULT_ILLEGAL_DATE.intValue())))
            .andExpect(jsonPath("$.[*].illegalSite").value(hasItem(DEFAULT_ILLEGAL_SITE.toString())))
            .andExpect(jsonPath("$.[*].detail").value(hasItem(DEFAULT_DETAIL.toString())))
            .andExpect(jsonPath("$.[*].illegalMoney").value(hasItem(DEFAULT_ILLEGAL_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].illegalDeduct").value(hasItem(DEFAULT_ILLEGAL_DEDUCT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getIllegalRecord() throws Exception {
        // Initialize the database
        illegalRecordRepository.saveAndFlush(illegalRecord);

        // Get the illegalRecord
        restIllegalRecordMockMvc.perform(get("/api/illegal-records/{id}", illegalRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(illegalRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.illegalDate").value(DEFAULT_ILLEGAL_DATE.intValue()))
            .andExpect(jsonPath("$.illegalSite").value(DEFAULT_ILLEGAL_SITE.toString()))
            .andExpect(jsonPath("$.detail").value(DEFAULT_DETAIL.toString()))
            .andExpect(jsonPath("$.illegalMoney").value(DEFAULT_ILLEGAL_MONEY.intValue()))
            .andExpect(jsonPath("$.illegalDeduct").value(DEFAULT_ILLEGAL_DEDUCT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingIllegalRecord() throws Exception {
        // Get the illegalRecord
        restIllegalRecordMockMvc.perform(get("/api/illegal-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIllegalRecord() throws Exception {
        // Initialize the database
        illegalRecordService.save(illegalRecord);

        int databaseSizeBeforeUpdate = illegalRecordRepository.findAll().size();

        // Update the illegalRecord
        IllegalRecord updatedIllegalRecord = illegalRecordRepository.findOne(illegalRecord.getId());
        updatedIllegalRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .illegalDate(UPDATED_ILLEGAL_DATE)
                .illegalSite(UPDATED_ILLEGAL_SITE)
                .detail(UPDATED_DETAIL)
                .illegalMoney(UPDATED_ILLEGAL_MONEY)
                .illegalDeduct(UPDATED_ILLEGAL_DEDUCT)
                .status(UPDATED_STATUS);

        restIllegalRecordMockMvc.perform(put("/api/illegal-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIllegalRecord)))
            .andExpect(status().isOk());

        // Validate the IllegalRecord in the database
        List<IllegalRecord> illegalRecordList = illegalRecordRepository.findAll();
        assertThat(illegalRecordList).hasSize(databaseSizeBeforeUpdate);
        IllegalRecord testIllegalRecord = illegalRecordList.get(illegalRecordList.size() - 1);
        assertThat(testIllegalRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testIllegalRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testIllegalRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testIllegalRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testIllegalRecord.getIllegalDate()).isEqualTo(UPDATED_ILLEGAL_DATE);
        assertThat(testIllegalRecord.getIllegalSite()).isEqualTo(UPDATED_ILLEGAL_SITE);
        assertThat(testIllegalRecord.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testIllegalRecord.getIllegalMoney()).isEqualTo(UPDATED_ILLEGAL_MONEY);
        assertThat(testIllegalRecord.getIllegalDeduct()).isEqualTo(UPDATED_ILLEGAL_DEDUCT);
        assertThat(testIllegalRecord.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingIllegalRecord() throws Exception {
        int databaseSizeBeforeUpdate = illegalRecordRepository.findAll().size();

        // Create the IllegalRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIllegalRecordMockMvc.perform(put("/api/illegal-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(illegalRecord)))
            .andExpect(status().isCreated());

        // Validate the IllegalRecord in the database
        List<IllegalRecord> illegalRecordList = illegalRecordRepository.findAll();
        assertThat(illegalRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIllegalRecord() throws Exception {
        // Initialize the database
        illegalRecordService.save(illegalRecord);

        int databaseSizeBeforeDelete = illegalRecordRepository.findAll().size();

        // Get the illegalRecord
        restIllegalRecordMockMvc.perform(delete("/api/illegal-records/{id}", illegalRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IllegalRecord> illegalRecordList = illegalRecordRepository.findAll();
        assertThat(illegalRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IllegalRecord.class);
    }
}

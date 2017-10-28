package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.ValidateRecord;
import com.duma.repository.ValidateRecordRepository;
import com.duma.service.ValidateRecordService;
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
 * Test class for the ValidateRecordResource REST controller.
 *
 * @see ValidateRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class ValidateRecordResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_RECEIPT_DATE = 1L;
    private static final Long UPDATED_RECEIPT_DATE = 2L;

    private static final Long DEFAULT_OPERATING_DATE = 1L;
    private static final Long UPDATED_OPERATING_DATE = 2L;

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_VALIDATE_TIME = 1L;
    private static final Long UPDATED_VALIDATE_TIME = 2L;

    private static final Integer DEFAULT_KILOMETRE = 1;
    private static final Integer UPDATED_KILOMETRE = 2;

    private static final Integer DEFAULT_DAMAGE = 1;
    private static final Integer UPDATED_DAMAGE = 2;

    private static final String DEFAULT_DESCRIBES = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIBES = "BBBBBBBBBB";

    private static final String DEFAULT_DAMAGEP_ICTURE = "AAAAAAAAAA";
    private static final String UPDATED_DAMAGEP_ICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private ValidateRecordRepository validateRecordRepository;

    @Autowired
    private ValidateRecordService validateRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restValidateRecordMockMvc;

    private ValidateRecord validateRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ValidateRecordResource validateRecordResource = new ValidateRecordResource(validateRecordService);
        this.restValidateRecordMockMvc = MockMvcBuilders.standaloneSetup(validateRecordResource)
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
    public static ValidateRecord createEntity(EntityManager em) {
        ValidateRecord validateRecord = new ValidateRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .operatingDate(DEFAULT_OPERATING_DATE)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .validateTime(DEFAULT_VALIDATE_TIME)
                .kilometre(DEFAULT_KILOMETRE)
                .damage(DEFAULT_DAMAGE)
                .describes(DEFAULT_DESCRIBES)
                .damagepIcture(DEFAULT_DAMAGEP_ICTURE)
                .remark(DEFAULT_REMARK);
        return validateRecord;
    }

    @Before
    public void initTest() {
        validateRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createValidateRecord() throws Exception {
        int databaseSizeBeforeCreate = validateRecordRepository.findAll().size();

        // Create the ValidateRecord

        restValidateRecordMockMvc.perform(post("/api/validate-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validateRecord)))
            .andExpect(status().isCreated());

        // Validate the ValidateRecord in the database
        List<ValidateRecord> validateRecordList = validateRecordRepository.findAll();
        assertThat(validateRecordList).hasSize(databaseSizeBeforeCreate + 1);
        ValidateRecord testValidateRecord = validateRecordList.get(validateRecordList.size() - 1);
        assertThat(testValidateRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testValidateRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testValidateRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testValidateRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testValidateRecord.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testValidateRecord.getOperatingDate()).isEqualTo(DEFAULT_OPERATING_DATE);
        assertThat(testValidateRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testValidateRecord.getValidateTime()).isEqualTo(DEFAULT_VALIDATE_TIME);
        assertThat(testValidateRecord.getKilometre()).isEqualTo(DEFAULT_KILOMETRE);
        assertThat(testValidateRecord.getDamage()).isEqualTo(DEFAULT_DAMAGE);
        assertThat(testValidateRecord.getDescribes()).isEqualTo(DEFAULT_DESCRIBES);
        assertThat(testValidateRecord.getDamagepIcture()).isEqualTo(DEFAULT_DAMAGEP_ICTURE);
        assertThat(testValidateRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createValidateRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = validateRecordRepository.findAll().size();

        // Create the ValidateRecord with an existing ID
        ValidateRecord existingValidateRecord = new ValidateRecord();
        existingValidateRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValidateRecordMockMvc.perform(post("/api/validate-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingValidateRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ValidateRecord> validateRecordList = validateRecordRepository.findAll();
        assertThat(validateRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllValidateRecords() throws Exception {
        // Initialize the database
        validateRecordRepository.saveAndFlush(validateRecord);

        // Get all the validateRecordList
        restValidateRecordMockMvc.perform(get("/api/validate-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(validateRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].operatingDate").value(hasItem(DEFAULT_OPERATING_DATE.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].validateTime").value(hasItem(DEFAULT_VALIDATE_TIME.intValue())))
            .andExpect(jsonPath("$.[*].kilometre").value(hasItem(DEFAULT_KILOMETRE)))
            .andExpect(jsonPath("$.[*].damage").value(hasItem(DEFAULT_DAMAGE)))
            .andExpect(jsonPath("$.[*].describes").value(hasItem(DEFAULT_DESCRIBES.toString())))
            .andExpect(jsonPath("$.[*].damagepIcture").value(hasItem(DEFAULT_DAMAGEP_ICTURE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getValidateRecord() throws Exception {
        // Initialize the database
        validateRecordRepository.saveAndFlush(validateRecord);

        // Get the validateRecord
        restValidateRecordMockMvc.perform(get("/api/validate-records/{id}", validateRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(validateRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.operatingDate").value(DEFAULT_OPERATING_DATE.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.validateTime").value(DEFAULT_VALIDATE_TIME.intValue()))
            .andExpect(jsonPath("$.kilometre").value(DEFAULT_KILOMETRE))
            .andExpect(jsonPath("$.damage").value(DEFAULT_DAMAGE))
            .andExpect(jsonPath("$.describes").value(DEFAULT_DESCRIBES.toString()))
            .andExpect(jsonPath("$.damagepIcture").value(DEFAULT_DAMAGEP_ICTURE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingValidateRecord() throws Exception {
        // Get the validateRecord
        restValidateRecordMockMvc.perform(get("/api/validate-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValidateRecord() throws Exception {
        // Initialize the database
        validateRecordService.save(validateRecord);

        int databaseSizeBeforeUpdate = validateRecordRepository.findAll().size();

        // Update the validateRecord
        ValidateRecord updatedValidateRecord = validateRecordRepository.findOne(validateRecord.getId());
        updatedValidateRecord
                .driverId(UPDATED_DRIVER_ID)
                .companyId(UPDATED_COMPANY_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .operatingDate(UPDATED_OPERATING_DATE)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .validateTime(UPDATED_VALIDATE_TIME)
                .kilometre(UPDATED_KILOMETRE)
                .damage(UPDATED_DAMAGE)
                .describes(UPDATED_DESCRIBES)
                .damagepIcture(UPDATED_DAMAGEP_ICTURE)
                .remark(UPDATED_REMARK);

        restValidateRecordMockMvc.perform(put("/api/validate-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedValidateRecord)))
            .andExpect(status().isOk());

        // Validate the ValidateRecord in the database
        List<ValidateRecord> validateRecordList = validateRecordRepository.findAll();
        assertThat(validateRecordList).hasSize(databaseSizeBeforeUpdate);
        ValidateRecord testValidateRecord = validateRecordList.get(validateRecordList.size() - 1);
        assertThat(testValidateRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testValidateRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testValidateRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testValidateRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testValidateRecord.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testValidateRecord.getOperatingDate()).isEqualTo(UPDATED_OPERATING_DATE);
        assertThat(testValidateRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testValidateRecord.getValidateTime()).isEqualTo(UPDATED_VALIDATE_TIME);
        assertThat(testValidateRecord.getKilometre()).isEqualTo(UPDATED_KILOMETRE);
        assertThat(testValidateRecord.getDamage()).isEqualTo(UPDATED_DAMAGE);
        assertThat(testValidateRecord.getDescribes()).isEqualTo(UPDATED_DESCRIBES);
        assertThat(testValidateRecord.getDamagepIcture()).isEqualTo(UPDATED_DAMAGEP_ICTURE);
        assertThat(testValidateRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingValidateRecord() throws Exception {
        int databaseSizeBeforeUpdate = validateRecordRepository.findAll().size();

        // Create the ValidateRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restValidateRecordMockMvc.perform(put("/api/validate-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(validateRecord)))
            .andExpect(status().isCreated());

        // Validate the ValidateRecord in the database
        List<ValidateRecord> validateRecordList = validateRecordRepository.findAll();
        assertThat(validateRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteValidateRecord() throws Exception {
        // Initialize the database
        validateRecordService.save(validateRecord);

        int databaseSizeBeforeDelete = validateRecordRepository.findAll().size();

        // Get the validateRecord
        restValidateRecordMockMvc.perform(delete("/api/validate-records/{id}", validateRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ValidateRecord> validateRecordList = validateRecordRepository.findAll();
        assertThat(validateRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidateRecord.class);
    }
}

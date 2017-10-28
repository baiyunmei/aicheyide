package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.AiRecord;
import com.duma.repository.AiRecordRepository;
import com.duma.service.AiRecordService;
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
 * Test class for the AiRecordResource REST controller.
 *
 * @see AiRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class AiRecordResourceIntTest {

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

    private static final Long DEFAULT_AI_DATE = 1L;
    private static final Long UPDATED_AI_DATE = 2L;

    private static final Long DEFAULT_NEXT_AI_DATE = 1L;
    private static final Long UPDATED_NEXT_AI_DATE = 2L;

    private static final String DEFAULT_AI_CONDUCTOR = "AAAAAAAAAA";
    private static final String UPDATED_AI_CONDUCTOR = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private AiRecordRepository aiRecordRepository;

    @Autowired
    private AiRecordService aiRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAiRecordMockMvc;

    private AiRecord aiRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AiRecordResource aiRecordResource = new AiRecordResource(aiRecordService);
        this.restAiRecordMockMvc = MockMvcBuilders.standaloneSetup(aiRecordResource)
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
    public static AiRecord createEntity(EntityManager em) {
        AiRecord aiRecord = new AiRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .aiDate(DEFAULT_AI_DATE)
                .nextAiDate(DEFAULT_NEXT_AI_DATE)
                .aiConductor(DEFAULT_AI_CONDUCTOR)
                .money(DEFAULT_MONEY)
                .remark(DEFAULT_REMARK);
        return aiRecord;
    }

    @Before
    public void initTest() {
        aiRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createAiRecord() throws Exception {
        int databaseSizeBeforeCreate = aiRecordRepository.findAll().size();

        // Create the AiRecord

        restAiRecordMockMvc.perform(post("/api/ai-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aiRecord)))
            .andExpect(status().isCreated());

        // Validate the AiRecord in the database
        List<AiRecord> aiRecordList = aiRecordRepository.findAll();
        assertThat(aiRecordList).hasSize(databaseSizeBeforeCreate + 1);
        AiRecord testAiRecord = aiRecordList.get(aiRecordList.size() - 1);
        assertThat(testAiRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testAiRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testAiRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testAiRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testAiRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testAiRecord.getAiDate()).isEqualTo(DEFAULT_AI_DATE);
        assertThat(testAiRecord.getNextAiDate()).isEqualTo(DEFAULT_NEXT_AI_DATE);
        assertThat(testAiRecord.getAiConductor()).isEqualTo(DEFAULT_AI_CONDUCTOR);
        assertThat(testAiRecord.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testAiRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createAiRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aiRecordRepository.findAll().size();

        // Create the AiRecord with an existing ID
        AiRecord existingAiRecord = new AiRecord();
        existingAiRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAiRecordMockMvc.perform(post("/api/ai-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAiRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AiRecord> aiRecordList = aiRecordRepository.findAll();
        assertThat(aiRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAiRecords() throws Exception {
        // Initialize the database
        aiRecordRepository.saveAndFlush(aiRecord);

        // Get all the aiRecordList
        restAiRecordMockMvc.perform(get("/api/ai-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aiRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].aiDate").value(hasItem(DEFAULT_AI_DATE.intValue())))
            .andExpect(jsonPath("$.[*].nextAiDate").value(hasItem(DEFAULT_NEXT_AI_DATE.intValue())))
            .andExpect(jsonPath("$.[*].aiConductor").value(hasItem(DEFAULT_AI_CONDUCTOR.toString())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getAiRecord() throws Exception {
        // Initialize the database
        aiRecordRepository.saveAndFlush(aiRecord);

        // Get the aiRecord
        restAiRecordMockMvc.perform(get("/api/ai-records/{id}", aiRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aiRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.aiDate").value(DEFAULT_AI_DATE.intValue()))
            .andExpect(jsonPath("$.nextAiDate").value(DEFAULT_NEXT_AI_DATE.intValue()))
            .andExpect(jsonPath("$.aiConductor").value(DEFAULT_AI_CONDUCTOR.toString()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAiRecord() throws Exception {
        // Get the aiRecord
        restAiRecordMockMvc.perform(get("/api/ai-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAiRecord() throws Exception {
        // Initialize the database
        aiRecordService.save(aiRecord);

        int databaseSizeBeforeUpdate = aiRecordRepository.findAll().size();

        // Update the aiRecord
        AiRecord updatedAiRecord = aiRecordRepository.findOne(aiRecord.getId());
        updatedAiRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .aiDate(UPDATED_AI_DATE)
                .nextAiDate(UPDATED_NEXT_AI_DATE)
                .aiConductor(UPDATED_AI_CONDUCTOR)
                .money(UPDATED_MONEY)
                .remark(UPDATED_REMARK);

        restAiRecordMockMvc.perform(put("/api/ai-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAiRecord)))
            .andExpect(status().isOk());

        // Validate the AiRecord in the database
        List<AiRecord> aiRecordList = aiRecordRepository.findAll();
        assertThat(aiRecordList).hasSize(databaseSizeBeforeUpdate);
        AiRecord testAiRecord = aiRecordList.get(aiRecordList.size() - 1);
        assertThat(testAiRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testAiRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testAiRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testAiRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testAiRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testAiRecord.getAiDate()).isEqualTo(UPDATED_AI_DATE);
        assertThat(testAiRecord.getNextAiDate()).isEqualTo(UPDATED_NEXT_AI_DATE);
        assertThat(testAiRecord.getAiConductor()).isEqualTo(UPDATED_AI_CONDUCTOR);
        assertThat(testAiRecord.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testAiRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingAiRecord() throws Exception {
        int databaseSizeBeforeUpdate = aiRecordRepository.findAll().size();

        // Create the AiRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAiRecordMockMvc.perform(put("/api/ai-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aiRecord)))
            .andExpect(status().isCreated());

        // Validate the AiRecord in the database
        List<AiRecord> aiRecordList = aiRecordRepository.findAll();
        assertThat(aiRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAiRecord() throws Exception {
        // Initialize the database
        aiRecordService.save(aiRecord);

        int databaseSizeBeforeDelete = aiRecordRepository.findAll().size();

        // Get the aiRecord
        restAiRecordMockMvc.perform(delete("/api/ai-records/{id}", aiRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AiRecord> aiRecordList = aiRecordRepository.findAll();
        assertThat(aiRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AiRecord.class);
    }
}

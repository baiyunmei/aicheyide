package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.PostponeRecord;
import com.duma.repository.PostponeRecordRepository;
import com.duma.service.PostponeRecordService;
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
 * Test class for the PostponeRecordResource REST controller.
 *
 * @see PostponeRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class PostponeRecordResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_OPERATION_TIME = 1L;
    private static final Long UPDATED_OPERATION_TIME = 2L;

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_POSTPONE_DATA = 1;
    private static final Integer UPDATED_POSTPONE_DATA = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private PostponeRecordRepository postponeRecordRepository;

    @Autowired
    private PostponeRecordService postponeRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPostponeRecordMockMvc;

    private PostponeRecord postponeRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PostponeRecordResource postponeRecordResource = new PostponeRecordResource(postponeRecordService);
        this.restPostponeRecordMockMvc = MockMvcBuilders.standaloneSetup(postponeRecordResource)
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
    public static PostponeRecord createEntity(EntityManager em) {
        PostponeRecord postponeRecord = new PostponeRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .operationTime(DEFAULT_OPERATION_TIME)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .postponeData(DEFAULT_POSTPONE_DATA)
                .remark(DEFAULT_REMARK);
        return postponeRecord;
    }

    @Before
    public void initTest() {
        postponeRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createPostponeRecord() throws Exception {
        int databaseSizeBeforeCreate = postponeRecordRepository.findAll().size();

        // Create the PostponeRecord

        restPostponeRecordMockMvc.perform(post("/api/postpone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postponeRecord)))
            .andExpect(status().isCreated());

        // Validate the PostponeRecord in the database
        List<PostponeRecord> postponeRecordList = postponeRecordRepository.findAll();
        assertThat(postponeRecordList).hasSize(databaseSizeBeforeCreate + 1);
        PostponeRecord testPostponeRecord = postponeRecordList.get(postponeRecordList.size() - 1);
        assertThat(testPostponeRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testPostponeRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testPostponeRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testPostponeRecord.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testPostponeRecord.getOperationTime()).isEqualTo(DEFAULT_OPERATION_TIME);
        assertThat(testPostponeRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testPostponeRecord.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testPostponeRecord.getPostponeData()).isEqualTo(DEFAULT_POSTPONE_DATA);
        assertThat(testPostponeRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createPostponeRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = postponeRecordRepository.findAll().size();

        // Create the PostponeRecord with an existing ID
        PostponeRecord existingPostponeRecord = new PostponeRecord();
        existingPostponeRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPostponeRecordMockMvc.perform(post("/api/postpone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPostponeRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PostponeRecord> postponeRecordList = postponeRecordRepository.findAll();
        assertThat(postponeRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPostponeRecords() throws Exception {
        // Initialize the database
        postponeRecordRepository.saveAndFlush(postponeRecord);

        // Get all the postponeRecordList
        restPostponeRecordMockMvc.perform(get("/api/postpone-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(postponeRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].operationTime").value(hasItem(DEFAULT_OPERATION_TIME.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].postponeData").value(hasItem(DEFAULT_POSTPONE_DATA)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getPostponeRecord() throws Exception {
        // Initialize the database
        postponeRecordRepository.saveAndFlush(postponeRecord);

        // Get the postponeRecord
        restPostponeRecordMockMvc.perform(get("/api/postpone-records/{id}", postponeRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(postponeRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.operationTime").value(DEFAULT_OPERATION_TIME.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.postponeData").value(DEFAULT_POSTPONE_DATA))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPostponeRecord() throws Exception {
        // Get the postponeRecord
        restPostponeRecordMockMvc.perform(get("/api/postpone-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePostponeRecord() throws Exception {
        // Initialize the database
        postponeRecordService.save(postponeRecord);

        int databaseSizeBeforeUpdate = postponeRecordRepository.findAll().size();

        // Update the postponeRecord
        PostponeRecord updatedPostponeRecord = postponeRecordRepository.findOne(postponeRecord.getId());
        updatedPostponeRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .operationTime(UPDATED_OPERATION_TIME)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .postponeData(UPDATED_POSTPONE_DATA)
                .remark(UPDATED_REMARK);

        restPostponeRecordMockMvc.perform(put("/api/postpone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPostponeRecord)))
            .andExpect(status().isOk());

        // Validate the PostponeRecord in the database
        List<PostponeRecord> postponeRecordList = postponeRecordRepository.findAll();
        assertThat(postponeRecordList).hasSize(databaseSizeBeforeUpdate);
        PostponeRecord testPostponeRecord = postponeRecordList.get(postponeRecordList.size() - 1);
        assertThat(testPostponeRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testPostponeRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testPostponeRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testPostponeRecord.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testPostponeRecord.getOperationTime()).isEqualTo(UPDATED_OPERATION_TIME);
        assertThat(testPostponeRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testPostponeRecord.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testPostponeRecord.getPostponeData()).isEqualTo(UPDATED_POSTPONE_DATA);
        assertThat(testPostponeRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingPostponeRecord() throws Exception {
        int databaseSizeBeforeUpdate = postponeRecordRepository.findAll().size();

        // Create the PostponeRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPostponeRecordMockMvc.perform(put("/api/postpone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postponeRecord)))
            .andExpect(status().isCreated());

        // Validate the PostponeRecord in the database
        List<PostponeRecord> postponeRecordList = postponeRecordRepository.findAll();
        assertThat(postponeRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePostponeRecord() throws Exception {
        // Initialize the database
        postponeRecordService.save(postponeRecord);

        int databaseSizeBeforeDelete = postponeRecordRepository.findAll().size();

        // Get the postponeRecord
        restPostponeRecordMockMvc.perform(delete("/api/postpone-records/{id}", postponeRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PostponeRecord> postponeRecordList = postponeRecordRepository.findAll();
        assertThat(postponeRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PostponeRecord.class);
    }
}

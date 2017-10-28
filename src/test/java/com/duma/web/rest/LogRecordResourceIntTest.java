package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.LogRecord;
import com.duma.repository.LogRecordRepository;
import com.duma.service.LogRecordService;
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
 * Test class for the LogRecordResource REST controller.
 *
 * @see LogRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class LogRecordResourceIntTest {

    private static final String DEFAULT_OPERATING_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATING_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATOR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATOR = "BBBBBBBBBB";

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_DEPARTMENT_ID = 2L;

    private static final Long DEFAULT_OPERATOR_ID = 1L;
    private static final Long UPDATED_OPERATOR_ID = 2L;

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final Long DEFAULT_OPERATING_DATE = 1L;
    private static final Long UPDATED_OPERATING_DATE = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Autowired
    private LogRecordService logRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLogRecordMockMvc;

    private LogRecord logRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LogRecordResource logRecordResource = new LogRecordResource(logRecordService);
        this.restLogRecordMockMvc = MockMvcBuilders.standaloneSetup(logRecordResource)
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
    public static LogRecord createEntity(EntityManager em) {
        LogRecord logRecord = new LogRecord()
                .operatingType(DEFAULT_OPERATING_TYPE)
                .operator(DEFAULT_OPERATOR)
                .companyId(DEFAULT_COMPANY_ID)
                .departmentId(DEFAULT_DEPARTMENT_ID)
                .operatorId(DEFAULT_OPERATOR_ID)
                .ip(DEFAULT_IP)
                .role(DEFAULT_ROLE)
                .operatingDate(DEFAULT_OPERATING_DATE)
                .remark(DEFAULT_REMARK);
        return logRecord;
    }

    @Before
    public void initTest() {
        logRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogRecord() throws Exception {
        int databaseSizeBeforeCreate = logRecordRepository.findAll().size();

        // Create the LogRecord

        restLogRecordMockMvc.perform(post("/api/log-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logRecord)))
            .andExpect(status().isCreated());

        // Validate the LogRecord in the database
        List<LogRecord> logRecordList = logRecordRepository.findAll();
        assertThat(logRecordList).hasSize(databaseSizeBeforeCreate + 1);
        LogRecord testLogRecord = logRecordList.get(logRecordList.size() - 1);
        assertThat(testLogRecord.getOperatingType()).isEqualTo(DEFAULT_OPERATING_TYPE);
        assertThat(testLogRecord.getOperator()).isEqualTo(DEFAULT_OPERATOR);
        assertThat(testLogRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testLogRecord.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testLogRecord.getOperatorId()).isEqualTo(DEFAULT_OPERATOR_ID);
        assertThat(testLogRecord.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testLogRecord.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testLogRecord.getOperatingDate()).isEqualTo(DEFAULT_OPERATING_DATE);
        assertThat(testLogRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createLogRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logRecordRepository.findAll().size();

        // Create the LogRecord with an existing ID
        LogRecord existingLogRecord = new LogRecord();
        existingLogRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogRecordMockMvc.perform(post("/api/log-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingLogRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<LogRecord> logRecordList = logRecordRepository.findAll();
        assertThat(logRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLogRecords() throws Exception {
        // Initialize the database
        logRecordRepository.saveAndFlush(logRecord);

        // Get all the logRecordList
        restLogRecordMockMvc.perform(get("/api/log-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].operatingType").value(hasItem(DEFAULT_OPERATING_TYPE.toString())))
            .andExpect(jsonPath("$.[*].operator").value(hasItem(DEFAULT_OPERATOR.toString())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].operatorId").value(hasItem(DEFAULT_OPERATOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())))
            .andExpect(jsonPath("$.[*].operatingDate").value(hasItem(DEFAULT_OPERATING_DATE.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getLogRecord() throws Exception {
        // Initialize the database
        logRecordRepository.saveAndFlush(logRecord);

        // Get the logRecord
        restLogRecordMockMvc.perform(get("/api/log-records/{id}", logRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(logRecord.getId().intValue()))
            .andExpect(jsonPath("$.operatingType").value(DEFAULT_OPERATING_TYPE.toString()))
            .andExpect(jsonPath("$.operator").value(DEFAULT_OPERATOR.toString()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.operatorId").value(DEFAULT_OPERATOR_ID.intValue()))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()))
            .andExpect(jsonPath("$.operatingDate").value(DEFAULT_OPERATING_DATE.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLogRecord() throws Exception {
        // Get the logRecord
        restLogRecordMockMvc.perform(get("/api/log-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogRecord() throws Exception {
        // Initialize the database
        logRecordService.save(logRecord);

        int databaseSizeBeforeUpdate = logRecordRepository.findAll().size();

        // Update the logRecord
        LogRecord updatedLogRecord = logRecordRepository.findOne(logRecord.getId());
        updatedLogRecord
                .operatingType(UPDATED_OPERATING_TYPE)
                .operator(UPDATED_OPERATOR)
                .companyId(UPDATED_COMPANY_ID)
                .departmentId(UPDATED_DEPARTMENT_ID)
                .operatorId(UPDATED_OPERATOR_ID)
                .ip(UPDATED_IP)
                .role(UPDATED_ROLE)
                .operatingDate(UPDATED_OPERATING_DATE)
                .remark(UPDATED_REMARK);

        restLogRecordMockMvc.perform(put("/api/log-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLogRecord)))
            .andExpect(status().isOk());

        // Validate the LogRecord in the database
        List<LogRecord> logRecordList = logRecordRepository.findAll();
        assertThat(logRecordList).hasSize(databaseSizeBeforeUpdate);
        LogRecord testLogRecord = logRecordList.get(logRecordList.size() - 1);
        assertThat(testLogRecord.getOperatingType()).isEqualTo(UPDATED_OPERATING_TYPE);
        assertThat(testLogRecord.getOperator()).isEqualTo(UPDATED_OPERATOR);
        assertThat(testLogRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testLogRecord.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLogRecord.getOperatorId()).isEqualTo(UPDATED_OPERATOR_ID);
        assertThat(testLogRecord.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testLogRecord.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testLogRecord.getOperatingDate()).isEqualTo(UPDATED_OPERATING_DATE);
        assertThat(testLogRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingLogRecord() throws Exception {
        int databaseSizeBeforeUpdate = logRecordRepository.findAll().size();

        // Create the LogRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLogRecordMockMvc.perform(put("/api/log-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logRecord)))
            .andExpect(status().isCreated());

        // Validate the LogRecord in the database
        List<LogRecord> logRecordList = logRecordRepository.findAll();
        assertThat(logRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLogRecord() throws Exception {
        // Initialize the database
        logRecordService.save(logRecord);

        int databaseSizeBeforeDelete = logRecordRepository.findAll().size();

        // Get the logRecord
        restLogRecordMockMvc.perform(delete("/api/log-records/{id}", logRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LogRecord> logRecordList = logRecordRepository.findAll();
        assertThat(logRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogRecord.class);
    }
}

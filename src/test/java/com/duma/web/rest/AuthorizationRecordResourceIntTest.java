package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.AuthorizationRecord;
import com.duma.repository.AuthorizationRecordRepository;
import com.duma.service.AuthorizationRecordService;
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
 * Test class for the AuthorizationRecordResource REST controller.
 *
 * @see AuthorizationRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class AuthorizationRecordResourceIntTest {

    private static final String DEFAULT_CONTRACT_NUMBERING = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_NUMBERING = "BBBBBBBBBB";

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_PURCHASE_DATE = 1L;
    private static final Long UPDATED_PURCHASE_DATE = 2L;

    private static final Long DEFAULT_AUTHORIZATION_START_DATE = 1L;
    private static final Long UPDATED_AUTHORIZATION_START_DATE = 2L;

    private static final Long DEFAULT_AUTHORIZATION_FINISH_DATE = 1L;
    private static final Long UPDATED_AUTHORIZATION_FINISH_DATE = 2L;

    private static final Integer DEFAULT_AUTHORIZATION_TYPE = 1;
    private static final Integer UPDATED_AUTHORIZATION_TYPE = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Long DEFAULT_AUTHORIZATION_ID = 1L;
    private static final Long UPDATED_AUTHORIZATION_ID = 2L;

    @Autowired
    private AuthorizationRecordRepository authorizationRecordRepository;

    @Autowired
    private AuthorizationRecordService authorizationRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAuthorizationRecordMockMvc;

    private AuthorizationRecord authorizationRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AuthorizationRecordResource authorizationRecordResource = new AuthorizationRecordResource(authorizationRecordService);
        this.restAuthorizationRecordMockMvc = MockMvcBuilders.standaloneSetup(authorizationRecordResource)
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
    public static AuthorizationRecord createEntity(EntityManager em) {
        AuthorizationRecord authorizationRecord = new AuthorizationRecord()
                .contractNumbering(DEFAULT_CONTRACT_NUMBERING)
                .companyId(DEFAULT_COMPANY_ID)
                .purchaseDate(DEFAULT_PURCHASE_DATE)
                .authorizationStartDate(DEFAULT_AUTHORIZATION_START_DATE)
                .authorizationFinishDate(DEFAULT_AUTHORIZATION_FINISH_DATE)
                .authorizationType(DEFAULT_AUTHORIZATION_TYPE)
                .remark(DEFAULT_REMARK)
                .authorizationId(DEFAULT_AUTHORIZATION_ID);
        return authorizationRecord;
    }

    @Before
    public void initTest() {
        authorizationRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuthorizationRecord() throws Exception {
        int databaseSizeBeforeCreate = authorizationRecordRepository.findAll().size();

        // Create the AuthorizationRecord

        restAuthorizationRecordMockMvc.perform(post("/api/authorization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(authorizationRecord)))
            .andExpect(status().isCreated());

        // Validate the AuthorizationRecord in the database
        List<AuthorizationRecord> authorizationRecordList = authorizationRecordRepository.findAll();
        assertThat(authorizationRecordList).hasSize(databaseSizeBeforeCreate + 1);
        AuthorizationRecord testAuthorizationRecord = authorizationRecordList.get(authorizationRecordList.size() - 1);
        assertThat(testAuthorizationRecord.getContractNumbering()).isEqualTo(DEFAULT_CONTRACT_NUMBERING);
        assertThat(testAuthorizationRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testAuthorizationRecord.getPurchaseDate()).isEqualTo(DEFAULT_PURCHASE_DATE);
        assertThat(testAuthorizationRecord.getAuthorizationStartDate()).isEqualTo(DEFAULT_AUTHORIZATION_START_DATE);
        assertThat(testAuthorizationRecord.getAuthorizationFinishDate()).isEqualTo(DEFAULT_AUTHORIZATION_FINISH_DATE);
        assertThat(testAuthorizationRecord.getAuthorizationType()).isEqualTo(DEFAULT_AUTHORIZATION_TYPE);
        assertThat(testAuthorizationRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testAuthorizationRecord.getAuthorizationId()).isEqualTo(DEFAULT_AUTHORIZATION_ID);
    }

    @Test
    @Transactional
    public void createAuthorizationRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = authorizationRecordRepository.findAll().size();

        // Create the AuthorizationRecord with an existing ID
        AuthorizationRecord existingAuthorizationRecord = new AuthorizationRecord();
        existingAuthorizationRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuthorizationRecordMockMvc.perform(post("/api/authorization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAuthorizationRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AuthorizationRecord> authorizationRecordList = authorizationRecordRepository.findAll();
        assertThat(authorizationRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAuthorizationRecords() throws Exception {
        // Initialize the database
        authorizationRecordRepository.saveAndFlush(authorizationRecord);

        // Get all the authorizationRecordList
        restAuthorizationRecordMockMvc.perform(get("/api/authorization-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(authorizationRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractNumbering").value(hasItem(DEFAULT_CONTRACT_NUMBERING.toString())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].purchaseDate").value(hasItem(DEFAULT_PURCHASE_DATE.intValue())))
            .andExpect(jsonPath("$.[*].authorizationStartDate").value(hasItem(DEFAULT_AUTHORIZATION_START_DATE.intValue())))
            .andExpect(jsonPath("$.[*].authorizationFinishDate").value(hasItem(DEFAULT_AUTHORIZATION_FINISH_DATE.intValue())))
            .andExpect(jsonPath("$.[*].authorizationType").value(hasItem(DEFAULT_AUTHORIZATION_TYPE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].authorizationId").value(hasItem(DEFAULT_AUTHORIZATION_ID.intValue())));
    }

    @Test
    @Transactional
    public void getAuthorizationRecord() throws Exception {
        // Initialize the database
        authorizationRecordRepository.saveAndFlush(authorizationRecord);

        // Get the authorizationRecord
        restAuthorizationRecordMockMvc.perform(get("/api/authorization-records/{id}", authorizationRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(authorizationRecord.getId().intValue()))
            .andExpect(jsonPath("$.contractNumbering").value(DEFAULT_CONTRACT_NUMBERING.toString()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.purchaseDate").value(DEFAULT_PURCHASE_DATE.intValue()))
            .andExpect(jsonPath("$.authorizationStartDate").value(DEFAULT_AUTHORIZATION_START_DATE.intValue()))
            .andExpect(jsonPath("$.authorizationFinishDate").value(DEFAULT_AUTHORIZATION_FINISH_DATE.intValue()))
            .andExpect(jsonPath("$.authorizationType").value(DEFAULT_AUTHORIZATION_TYPE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.authorizationId").value(DEFAULT_AUTHORIZATION_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAuthorizationRecord() throws Exception {
        // Get the authorizationRecord
        restAuthorizationRecordMockMvc.perform(get("/api/authorization-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuthorizationRecord() throws Exception {
        // Initialize the database
        authorizationRecordService.save(authorizationRecord);

        int databaseSizeBeforeUpdate = authorizationRecordRepository.findAll().size();

        // Update the authorizationRecord
        AuthorizationRecord updatedAuthorizationRecord = authorizationRecordRepository.findOne(authorizationRecord.getId());
        updatedAuthorizationRecord
                .contractNumbering(UPDATED_CONTRACT_NUMBERING)
                .companyId(UPDATED_COMPANY_ID)
                .purchaseDate(UPDATED_PURCHASE_DATE)
                .authorizationStartDate(UPDATED_AUTHORIZATION_START_DATE)
                .authorizationFinishDate(UPDATED_AUTHORIZATION_FINISH_DATE)
                .authorizationType(UPDATED_AUTHORIZATION_TYPE)
                .remark(UPDATED_REMARK)
                .authorizationId(UPDATED_AUTHORIZATION_ID);

        restAuthorizationRecordMockMvc.perform(put("/api/authorization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAuthorizationRecord)))
            .andExpect(status().isOk());

        // Validate the AuthorizationRecord in the database
        List<AuthorizationRecord> authorizationRecordList = authorizationRecordRepository.findAll();
        assertThat(authorizationRecordList).hasSize(databaseSizeBeforeUpdate);
        AuthorizationRecord testAuthorizationRecord = authorizationRecordList.get(authorizationRecordList.size() - 1);
        assertThat(testAuthorizationRecord.getContractNumbering()).isEqualTo(UPDATED_CONTRACT_NUMBERING);
        assertThat(testAuthorizationRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testAuthorizationRecord.getPurchaseDate()).isEqualTo(UPDATED_PURCHASE_DATE);
        assertThat(testAuthorizationRecord.getAuthorizationStartDate()).isEqualTo(UPDATED_AUTHORIZATION_START_DATE);
        assertThat(testAuthorizationRecord.getAuthorizationFinishDate()).isEqualTo(UPDATED_AUTHORIZATION_FINISH_DATE);
        assertThat(testAuthorizationRecord.getAuthorizationType()).isEqualTo(UPDATED_AUTHORIZATION_TYPE);
        assertThat(testAuthorizationRecord.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testAuthorizationRecord.getAuthorizationId()).isEqualTo(UPDATED_AUTHORIZATION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAuthorizationRecord() throws Exception {
        int databaseSizeBeforeUpdate = authorizationRecordRepository.findAll().size();

        // Create the AuthorizationRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAuthorizationRecordMockMvc.perform(put("/api/authorization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(authorizationRecord)))
            .andExpect(status().isCreated());

        // Validate the AuthorizationRecord in the database
        List<AuthorizationRecord> authorizationRecordList = authorizationRecordRepository.findAll();
        assertThat(authorizationRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAuthorizationRecord() throws Exception {
        // Initialize the database
        authorizationRecordService.save(authorizationRecord);

        int databaseSizeBeforeDelete = authorizationRecordRepository.findAll().size();

        // Get the authorizationRecord
        restAuthorizationRecordMockMvc.perform(delete("/api/authorization-records/{id}", authorizationRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AuthorizationRecord> authorizationRecordList = authorizationRecordRepository.findAll();
        assertThat(authorizationRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuthorizationRecord.class);
    }
}

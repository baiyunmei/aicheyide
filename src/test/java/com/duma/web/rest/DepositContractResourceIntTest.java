package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.DepositContract;
import com.duma.repository.DepositContractRepository;
import com.duma.service.DepositContractService;
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
 * Test class for the DepositContractResource REST controller.
 *
 * @see DepositContractResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class DepositContractResourceIntTest {

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final String DEFAULT_AUDIT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_AUDIT = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT = "BBBBBBBBBB";

    private static final Long DEFAULT_AUDIT_ID = 1L;
    private static final Long UPDATED_AUDIT_ID = 2L;

    private static final Long DEFAULT_AUDIT_TIME = 1L;
    private static final Long UPDATED_AUDIT_TIME = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private DepositContractRepository depositContractRepository;

    @Autowired
    private DepositContractService depositContractService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDepositContractMockMvc;

    private DepositContract depositContract;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepositContractResource depositContractResource = new DepositContractResource(depositContractService);
        this.restDepositContractMockMvc = MockMvcBuilders.standaloneSetup(depositContractResource)
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
    public static DepositContract createEntity(EntityManager em) {
        DepositContract depositContract = new DepositContract()
                .orderId(DEFAULT_ORDER_ID)
                .driverId(DEFAULT_DRIVER_ID)
                .auditResult(DEFAULT_AUDIT_RESULT)
                .audit(DEFAULT_AUDIT)
                .auditId(DEFAULT_AUDIT_ID)
                .auditTime(DEFAULT_AUDIT_TIME)
                .remark(DEFAULT_REMARK);
        return depositContract;
    }

    @Before
    public void initTest() {
        depositContract = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepositContract() throws Exception {
        int databaseSizeBeforeCreate = depositContractRepository.findAll().size();

        // Create the DepositContract

        restDepositContractMockMvc.perform(post("/api/deposit-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depositContract)))
            .andExpect(status().isCreated());

        // Validate the DepositContract in the database
        List<DepositContract> depositContractList = depositContractRepository.findAll();
        assertThat(depositContractList).hasSize(databaseSizeBeforeCreate + 1);
        DepositContract testDepositContract = depositContractList.get(depositContractList.size() - 1);
        assertThat(testDepositContract.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testDepositContract.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testDepositContract.getAuditResult()).isEqualTo(DEFAULT_AUDIT_RESULT);
        assertThat(testDepositContract.getAudit()).isEqualTo(DEFAULT_AUDIT);
        assertThat(testDepositContract.getAuditId()).isEqualTo(DEFAULT_AUDIT_ID);
        assertThat(testDepositContract.getAuditTime()).isEqualTo(DEFAULT_AUDIT_TIME);
        assertThat(testDepositContract.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createDepositContractWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = depositContractRepository.findAll().size();

        // Create the DepositContract with an existing ID
        DepositContract existingDepositContract = new DepositContract();
        existingDepositContract.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepositContractMockMvc.perform(post("/api/deposit-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDepositContract)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DepositContract> depositContractList = depositContractRepository.findAll();
        assertThat(depositContractList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDepositContracts() throws Exception {
        // Initialize the database
        depositContractRepository.saveAndFlush(depositContract);

        // Get all the depositContractList
        restDepositContractMockMvc.perform(get("/api/deposit-contracts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depositContract.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].auditResult").value(hasItem(DEFAULT_AUDIT_RESULT.toString())))
            .andExpect(jsonPath("$.[*].audit").value(hasItem(DEFAULT_AUDIT.toString())))
            .andExpect(jsonPath("$.[*].auditId").value(hasItem(DEFAULT_AUDIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].auditTime").value(hasItem(DEFAULT_AUDIT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getDepositContract() throws Exception {
        // Initialize the database
        depositContractRepository.saveAndFlush(depositContract);

        // Get the depositContract
        restDepositContractMockMvc.perform(get("/api/deposit-contracts/{id}", depositContract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(depositContract.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.auditResult").value(DEFAULT_AUDIT_RESULT.toString()))
            .andExpect(jsonPath("$.audit").value(DEFAULT_AUDIT.toString()))
            .andExpect(jsonPath("$.auditId").value(DEFAULT_AUDIT_ID.intValue()))
            .andExpect(jsonPath("$.auditTime").value(DEFAULT_AUDIT_TIME.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDepositContract() throws Exception {
        // Get the depositContract
        restDepositContractMockMvc.perform(get("/api/deposit-contracts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepositContract() throws Exception {
        // Initialize the database
        depositContractService.save(depositContract);

        int databaseSizeBeforeUpdate = depositContractRepository.findAll().size();

        // Update the depositContract
        DepositContract updatedDepositContract = depositContractRepository.findOne(depositContract.getId());
        updatedDepositContract
                .orderId(UPDATED_ORDER_ID)
                .driverId(UPDATED_DRIVER_ID)
                .auditResult(UPDATED_AUDIT_RESULT)
                .audit(UPDATED_AUDIT)
                .auditId(UPDATED_AUDIT_ID)
                .auditTime(UPDATED_AUDIT_TIME)
                .remark(UPDATED_REMARK);

        restDepositContractMockMvc.perform(put("/api/deposit-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDepositContract)))
            .andExpect(status().isOk());

        // Validate the DepositContract in the database
        List<DepositContract> depositContractList = depositContractRepository.findAll();
        assertThat(depositContractList).hasSize(databaseSizeBeforeUpdate);
        DepositContract testDepositContract = depositContractList.get(depositContractList.size() - 1);
        assertThat(testDepositContract.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testDepositContract.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testDepositContract.getAuditResult()).isEqualTo(UPDATED_AUDIT_RESULT);
        assertThat(testDepositContract.getAudit()).isEqualTo(UPDATED_AUDIT);
        assertThat(testDepositContract.getAuditId()).isEqualTo(UPDATED_AUDIT_ID);
        assertThat(testDepositContract.getAuditTime()).isEqualTo(UPDATED_AUDIT_TIME);
        assertThat(testDepositContract.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingDepositContract() throws Exception {
        int databaseSizeBeforeUpdate = depositContractRepository.findAll().size();

        // Create the DepositContract

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepositContractMockMvc.perform(put("/api/deposit-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depositContract)))
            .andExpect(status().isCreated());

        // Validate the DepositContract in the database
        List<DepositContract> depositContractList = depositContractRepository.findAll();
        assertThat(depositContractList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDepositContract() throws Exception {
        // Initialize the database
        depositContractService.save(depositContract);

        int databaseSizeBeforeDelete = depositContractRepository.findAll().size();

        // Get the depositContract
        restDepositContractMockMvc.perform(delete("/api/deposit-contracts/{id}", depositContract.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DepositContract> depositContractList = depositContractRepository.findAll();
        assertThat(depositContractList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepositContract.class);
    }
}

package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.ClaimSettlementAudit;
import com.duma.repository.ClaimSettlementAuditRepository;
import com.duma.service.ClaimSettlementAuditService;
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
 * Test class for the ClaimSettlementAuditResource REST controller.
 *
 * @see ClaimSettlementAuditResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class ClaimSettlementAuditResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final BigDecimal DEFAULT_CLAIM_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_CLAIM_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_ILLEGAL_NAAM = 1;
    private static final Integer UPDATED_ILLEGAL_NAAM = 2;

    private static final String DEFAULT_ILLEGAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ILLEGAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ILLEGAL_ACCESSORY = "AAAAAAAAAA";
    private static final String UPDATED_ILLEGAL_ACCESSORY = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PAYMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAYMENT = new BigDecimal(2);

    private static final String DEFAULT_REPAIR_ACCESSORY = "AAAAAAAAAA";
    private static final String UPDATED_REPAIR_ACCESSORY = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DEBT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEBT = new BigDecimal(2);

    private static final String DEFAULT_DEBT_ACCESSORY = "AAAAAAAAAA";
    private static final String UPDATED_DEBT_ACCESSORY = "BBBBBBBBBB";

    private static final String DEFAULT_DEBT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_DEBT_REMARK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ACTUAL_PAYMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACTUAL_PAYMENT = new BigDecimal(2);

    private static final Long DEFAULT_PAYMENT_TIME = 1L;
    private static final Long UPDATED_PAYMENT_TIME = 2L;

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYMENT_WAY = 1;
    private static final Integer UPDATED_PAYMENT_WAY = 2;

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_AUDIT_STATUS = 1;
    private static final Integer UPDATED_AUDIT_STATUS = 2;

    @Autowired
    private ClaimSettlementAuditRepository claimSettlementAuditRepository;

    @Autowired
    private ClaimSettlementAuditService claimSettlementAuditService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClaimSettlementAuditMockMvc;

    private ClaimSettlementAudit claimSettlementAudit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ClaimSettlementAuditResource claimSettlementAuditResource = new ClaimSettlementAuditResource(claimSettlementAuditService);
        this.restClaimSettlementAuditMockMvc = MockMvcBuilders.standaloneSetup(claimSettlementAuditResource)
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
    public static ClaimSettlementAudit createEntity(EntityManager em) {
        ClaimSettlementAudit claimSettlementAudit = new ClaimSettlementAudit()
                .driverId(DEFAULT_DRIVER_ID)
                .orderId(DEFAULT_ORDER_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .claimMoney(DEFAULT_CLAIM_MONEY)
                .illegalNaam(DEFAULT_ILLEGAL_NAAM)
                .illegalNumber(DEFAULT_ILLEGAL_NUMBER)
                .illegalAccessory(DEFAULT_ILLEGAL_ACCESSORY)
                .payment(DEFAULT_PAYMENT)
                .repairAccessory(DEFAULT_REPAIR_ACCESSORY)
                .debt(DEFAULT_DEBT)
                .debtAccessory(DEFAULT_DEBT_ACCESSORY)
                .debtRemark(DEFAULT_DEBT_REMARK)
                .actualPayment(DEFAULT_ACTUAL_PAYMENT)
                .paymentTime(DEFAULT_PAYMENT_TIME)
                .accountNumber(DEFAULT_ACCOUNT_NUMBER)
                .paymentWay(DEFAULT_PAYMENT_WAY)
                .serialNumber(DEFAULT_SERIAL_NUMBER)
                .auditStatus(DEFAULT_AUDIT_STATUS);
        return claimSettlementAudit;
    }

    @Before
    public void initTest() {
        claimSettlementAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createClaimSettlementAudit() throws Exception {
        int databaseSizeBeforeCreate = claimSettlementAuditRepository.findAll().size();

        // Create the ClaimSettlementAudit

        restClaimSettlementAuditMockMvc.perform(post("/api/claim-settlement-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimSettlementAudit)))
            .andExpect(status().isCreated());

        // Validate the ClaimSettlementAudit in the database
        List<ClaimSettlementAudit> claimSettlementAuditList = claimSettlementAuditRepository.findAll();
        assertThat(claimSettlementAuditList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimSettlementAudit testClaimSettlementAudit = claimSettlementAuditList.get(claimSettlementAuditList.size() - 1);
        assertThat(testClaimSettlementAudit.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testClaimSettlementAudit.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testClaimSettlementAudit.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testClaimSettlementAudit.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testClaimSettlementAudit.getClaimMoney()).isEqualTo(DEFAULT_CLAIM_MONEY);
        assertThat(testClaimSettlementAudit.getIllegalNaam()).isEqualTo(DEFAULT_ILLEGAL_NAAM);
        assertThat(testClaimSettlementAudit.getIllegalNumber()).isEqualTo(DEFAULT_ILLEGAL_NUMBER);
        assertThat(testClaimSettlementAudit.getIllegalAccessory()).isEqualTo(DEFAULT_ILLEGAL_ACCESSORY);
        assertThat(testClaimSettlementAudit.getPayment()).isEqualTo(DEFAULT_PAYMENT);
        assertThat(testClaimSettlementAudit.getRepairAccessory()).isEqualTo(DEFAULT_REPAIR_ACCESSORY);
        assertThat(testClaimSettlementAudit.getDebt()).isEqualTo(DEFAULT_DEBT);
        assertThat(testClaimSettlementAudit.getDebtAccessory()).isEqualTo(DEFAULT_DEBT_ACCESSORY);
        assertThat(testClaimSettlementAudit.getDebtRemark()).isEqualTo(DEFAULT_DEBT_REMARK);
        assertThat(testClaimSettlementAudit.getActualPayment()).isEqualTo(DEFAULT_ACTUAL_PAYMENT);
        assertThat(testClaimSettlementAudit.getPaymentTime()).isEqualTo(DEFAULT_PAYMENT_TIME);
        assertThat(testClaimSettlementAudit.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testClaimSettlementAudit.getPaymentWay()).isEqualTo(DEFAULT_PAYMENT_WAY);
        assertThat(testClaimSettlementAudit.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testClaimSettlementAudit.getAuditStatus()).isEqualTo(DEFAULT_AUDIT_STATUS);
    }

    @Test
    @Transactional
    public void createClaimSettlementAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claimSettlementAuditRepository.findAll().size();

        // Create the ClaimSettlementAudit with an existing ID
        ClaimSettlementAudit existingClaimSettlementAudit = new ClaimSettlementAudit();
        existingClaimSettlementAudit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimSettlementAuditMockMvc.perform(post("/api/claim-settlement-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingClaimSettlementAudit)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ClaimSettlementAudit> claimSettlementAuditList = claimSettlementAuditRepository.findAll();
        assertThat(claimSettlementAuditList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClaimSettlementAudits() throws Exception {
        // Initialize the database
        claimSettlementAuditRepository.saveAndFlush(claimSettlementAudit);

        // Get all the claimSettlementAuditList
        restClaimSettlementAuditMockMvc.perform(get("/api/claim-settlement-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claimSettlementAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].claimMoney").value(hasItem(DEFAULT_CLAIM_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].illegalNaam").value(hasItem(DEFAULT_ILLEGAL_NAAM)))
            .andExpect(jsonPath("$.[*].illegalNumber").value(hasItem(DEFAULT_ILLEGAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].illegalAccessory").value(hasItem(DEFAULT_ILLEGAL_ACCESSORY.toString())))
            .andExpect(jsonPath("$.[*].payment").value(hasItem(DEFAULT_PAYMENT.intValue())))
            .andExpect(jsonPath("$.[*].repairAccessory").value(hasItem(DEFAULT_REPAIR_ACCESSORY.toString())))
            .andExpect(jsonPath("$.[*].debt").value(hasItem(DEFAULT_DEBT.intValue())))
            .andExpect(jsonPath("$.[*].debtAccessory").value(hasItem(DEFAULT_DEBT_ACCESSORY.toString())))
            .andExpect(jsonPath("$.[*].debtRemark").value(hasItem(DEFAULT_DEBT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].actualPayment").value(hasItem(DEFAULT_ACTUAL_PAYMENT.intValue())))
            .andExpect(jsonPath("$.[*].paymentTime").value(hasItem(DEFAULT_PAYMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].paymentWay").value(hasItem(DEFAULT_PAYMENT_WAY)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS)));
    }

    @Test
    @Transactional
    public void getClaimSettlementAudit() throws Exception {
        // Initialize the database
        claimSettlementAuditRepository.saveAndFlush(claimSettlementAudit);

        // Get the claimSettlementAudit
        restClaimSettlementAuditMockMvc.perform(get("/api/claim-settlement-audits/{id}", claimSettlementAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(claimSettlementAudit.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.claimMoney").value(DEFAULT_CLAIM_MONEY.intValue()))
            .andExpect(jsonPath("$.illegalNaam").value(DEFAULT_ILLEGAL_NAAM))
            .andExpect(jsonPath("$.illegalNumber").value(DEFAULT_ILLEGAL_NUMBER.toString()))
            .andExpect(jsonPath("$.illegalAccessory").value(DEFAULT_ILLEGAL_ACCESSORY.toString()))
            .andExpect(jsonPath("$.payment").value(DEFAULT_PAYMENT.intValue()))
            .andExpect(jsonPath("$.repairAccessory").value(DEFAULT_REPAIR_ACCESSORY.toString()))
            .andExpect(jsonPath("$.debt").value(DEFAULT_DEBT.intValue()))
            .andExpect(jsonPath("$.debtAccessory").value(DEFAULT_DEBT_ACCESSORY.toString()))
            .andExpect(jsonPath("$.debtRemark").value(DEFAULT_DEBT_REMARK.toString()))
            .andExpect(jsonPath("$.actualPayment").value(DEFAULT_ACTUAL_PAYMENT.intValue()))
            .andExpect(jsonPath("$.paymentTime").value(DEFAULT_PAYMENT_TIME.intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.paymentWay").value(DEFAULT_PAYMENT_WAY))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingClaimSettlementAudit() throws Exception {
        // Get the claimSettlementAudit
        restClaimSettlementAuditMockMvc.perform(get("/api/claim-settlement-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClaimSettlementAudit() throws Exception {
        // Initialize the database
        claimSettlementAuditService.save(claimSettlementAudit);

        int databaseSizeBeforeUpdate = claimSettlementAuditRepository.findAll().size();

        // Update the claimSettlementAudit
        ClaimSettlementAudit updatedClaimSettlementAudit = claimSettlementAuditRepository.findOne(claimSettlementAudit.getId());
        updatedClaimSettlementAudit
                .driverId(UPDATED_DRIVER_ID)
                .orderId(UPDATED_ORDER_ID)
                .companyId(UPDATED_COMPANY_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .claimMoney(UPDATED_CLAIM_MONEY)
                .illegalNaam(UPDATED_ILLEGAL_NAAM)
                .illegalNumber(UPDATED_ILLEGAL_NUMBER)
                .illegalAccessory(UPDATED_ILLEGAL_ACCESSORY)
                .payment(UPDATED_PAYMENT)
                .repairAccessory(UPDATED_REPAIR_ACCESSORY)
                .debt(UPDATED_DEBT)
                .debtAccessory(UPDATED_DEBT_ACCESSORY)
                .debtRemark(UPDATED_DEBT_REMARK)
                .actualPayment(UPDATED_ACTUAL_PAYMENT)
                .paymentTime(UPDATED_PAYMENT_TIME)
                .accountNumber(UPDATED_ACCOUNT_NUMBER)
                .paymentWay(UPDATED_PAYMENT_WAY)
                .serialNumber(UPDATED_SERIAL_NUMBER)
                .auditStatus(UPDATED_AUDIT_STATUS);

        restClaimSettlementAuditMockMvc.perform(put("/api/claim-settlement-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClaimSettlementAudit)))
            .andExpect(status().isOk());

        // Validate the ClaimSettlementAudit in the database
        List<ClaimSettlementAudit> claimSettlementAuditList = claimSettlementAuditRepository.findAll();
        assertThat(claimSettlementAuditList).hasSize(databaseSizeBeforeUpdate);
        ClaimSettlementAudit testClaimSettlementAudit = claimSettlementAuditList.get(claimSettlementAuditList.size() - 1);
        assertThat(testClaimSettlementAudit.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testClaimSettlementAudit.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testClaimSettlementAudit.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testClaimSettlementAudit.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testClaimSettlementAudit.getClaimMoney()).isEqualTo(UPDATED_CLAIM_MONEY);
        assertThat(testClaimSettlementAudit.getIllegalNaam()).isEqualTo(UPDATED_ILLEGAL_NAAM);
        assertThat(testClaimSettlementAudit.getIllegalNumber()).isEqualTo(UPDATED_ILLEGAL_NUMBER);
        assertThat(testClaimSettlementAudit.getIllegalAccessory()).isEqualTo(UPDATED_ILLEGAL_ACCESSORY);
        assertThat(testClaimSettlementAudit.getPayment()).isEqualTo(UPDATED_PAYMENT);
        assertThat(testClaimSettlementAudit.getRepairAccessory()).isEqualTo(UPDATED_REPAIR_ACCESSORY);
        assertThat(testClaimSettlementAudit.getDebt()).isEqualTo(UPDATED_DEBT);
        assertThat(testClaimSettlementAudit.getDebtAccessory()).isEqualTo(UPDATED_DEBT_ACCESSORY);
        assertThat(testClaimSettlementAudit.getDebtRemark()).isEqualTo(UPDATED_DEBT_REMARK);
        assertThat(testClaimSettlementAudit.getActualPayment()).isEqualTo(UPDATED_ACTUAL_PAYMENT);
        assertThat(testClaimSettlementAudit.getPaymentTime()).isEqualTo(UPDATED_PAYMENT_TIME);
        assertThat(testClaimSettlementAudit.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testClaimSettlementAudit.getPaymentWay()).isEqualTo(UPDATED_PAYMENT_WAY);
        assertThat(testClaimSettlementAudit.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testClaimSettlementAudit.getAuditStatus()).isEqualTo(UPDATED_AUDIT_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingClaimSettlementAudit() throws Exception {
        int databaseSizeBeforeUpdate = claimSettlementAuditRepository.findAll().size();

        // Create the ClaimSettlementAudit

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClaimSettlementAuditMockMvc.perform(put("/api/claim-settlement-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(claimSettlementAudit)))
            .andExpect(status().isCreated());

        // Validate the ClaimSettlementAudit in the database
        List<ClaimSettlementAudit> claimSettlementAuditList = claimSettlementAuditRepository.findAll();
        assertThat(claimSettlementAuditList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteClaimSettlementAudit() throws Exception {
        // Initialize the database
        claimSettlementAuditService.save(claimSettlementAudit);

        int databaseSizeBeforeDelete = claimSettlementAuditRepository.findAll().size();

        // Get the claimSettlementAudit
        restClaimSettlementAuditMockMvc.perform(delete("/api/claim-settlement-audits/{id}", claimSettlementAudit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ClaimSettlementAudit> claimSettlementAuditList = claimSettlementAuditRepository.findAll();
        assertThat(claimSettlementAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimSettlementAudit.class);
    }
}

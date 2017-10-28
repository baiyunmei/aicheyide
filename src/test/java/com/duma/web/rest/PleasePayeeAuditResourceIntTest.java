package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.PleasePayeeAudit;
import com.duma.repository.PleasePayeeAuditRepository;
import com.duma.service.PleasePayeeAuditService;
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
 * Test class for the PleasePayeeAuditResource REST controller.
 *
 * @see PleasePayeeAuditResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class PleasePayeeAuditResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_RECEIPT_DATE = 1L;
    private static final Long UPDATED_RECEIPT_DATE = 2L;

    private static final Long DEFAULT_PLEASE_PAYEE_ID = 1L;
    private static final Long UPDATED_PLEASE_PAYEE_ID = 2L;

    private static final String DEFAULT_PLEASE_PAYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PLEASE_PAYEE_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PLEASE_PAYEE_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_PLEASE_PAYEE_MONEY = new BigDecimal(2);

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_GATHERING_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GATHERING_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GATHERING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GATHERING_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_USE_TIME = 1L;
    private static final Long UPDATED_USE_TIME = 2L;

    private static final String DEFAULT_REMARK_1 = "AAAAAAAAAA";
    private static final String UPDATED_REMARK_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ACCESSORY = "AAAAAAAAAA";
    private static final String UPDATED_ACCESSORY = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_REMARK_2 = "AAAAAAAAAA";
    private static final String UPDATED_REMARK_2 = "BBBBBBBBBB";

    private static final String DEFAULT_REMIT = "AAAAAAAAAA";
    private static final String UPDATED_REMIT = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_ESTABLISH_TIME = 1L;
    private static final Long UPDATED_ESTABLISH_TIME = 2L;

    private static final Long DEFAULT_AMEND_TIME = 1L;
    private static final Long UPDATED_AMEND_TIME = 2L;

    private static final String DEFAULT_AMEND_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_AMEND_PERSON = "BBBBBBBBBB";

    private static final String DEFAULT_SUBMITTER = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTER = "BBBBBBBBBB";

    private static final Long DEFAULT_SUBMIT_TIME = 1L;
    private static final Long UPDATED_SUBMIT_TIME = 2L;

    private static final String DEFAULT_AUDITOR = "AAAAAAAAAA";
    private static final String UPDATED_AUDITOR = "BBBBBBBBBB";

    private static final Long DEFAULT_AUDITOR_TIME = 1L;
    private static final Long UPDATED_AUDITOR_TIME = 2L;

    private static final Integer DEFAULT_CONDITIONS = 1;
    private static final Integer UPDATED_CONDITIONS = 2;

    private static final String DEFAULT_PLEASE_PAYEE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PLEASE_PAYEE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_SHELF = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_SHELF = "BBBBBBBBBB";

    private static final String DEFAULT_ENGINE = "AAAAAAAAAA";
    private static final String UPDATED_ENGINE = "BBBBBBBBBB";

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    @Autowired
    private PleasePayeeAuditRepository pleasePayeeAuditRepository;

    @Autowired
    private PleasePayeeAuditService pleasePayeeAuditService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPleasePayeeAuditMockMvc;

    private PleasePayeeAudit pleasePayeeAudit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PleasePayeeAuditResource pleasePayeeAuditResource = new PleasePayeeAuditResource(pleasePayeeAuditService);
        this.restPleasePayeeAuditMockMvc = MockMvcBuilders.standaloneSetup(pleasePayeeAuditResource)
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
    public static PleasePayeeAudit createEntity(EntityManager em) {
        PleasePayeeAudit pleasePayeeAudit = new PleasePayeeAudit()
                .companyId(DEFAULT_COMPANY_ID)
                .orderId(DEFAULT_ORDER_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .pleasePayeeId(DEFAULT_PLEASE_PAYEE_ID)
                .pleasePayeeName(DEFAULT_PLEASE_PAYEE_NAME)
                .pleasePayeeMoney(DEFAULT_PLEASE_PAYEE_MONEY)
                .reason(DEFAULT_REASON)
                .gatheringName(DEFAULT_GATHERING_NAME)
                .gatheringNumber(DEFAULT_GATHERING_NUMBER)
                .useTime(DEFAULT_USE_TIME)
                .remark1(DEFAULT_REMARK_1)
                .accessory(DEFAULT_ACCESSORY)
                .status(DEFAULT_STATUS)
                .remark2(DEFAULT_REMARK_2)
                .remit(DEFAULT_REMIT)
                .serialNumber(DEFAULT_SERIAL_NUMBER)
                .establishTime(DEFAULT_ESTABLISH_TIME)
                .amendTime(DEFAULT_AMEND_TIME)
                .amendPerson(DEFAULT_AMEND_PERSON)
                .submitter(DEFAULT_SUBMITTER)
                .submitTime(DEFAULT_SUBMIT_TIME)
                .auditor(DEFAULT_AUDITOR)
                .auditorTime(DEFAULT_AUDITOR_TIME)
                .conditions(DEFAULT_CONDITIONS)
                .pleasePayeeType(DEFAULT_PLEASE_PAYEE_TYPE)
                .vehicleShelf(DEFAULT_VEHICLE_SHELF)
                .engine(DEFAULT_ENGINE)
                .driverId(DEFAULT_DRIVER_ID)
                .driverName(DEFAULT_DRIVER_NAME);
        return pleasePayeeAudit;
    }

    @Before
    public void initTest() {
        pleasePayeeAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createPleasePayeeAudit() throws Exception {
        int databaseSizeBeforeCreate = pleasePayeeAuditRepository.findAll().size();

        // Create the PleasePayeeAudit

        restPleasePayeeAuditMockMvc.perform(post("/api/please-payee-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pleasePayeeAudit)))
            .andExpect(status().isCreated());

        // Validate the PleasePayeeAudit in the database
        List<PleasePayeeAudit> pleasePayeeAuditList = pleasePayeeAuditRepository.findAll();
        assertThat(pleasePayeeAuditList).hasSize(databaseSizeBeforeCreate + 1);
        PleasePayeeAudit testPleasePayeeAudit = pleasePayeeAuditList.get(pleasePayeeAuditList.size() - 1);
        assertThat(testPleasePayeeAudit.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testPleasePayeeAudit.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testPleasePayeeAudit.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testPleasePayeeAudit.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testPleasePayeeAudit.getPleasePayeeId()).isEqualTo(DEFAULT_PLEASE_PAYEE_ID);
        assertThat(testPleasePayeeAudit.getPleasePayeeName()).isEqualTo(DEFAULT_PLEASE_PAYEE_NAME);
        assertThat(testPleasePayeeAudit.getPleasePayeeMoney()).isEqualTo(DEFAULT_PLEASE_PAYEE_MONEY);
        assertThat(testPleasePayeeAudit.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testPleasePayeeAudit.getGatheringName()).isEqualTo(DEFAULT_GATHERING_NAME);
        assertThat(testPleasePayeeAudit.getGatheringNumber()).isEqualTo(DEFAULT_GATHERING_NUMBER);
        assertThat(testPleasePayeeAudit.getUseTime()).isEqualTo(DEFAULT_USE_TIME);
        assertThat(testPleasePayeeAudit.getRemark1()).isEqualTo(DEFAULT_REMARK_1);
        assertThat(testPleasePayeeAudit.getAccessory()).isEqualTo(DEFAULT_ACCESSORY);
        assertThat(testPleasePayeeAudit.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPleasePayeeAudit.getRemark2()).isEqualTo(DEFAULT_REMARK_2);
        assertThat(testPleasePayeeAudit.getRemit()).isEqualTo(DEFAULT_REMIT);
        assertThat(testPleasePayeeAudit.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testPleasePayeeAudit.getEstablishTime()).isEqualTo(DEFAULT_ESTABLISH_TIME);
        assertThat(testPleasePayeeAudit.getAmendTime()).isEqualTo(DEFAULT_AMEND_TIME);
        assertThat(testPleasePayeeAudit.getAmendPerson()).isEqualTo(DEFAULT_AMEND_PERSON);
        assertThat(testPleasePayeeAudit.getSubmitter()).isEqualTo(DEFAULT_SUBMITTER);
        assertThat(testPleasePayeeAudit.getSubmitTime()).isEqualTo(DEFAULT_SUBMIT_TIME);
        assertThat(testPleasePayeeAudit.getAuditor()).isEqualTo(DEFAULT_AUDITOR);
        assertThat(testPleasePayeeAudit.getAuditorTime()).isEqualTo(DEFAULT_AUDITOR_TIME);
        assertThat(testPleasePayeeAudit.getConditions()).isEqualTo(DEFAULT_CONDITIONS);
        assertThat(testPleasePayeeAudit.getPleasePayeeType()).isEqualTo(DEFAULT_PLEASE_PAYEE_TYPE);
        assertThat(testPleasePayeeAudit.getVehicleShelf()).isEqualTo(DEFAULT_VEHICLE_SHELF);
        assertThat(testPleasePayeeAudit.getEngine()).isEqualTo(DEFAULT_ENGINE);
        assertThat(testPleasePayeeAudit.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testPleasePayeeAudit.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
    }

    @Test
    @Transactional
    public void createPleasePayeeAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pleasePayeeAuditRepository.findAll().size();

        // Create the PleasePayeeAudit with an existing ID
        PleasePayeeAudit existingPleasePayeeAudit = new PleasePayeeAudit();
        existingPleasePayeeAudit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPleasePayeeAuditMockMvc.perform(post("/api/please-payee-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPleasePayeeAudit)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PleasePayeeAudit> pleasePayeeAuditList = pleasePayeeAuditRepository.findAll();
        assertThat(pleasePayeeAuditList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPleasePayeeAudits() throws Exception {
        // Initialize the database
        pleasePayeeAuditRepository.saveAndFlush(pleasePayeeAudit);

        // Get all the pleasePayeeAuditList
        restPleasePayeeAuditMockMvc.perform(get("/api/please-payee-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pleasePayeeAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].pleasePayeeId").value(hasItem(DEFAULT_PLEASE_PAYEE_ID.intValue())))
            .andExpect(jsonPath("$.[*].pleasePayeeName").value(hasItem(DEFAULT_PLEASE_PAYEE_NAME.toString())))
            .andExpect(jsonPath("$.[*].pleasePayeeMoney").value(hasItem(DEFAULT_PLEASE_PAYEE_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
            .andExpect(jsonPath("$.[*].gatheringName").value(hasItem(DEFAULT_GATHERING_NAME.toString())))
            .andExpect(jsonPath("$.[*].gatheringNumber").value(hasItem(DEFAULT_GATHERING_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].useTime").value(hasItem(DEFAULT_USE_TIME.intValue())))
            .andExpect(jsonPath("$.[*].remark1").value(hasItem(DEFAULT_REMARK_1.toString())))
            .andExpect(jsonPath("$.[*].accessory").value(hasItem(DEFAULT_ACCESSORY.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].remark2").value(hasItem(DEFAULT_REMARK_2.toString())))
            .andExpect(jsonPath("$.[*].remit").value(hasItem(DEFAULT_REMIT.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].establishTime").value(hasItem(DEFAULT_ESTABLISH_TIME.intValue())))
            .andExpect(jsonPath("$.[*].amendTime").value(hasItem(DEFAULT_AMEND_TIME.intValue())))
            .andExpect(jsonPath("$.[*].amendPerson").value(hasItem(DEFAULT_AMEND_PERSON.toString())))
            .andExpect(jsonPath("$.[*].submitter").value(hasItem(DEFAULT_SUBMITTER.toString())))
            .andExpect(jsonPath("$.[*].submitTime").value(hasItem(DEFAULT_SUBMIT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].auditor").value(hasItem(DEFAULT_AUDITOR.toString())))
            .andExpect(jsonPath("$.[*].auditorTime").value(hasItem(DEFAULT_AUDITOR_TIME.intValue())))
            .andExpect(jsonPath("$.[*].conditions").value(hasItem(DEFAULT_CONDITIONS)))
            .andExpect(jsonPath("$.[*].pleasePayeeType").value(hasItem(DEFAULT_PLEASE_PAYEE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].vehicleShelf").value(hasItem(DEFAULT_VEHICLE_SHELF.toString())))
            .andExpect(jsonPath("$.[*].engine").value(hasItem(DEFAULT_ENGINE.toString())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())));
    }

    @Test
    @Transactional
    public void getPleasePayeeAudit() throws Exception {
        // Initialize the database
        pleasePayeeAuditRepository.saveAndFlush(pleasePayeeAudit);

        // Get the pleasePayeeAudit
        restPleasePayeeAuditMockMvc.perform(get("/api/please-payee-audits/{id}", pleasePayeeAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pleasePayeeAudit.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.pleasePayeeId").value(DEFAULT_PLEASE_PAYEE_ID.intValue()))
            .andExpect(jsonPath("$.pleasePayeeName").value(DEFAULT_PLEASE_PAYEE_NAME.toString()))
            .andExpect(jsonPath("$.pleasePayeeMoney").value(DEFAULT_PLEASE_PAYEE_MONEY.intValue()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON.toString()))
            .andExpect(jsonPath("$.gatheringName").value(DEFAULT_GATHERING_NAME.toString()))
            .andExpect(jsonPath("$.gatheringNumber").value(DEFAULT_GATHERING_NUMBER.toString()))
            .andExpect(jsonPath("$.useTime").value(DEFAULT_USE_TIME.intValue()))
            .andExpect(jsonPath("$.remark1").value(DEFAULT_REMARK_1.toString()))
            .andExpect(jsonPath("$.accessory").value(DEFAULT_ACCESSORY.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.remark2").value(DEFAULT_REMARK_2.toString()))
            .andExpect(jsonPath("$.remit").value(DEFAULT_REMIT.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.establishTime").value(DEFAULT_ESTABLISH_TIME.intValue()))
            .andExpect(jsonPath("$.amendTime").value(DEFAULT_AMEND_TIME.intValue()))
            .andExpect(jsonPath("$.amendPerson").value(DEFAULT_AMEND_PERSON.toString()))
            .andExpect(jsonPath("$.submitter").value(DEFAULT_SUBMITTER.toString()))
            .andExpect(jsonPath("$.submitTime").value(DEFAULT_SUBMIT_TIME.intValue()))
            .andExpect(jsonPath("$.auditor").value(DEFAULT_AUDITOR.toString()))
            .andExpect(jsonPath("$.auditorTime").value(DEFAULT_AUDITOR_TIME.intValue()))
            .andExpect(jsonPath("$.conditions").value(DEFAULT_CONDITIONS))
            .andExpect(jsonPath("$.pleasePayeeType").value(DEFAULT_PLEASE_PAYEE_TYPE.toString()))
            .andExpect(jsonPath("$.vehicleShelf").value(DEFAULT_VEHICLE_SHELF.toString()))
            .andExpect(jsonPath("$.engine").value(DEFAULT_ENGINE.toString()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPleasePayeeAudit() throws Exception {
        // Get the pleasePayeeAudit
        restPleasePayeeAuditMockMvc.perform(get("/api/please-payee-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePleasePayeeAudit() throws Exception {
        // Initialize the database
        pleasePayeeAuditService.save(pleasePayeeAudit);

        int databaseSizeBeforeUpdate = pleasePayeeAuditRepository.findAll().size();

        // Update the pleasePayeeAudit
        PleasePayeeAudit updatedPleasePayeeAudit = pleasePayeeAuditRepository.findOne(pleasePayeeAudit.getId());
        updatedPleasePayeeAudit
                .companyId(UPDATED_COMPANY_ID)
                .orderId(UPDATED_ORDER_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .pleasePayeeId(UPDATED_PLEASE_PAYEE_ID)
                .pleasePayeeName(UPDATED_PLEASE_PAYEE_NAME)
                .pleasePayeeMoney(UPDATED_PLEASE_PAYEE_MONEY)
                .reason(UPDATED_REASON)
                .gatheringName(UPDATED_GATHERING_NAME)
                .gatheringNumber(UPDATED_GATHERING_NUMBER)
                .useTime(UPDATED_USE_TIME)
                .remark1(UPDATED_REMARK_1)
                .accessory(UPDATED_ACCESSORY)
                .status(UPDATED_STATUS)
                .remark2(UPDATED_REMARK_2)
                .remit(UPDATED_REMIT)
                .serialNumber(UPDATED_SERIAL_NUMBER)
                .establishTime(UPDATED_ESTABLISH_TIME)
                .amendTime(UPDATED_AMEND_TIME)
                .amendPerson(UPDATED_AMEND_PERSON)
                .submitter(UPDATED_SUBMITTER)
                .submitTime(UPDATED_SUBMIT_TIME)
                .auditor(UPDATED_AUDITOR)
                .auditorTime(UPDATED_AUDITOR_TIME)
                .conditions(UPDATED_CONDITIONS)
                .pleasePayeeType(UPDATED_PLEASE_PAYEE_TYPE)
                .vehicleShelf(UPDATED_VEHICLE_SHELF)
                .engine(UPDATED_ENGINE)
                .driverId(UPDATED_DRIVER_ID)
                .driverName(UPDATED_DRIVER_NAME);

        restPleasePayeeAuditMockMvc.perform(put("/api/please-payee-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPleasePayeeAudit)))
            .andExpect(status().isOk());

        // Validate the PleasePayeeAudit in the database
        List<PleasePayeeAudit> pleasePayeeAuditList = pleasePayeeAuditRepository.findAll();
        assertThat(pleasePayeeAuditList).hasSize(databaseSizeBeforeUpdate);
        PleasePayeeAudit testPleasePayeeAudit = pleasePayeeAuditList.get(pleasePayeeAuditList.size() - 1);
        assertThat(testPleasePayeeAudit.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testPleasePayeeAudit.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testPleasePayeeAudit.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testPleasePayeeAudit.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testPleasePayeeAudit.getPleasePayeeId()).isEqualTo(UPDATED_PLEASE_PAYEE_ID);
        assertThat(testPleasePayeeAudit.getPleasePayeeName()).isEqualTo(UPDATED_PLEASE_PAYEE_NAME);
        assertThat(testPleasePayeeAudit.getPleasePayeeMoney()).isEqualTo(UPDATED_PLEASE_PAYEE_MONEY);
        assertThat(testPleasePayeeAudit.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testPleasePayeeAudit.getGatheringName()).isEqualTo(UPDATED_GATHERING_NAME);
        assertThat(testPleasePayeeAudit.getGatheringNumber()).isEqualTo(UPDATED_GATHERING_NUMBER);
        assertThat(testPleasePayeeAudit.getUseTime()).isEqualTo(UPDATED_USE_TIME);
        assertThat(testPleasePayeeAudit.getRemark1()).isEqualTo(UPDATED_REMARK_1);
        assertThat(testPleasePayeeAudit.getAccessory()).isEqualTo(UPDATED_ACCESSORY);
        assertThat(testPleasePayeeAudit.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPleasePayeeAudit.getRemark2()).isEqualTo(UPDATED_REMARK_2);
        assertThat(testPleasePayeeAudit.getRemit()).isEqualTo(UPDATED_REMIT);
        assertThat(testPleasePayeeAudit.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testPleasePayeeAudit.getEstablishTime()).isEqualTo(UPDATED_ESTABLISH_TIME);
        assertThat(testPleasePayeeAudit.getAmendTime()).isEqualTo(UPDATED_AMEND_TIME);
        assertThat(testPleasePayeeAudit.getAmendPerson()).isEqualTo(UPDATED_AMEND_PERSON);
        assertThat(testPleasePayeeAudit.getSubmitter()).isEqualTo(UPDATED_SUBMITTER);
        assertThat(testPleasePayeeAudit.getSubmitTime()).isEqualTo(UPDATED_SUBMIT_TIME);
        assertThat(testPleasePayeeAudit.getAuditor()).isEqualTo(UPDATED_AUDITOR);
        assertThat(testPleasePayeeAudit.getAuditorTime()).isEqualTo(UPDATED_AUDITOR_TIME);
        assertThat(testPleasePayeeAudit.getConditions()).isEqualTo(UPDATED_CONDITIONS);
        assertThat(testPleasePayeeAudit.getPleasePayeeType()).isEqualTo(UPDATED_PLEASE_PAYEE_TYPE);
        assertThat(testPleasePayeeAudit.getVehicleShelf()).isEqualTo(UPDATED_VEHICLE_SHELF);
        assertThat(testPleasePayeeAudit.getEngine()).isEqualTo(UPDATED_ENGINE);
        assertThat(testPleasePayeeAudit.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testPleasePayeeAudit.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPleasePayeeAudit() throws Exception {
        int databaseSizeBeforeUpdate = pleasePayeeAuditRepository.findAll().size();

        // Create the PleasePayeeAudit

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPleasePayeeAuditMockMvc.perform(put("/api/please-payee-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pleasePayeeAudit)))
            .andExpect(status().isCreated());

        // Validate the PleasePayeeAudit in the database
        List<PleasePayeeAudit> pleasePayeeAuditList = pleasePayeeAuditRepository.findAll();
        assertThat(pleasePayeeAuditList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePleasePayeeAudit() throws Exception {
        // Initialize the database
        pleasePayeeAuditService.save(pleasePayeeAudit);

        int databaseSizeBeforeDelete = pleasePayeeAuditRepository.findAll().size();

        // Get the pleasePayeeAudit
        restPleasePayeeAuditMockMvc.perform(delete("/api/please-payee-audits/{id}", pleasePayeeAudit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PleasePayeeAudit> pleasePayeeAuditList = pleasePayeeAuditRepository.findAll();
        assertThat(pleasePayeeAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PleasePayeeAudit.class);
    }
}

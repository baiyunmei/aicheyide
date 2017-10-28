package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.RetreatDeposit;
import com.duma.repository.RetreatDepositRepository;
import com.duma.service.RetreatDepositService;
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
 * Test class for the RetreatDepositResource REST controller.
 *
 * @see RetreatDepositResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class RetreatDepositResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_REFUND_TIME = 1L;
    private static final Long UPDATED_REFUND_TIME = 2L;

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PLEDGE_STATUS = 1;
    private static final Integer UPDATED_PLEDGE_STATUS = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_RECEIPT_DATE = 1L;
    private static final Long UPDATED_RECEIPT_DATE = 2L;

    private static final Long DEFAULT_NEXT_RENT_TIME = 1L;
    private static final Long UPDATED_NEXT_RENT_TIME = 2L;

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    private static final Integer DEFAULT_WHETHER_REFUND = 1;
    private static final Integer UPDATED_WHETHER_REFUND = 2;

    @Autowired
    private RetreatDepositRepository retreatDepositRepository;

    @Autowired
    private RetreatDepositService retreatDepositService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRetreatDepositMockMvc;

    private RetreatDeposit retreatDeposit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RetreatDepositResource retreatDepositResource = new RetreatDepositResource(retreatDepositService);
        this.restRetreatDepositMockMvc = MockMvcBuilders.standaloneSetup(retreatDepositResource)
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
    public static RetreatDeposit createEntity(EntityManager em) {
        RetreatDeposit retreatDeposit = new RetreatDeposit()
                .driverId(DEFAULT_DRIVER_ID)
                .orderId(DEFAULT_ORDER_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .refundTime(DEFAULT_REFUND_TIME)
                .money(DEFAULT_MONEY)
                .source(DEFAULT_SOURCE)
                .pledgeStatus(DEFAULT_PLEDGE_STATUS)
                .remark(DEFAULT_REMARK)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .nextRentTime(DEFAULT_NEXT_RENT_TIME)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME)
                .whetherRefund(DEFAULT_WHETHER_REFUND);
        return retreatDeposit;
    }

    @Before
    public void initTest() {
        retreatDeposit = createEntity(em);
    }

    @Test
    @Transactional
    public void createRetreatDeposit() throws Exception {
        int databaseSizeBeforeCreate = retreatDepositRepository.findAll().size();

        // Create the RetreatDeposit

        restRetreatDepositMockMvc.perform(post("/api/retreat-deposits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retreatDeposit)))
            .andExpect(status().isCreated());

        // Validate the RetreatDeposit in the database
        List<RetreatDeposit> retreatDepositList = retreatDepositRepository.findAll();
        assertThat(retreatDepositList).hasSize(databaseSizeBeforeCreate + 1);
        RetreatDeposit testRetreatDeposit = retreatDepositList.get(retreatDepositList.size() - 1);
        assertThat(testRetreatDeposit.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testRetreatDeposit.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testRetreatDeposit.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testRetreatDeposit.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testRetreatDeposit.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testRetreatDeposit.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testRetreatDeposit.getRefundTime()).isEqualTo(DEFAULT_REFUND_TIME);
        assertThat(testRetreatDeposit.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testRetreatDeposit.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testRetreatDeposit.getPledgeStatus()).isEqualTo(DEFAULT_PLEDGE_STATUS);
        assertThat(testRetreatDeposit.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testRetreatDeposit.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testRetreatDeposit.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testRetreatDeposit.getNextRentTime()).isEqualTo(DEFAULT_NEXT_RENT_TIME);
        assertThat(testRetreatDeposit.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testRetreatDeposit.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testRetreatDeposit.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testRetreatDeposit.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
        assertThat(testRetreatDeposit.getWhetherRefund()).isEqualTo(DEFAULT_WHETHER_REFUND);
    }

    @Test
    @Transactional
    public void createRetreatDepositWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = retreatDepositRepository.findAll().size();

        // Create the RetreatDeposit with an existing ID
        RetreatDeposit existingRetreatDeposit = new RetreatDeposit();
        existingRetreatDeposit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRetreatDepositMockMvc.perform(post("/api/retreat-deposits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRetreatDeposit)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RetreatDeposit> retreatDepositList = retreatDepositRepository.findAll();
        assertThat(retreatDepositList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRetreatDeposits() throws Exception {
        // Initialize the database
        retreatDepositRepository.saveAndFlush(retreatDeposit);

        // Get all the retreatDepositList
        restRetreatDepositMockMvc.perform(get("/api/retreat-deposits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(retreatDeposit.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].refundTime").value(hasItem(DEFAULT_REFUND_TIME.intValue())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].pledgeStatus").value(hasItem(DEFAULT_PLEDGE_STATUS)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].nextRentTime").value(hasItem(DEFAULT_NEXT_RENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].whetherRefund").value(hasItem(DEFAULT_WHETHER_REFUND)));
    }

    @Test
    @Transactional
    public void getRetreatDeposit() throws Exception {
        // Initialize the database
        retreatDepositRepository.saveAndFlush(retreatDeposit);

        // Get the retreatDeposit
        restRetreatDepositMockMvc.perform(get("/api/retreat-deposits/{id}", retreatDeposit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(retreatDeposit.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.refundTime").value(DEFAULT_REFUND_TIME.intValue()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.pledgeStatus").value(DEFAULT_PLEDGE_STATUS))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.nextRentTime").value(DEFAULT_NEXT_RENT_TIME.intValue()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()))
            .andExpect(jsonPath("$.whetherRefund").value(DEFAULT_WHETHER_REFUND));
    }

    @Test
    @Transactional
    public void getNonExistingRetreatDeposit() throws Exception {
        // Get the retreatDeposit
        restRetreatDepositMockMvc.perform(get("/api/retreat-deposits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRetreatDeposit() throws Exception {
        // Initialize the database
        retreatDepositService.save(retreatDeposit);

        int databaseSizeBeforeUpdate = retreatDepositRepository.findAll().size();

        // Update the retreatDeposit
        RetreatDeposit updatedRetreatDeposit = retreatDepositRepository.findOne(retreatDeposit.getId());
        updatedRetreatDeposit
                .driverId(UPDATED_DRIVER_ID)
                .orderId(UPDATED_ORDER_ID)
                .companyId(UPDATED_COMPANY_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .refundTime(UPDATED_REFUND_TIME)
                .money(UPDATED_MONEY)
                .source(UPDATED_SOURCE)
                .pledgeStatus(UPDATED_PLEDGE_STATUS)
                .remark(UPDATED_REMARK)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .nextRentTime(UPDATED_NEXT_RENT_TIME)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME)
                .whetherRefund(UPDATED_WHETHER_REFUND);

        restRetreatDepositMockMvc.perform(put("/api/retreat-deposits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRetreatDeposit)))
            .andExpect(status().isOk());

        // Validate the RetreatDeposit in the database
        List<RetreatDeposit> retreatDepositList = retreatDepositRepository.findAll();
        assertThat(retreatDepositList).hasSize(databaseSizeBeforeUpdate);
        RetreatDeposit testRetreatDeposit = retreatDepositList.get(retreatDepositList.size() - 1);
        assertThat(testRetreatDeposit.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testRetreatDeposit.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testRetreatDeposit.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testRetreatDeposit.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testRetreatDeposit.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testRetreatDeposit.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testRetreatDeposit.getRefundTime()).isEqualTo(UPDATED_REFUND_TIME);
        assertThat(testRetreatDeposit.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testRetreatDeposit.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testRetreatDeposit.getPledgeStatus()).isEqualTo(UPDATED_PLEDGE_STATUS);
        assertThat(testRetreatDeposit.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testRetreatDeposit.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testRetreatDeposit.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testRetreatDeposit.getNextRentTime()).isEqualTo(UPDATED_NEXT_RENT_TIME);
        assertThat(testRetreatDeposit.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testRetreatDeposit.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testRetreatDeposit.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testRetreatDeposit.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
        assertThat(testRetreatDeposit.getWhetherRefund()).isEqualTo(UPDATED_WHETHER_REFUND);
    }

    @Test
    @Transactional
    public void updateNonExistingRetreatDeposit() throws Exception {
        int databaseSizeBeforeUpdate = retreatDepositRepository.findAll().size();

        // Create the RetreatDeposit

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRetreatDepositMockMvc.perform(put("/api/retreat-deposits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(retreatDeposit)))
            .andExpect(status().isCreated());

        // Validate the RetreatDeposit in the database
        List<RetreatDeposit> retreatDepositList = retreatDepositRepository.findAll();
        assertThat(retreatDepositList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRetreatDeposit() throws Exception {
        // Initialize the database
        retreatDepositService.save(retreatDeposit);

        int databaseSizeBeforeDelete = retreatDepositRepository.findAll().size();

        // Get the retreatDeposit
        restRetreatDepositMockMvc.perform(delete("/api/retreat-deposits/{id}", retreatDeposit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RetreatDeposit> retreatDepositList = retreatDepositRepository.findAll();
        assertThat(retreatDepositList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetreatDeposit.class);
    }
}

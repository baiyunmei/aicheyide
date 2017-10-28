package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.OverdueGathering;
import com.duma.repository.OverdueGatheringRepository;
import com.duma.service.OverdueGatheringService;
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
 * Test class for the OverdueGatheringResource REST controller.
 *
 * @see OverdueGatheringResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class OverdueGatheringResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_REPAYMENT_TIME = 1L;
    private static final Long UPDATED_REPAYMENT_TIME = 2L;

    private static final BigDecimal DEFAULT_OVERDUE_AWAIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OVERDUE_AWAIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OVERDUE_INTEREST = new BigDecimal(1);
    private static final BigDecimal UPDATED_OVERDUE_INTEREST = new BigDecimal(2);

    private static final Integer DEFAULT_OVERDUE_DATA = 1;
    private static final Integer UPDATED_OVERDUE_DATA = 2;

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(2);

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_RECEIPT_DATE = 1L;
    private static final Long UPDATED_RECEIPT_DATE = 2L;

    private static final BigDecimal DEFAULT_PRACTICAL_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRACTICAL_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_PAYMENT_WAY = 1;
    private static final Integer UPDATED_PAYMENT_WAY = 2;

    private static final String DEFAULT_REMARK_1 = "AAAAAAAAAA";
    private static final String UPDATED_REMARK_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    private static final Integer DEFAULT_GATHERING = 1;
    private static final Integer UPDATED_GATHERING = 2;

    private static final Long DEFAULT_GATHER_TIME = 1L;
    private static final Long UPDATED_GATHER_TIME = 2L;

    private static final String DEFAULT_REMARK_2 = "AAAAAAAAAA";
    private static final String UPDATED_REMARK_2 = "BBBBBBBBBB";

    @Autowired
    private OverdueGatheringRepository overdueGatheringRepository;

    @Autowired
    private OverdueGatheringService overdueGatheringService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOverdueGatheringMockMvc;

    private OverdueGathering overdueGathering;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OverdueGatheringResource overdueGatheringResource = new OverdueGatheringResource(overdueGatheringService);
        this.restOverdueGatheringMockMvc = MockMvcBuilders.standaloneSetup(overdueGatheringResource)
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
    public static OverdueGathering createEntity(EntityManager em) {
        OverdueGathering overdueGathering = new OverdueGathering()
                .driverId(DEFAULT_DRIVER_ID)
                .orderId(DEFAULT_ORDER_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .repaymentTime(DEFAULT_REPAYMENT_TIME)
                .overdueAwait(DEFAULT_OVERDUE_AWAIT)
                .overdueInterest(DEFAULT_OVERDUE_INTEREST)
                .overdueData(DEFAULT_OVERDUE_DATA)
                .total(DEFAULT_TOTAL)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .practicalMoney(DEFAULT_PRACTICAL_MONEY)
                .paymentWay(DEFAULT_PAYMENT_WAY)
                .remark1(DEFAULT_REMARK_1)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME)
                .gathering(DEFAULT_GATHERING)
                .gatherTime(DEFAULT_GATHER_TIME)
                .remark2(DEFAULT_REMARK_2);
        return overdueGathering;
    }

    @Before
    public void initTest() {
        overdueGathering = createEntity(em);
    }

    @Test
    @Transactional
    public void createOverdueGathering() throws Exception {
        int databaseSizeBeforeCreate = overdueGatheringRepository.findAll().size();

        // Create the OverdueGathering

        restOverdueGatheringMockMvc.perform(post("/api/overdue-gatherings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueGathering)))
            .andExpect(status().isCreated());

        // Validate the OverdueGathering in the database
        List<OverdueGathering> overdueGatheringList = overdueGatheringRepository.findAll();
        assertThat(overdueGatheringList).hasSize(databaseSizeBeforeCreate + 1);
        OverdueGathering testOverdueGathering = overdueGatheringList.get(overdueGatheringList.size() - 1);
        assertThat(testOverdueGathering.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testOverdueGathering.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOverdueGathering.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testOverdueGathering.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testOverdueGathering.getRepaymentTime()).isEqualTo(DEFAULT_REPAYMENT_TIME);
        assertThat(testOverdueGathering.getOverdueAwait()).isEqualTo(DEFAULT_OVERDUE_AWAIT);
        assertThat(testOverdueGathering.getOverdueInterest()).isEqualTo(DEFAULT_OVERDUE_INTEREST);
        assertThat(testOverdueGathering.getOverdueData()).isEqualTo(DEFAULT_OVERDUE_DATA);
        assertThat(testOverdueGathering.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testOverdueGathering.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testOverdueGathering.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testOverdueGathering.getPracticalMoney()).isEqualTo(DEFAULT_PRACTICAL_MONEY);
        assertThat(testOverdueGathering.getPaymentWay()).isEqualTo(DEFAULT_PAYMENT_WAY);
        assertThat(testOverdueGathering.getRemark1()).isEqualTo(DEFAULT_REMARK_1);
        assertThat(testOverdueGathering.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testOverdueGathering.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testOverdueGathering.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testOverdueGathering.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
        assertThat(testOverdueGathering.getGathering()).isEqualTo(DEFAULT_GATHERING);
        assertThat(testOverdueGathering.getGatherTime()).isEqualTo(DEFAULT_GATHER_TIME);
        assertThat(testOverdueGathering.getRemark2()).isEqualTo(DEFAULT_REMARK_2);
    }

    @Test
    @Transactional
    public void createOverdueGatheringWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = overdueGatheringRepository.findAll().size();

        // Create the OverdueGathering with an existing ID
        OverdueGathering existingOverdueGathering = new OverdueGathering();
        existingOverdueGathering.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOverdueGatheringMockMvc.perform(post("/api/overdue-gatherings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingOverdueGathering)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<OverdueGathering> overdueGatheringList = overdueGatheringRepository.findAll();
        assertThat(overdueGatheringList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOverdueGatherings() throws Exception {
        // Initialize the database
        overdueGatheringRepository.saveAndFlush(overdueGathering);

        // Get all the overdueGatheringList
        restOverdueGatheringMockMvc.perform(get("/api/overdue-gatherings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(overdueGathering.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].repaymentTime").value(hasItem(DEFAULT_REPAYMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].overdueAwait").value(hasItem(DEFAULT_OVERDUE_AWAIT.intValue())))
            .andExpect(jsonPath("$.[*].overdueInterest").value(hasItem(DEFAULT_OVERDUE_INTEREST.intValue())))
            .andExpect(jsonPath("$.[*].overdueData").value(hasItem(DEFAULT_OVERDUE_DATA)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].practicalMoney").value(hasItem(DEFAULT_PRACTICAL_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].paymentWay").value(hasItem(DEFAULT_PAYMENT_WAY)))
            .andExpect(jsonPath("$.[*].remark1").value(hasItem(DEFAULT_REMARK_1.toString())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].gathering").value(hasItem(DEFAULT_GATHERING)))
            .andExpect(jsonPath("$.[*].gatherTime").value(hasItem(DEFAULT_GATHER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].remark2").value(hasItem(DEFAULT_REMARK_2.toString())));
    }

    @Test
    @Transactional
    public void getOverdueGathering() throws Exception {
        // Initialize the database
        overdueGatheringRepository.saveAndFlush(overdueGathering);

        // Get the overdueGathering
        restOverdueGatheringMockMvc.perform(get("/api/overdue-gatherings/{id}", overdueGathering.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(overdueGathering.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.repaymentTime").value(DEFAULT_REPAYMENT_TIME.intValue()))
            .andExpect(jsonPath("$.overdueAwait").value(DEFAULT_OVERDUE_AWAIT.intValue()))
            .andExpect(jsonPath("$.overdueInterest").value(DEFAULT_OVERDUE_INTEREST.intValue()))
            .andExpect(jsonPath("$.overdueData").value(DEFAULT_OVERDUE_DATA))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.practicalMoney").value(DEFAULT_PRACTICAL_MONEY.intValue()))
            .andExpect(jsonPath("$.paymentWay").value(DEFAULT_PAYMENT_WAY))
            .andExpect(jsonPath("$.remark1").value(DEFAULT_REMARK_1.toString()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()))
            .andExpect(jsonPath("$.gathering").value(DEFAULT_GATHERING))
            .andExpect(jsonPath("$.gatherTime").value(DEFAULT_GATHER_TIME.intValue()))
            .andExpect(jsonPath("$.remark2").value(DEFAULT_REMARK_2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOverdueGathering() throws Exception {
        // Get the overdueGathering
        restOverdueGatheringMockMvc.perform(get("/api/overdue-gatherings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOverdueGathering() throws Exception {
        // Initialize the database
        overdueGatheringService.save(overdueGathering);

        int databaseSizeBeforeUpdate = overdueGatheringRepository.findAll().size();

        // Update the overdueGathering
        OverdueGathering updatedOverdueGathering = overdueGatheringRepository.findOne(overdueGathering.getId());
        updatedOverdueGathering
                .driverId(UPDATED_DRIVER_ID)
                .orderId(UPDATED_ORDER_ID)
                .companyId(UPDATED_COMPANY_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .repaymentTime(UPDATED_REPAYMENT_TIME)
                .overdueAwait(UPDATED_OVERDUE_AWAIT)
                .overdueInterest(UPDATED_OVERDUE_INTEREST)
                .overdueData(UPDATED_OVERDUE_DATA)
                .total(UPDATED_TOTAL)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .practicalMoney(UPDATED_PRACTICAL_MONEY)
                .paymentWay(UPDATED_PAYMENT_WAY)
                .remark1(UPDATED_REMARK_1)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME)
                .gathering(UPDATED_GATHERING)
                .gatherTime(UPDATED_GATHER_TIME)
                .remark2(UPDATED_REMARK_2);

        restOverdueGatheringMockMvc.perform(put("/api/overdue-gatherings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOverdueGathering)))
            .andExpect(status().isOk());

        // Validate the OverdueGathering in the database
        List<OverdueGathering> overdueGatheringList = overdueGatheringRepository.findAll();
        assertThat(overdueGatheringList).hasSize(databaseSizeBeforeUpdate);
        OverdueGathering testOverdueGathering = overdueGatheringList.get(overdueGatheringList.size() - 1);
        assertThat(testOverdueGathering.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testOverdueGathering.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOverdueGathering.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testOverdueGathering.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testOverdueGathering.getRepaymentTime()).isEqualTo(UPDATED_REPAYMENT_TIME);
        assertThat(testOverdueGathering.getOverdueAwait()).isEqualTo(UPDATED_OVERDUE_AWAIT);
        assertThat(testOverdueGathering.getOverdueInterest()).isEqualTo(UPDATED_OVERDUE_INTEREST);
        assertThat(testOverdueGathering.getOverdueData()).isEqualTo(UPDATED_OVERDUE_DATA);
        assertThat(testOverdueGathering.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testOverdueGathering.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testOverdueGathering.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testOverdueGathering.getPracticalMoney()).isEqualTo(UPDATED_PRACTICAL_MONEY);
        assertThat(testOverdueGathering.getPaymentWay()).isEqualTo(UPDATED_PAYMENT_WAY);
        assertThat(testOverdueGathering.getRemark1()).isEqualTo(UPDATED_REMARK_1);
        assertThat(testOverdueGathering.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testOverdueGathering.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testOverdueGathering.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testOverdueGathering.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
        assertThat(testOverdueGathering.getGathering()).isEqualTo(UPDATED_GATHERING);
        assertThat(testOverdueGathering.getGatherTime()).isEqualTo(UPDATED_GATHER_TIME);
        assertThat(testOverdueGathering.getRemark2()).isEqualTo(UPDATED_REMARK_2);
    }

    @Test
    @Transactional
    public void updateNonExistingOverdueGathering() throws Exception {
        int databaseSizeBeforeUpdate = overdueGatheringRepository.findAll().size();

        // Create the OverdueGathering

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOverdueGatheringMockMvc.perform(put("/api/overdue-gatherings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueGathering)))
            .andExpect(status().isCreated());

        // Validate the OverdueGathering in the database
        List<OverdueGathering> overdueGatheringList = overdueGatheringRepository.findAll();
        assertThat(overdueGatheringList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOverdueGathering() throws Exception {
        // Initialize the database
        overdueGatheringService.save(overdueGathering);

        int databaseSizeBeforeDelete = overdueGatheringRepository.findAll().size();

        // Get the overdueGathering
        restOverdueGatheringMockMvc.perform(delete("/api/overdue-gatherings/{id}", overdueGathering.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OverdueGathering> overdueGatheringList = overdueGatheringRepository.findAll();
        assertThat(overdueGatheringList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OverdueGathering.class);
    }
}

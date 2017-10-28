package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.MonthlyManagement;
import com.duma.repository.MonthlyManagementRepository;
import com.duma.service.MonthlyManagementService;
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
 * Test class for the MonthlyManagementResource REST controller.
 *
 * @see MonthlyManagementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class MonthlyManagementResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT = "BBBBBBBBBB";

    private static final Long DEFAULT_REPAYMENT_TIME = 1L;
    private static final Long UPDATED_REPAYMENT_TIME = 2L;

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_OVERDUE = 1;
    private static final Integer UPDATED_OVERDUE = 2;

    private static final Integer DEFAULT_PERIODS = 1;
    private static final Integer UPDATED_PERIODS = 2;

    private static final BigDecimal DEFAULT_NEXT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_NEXT_MONEY = new BigDecimal(2);

    private static final Long DEFAULT_NEXT_DATE = 1L;
    private static final Long UPDATED_NEXT_DATE = 2L;

    private static final Integer DEFAULT_PERIODS_STATUS = 1;
    private static final Integer UPDATED_PERIODS_STATUS = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private MonthlyManagementRepository monthlyManagementRepository;

    @Autowired
    private MonthlyManagementService monthlyManagementService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMonthlyManagementMockMvc;

    private MonthlyManagement monthlyManagement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MonthlyManagementResource monthlyManagementResource = new MonthlyManagementResource(monthlyManagementService);
        this.restMonthlyManagementMockMvc = MockMvcBuilders.standaloneSetup(monthlyManagementResource)
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
    public static MonthlyManagement createEntity(EntityManager em) {
        MonthlyManagement monthlyManagement = new MonthlyManagement()
                .driverId(DEFAULT_DRIVER_ID)
                .orderId(DEFAULT_ORDER_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .driverName(DEFAULT_DRIVER_NAME)
                .bankAccount(DEFAULT_BANK_ACCOUNT)
                .repaymentTime(DEFAULT_REPAYMENT_TIME)
                .money(DEFAULT_MONEY)
                .overdue(DEFAULT_OVERDUE)
                .periods(DEFAULT_PERIODS)
                .nextMoney(DEFAULT_NEXT_MONEY)
                .nextDate(DEFAULT_NEXT_DATE)
                .periodsStatus(DEFAULT_PERIODS_STATUS)
                .remark(DEFAULT_REMARK);
        return monthlyManagement;
    }

    @Before
    public void initTest() {
        monthlyManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createMonthlyManagement() throws Exception {
        int databaseSizeBeforeCreate = monthlyManagementRepository.findAll().size();

        // Create the MonthlyManagement

        restMonthlyManagementMockMvc.perform(post("/api/monthly-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyManagement)))
            .andExpect(status().isCreated());

        // Validate the MonthlyManagement in the database
        List<MonthlyManagement> monthlyManagementList = monthlyManagementRepository.findAll();
        assertThat(monthlyManagementList).hasSize(databaseSizeBeforeCreate + 1);
        MonthlyManagement testMonthlyManagement = monthlyManagementList.get(monthlyManagementList.size() - 1);
        assertThat(testMonthlyManagement.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testMonthlyManagement.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testMonthlyManagement.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testMonthlyManagement.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testMonthlyManagement.getBankAccount()).isEqualTo(DEFAULT_BANK_ACCOUNT);
        assertThat(testMonthlyManagement.getRepaymentTime()).isEqualTo(DEFAULT_REPAYMENT_TIME);
        assertThat(testMonthlyManagement.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testMonthlyManagement.getOverdue()).isEqualTo(DEFAULT_OVERDUE);
        assertThat(testMonthlyManagement.getPeriods()).isEqualTo(DEFAULT_PERIODS);
        assertThat(testMonthlyManagement.getNextMoney()).isEqualTo(DEFAULT_NEXT_MONEY);
        assertThat(testMonthlyManagement.getNextDate()).isEqualTo(DEFAULT_NEXT_DATE);
        assertThat(testMonthlyManagement.getPeriodsStatus()).isEqualTo(DEFAULT_PERIODS_STATUS);
        assertThat(testMonthlyManagement.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createMonthlyManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = monthlyManagementRepository.findAll().size();

        // Create the MonthlyManagement with an existing ID
        MonthlyManagement existingMonthlyManagement = new MonthlyManagement();
        existingMonthlyManagement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMonthlyManagementMockMvc.perform(post("/api/monthly-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMonthlyManagement)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MonthlyManagement> monthlyManagementList = monthlyManagementRepository.findAll();
        assertThat(monthlyManagementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMonthlyManagements() throws Exception {
        // Initialize the database
        monthlyManagementRepository.saveAndFlush(monthlyManagement);

        // Get all the monthlyManagementList
        restMonthlyManagementMockMvc.perform(get("/api/monthly-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monthlyManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DEFAULT_BANK_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].repaymentTime").value(hasItem(DEFAULT_REPAYMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].overdue").value(hasItem(DEFAULT_OVERDUE)))
            .andExpect(jsonPath("$.[*].periods").value(hasItem(DEFAULT_PERIODS)))
            .andExpect(jsonPath("$.[*].nextMoney").value(hasItem(DEFAULT_NEXT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].nextDate").value(hasItem(DEFAULT_NEXT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].periodsStatus").value(hasItem(DEFAULT_PERIODS_STATUS)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getMonthlyManagement() throws Exception {
        // Initialize the database
        monthlyManagementRepository.saveAndFlush(monthlyManagement);

        // Get the monthlyManagement
        restMonthlyManagementMockMvc.perform(get("/api/monthly-managements/{id}", monthlyManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(monthlyManagement.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.bankAccount").value(DEFAULT_BANK_ACCOUNT.toString()))
            .andExpect(jsonPath("$.repaymentTime").value(DEFAULT_REPAYMENT_TIME.intValue()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.overdue").value(DEFAULT_OVERDUE))
            .andExpect(jsonPath("$.periods").value(DEFAULT_PERIODS))
            .andExpect(jsonPath("$.nextMoney").value(DEFAULT_NEXT_MONEY.intValue()))
            .andExpect(jsonPath("$.nextDate").value(DEFAULT_NEXT_DATE.intValue()))
            .andExpect(jsonPath("$.periodsStatus").value(DEFAULT_PERIODS_STATUS))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMonthlyManagement() throws Exception {
        // Get the monthlyManagement
        restMonthlyManagementMockMvc.perform(get("/api/monthly-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMonthlyManagement() throws Exception {
        // Initialize the database
        monthlyManagementService.save(monthlyManagement);

        int databaseSizeBeforeUpdate = monthlyManagementRepository.findAll().size();

        // Update the monthlyManagement
        MonthlyManagement updatedMonthlyManagement = monthlyManagementRepository.findOne(monthlyManagement.getId());
        updatedMonthlyManagement
                .driverId(UPDATED_DRIVER_ID)
                .orderId(UPDATED_ORDER_ID)
                .companyId(UPDATED_COMPANY_ID)
                .driverName(UPDATED_DRIVER_NAME)
                .bankAccount(UPDATED_BANK_ACCOUNT)
                .repaymentTime(UPDATED_REPAYMENT_TIME)
                .money(UPDATED_MONEY)
                .overdue(UPDATED_OVERDUE)
                .periods(UPDATED_PERIODS)
                .nextMoney(UPDATED_NEXT_MONEY)
                .nextDate(UPDATED_NEXT_DATE)
                .periodsStatus(UPDATED_PERIODS_STATUS)
                .remark(UPDATED_REMARK);

        restMonthlyManagementMockMvc.perform(put("/api/monthly-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMonthlyManagement)))
            .andExpect(status().isOk());

        // Validate the MonthlyManagement in the database
        List<MonthlyManagement> monthlyManagementList = monthlyManagementRepository.findAll();
        assertThat(monthlyManagementList).hasSize(databaseSizeBeforeUpdate);
        MonthlyManagement testMonthlyManagement = monthlyManagementList.get(monthlyManagementList.size() - 1);
        assertThat(testMonthlyManagement.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testMonthlyManagement.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testMonthlyManagement.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testMonthlyManagement.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testMonthlyManagement.getBankAccount()).isEqualTo(UPDATED_BANK_ACCOUNT);
        assertThat(testMonthlyManagement.getRepaymentTime()).isEqualTo(UPDATED_REPAYMENT_TIME);
        assertThat(testMonthlyManagement.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testMonthlyManagement.getOverdue()).isEqualTo(UPDATED_OVERDUE);
        assertThat(testMonthlyManagement.getPeriods()).isEqualTo(UPDATED_PERIODS);
        assertThat(testMonthlyManagement.getNextMoney()).isEqualTo(UPDATED_NEXT_MONEY);
        assertThat(testMonthlyManagement.getNextDate()).isEqualTo(UPDATED_NEXT_DATE);
        assertThat(testMonthlyManagement.getPeriodsStatus()).isEqualTo(UPDATED_PERIODS_STATUS);
        assertThat(testMonthlyManagement.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingMonthlyManagement() throws Exception {
        int databaseSizeBeforeUpdate = monthlyManagementRepository.findAll().size();

        // Create the MonthlyManagement

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMonthlyManagementMockMvc.perform(put("/api/monthly-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyManagement)))
            .andExpect(status().isCreated());

        // Validate the MonthlyManagement in the database
        List<MonthlyManagement> monthlyManagementList = monthlyManagementRepository.findAll();
        assertThat(monthlyManagementList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMonthlyManagement() throws Exception {
        // Initialize the database
        monthlyManagementService.save(monthlyManagement);

        int databaseSizeBeforeDelete = monthlyManagementRepository.findAll().size();

        // Get the monthlyManagement
        restMonthlyManagementMockMvc.perform(delete("/api/monthly-managements/{id}", monthlyManagement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MonthlyManagement> monthlyManagementList = monthlyManagementRepository.findAll();
        assertThat(monthlyManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonthlyManagement.class);
    }
}

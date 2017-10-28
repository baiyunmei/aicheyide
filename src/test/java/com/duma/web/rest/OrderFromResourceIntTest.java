package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.OrderFrom;
import com.duma.repository.OrderFromRepository;
import com.duma.service.OrderFromService;
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
 * Test class for the OrderFromResource REST controller.
 *
 * @see OrderFromResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class OrderFromResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_MARKET_ID = 1L;
    private static final Long UPDATED_MARKET_ID = 2L;

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final String DEFAULT_PRODUCT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FUEL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FUEL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BRAND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSIONS = 1;
    private static final Integer UPDATED_VERSIONS = 2;

    private static final String DEFAULT_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_YEAR = "BBBBBBBBBB";

    private static final Integer DEFAULT_REFIT = 1;
    private static final Integer UPDATED_REFIT = 2;

    private static final String DEFAULT_SALES_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_SALES_PLAN = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_PLAN_ID = "AAAAAAAAAA";
    private static final String UPDATED_SALES_PLAN_ID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DOWN_PAYMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DOWN_PAYMENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MONTHLY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTHLY = new BigDecimal(2);

    private static final Integer DEFAULT_PERIODS = 1;
    private static final Integer UPDATED_PERIODS = 2;

    private static final BigDecimal DEFAULT_CASH_PLEDGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASH_PLEDGE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_RENT = new BigDecimal(2);

    private static final String DEFAULT_REFERRALS_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REFERRALS_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_AUDIT_STATUS = 1;
    private static final Integer UPDATED_AUDIT_STATUS = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private OrderFromRepository orderFromRepository;

    @Autowired
    private OrderFromService orderFromService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderFromMockMvc;

    private OrderFrom orderFrom;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrderFromResource orderFromResource = new OrderFromResource(orderFromService);
        this.restOrderFromMockMvc = MockMvcBuilders.standaloneSetup(orderFromResource)
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
    public static OrderFrom createEntity(EntityManager em) {
        OrderFrom orderFrom = new OrderFrom()
                .companyId(DEFAULT_COMPANY_ID)
                .marketId(DEFAULT_MARKET_ID)
                .driverId(DEFAULT_DRIVER_ID)
                .productType(DEFAULT_PRODUCT_TYPE)
                .fuelType(DEFAULT_FUEL_TYPE)
                .brandName(DEFAULT_BRAND_NAME)
                .type(DEFAULT_TYPE)
                .versions(DEFAULT_VERSIONS)
                .year(DEFAULT_YEAR)
                .refit(DEFAULT_REFIT)
                .salesPlan(DEFAULT_SALES_PLAN)
                .salesPlanId(DEFAULT_SALES_PLAN_ID)
                .downPayment(DEFAULT_DOWN_PAYMENT)
                .monthly(DEFAULT_MONTHLY)
                .periods(DEFAULT_PERIODS)
                .cashPledge(DEFAULT_CASH_PLEDGE)
                .rent(DEFAULT_RENT)
                .referralsIdNumber(DEFAULT_REFERRALS_ID_NUMBER)
                .phone(DEFAULT_PHONE)
                .name(DEFAULT_NAME)
                .auditStatus(DEFAULT_AUDIT_STATUS)
                .status(DEFAULT_STATUS);
        return orderFrom;
    }

    @Before
    public void initTest() {
        orderFrom = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderFrom() throws Exception {
        int databaseSizeBeforeCreate = orderFromRepository.findAll().size();

        // Create the OrderFrom

        restOrderFromMockMvc.perform(post("/api/order-froms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderFrom)))
            .andExpect(status().isCreated());

        // Validate the OrderFrom in the database
        List<OrderFrom> orderFromList = orderFromRepository.findAll();
        assertThat(orderFromList).hasSize(databaseSizeBeforeCreate + 1);
        OrderFrom testOrderFrom = orderFromList.get(orderFromList.size() - 1);
        assertThat(testOrderFrom.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testOrderFrom.getMarketId()).isEqualTo(DEFAULT_MARKET_ID);
        assertThat(testOrderFrom.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testOrderFrom.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testOrderFrom.getFuelType()).isEqualTo(DEFAULT_FUEL_TYPE);
        assertThat(testOrderFrom.getBrandName()).isEqualTo(DEFAULT_BRAND_NAME);
        assertThat(testOrderFrom.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testOrderFrom.getVersions()).isEqualTo(DEFAULT_VERSIONS);
        assertThat(testOrderFrom.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testOrderFrom.getRefit()).isEqualTo(DEFAULT_REFIT);
        assertThat(testOrderFrom.getSalesPlan()).isEqualTo(DEFAULT_SALES_PLAN);
        assertThat(testOrderFrom.getSalesPlanId()).isEqualTo(DEFAULT_SALES_PLAN_ID);
        assertThat(testOrderFrom.getDownPayment()).isEqualTo(DEFAULT_DOWN_PAYMENT);
        assertThat(testOrderFrom.getMonthly()).isEqualTo(DEFAULT_MONTHLY);
        assertThat(testOrderFrom.getPeriods()).isEqualTo(DEFAULT_PERIODS);
        assertThat(testOrderFrom.getCashPledge()).isEqualTo(DEFAULT_CASH_PLEDGE);
        assertThat(testOrderFrom.getRent()).isEqualTo(DEFAULT_RENT);
        assertThat(testOrderFrom.getReferralsIdNumber()).isEqualTo(DEFAULT_REFERRALS_ID_NUMBER);
        assertThat(testOrderFrom.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOrderFrom.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrderFrom.getAuditStatus()).isEqualTo(DEFAULT_AUDIT_STATUS);
        assertThat(testOrderFrom.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createOrderFromWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderFromRepository.findAll().size();

        // Create the OrderFrom with an existing ID
        OrderFrom existingOrderFrom = new OrderFrom();
        existingOrderFrom.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderFromMockMvc.perform(post("/api/order-froms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingOrderFrom)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<OrderFrom> orderFromList = orderFromRepository.findAll();
        assertThat(orderFromList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderFroms() throws Exception {
        // Initialize the database
        orderFromRepository.saveAndFlush(orderFrom);

        // Get all the orderFromList
        restOrderFromMockMvc.perform(get("/api/order-froms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderFrom.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].marketId").value(hasItem(DEFAULT_MARKET_ID.intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fuelType").value(hasItem(DEFAULT_FUEL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].brandName").value(hasItem(DEFAULT_BRAND_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].versions").value(hasItem(DEFAULT_VERSIONS)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.toString())))
            .andExpect(jsonPath("$.[*].refit").value(hasItem(DEFAULT_REFIT)))
            .andExpect(jsonPath("$.[*].salesPlan").value(hasItem(DEFAULT_SALES_PLAN.toString())))
            .andExpect(jsonPath("$.[*].salesPlanId").value(hasItem(DEFAULT_SALES_PLAN_ID.toString())))
            .andExpect(jsonPath("$.[*].downPayment").value(hasItem(DEFAULT_DOWN_PAYMENT.intValue())))
            .andExpect(jsonPath("$.[*].monthly").value(hasItem(DEFAULT_MONTHLY.intValue())))
            .andExpect(jsonPath("$.[*].periods").value(hasItem(DEFAULT_PERIODS)))
            .andExpect(jsonPath("$.[*].cashPledge").value(hasItem(DEFAULT_CASH_PLEDGE.intValue())))
            .andExpect(jsonPath("$.[*].rent").value(hasItem(DEFAULT_RENT.intValue())))
            .andExpect(jsonPath("$.[*].referralsIdNumber").value(hasItem(DEFAULT_REFERRALS_ID_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getOrderFrom() throws Exception {
        // Initialize the database
        orderFromRepository.saveAndFlush(orderFrom);

        // Get the orderFrom
        restOrderFromMockMvc.perform(get("/api/order-froms/{id}", orderFrom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderFrom.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.marketId").value(DEFAULT_MARKET_ID.intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.fuelType").value(DEFAULT_FUEL_TYPE.toString()))
            .andExpect(jsonPath("$.brandName").value(DEFAULT_BRAND_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.versions").value(DEFAULT_VERSIONS))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.toString()))
            .andExpect(jsonPath("$.refit").value(DEFAULT_REFIT))
            .andExpect(jsonPath("$.salesPlan").value(DEFAULT_SALES_PLAN.toString()))
            .andExpect(jsonPath("$.salesPlanId").value(DEFAULT_SALES_PLAN_ID.toString()))
            .andExpect(jsonPath("$.downPayment").value(DEFAULT_DOWN_PAYMENT.intValue()))
            .andExpect(jsonPath("$.monthly").value(DEFAULT_MONTHLY.intValue()))
            .andExpect(jsonPath("$.periods").value(DEFAULT_PERIODS))
            .andExpect(jsonPath("$.cashPledge").value(DEFAULT_CASH_PLEDGE.intValue()))
            .andExpect(jsonPath("$.rent").value(DEFAULT_RENT.intValue()))
            .andExpect(jsonPath("$.referralsIdNumber").value(DEFAULT_REFERRALS_ID_NUMBER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingOrderFrom() throws Exception {
        // Get the orderFrom
        restOrderFromMockMvc.perform(get("/api/order-froms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderFrom() throws Exception {
        // Initialize the database
        orderFromService.save(orderFrom);

        int databaseSizeBeforeUpdate = orderFromRepository.findAll().size();

        // Update the orderFrom
        OrderFrom updatedOrderFrom = orderFromRepository.findOne(orderFrom.getId());
        updatedOrderFrom
                .companyId(UPDATED_COMPANY_ID)
                .marketId(UPDATED_MARKET_ID)
                .driverId(UPDATED_DRIVER_ID)
                .productType(UPDATED_PRODUCT_TYPE)
                .fuelType(UPDATED_FUEL_TYPE)
                .brandName(UPDATED_BRAND_NAME)
                .type(UPDATED_TYPE)
                .versions(UPDATED_VERSIONS)
                .year(UPDATED_YEAR)
                .refit(UPDATED_REFIT)
                .salesPlan(UPDATED_SALES_PLAN)
                .salesPlanId(UPDATED_SALES_PLAN_ID)
                .downPayment(UPDATED_DOWN_PAYMENT)
                .monthly(UPDATED_MONTHLY)
                .periods(UPDATED_PERIODS)
                .cashPledge(UPDATED_CASH_PLEDGE)
                .rent(UPDATED_RENT)
                .referralsIdNumber(UPDATED_REFERRALS_ID_NUMBER)
                .phone(UPDATED_PHONE)
                .name(UPDATED_NAME)
                .auditStatus(UPDATED_AUDIT_STATUS)
                .status(UPDATED_STATUS);

        restOrderFromMockMvc.perform(put("/api/order-froms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderFrom)))
            .andExpect(status().isOk());

        // Validate the OrderFrom in the database
        List<OrderFrom> orderFromList = orderFromRepository.findAll();
        assertThat(orderFromList).hasSize(databaseSizeBeforeUpdate);
        OrderFrom testOrderFrom = orderFromList.get(orderFromList.size() - 1);
        assertThat(testOrderFrom.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testOrderFrom.getMarketId()).isEqualTo(UPDATED_MARKET_ID);
        assertThat(testOrderFrom.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testOrderFrom.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testOrderFrom.getFuelType()).isEqualTo(UPDATED_FUEL_TYPE);
        assertThat(testOrderFrom.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
        assertThat(testOrderFrom.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOrderFrom.getVersions()).isEqualTo(UPDATED_VERSIONS);
        assertThat(testOrderFrom.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testOrderFrom.getRefit()).isEqualTo(UPDATED_REFIT);
        assertThat(testOrderFrom.getSalesPlan()).isEqualTo(UPDATED_SALES_PLAN);
        assertThat(testOrderFrom.getSalesPlanId()).isEqualTo(UPDATED_SALES_PLAN_ID);
        assertThat(testOrderFrom.getDownPayment()).isEqualTo(UPDATED_DOWN_PAYMENT);
        assertThat(testOrderFrom.getMonthly()).isEqualTo(UPDATED_MONTHLY);
        assertThat(testOrderFrom.getPeriods()).isEqualTo(UPDATED_PERIODS);
        assertThat(testOrderFrom.getCashPledge()).isEqualTo(UPDATED_CASH_PLEDGE);
        assertThat(testOrderFrom.getRent()).isEqualTo(UPDATED_RENT);
        assertThat(testOrderFrom.getReferralsIdNumber()).isEqualTo(UPDATED_REFERRALS_ID_NUMBER);
        assertThat(testOrderFrom.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOrderFrom.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrderFrom.getAuditStatus()).isEqualTo(UPDATED_AUDIT_STATUS);
        assertThat(testOrderFrom.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderFrom() throws Exception {
        int databaseSizeBeforeUpdate = orderFromRepository.findAll().size();

        // Create the OrderFrom

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrderFromMockMvc.perform(put("/api/order-froms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderFrom)))
            .andExpect(status().isCreated());

        // Validate the OrderFrom in the database
        List<OrderFrom> orderFromList = orderFromRepository.findAll();
        assertThat(orderFromList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrderFrom() throws Exception {
        // Initialize the database
        orderFromService.save(orderFrom);

        int databaseSizeBeforeDelete = orderFromRepository.findAll().size();

        // Get the orderFrom
        restOrderFromMockMvc.perform(delete("/api/order-froms/{id}", orderFrom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderFrom> orderFromList = orderFromRepository.findAll();
        assertThat(orderFromList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderFrom.class);
    }
}

package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.OverdueManagement;
import com.duma.repository.OverdueManagementRepository;
import com.duma.service.OverdueManagementService;
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
 * Test class for the OverdueManagementResource REST controller.
 *
 * @see OverdueManagementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class OverdueManagementResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_REPAYMENT_TIME = 1L;
    private static final Long UPDATED_REPAYMENT_TIME = 2L;

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_OVERDUE_DATA = 1;
    private static final Integer UPDATED_OVERDUE_DATA = 2;

    private static final BigDecimal DEFAULT_OVERDUE_INTEREST = new BigDecimal(1);
    private static final BigDecimal UPDATED_OVERDUE_INTEREST = new BigDecimal(2);

    private static final Long DEFAULT_NEXT_MONEY_DATA = 1L;
    private static final Long UPDATED_NEXT_MONEY_DATA = 2L;

    private static final Integer DEFAULT_POSTPONE_DATA = 1;
    private static final Integer UPDATED_POSTPONE_DATA = 2;

    private static final String DEFAULT_INFORM_VEHICLE = "AAAAAAAAAA";
    private static final String UPDATED_INFORM_VEHICLE = "BBBBBBBBBB";

    private static final Long DEFAULT_RECYCLE_VEHICLE = 1L;
    private static final Long UPDATED_RECYCLE_VEHICLE = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private OverdueManagementRepository overdueManagementRepository;

    @Autowired
    private OverdueManagementService overdueManagementService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOverdueManagementMockMvc;

    private OverdueManagement overdueManagement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OverdueManagementResource overdueManagementResource = new OverdueManagementResource(overdueManagementService);
        this.restOverdueManagementMockMvc = MockMvcBuilders.standaloneSetup(overdueManagementResource)
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
    public static OverdueManagement createEntity(EntityManager em) {
        OverdueManagement overdueManagement = new OverdueManagement()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .orderId(DEFAULT_ORDER_ID)
                .repaymentTime(DEFAULT_REPAYMENT_TIME)
                .money(DEFAULT_MONEY)
                .overdueData(DEFAULT_OVERDUE_DATA)
                .overdueInterest(DEFAULT_OVERDUE_INTEREST)
                .nextMoneyData(DEFAULT_NEXT_MONEY_DATA)
                .postponeData(DEFAULT_POSTPONE_DATA)
                .informVehicle(DEFAULT_INFORM_VEHICLE)
                .recycleVehicle(DEFAULT_RECYCLE_VEHICLE)
                .remark(DEFAULT_REMARK);
        return overdueManagement;
    }

    @Before
    public void initTest() {
        overdueManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createOverdueManagement() throws Exception {
        int databaseSizeBeforeCreate = overdueManagementRepository.findAll().size();

        // Create the OverdueManagement

        restOverdueManagementMockMvc.perform(post("/api/overdue-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueManagement)))
            .andExpect(status().isCreated());

        // Validate the OverdueManagement in the database
        List<OverdueManagement> overdueManagementList = overdueManagementRepository.findAll();
        assertThat(overdueManagementList).hasSize(databaseSizeBeforeCreate + 1);
        OverdueManagement testOverdueManagement = overdueManagementList.get(overdueManagementList.size() - 1);
        assertThat(testOverdueManagement.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testOverdueManagement.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testOverdueManagement.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testOverdueManagement.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testOverdueManagement.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testOverdueManagement.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOverdueManagement.getRepaymentTime()).isEqualTo(DEFAULT_REPAYMENT_TIME);
        assertThat(testOverdueManagement.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testOverdueManagement.getOverdueData()).isEqualTo(DEFAULT_OVERDUE_DATA);
        assertThat(testOverdueManagement.getOverdueInterest()).isEqualTo(DEFAULT_OVERDUE_INTEREST);
        assertThat(testOverdueManagement.getNextMoneyData()).isEqualTo(DEFAULT_NEXT_MONEY_DATA);
        assertThat(testOverdueManagement.getPostponeData()).isEqualTo(DEFAULT_POSTPONE_DATA);
        assertThat(testOverdueManagement.getInformVehicle()).isEqualTo(DEFAULT_INFORM_VEHICLE);
        assertThat(testOverdueManagement.getRecycleVehicle()).isEqualTo(DEFAULT_RECYCLE_VEHICLE);
        assertThat(testOverdueManagement.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createOverdueManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = overdueManagementRepository.findAll().size();

        // Create the OverdueManagement with an existing ID
        OverdueManagement existingOverdueManagement = new OverdueManagement();
        existingOverdueManagement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOverdueManagementMockMvc.perform(post("/api/overdue-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingOverdueManagement)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<OverdueManagement> overdueManagementList = overdueManagementRepository.findAll();
        assertThat(overdueManagementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOverdueManagements() throws Exception {
        // Initialize the database
        overdueManagementRepository.saveAndFlush(overdueManagement);

        // Get all the overdueManagementList
        restOverdueManagementMockMvc.perform(get("/api/overdue-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(overdueManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].repaymentTime").value(hasItem(DEFAULT_REPAYMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].overdueData").value(hasItem(DEFAULT_OVERDUE_DATA)))
            .andExpect(jsonPath("$.[*].overdueInterest").value(hasItem(DEFAULT_OVERDUE_INTEREST.intValue())))
            .andExpect(jsonPath("$.[*].nextMoneyData").value(hasItem(DEFAULT_NEXT_MONEY_DATA.intValue())))
            .andExpect(jsonPath("$.[*].postponeData").value(hasItem(DEFAULT_POSTPONE_DATA)))
            .andExpect(jsonPath("$.[*].informVehicle").value(hasItem(DEFAULT_INFORM_VEHICLE.toString())))
            .andExpect(jsonPath("$.[*].recycleVehicle").value(hasItem(DEFAULT_RECYCLE_VEHICLE.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getOverdueManagement() throws Exception {
        // Initialize the database
        overdueManagementRepository.saveAndFlush(overdueManagement);

        // Get the overdueManagement
        restOverdueManagementMockMvc.perform(get("/api/overdue-managements/{id}", overdueManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(overdueManagement.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.repaymentTime").value(DEFAULT_REPAYMENT_TIME.intValue()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.overdueData").value(DEFAULT_OVERDUE_DATA))
            .andExpect(jsonPath("$.overdueInterest").value(DEFAULT_OVERDUE_INTEREST.intValue()))
            .andExpect(jsonPath("$.nextMoneyData").value(DEFAULT_NEXT_MONEY_DATA.intValue()))
            .andExpect(jsonPath("$.postponeData").value(DEFAULT_POSTPONE_DATA))
            .andExpect(jsonPath("$.informVehicle").value(DEFAULT_INFORM_VEHICLE.toString()))
            .andExpect(jsonPath("$.recycleVehicle").value(DEFAULT_RECYCLE_VEHICLE.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOverdueManagement() throws Exception {
        // Get the overdueManagement
        restOverdueManagementMockMvc.perform(get("/api/overdue-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOverdueManagement() throws Exception {
        // Initialize the database
        overdueManagementService.save(overdueManagement);

        int databaseSizeBeforeUpdate = overdueManagementRepository.findAll().size();

        // Update the overdueManagement
        OverdueManagement updatedOverdueManagement = overdueManagementRepository.findOne(overdueManagement.getId());
        updatedOverdueManagement
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .orderId(UPDATED_ORDER_ID)
                .repaymentTime(UPDATED_REPAYMENT_TIME)
                .money(UPDATED_MONEY)
                .overdueData(UPDATED_OVERDUE_DATA)
                .overdueInterest(UPDATED_OVERDUE_INTEREST)
                .nextMoneyData(UPDATED_NEXT_MONEY_DATA)
                .postponeData(UPDATED_POSTPONE_DATA)
                .informVehicle(UPDATED_INFORM_VEHICLE)
                .recycleVehicle(UPDATED_RECYCLE_VEHICLE)
                .remark(UPDATED_REMARK);

        restOverdueManagementMockMvc.perform(put("/api/overdue-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOverdueManagement)))
            .andExpect(status().isOk());

        // Validate the OverdueManagement in the database
        List<OverdueManagement> overdueManagementList = overdueManagementRepository.findAll();
        assertThat(overdueManagementList).hasSize(databaseSizeBeforeUpdate);
        OverdueManagement testOverdueManagement = overdueManagementList.get(overdueManagementList.size() - 1);
        assertThat(testOverdueManagement.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testOverdueManagement.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testOverdueManagement.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testOverdueManagement.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testOverdueManagement.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testOverdueManagement.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOverdueManagement.getRepaymentTime()).isEqualTo(UPDATED_REPAYMENT_TIME);
        assertThat(testOverdueManagement.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testOverdueManagement.getOverdueData()).isEqualTo(UPDATED_OVERDUE_DATA);
        assertThat(testOverdueManagement.getOverdueInterest()).isEqualTo(UPDATED_OVERDUE_INTEREST);
        assertThat(testOverdueManagement.getNextMoneyData()).isEqualTo(UPDATED_NEXT_MONEY_DATA);
        assertThat(testOverdueManagement.getPostponeData()).isEqualTo(UPDATED_POSTPONE_DATA);
        assertThat(testOverdueManagement.getInformVehicle()).isEqualTo(UPDATED_INFORM_VEHICLE);
        assertThat(testOverdueManagement.getRecycleVehicle()).isEqualTo(UPDATED_RECYCLE_VEHICLE);
        assertThat(testOverdueManagement.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingOverdueManagement() throws Exception {
        int databaseSizeBeforeUpdate = overdueManagementRepository.findAll().size();

        // Create the OverdueManagement

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOverdueManagementMockMvc.perform(put("/api/overdue-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(overdueManagement)))
            .andExpect(status().isCreated());

        // Validate the OverdueManagement in the database
        List<OverdueManagement> overdueManagementList = overdueManagementRepository.findAll();
        assertThat(overdueManagementList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOverdueManagement() throws Exception {
        // Initialize the database
        overdueManagementService.save(overdueManagement);

        int databaseSizeBeforeDelete = overdueManagementRepository.findAll().size();

        // Get the overdueManagement
        restOverdueManagementMockMvc.perform(delete("/api/overdue-managements/{id}", overdueManagement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OverdueManagement> overdueManagementList = overdueManagementRepository.findAll();
        assertThat(overdueManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OverdueManagement.class);
    }
}

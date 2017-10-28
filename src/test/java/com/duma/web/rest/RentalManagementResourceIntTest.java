package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.RentalManagement;
import com.duma.repository.RentalManagementRepository;
import com.duma.service.RentalManagementService;
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
 * Test class for the RentalManagementResource REST controller.
 *
 * @see RentalManagementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class RentalManagementResourceIntTest {

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

    private static final Long DEFAULT_RENT_TIME = 1L;
    private static final Long UPDATED_RENT_TIME = 2L;

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_OVERDUE = 1;
    private static final Integer UPDATED_OVERDUE = 2;

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

    private static final Integer DEFAULT_WHETHER_GATHER = 1;
    private static final Integer UPDATED_WHETHER_GATHER = 2;

    @Autowired
    private RentalManagementRepository rentalManagementRepository;

    @Autowired
    private RentalManagementService rentalManagementService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRentalManagementMockMvc;

    private RentalManagement rentalManagement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RentalManagementResource rentalManagementResource = new RentalManagementResource(rentalManagementService);
        this.restRentalManagementMockMvc = MockMvcBuilders.standaloneSetup(rentalManagementResource)
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
    public static RentalManagement createEntity(EntityManager em) {
        RentalManagement rentalManagement = new RentalManagement()
                .driverId(DEFAULT_DRIVER_ID)
                .orderId(DEFAULT_ORDER_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .rentTime(DEFAULT_RENT_TIME)
                .money(DEFAULT_MONEY)
                .overdue(DEFAULT_OVERDUE)
                .remark(DEFAULT_REMARK)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .nextRentTime(DEFAULT_NEXT_RENT_TIME)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME)
                .whetherGather(DEFAULT_WHETHER_GATHER);
        return rentalManagement;
    }

    @Before
    public void initTest() {
        rentalManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createRentalManagement() throws Exception {
        int databaseSizeBeforeCreate = rentalManagementRepository.findAll().size();

        // Create the RentalManagement

        restRentalManagementMockMvc.perform(post("/api/rental-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rentalManagement)))
            .andExpect(status().isCreated());

        // Validate the RentalManagement in the database
        List<RentalManagement> rentalManagementList = rentalManagementRepository.findAll();
        assertThat(rentalManagementList).hasSize(databaseSizeBeforeCreate + 1);
        RentalManagement testRentalManagement = rentalManagementList.get(rentalManagementList.size() - 1);
        assertThat(testRentalManagement.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testRentalManagement.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testRentalManagement.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testRentalManagement.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testRentalManagement.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testRentalManagement.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testRentalManagement.getRentTime()).isEqualTo(DEFAULT_RENT_TIME);
        assertThat(testRentalManagement.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testRentalManagement.getOverdue()).isEqualTo(DEFAULT_OVERDUE);
        assertThat(testRentalManagement.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testRentalManagement.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testRentalManagement.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testRentalManagement.getNextRentTime()).isEqualTo(DEFAULT_NEXT_RENT_TIME);
        assertThat(testRentalManagement.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testRentalManagement.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testRentalManagement.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testRentalManagement.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
        assertThat(testRentalManagement.getWhetherGather()).isEqualTo(DEFAULT_WHETHER_GATHER);
    }

    @Test
    @Transactional
    public void createRentalManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rentalManagementRepository.findAll().size();

        // Create the RentalManagement with an existing ID
        RentalManagement existingRentalManagement = new RentalManagement();
        existingRentalManagement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRentalManagementMockMvc.perform(post("/api/rental-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRentalManagement)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RentalManagement> rentalManagementList = rentalManagementRepository.findAll();
        assertThat(rentalManagementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRentalManagements() throws Exception {
        // Initialize the database
        rentalManagementRepository.saveAndFlush(rentalManagement);

        // Get all the rentalManagementList
        restRentalManagementMockMvc.perform(get("/api/rental-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rentalManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].rentTime").value(hasItem(DEFAULT_RENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].overdue").value(hasItem(DEFAULT_OVERDUE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].nextRentTime").value(hasItem(DEFAULT_NEXT_RENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].whetherGather").value(hasItem(DEFAULT_WHETHER_GATHER)));
    }

    @Test
    @Transactional
    public void getRentalManagement() throws Exception {
        // Initialize the database
        rentalManagementRepository.saveAndFlush(rentalManagement);

        // Get the rentalManagement
        restRentalManagementMockMvc.perform(get("/api/rental-managements/{id}", rentalManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rentalManagement.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.rentTime").value(DEFAULT_RENT_TIME.intValue()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.overdue").value(DEFAULT_OVERDUE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.nextRentTime").value(DEFAULT_NEXT_RENT_TIME.intValue()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()))
            .andExpect(jsonPath("$.whetherGather").value(DEFAULT_WHETHER_GATHER));
    }

    @Test
    @Transactional
    public void getNonExistingRentalManagement() throws Exception {
        // Get the rentalManagement
        restRentalManagementMockMvc.perform(get("/api/rental-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRentalManagement() throws Exception {
        // Initialize the database
        rentalManagementService.save(rentalManagement);

        int databaseSizeBeforeUpdate = rentalManagementRepository.findAll().size();

        // Update the rentalManagement
        RentalManagement updatedRentalManagement = rentalManagementRepository.findOne(rentalManagement.getId());
        updatedRentalManagement
                .driverId(UPDATED_DRIVER_ID)
                .orderId(UPDATED_ORDER_ID)
                .companyId(UPDATED_COMPANY_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .rentTime(UPDATED_RENT_TIME)
                .money(UPDATED_MONEY)
                .overdue(UPDATED_OVERDUE)
                .remark(UPDATED_REMARK)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .nextRentTime(UPDATED_NEXT_RENT_TIME)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME)
                .whetherGather(UPDATED_WHETHER_GATHER);

        restRentalManagementMockMvc.perform(put("/api/rental-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRentalManagement)))
            .andExpect(status().isOk());

        // Validate the RentalManagement in the database
        List<RentalManagement> rentalManagementList = rentalManagementRepository.findAll();
        assertThat(rentalManagementList).hasSize(databaseSizeBeforeUpdate);
        RentalManagement testRentalManagement = rentalManagementList.get(rentalManagementList.size() - 1);
        assertThat(testRentalManagement.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testRentalManagement.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testRentalManagement.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testRentalManagement.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testRentalManagement.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testRentalManagement.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testRentalManagement.getRentTime()).isEqualTo(UPDATED_RENT_TIME);
        assertThat(testRentalManagement.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testRentalManagement.getOverdue()).isEqualTo(UPDATED_OVERDUE);
        assertThat(testRentalManagement.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testRentalManagement.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testRentalManagement.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testRentalManagement.getNextRentTime()).isEqualTo(UPDATED_NEXT_RENT_TIME);
        assertThat(testRentalManagement.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testRentalManagement.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testRentalManagement.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testRentalManagement.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
        assertThat(testRentalManagement.getWhetherGather()).isEqualTo(UPDATED_WHETHER_GATHER);
    }

    @Test
    @Transactional
    public void updateNonExistingRentalManagement() throws Exception {
        int databaseSizeBeforeUpdate = rentalManagementRepository.findAll().size();

        // Create the RentalManagement

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRentalManagementMockMvc.perform(put("/api/rental-managements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rentalManagement)))
            .andExpect(status().isCreated());

        // Validate the RentalManagement in the database
        List<RentalManagement> rentalManagementList = rentalManagementRepository.findAll();
        assertThat(rentalManagementList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRentalManagement() throws Exception {
        // Initialize the database
        rentalManagementService.save(rentalManagement);

        int databaseSizeBeforeDelete = rentalManagementRepository.findAll().size();

        // Get the rentalManagement
        restRentalManagementMockMvc.perform(delete("/api/rental-managements/{id}", rentalManagement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RentalManagement> rentalManagementList = rentalManagementRepository.findAll();
        assertThat(rentalManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RentalManagement.class);
    }
}

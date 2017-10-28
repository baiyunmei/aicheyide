package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.ForcedSettle;
import com.duma.repository.ForcedSettleRepository;
import com.duma.service.ForcedSettleService;
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
 * Test class for the ForcedSettleResource REST controller.
 *
 * @see ForcedSettleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class ForcedSettleResourceIntTest {

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

    private static final Integer DEFAULT_PERIODS = 1;
    private static final Integer UPDATED_PERIODS = 2;

    private static final BigDecimal DEFAULT_SUM_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUM_MONEY = new BigDecimal(2);

    private static final Integer DEFAULT_NOT_PERIODS = 1;
    private static final Integer UPDATED_NOT_PERIODS = 2;

    private static final BigDecimal DEFAULT_NOT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_NOT_MONEY = new BigDecimal(2);

    private static final String DEFAULT_SETTLE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SETTLE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_RECEIPT_DATE = 1L;
    private static final Long UPDATED_RECEIPT_DATE = 2L;

    private static final Integer DEFAULT_WHETHER_RECYCLE = 1;
    private static final Integer UPDATED_WHETHER_RECYCLE = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private ForcedSettleRepository forcedSettleRepository;

    @Autowired
    private ForcedSettleService forcedSettleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restForcedSettleMockMvc;

    private ForcedSettle forcedSettle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ForcedSettleResource forcedSettleResource = new ForcedSettleResource(forcedSettleService);
        this.restForcedSettleMockMvc = MockMvcBuilders.standaloneSetup(forcedSettleResource)
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
    public static ForcedSettle createEntity(EntityManager em) {
        ForcedSettle forcedSettle = new ForcedSettle()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .periods(DEFAULT_PERIODS)
                .sumMoney(DEFAULT_SUM_MONEY)
                .notPeriods(DEFAULT_NOT_PERIODS)
                .notMoney(DEFAULT_NOT_MONEY)
                .settleType(DEFAULT_SETTLE_TYPE)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .whetherRecycle(DEFAULT_WHETHER_RECYCLE)
                .remark(DEFAULT_REMARK)
                .status(DEFAULT_STATUS);
        return forcedSettle;
    }

    @Before
    public void initTest() {
        forcedSettle = createEntity(em);
    }

    @Test
    @Transactional
    public void createForcedSettle() throws Exception {
        int databaseSizeBeforeCreate = forcedSettleRepository.findAll().size();

        // Create the ForcedSettle

        restForcedSettleMockMvc.perform(post("/api/forced-settles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(forcedSettle)))
            .andExpect(status().isCreated());

        // Validate the ForcedSettle in the database
        List<ForcedSettle> forcedSettleList = forcedSettleRepository.findAll();
        assertThat(forcedSettleList).hasSize(databaseSizeBeforeCreate + 1);
        ForcedSettle testForcedSettle = forcedSettleList.get(forcedSettleList.size() - 1);
        assertThat(testForcedSettle.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testForcedSettle.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testForcedSettle.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testForcedSettle.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testForcedSettle.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testForcedSettle.getPeriods()).isEqualTo(DEFAULT_PERIODS);
        assertThat(testForcedSettle.getSumMoney()).isEqualTo(DEFAULT_SUM_MONEY);
        assertThat(testForcedSettle.getNotPeriods()).isEqualTo(DEFAULT_NOT_PERIODS);
        assertThat(testForcedSettle.getNotMoney()).isEqualTo(DEFAULT_NOT_MONEY);
        assertThat(testForcedSettle.getSettleType()).isEqualTo(DEFAULT_SETTLE_TYPE);
        assertThat(testForcedSettle.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testForcedSettle.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testForcedSettle.getWhetherRecycle()).isEqualTo(DEFAULT_WHETHER_RECYCLE);
        assertThat(testForcedSettle.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testForcedSettle.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createForcedSettleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = forcedSettleRepository.findAll().size();

        // Create the ForcedSettle with an existing ID
        ForcedSettle existingForcedSettle = new ForcedSettle();
        existingForcedSettle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restForcedSettleMockMvc.perform(post("/api/forced-settles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingForcedSettle)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ForcedSettle> forcedSettleList = forcedSettleRepository.findAll();
        assertThat(forcedSettleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllForcedSettles() throws Exception {
        // Initialize the database
        forcedSettleRepository.saveAndFlush(forcedSettle);

        // Get all the forcedSettleList
        restForcedSettleMockMvc.perform(get("/api/forced-settles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(forcedSettle.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].periods").value(hasItem(DEFAULT_PERIODS)))
            .andExpect(jsonPath("$.[*].sumMoney").value(hasItem(DEFAULT_SUM_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].notPeriods").value(hasItem(DEFAULT_NOT_PERIODS)))
            .andExpect(jsonPath("$.[*].notMoney").value(hasItem(DEFAULT_NOT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].settleType").value(hasItem(DEFAULT_SETTLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].whetherRecycle").value(hasItem(DEFAULT_WHETHER_RECYCLE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getForcedSettle() throws Exception {
        // Initialize the database
        forcedSettleRepository.saveAndFlush(forcedSettle);

        // Get the forcedSettle
        restForcedSettleMockMvc.perform(get("/api/forced-settles/{id}", forcedSettle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(forcedSettle.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.periods").value(DEFAULT_PERIODS))
            .andExpect(jsonPath("$.sumMoney").value(DEFAULT_SUM_MONEY.intValue()))
            .andExpect(jsonPath("$.notPeriods").value(DEFAULT_NOT_PERIODS))
            .andExpect(jsonPath("$.notMoney").value(DEFAULT_NOT_MONEY.intValue()))
            .andExpect(jsonPath("$.settleType").value(DEFAULT_SETTLE_TYPE.toString()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.whetherRecycle").value(DEFAULT_WHETHER_RECYCLE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingForcedSettle() throws Exception {
        // Get the forcedSettle
        restForcedSettleMockMvc.perform(get("/api/forced-settles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateForcedSettle() throws Exception {
        // Initialize the database
        forcedSettleService.save(forcedSettle);

        int databaseSizeBeforeUpdate = forcedSettleRepository.findAll().size();

        // Update the forcedSettle
        ForcedSettle updatedForcedSettle = forcedSettleRepository.findOne(forcedSettle.getId());
        updatedForcedSettle
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .periods(UPDATED_PERIODS)
                .sumMoney(UPDATED_SUM_MONEY)
                .notPeriods(UPDATED_NOT_PERIODS)
                .notMoney(UPDATED_NOT_MONEY)
                .settleType(UPDATED_SETTLE_TYPE)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .whetherRecycle(UPDATED_WHETHER_RECYCLE)
                .remark(UPDATED_REMARK)
                .status(UPDATED_STATUS);

        restForcedSettleMockMvc.perform(put("/api/forced-settles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedForcedSettle)))
            .andExpect(status().isOk());

        // Validate the ForcedSettle in the database
        List<ForcedSettle> forcedSettleList = forcedSettleRepository.findAll();
        assertThat(forcedSettleList).hasSize(databaseSizeBeforeUpdate);
        ForcedSettle testForcedSettle = forcedSettleList.get(forcedSettleList.size() - 1);
        assertThat(testForcedSettle.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testForcedSettle.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testForcedSettle.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testForcedSettle.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testForcedSettle.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testForcedSettle.getPeriods()).isEqualTo(UPDATED_PERIODS);
        assertThat(testForcedSettle.getSumMoney()).isEqualTo(UPDATED_SUM_MONEY);
        assertThat(testForcedSettle.getNotPeriods()).isEqualTo(UPDATED_NOT_PERIODS);
        assertThat(testForcedSettle.getNotMoney()).isEqualTo(UPDATED_NOT_MONEY);
        assertThat(testForcedSettle.getSettleType()).isEqualTo(UPDATED_SETTLE_TYPE);
        assertThat(testForcedSettle.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testForcedSettle.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testForcedSettle.getWhetherRecycle()).isEqualTo(UPDATED_WHETHER_RECYCLE);
        assertThat(testForcedSettle.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testForcedSettle.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingForcedSettle() throws Exception {
        int databaseSizeBeforeUpdate = forcedSettleRepository.findAll().size();

        // Create the ForcedSettle

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restForcedSettleMockMvc.perform(put("/api/forced-settles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(forcedSettle)))
            .andExpect(status().isCreated());

        // Validate the ForcedSettle in the database
        List<ForcedSettle> forcedSettleList = forcedSettleRepository.findAll();
        assertThat(forcedSettleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteForcedSettle() throws Exception {
        // Initialize the database
        forcedSettleService.save(forcedSettle);

        int databaseSizeBeforeDelete = forcedSettleRepository.findAll().size();

        // Get the forcedSettle
        restForcedSettleMockMvc.perform(delete("/api/forced-settles/{id}", forcedSettle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ForcedSettle> forcedSettleList = forcedSettleRepository.findAll();
        assertThat(forcedSettleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ForcedSettle.class);
    }
}

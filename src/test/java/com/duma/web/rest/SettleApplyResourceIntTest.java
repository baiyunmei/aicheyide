package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.SettleApply;
import com.duma.repository.SettleApplyRepository;
import com.duma.service.SettleApplyService;
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
 * Test class for the SettleApplyResource REST controller.
 *
 * @see SettleApplyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class SettleApplyResourceIntTest {

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

    private static final String DEFAULT_SETTLE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SETTLE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_RECEIPT_DATE = 1L;
    private static final Long UPDATED_RECEIPT_DATE = 2L;

    private static final Integer DEFAULT_DEDUCT_MARKS = 1;
    private static final Integer UPDATED_DEDUCT_MARKS = 2;

    private static final BigDecimal DEFAULT_FINE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FINE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PENDING = new BigDecimal(1);
    private static final BigDecimal UPDATED_PENDING = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private SettleApplyRepository settleApplyRepository;

    @Autowired
    private SettleApplyService settleApplyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSettleApplyMockMvc;

    private SettleApply settleApply;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SettleApplyResource settleApplyResource = new SettleApplyResource(settleApplyService);
        this.restSettleApplyMockMvc = MockMvcBuilders.standaloneSetup(settleApplyResource)
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
    public static SettleApply createEntity(EntityManager em) {
        SettleApply settleApply = new SettleApply()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .settleType(DEFAULT_SETTLE_TYPE)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .deductMarks(DEFAULT_DEDUCT_MARKS)
                .fine(DEFAULT_FINE)
                .pending(DEFAULT_PENDING)
                .remark(DEFAULT_REMARK)
                .status(DEFAULT_STATUS);
        return settleApply;
    }

    @Before
    public void initTest() {
        settleApply = createEntity(em);
    }

    @Test
    @Transactional
    public void createSettleApply() throws Exception {
        int databaseSizeBeforeCreate = settleApplyRepository.findAll().size();

        // Create the SettleApply

        restSettleApplyMockMvc.perform(post("/api/settle-applies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settleApply)))
            .andExpect(status().isCreated());

        // Validate the SettleApply in the database
        List<SettleApply> settleApplyList = settleApplyRepository.findAll();
        assertThat(settleApplyList).hasSize(databaseSizeBeforeCreate + 1);
        SettleApply testSettleApply = settleApplyList.get(settleApplyList.size() - 1);
        assertThat(testSettleApply.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testSettleApply.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testSettleApply.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testSettleApply.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testSettleApply.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testSettleApply.getSettleType()).isEqualTo(DEFAULT_SETTLE_TYPE);
        assertThat(testSettleApply.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testSettleApply.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testSettleApply.getDeductMarks()).isEqualTo(DEFAULT_DEDUCT_MARKS);
        assertThat(testSettleApply.getFine()).isEqualTo(DEFAULT_FINE);
        assertThat(testSettleApply.getPending()).isEqualTo(DEFAULT_PENDING);
        assertThat(testSettleApply.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSettleApply.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createSettleApplyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = settleApplyRepository.findAll().size();

        // Create the SettleApply with an existing ID
        SettleApply existingSettleApply = new SettleApply();
        existingSettleApply.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSettleApplyMockMvc.perform(post("/api/settle-applies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingSettleApply)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<SettleApply> settleApplyList = settleApplyRepository.findAll();
        assertThat(settleApplyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSettleApplies() throws Exception {
        // Initialize the database
        settleApplyRepository.saveAndFlush(settleApply);

        // Get all the settleApplyList
        restSettleApplyMockMvc.perform(get("/api/settle-applies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(settleApply.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].settleType").value(hasItem(DEFAULT_SETTLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].deductMarks").value(hasItem(DEFAULT_DEDUCT_MARKS)))
            .andExpect(jsonPath("$.[*].fine").value(hasItem(DEFAULT_FINE.intValue())))
            .andExpect(jsonPath("$.[*].pending").value(hasItem(DEFAULT_PENDING.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getSettleApply() throws Exception {
        // Initialize the database
        settleApplyRepository.saveAndFlush(settleApply);

        // Get the settleApply
        restSettleApplyMockMvc.perform(get("/api/settle-applies/{id}", settleApply.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(settleApply.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.settleType").value(DEFAULT_SETTLE_TYPE.toString()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.deductMarks").value(DEFAULT_DEDUCT_MARKS))
            .andExpect(jsonPath("$.fine").value(DEFAULT_FINE.intValue()))
            .andExpect(jsonPath("$.pending").value(DEFAULT_PENDING.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingSettleApply() throws Exception {
        // Get the settleApply
        restSettleApplyMockMvc.perform(get("/api/settle-applies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSettleApply() throws Exception {
        // Initialize the database
        settleApplyService.save(settleApply);

        int databaseSizeBeforeUpdate = settleApplyRepository.findAll().size();

        // Update the settleApply
        SettleApply updatedSettleApply = settleApplyRepository.findOne(settleApply.getId());
        updatedSettleApply
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .settleType(UPDATED_SETTLE_TYPE)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .deductMarks(UPDATED_DEDUCT_MARKS)
                .fine(UPDATED_FINE)
                .pending(UPDATED_PENDING)
                .remark(UPDATED_REMARK)
                .status(UPDATED_STATUS);

        restSettleApplyMockMvc.perform(put("/api/settle-applies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSettleApply)))
            .andExpect(status().isOk());

        // Validate the SettleApply in the database
        List<SettleApply> settleApplyList = settleApplyRepository.findAll();
        assertThat(settleApplyList).hasSize(databaseSizeBeforeUpdate);
        SettleApply testSettleApply = settleApplyList.get(settleApplyList.size() - 1);
        assertThat(testSettleApply.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testSettleApply.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testSettleApply.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testSettleApply.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testSettleApply.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testSettleApply.getSettleType()).isEqualTo(UPDATED_SETTLE_TYPE);
        assertThat(testSettleApply.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testSettleApply.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testSettleApply.getDeductMarks()).isEqualTo(UPDATED_DEDUCT_MARKS);
        assertThat(testSettleApply.getFine()).isEqualTo(UPDATED_FINE);
        assertThat(testSettleApply.getPending()).isEqualTo(UPDATED_PENDING);
        assertThat(testSettleApply.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSettleApply.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSettleApply() throws Exception {
        int databaseSizeBeforeUpdate = settleApplyRepository.findAll().size();

        // Create the SettleApply

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSettleApplyMockMvc.perform(put("/api/settle-applies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settleApply)))
            .andExpect(status().isCreated());

        // Validate the SettleApply in the database
        List<SettleApply> settleApplyList = settleApplyRepository.findAll();
        assertThat(settleApplyList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSettleApply() throws Exception {
        // Initialize the database
        settleApplyService.save(settleApply);

        int databaseSizeBeforeDelete = settleApplyRepository.findAll().size();

        // Get the settleApply
        restSettleApplyMockMvc.perform(delete("/api/settle-applies/{id}", settleApply.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SettleApply> settleApplyList = settleApplyRepository.findAll();
        assertThat(settleApplyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SettleApply.class);
    }
}

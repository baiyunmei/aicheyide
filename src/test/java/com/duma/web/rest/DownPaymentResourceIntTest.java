package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.DownPayment;
import com.duma.repository.DownPaymentRepository;
import com.duma.service.DownPaymentService;
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
 * Test class for the DownPaymentResource REST controller.
 *
 * @see DownPaymentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class DownPaymentResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final BigDecimal DEFAULT_RECEIPT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_RECEIPT_MONEY = new BigDecimal(2);

    private static final String DEFAULT_RECEIPT_WATER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_WATER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIPT = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT = "BBBBBBBBBB";

    @Autowired
    private DownPaymentRepository downPaymentRepository;

    @Autowired
    private DownPaymentService downPaymentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDownPaymentMockMvc;

    private DownPayment downPayment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DownPaymentResource downPaymentResource = new DownPaymentResource(downPaymentService);
        this.restDownPaymentMockMvc = MockMvcBuilders.standaloneSetup(downPaymentResource)
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
    public static DownPayment createEntity(EntityManager em) {
        DownPayment downPayment = new DownPayment()
                .companyId(DEFAULT_COMPANY_ID)
                .orderId(DEFAULT_ORDER_ID)
                .receiptMoney(DEFAULT_RECEIPT_MONEY)
                .receiptWater(DEFAULT_RECEIPT_WATER)
                .accountNumber(DEFAULT_ACCOUNT_NUMBER)
                .receipt(DEFAULT_RECEIPT);
        return downPayment;
    }

    @Before
    public void initTest() {
        downPayment = createEntity(em);
    }

    @Test
    @Transactional
    public void createDownPayment() throws Exception {
        int databaseSizeBeforeCreate = downPaymentRepository.findAll().size();

        // Create the DownPayment

        restDownPaymentMockMvc.perform(post("/api/down-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downPayment)))
            .andExpect(status().isCreated());

        // Validate the DownPayment in the database
        List<DownPayment> downPaymentList = downPaymentRepository.findAll();
        assertThat(downPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        DownPayment testDownPayment = downPaymentList.get(downPaymentList.size() - 1);
        assertThat(testDownPayment.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testDownPayment.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testDownPayment.getReceiptMoney()).isEqualTo(DEFAULT_RECEIPT_MONEY);
        assertThat(testDownPayment.getReceiptWater()).isEqualTo(DEFAULT_RECEIPT_WATER);
        assertThat(testDownPayment.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testDownPayment.getReceipt()).isEqualTo(DEFAULT_RECEIPT);
    }

    @Test
    @Transactional
    public void createDownPaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = downPaymentRepository.findAll().size();

        // Create the DownPayment with an existing ID
        DownPayment existingDownPayment = new DownPayment();
        existingDownPayment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDownPaymentMockMvc.perform(post("/api/down-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDownPayment)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DownPayment> downPaymentList = downPaymentRepository.findAll();
        assertThat(downPaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDownPayments() throws Exception {
        // Initialize the database
        downPaymentRepository.saveAndFlush(downPayment);

        // Get all the downPaymentList
        restDownPaymentMockMvc.perform(get("/api/down-payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(downPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptMoney").value(hasItem(DEFAULT_RECEIPT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].receiptWater").value(hasItem(DEFAULT_RECEIPT_WATER.toString())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receipt").value(hasItem(DEFAULT_RECEIPT.toString())));
    }

    @Test
    @Transactional
    public void getDownPayment() throws Exception {
        // Initialize the database
        downPaymentRepository.saveAndFlush(downPayment);

        // Get the downPayment
        restDownPaymentMockMvc.perform(get("/api/down-payments/{id}", downPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(downPayment.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.receiptMoney").value(DEFAULT_RECEIPT_MONEY.intValue()))
            .andExpect(jsonPath("$.receiptWater").value(DEFAULT_RECEIPT_WATER.toString()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.receipt").value(DEFAULT_RECEIPT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDownPayment() throws Exception {
        // Get the downPayment
        restDownPaymentMockMvc.perform(get("/api/down-payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDownPayment() throws Exception {
        // Initialize the database
        downPaymentService.save(downPayment);

        int databaseSizeBeforeUpdate = downPaymentRepository.findAll().size();

        // Update the downPayment
        DownPayment updatedDownPayment = downPaymentRepository.findOne(downPayment.getId());
        updatedDownPayment
                .companyId(UPDATED_COMPANY_ID)
                .orderId(UPDATED_ORDER_ID)
                .receiptMoney(UPDATED_RECEIPT_MONEY)
                .receiptWater(UPDATED_RECEIPT_WATER)
                .accountNumber(UPDATED_ACCOUNT_NUMBER)
                .receipt(UPDATED_RECEIPT);

        restDownPaymentMockMvc.perform(put("/api/down-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDownPayment)))
            .andExpect(status().isOk());

        // Validate the DownPayment in the database
        List<DownPayment> downPaymentList = downPaymentRepository.findAll();
        assertThat(downPaymentList).hasSize(databaseSizeBeforeUpdate);
        DownPayment testDownPayment = downPaymentList.get(downPaymentList.size() - 1);
        assertThat(testDownPayment.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testDownPayment.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testDownPayment.getReceiptMoney()).isEqualTo(UPDATED_RECEIPT_MONEY);
        assertThat(testDownPayment.getReceiptWater()).isEqualTo(UPDATED_RECEIPT_WATER);
        assertThat(testDownPayment.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testDownPayment.getReceipt()).isEqualTo(UPDATED_RECEIPT);
    }

    @Test
    @Transactional
    public void updateNonExistingDownPayment() throws Exception {
        int databaseSizeBeforeUpdate = downPaymentRepository.findAll().size();

        // Create the DownPayment

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDownPaymentMockMvc.perform(put("/api/down-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downPayment)))
            .andExpect(status().isCreated());

        // Validate the DownPayment in the database
        List<DownPayment> downPaymentList = downPaymentRepository.findAll();
        assertThat(downPaymentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDownPayment() throws Exception {
        // Initialize the database
        downPaymentService.save(downPayment);

        int databaseSizeBeforeDelete = downPaymentRepository.findAll().size();

        // Get the downPayment
        restDownPaymentMockMvc.perform(delete("/api/down-payments/{id}", downPayment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DownPayment> downPaymentList = downPaymentRepository.findAll();
        assertThat(downPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DownPayment.class);
    }
}

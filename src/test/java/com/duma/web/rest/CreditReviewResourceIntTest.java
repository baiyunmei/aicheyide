package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.CreditReview;
import com.duma.repository.CreditReviewRepository;
import com.duma.service.CreditReviewService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CreditReviewResource REST controller.
 *
 * @see CreditReviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class CreditReviewResourceIntTest {

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final String DEFAULT_AUDIT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_AUDIT = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT = "BBBBBBBBBB";

    private static final Long DEFAULT_AUDIT_ID = 1L;
    private static final Long UPDATED_AUDIT_ID = 2L;

    private static final Long DEFAULT_AUDIT_TIME = 1L;
    private static final Long UPDATED_AUDIT_TIME = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private CreditReviewRepository creditReviewRepository;

    @Autowired
    private CreditReviewService creditReviewService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCreditReviewMockMvc;

    private CreditReview creditReview;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CreditReviewResource creditReviewResource = new CreditReviewResource(creditReviewService);
        this.restCreditReviewMockMvc = MockMvcBuilders.standaloneSetup(creditReviewResource)
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
    public static CreditReview createEntity(EntityManager em) {
        CreditReview creditReview = new CreditReview()
                .orderId(DEFAULT_ORDER_ID)
                .driverId(DEFAULT_DRIVER_ID)
                .auditResult(DEFAULT_AUDIT_RESULT)
                .audit(DEFAULT_AUDIT)
                .auditId(DEFAULT_AUDIT_ID)
                .auditTime(DEFAULT_AUDIT_TIME)
                .remark(DEFAULT_REMARK);
        return creditReview;
    }

    @Before
    public void initTest() {
        creditReview = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditReview() throws Exception {
        int databaseSizeBeforeCreate = creditReviewRepository.findAll().size();

        // Create the CreditReview

        restCreditReviewMockMvc.perform(post("/api/credit-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditReview)))
            .andExpect(status().isCreated());

        // Validate the CreditReview in the database
        List<CreditReview> creditReviewList = creditReviewRepository.findAll();
        assertThat(creditReviewList).hasSize(databaseSizeBeforeCreate + 1);
        CreditReview testCreditReview = creditReviewList.get(creditReviewList.size() - 1);
        assertThat(testCreditReview.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testCreditReview.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testCreditReview.getAuditResult()).isEqualTo(DEFAULT_AUDIT_RESULT);
        assertThat(testCreditReview.getAudit()).isEqualTo(DEFAULT_AUDIT);
        assertThat(testCreditReview.getAuditId()).isEqualTo(DEFAULT_AUDIT_ID);
        assertThat(testCreditReview.getAuditTime()).isEqualTo(DEFAULT_AUDIT_TIME);
        assertThat(testCreditReview.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createCreditReviewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditReviewRepository.findAll().size();

        // Create the CreditReview with an existing ID
        CreditReview existingCreditReview = new CreditReview();
        existingCreditReview.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditReviewMockMvc.perform(post("/api/credit-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCreditReview)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CreditReview> creditReviewList = creditReviewRepository.findAll();
        assertThat(creditReviewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCreditReviews() throws Exception {
        // Initialize the database
        creditReviewRepository.saveAndFlush(creditReview);

        // Get all the creditReviewList
        restCreditReviewMockMvc.perform(get("/api/credit-reviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditReview.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].auditResult").value(hasItem(DEFAULT_AUDIT_RESULT.toString())))
            .andExpect(jsonPath("$.[*].audit").value(hasItem(DEFAULT_AUDIT.toString())))
            .andExpect(jsonPath("$.[*].auditId").value(hasItem(DEFAULT_AUDIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].auditTime").value(hasItem(DEFAULT_AUDIT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getCreditReview() throws Exception {
        // Initialize the database
        creditReviewRepository.saveAndFlush(creditReview);

        // Get the creditReview
        restCreditReviewMockMvc.perform(get("/api/credit-reviews/{id}", creditReview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(creditReview.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.auditResult").value(DEFAULT_AUDIT_RESULT.toString()))
            .andExpect(jsonPath("$.audit").value(DEFAULT_AUDIT.toString()))
            .andExpect(jsonPath("$.auditId").value(DEFAULT_AUDIT_ID.intValue()))
            .andExpect(jsonPath("$.auditTime").value(DEFAULT_AUDIT_TIME.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCreditReview() throws Exception {
        // Get the creditReview
        restCreditReviewMockMvc.perform(get("/api/credit-reviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditReview() throws Exception {
        // Initialize the database
        creditReviewService.save(creditReview);

        int databaseSizeBeforeUpdate = creditReviewRepository.findAll().size();

        // Update the creditReview
        CreditReview updatedCreditReview = creditReviewRepository.findOne(creditReview.getId());
        updatedCreditReview
                .orderId(UPDATED_ORDER_ID)
                .driverId(UPDATED_DRIVER_ID)
                .auditResult(UPDATED_AUDIT_RESULT)
                .audit(UPDATED_AUDIT)
                .auditId(UPDATED_AUDIT_ID)
                .auditTime(UPDATED_AUDIT_TIME)
                .remark(UPDATED_REMARK);

        restCreditReviewMockMvc.perform(put("/api/credit-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCreditReview)))
            .andExpect(status().isOk());

        // Validate the CreditReview in the database
        List<CreditReview> creditReviewList = creditReviewRepository.findAll();
        assertThat(creditReviewList).hasSize(databaseSizeBeforeUpdate);
        CreditReview testCreditReview = creditReviewList.get(creditReviewList.size() - 1);
        assertThat(testCreditReview.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testCreditReview.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testCreditReview.getAuditResult()).isEqualTo(UPDATED_AUDIT_RESULT);
        assertThat(testCreditReview.getAudit()).isEqualTo(UPDATED_AUDIT);
        assertThat(testCreditReview.getAuditId()).isEqualTo(UPDATED_AUDIT_ID);
        assertThat(testCreditReview.getAuditTime()).isEqualTo(UPDATED_AUDIT_TIME);
        assertThat(testCreditReview.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditReview() throws Exception {
        int databaseSizeBeforeUpdate = creditReviewRepository.findAll().size();

        // Create the CreditReview

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCreditReviewMockMvc.perform(put("/api/credit-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditReview)))
            .andExpect(status().isCreated());

        // Validate the CreditReview in the database
        List<CreditReview> creditReviewList = creditReviewRepository.findAll();
        assertThat(creditReviewList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCreditReview() throws Exception {
        // Initialize the database
        creditReviewService.save(creditReview);

        int databaseSizeBeforeDelete = creditReviewRepository.findAll().size();

        // Get the creditReview
        restCreditReviewMockMvc.perform(delete("/api/credit-reviews/{id}", creditReview.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CreditReview> creditReviewList = creditReviewRepository.findAll();
        assertThat(creditReviewList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditReview.class);
    }
}

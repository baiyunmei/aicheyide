package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.InsurancePurchase;
import com.duma.repository.InsurancePurchaseRepository;
import com.duma.service.InsurancePurchaseService;
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
 * Test class for the InsurancePurchaseResource REST controller.
 *
 * @see InsurancePurchaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class InsurancePurchaseResourceIntTest {

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final String DEFAULT_INSURANCE_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_COMPANY = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_INSURANCE_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSURANCE_MONEY = new BigDecimal(2);

    private static final String DEFAULT_POLICY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_POLICY_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_IBEGIN_DATE = 1L;
    private static final Long UPDATED_IBEGIN_DATE = 2L;

    private static final Long DEFAULT_IFINISH_DATE = 1L;
    private static final Long UPDATED_IFINISH_DATE = 2L;

    private static final String DEFAULT_ICOMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ICOMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMERCIAL_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMMERCIAL_COMPANY = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_COMMERCIAL_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_COMMERCIAL_MONEY = new BigDecimal(2);

    private static final String DEFAULT_COMMERCIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_COMMERCIAL_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_CBEGIN_DATE = 1L;
    private static final Long UPDATED_CBEGIN_DATE = 2L;

    private static final Long DEFAULT_CFINISH_DATE = 1L;
    private static final Long UPDATED_CFINISH_DATE = 2L;

    private static final String DEFAULT_CCOMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CCOMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_IPHOTOGRAPH = "AAAAAAAAAA";
    private static final String UPDATED_IPHOTOGRAPH = "BBBBBBBBBB";

    private static final String DEFAULT_CPHOTOGRAPH = "AAAAAAAAAA";
    private static final String UPDATED_CPHOTOGRAPH = "BBBBBBBBBB";

    private static final Long DEFAULT_PURCHASE_TIME = 1L;
    private static final Long UPDATED_PURCHASE_TIME = 2L;

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    @Autowired
    private InsurancePurchaseRepository insurancePurchaseRepository;

    @Autowired
    private InsurancePurchaseService insurancePurchaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInsurancePurchaseMockMvc;

    private InsurancePurchase insurancePurchase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InsurancePurchaseResource insurancePurchaseResource = new InsurancePurchaseResource(insurancePurchaseService);
        this.restInsurancePurchaseMockMvc = MockMvcBuilders.standaloneSetup(insurancePurchaseResource)
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
    public static InsurancePurchase createEntity(EntityManager em) {
        InsurancePurchase insurancePurchase = new InsurancePurchase()
                .vehicleId(DEFAULT_VEHICLE_ID)
                .insuranceCompany(DEFAULT_INSURANCE_COMPANY)
                .insuranceMoney(DEFAULT_INSURANCE_MONEY)
                .policyNumber(DEFAULT_POLICY_NUMBER)
                .ibeginDate(DEFAULT_IBEGIN_DATE)
                .ifinishDate(DEFAULT_IFINISH_DATE)
                .icompanyName(DEFAULT_ICOMPANY_NAME)
                .commercialCompany(DEFAULT_COMMERCIAL_COMPANY)
                .commercialMoney(DEFAULT_COMMERCIAL_MONEY)
                .commercialNumber(DEFAULT_COMMERCIAL_NUMBER)
                .cbeginDate(DEFAULT_CBEGIN_DATE)
                .cfinishDate(DEFAULT_CFINISH_DATE)
                .ccompanyName(DEFAULT_CCOMPANY_NAME)
                .remark(DEFAULT_REMARK)
                .iphotograph(DEFAULT_IPHOTOGRAPH)
                .cphotograph(DEFAULT_CPHOTOGRAPH)
                .purchaseTime(DEFAULT_PURCHASE_TIME)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME);
        return insurancePurchase;
    }

    @Before
    public void initTest() {
        insurancePurchase = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsurancePurchase() throws Exception {
        int databaseSizeBeforeCreate = insurancePurchaseRepository.findAll().size();

        // Create the InsurancePurchase

        restInsurancePurchaseMockMvc.perform(post("/api/insurance-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insurancePurchase)))
            .andExpect(status().isCreated());

        // Validate the InsurancePurchase in the database
        List<InsurancePurchase> insurancePurchaseList = insurancePurchaseRepository.findAll();
        assertThat(insurancePurchaseList).hasSize(databaseSizeBeforeCreate + 1);
        InsurancePurchase testInsurancePurchase = insurancePurchaseList.get(insurancePurchaseList.size() - 1);
        assertThat(testInsurancePurchase.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testInsurancePurchase.getInsuranceCompany()).isEqualTo(DEFAULT_INSURANCE_COMPANY);
        assertThat(testInsurancePurchase.getInsuranceMoney()).isEqualTo(DEFAULT_INSURANCE_MONEY);
        assertThat(testInsurancePurchase.getPolicyNumber()).isEqualTo(DEFAULT_POLICY_NUMBER);
        assertThat(testInsurancePurchase.getIbeginDate()).isEqualTo(DEFAULT_IBEGIN_DATE);
        assertThat(testInsurancePurchase.getIfinishDate()).isEqualTo(DEFAULT_IFINISH_DATE);
        assertThat(testInsurancePurchase.getIcompanyName()).isEqualTo(DEFAULT_ICOMPANY_NAME);
        assertThat(testInsurancePurchase.getCommercialCompany()).isEqualTo(DEFAULT_COMMERCIAL_COMPANY);
        assertThat(testInsurancePurchase.getCommercialMoney()).isEqualTo(DEFAULT_COMMERCIAL_MONEY);
        assertThat(testInsurancePurchase.getCommercialNumber()).isEqualTo(DEFAULT_COMMERCIAL_NUMBER);
        assertThat(testInsurancePurchase.getCbeginDate()).isEqualTo(DEFAULT_CBEGIN_DATE);
        assertThat(testInsurancePurchase.getCfinishDate()).isEqualTo(DEFAULT_CFINISH_DATE);
        assertThat(testInsurancePurchase.getCcompanyName()).isEqualTo(DEFAULT_CCOMPANY_NAME);
        assertThat(testInsurancePurchase.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testInsurancePurchase.getIphotograph()).isEqualTo(DEFAULT_IPHOTOGRAPH);
        assertThat(testInsurancePurchase.getCphotograph()).isEqualTo(DEFAULT_CPHOTOGRAPH);
        assertThat(testInsurancePurchase.getPurchaseTime()).isEqualTo(DEFAULT_PURCHASE_TIME);
        assertThat(testInsurancePurchase.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testInsurancePurchase.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testInsurancePurchase.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testInsurancePurchase.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void createInsurancePurchaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insurancePurchaseRepository.findAll().size();

        // Create the InsurancePurchase with an existing ID
        InsurancePurchase existingInsurancePurchase = new InsurancePurchase();
        existingInsurancePurchase.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsurancePurchaseMockMvc.perform(post("/api/insurance-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingInsurancePurchase)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<InsurancePurchase> insurancePurchaseList = insurancePurchaseRepository.findAll();
        assertThat(insurancePurchaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInsurancePurchases() throws Exception {
        // Initialize the database
        insurancePurchaseRepository.saveAndFlush(insurancePurchase);

        // Get all the insurancePurchaseList
        restInsurancePurchaseMockMvc.perform(get("/api/insurance-purchases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insurancePurchase.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].insuranceCompany").value(hasItem(DEFAULT_INSURANCE_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].insuranceMoney").value(hasItem(DEFAULT_INSURANCE_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].policyNumber").value(hasItem(DEFAULT_POLICY_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].ibeginDate").value(hasItem(DEFAULT_IBEGIN_DATE.intValue())))
            .andExpect(jsonPath("$.[*].ifinishDate").value(hasItem(DEFAULT_IFINISH_DATE.intValue())))
            .andExpect(jsonPath("$.[*].icompanyName").value(hasItem(DEFAULT_ICOMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].commercialCompany").value(hasItem(DEFAULT_COMMERCIAL_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].commercialMoney").value(hasItem(DEFAULT_COMMERCIAL_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].commercialNumber").value(hasItem(DEFAULT_COMMERCIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].cbeginDate").value(hasItem(DEFAULT_CBEGIN_DATE.intValue())))
            .andExpect(jsonPath("$.[*].cfinishDate").value(hasItem(DEFAULT_CFINISH_DATE.intValue())))
            .andExpect(jsonPath("$.[*].ccompanyName").value(hasItem(DEFAULT_CCOMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].iphotograph").value(hasItem(DEFAULT_IPHOTOGRAPH.toString())))
            .andExpect(jsonPath("$.[*].cphotograph").value(hasItem(DEFAULT_CPHOTOGRAPH.toString())))
            .andExpect(jsonPath("$.[*].purchaseTime").value(hasItem(DEFAULT_PURCHASE_TIME.intValue())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())));
    }

    @Test
    @Transactional
    public void getInsurancePurchase() throws Exception {
        // Initialize the database
        insurancePurchaseRepository.saveAndFlush(insurancePurchase);

        // Get the insurancePurchase
        restInsurancePurchaseMockMvc.perform(get("/api/insurance-purchases/{id}", insurancePurchase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(insurancePurchase.getId().intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.insuranceCompany").value(DEFAULT_INSURANCE_COMPANY.toString()))
            .andExpect(jsonPath("$.insuranceMoney").value(DEFAULT_INSURANCE_MONEY.intValue()))
            .andExpect(jsonPath("$.policyNumber").value(DEFAULT_POLICY_NUMBER.toString()))
            .andExpect(jsonPath("$.ibeginDate").value(DEFAULT_IBEGIN_DATE.intValue()))
            .andExpect(jsonPath("$.ifinishDate").value(DEFAULT_IFINISH_DATE.intValue()))
            .andExpect(jsonPath("$.icompanyName").value(DEFAULT_ICOMPANY_NAME.toString()))
            .andExpect(jsonPath("$.commercialCompany").value(DEFAULT_COMMERCIAL_COMPANY.toString()))
            .andExpect(jsonPath("$.commercialMoney").value(DEFAULT_COMMERCIAL_MONEY.intValue()))
            .andExpect(jsonPath("$.commercialNumber").value(DEFAULT_COMMERCIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.cbeginDate").value(DEFAULT_CBEGIN_DATE.intValue()))
            .andExpect(jsonPath("$.cfinishDate").value(DEFAULT_CFINISH_DATE.intValue()))
            .andExpect(jsonPath("$.ccompanyName").value(DEFAULT_CCOMPANY_NAME.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.iphotograph").value(DEFAULT_IPHOTOGRAPH.toString()))
            .andExpect(jsonPath("$.cphotograph").value(DEFAULT_CPHOTOGRAPH.toString()))
            .andExpect(jsonPath("$.purchaseTime").value(DEFAULT_PURCHASE_TIME.intValue()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInsurancePurchase() throws Exception {
        // Get the insurancePurchase
        restInsurancePurchaseMockMvc.perform(get("/api/insurance-purchases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsurancePurchase() throws Exception {
        // Initialize the database
        insurancePurchaseService.save(insurancePurchase);

        int databaseSizeBeforeUpdate = insurancePurchaseRepository.findAll().size();

        // Update the insurancePurchase
        InsurancePurchase updatedInsurancePurchase = insurancePurchaseRepository.findOne(insurancePurchase.getId());
        updatedInsurancePurchase
                .vehicleId(UPDATED_VEHICLE_ID)
                .insuranceCompany(UPDATED_INSURANCE_COMPANY)
                .insuranceMoney(UPDATED_INSURANCE_MONEY)
                .policyNumber(UPDATED_POLICY_NUMBER)
                .ibeginDate(UPDATED_IBEGIN_DATE)
                .ifinishDate(UPDATED_IFINISH_DATE)
                .icompanyName(UPDATED_ICOMPANY_NAME)
                .commercialCompany(UPDATED_COMMERCIAL_COMPANY)
                .commercialMoney(UPDATED_COMMERCIAL_MONEY)
                .commercialNumber(UPDATED_COMMERCIAL_NUMBER)
                .cbeginDate(UPDATED_CBEGIN_DATE)
                .cfinishDate(UPDATED_CFINISH_DATE)
                .ccompanyName(UPDATED_CCOMPANY_NAME)
                .remark(UPDATED_REMARK)
                .iphotograph(UPDATED_IPHOTOGRAPH)
                .cphotograph(UPDATED_CPHOTOGRAPH)
                .purchaseTime(UPDATED_PURCHASE_TIME)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME);

        restInsurancePurchaseMockMvc.perform(put("/api/insurance-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInsurancePurchase)))
            .andExpect(status().isOk());

        // Validate the InsurancePurchase in the database
        List<InsurancePurchase> insurancePurchaseList = insurancePurchaseRepository.findAll();
        assertThat(insurancePurchaseList).hasSize(databaseSizeBeforeUpdate);
        InsurancePurchase testInsurancePurchase = insurancePurchaseList.get(insurancePurchaseList.size() - 1);
        assertThat(testInsurancePurchase.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testInsurancePurchase.getInsuranceCompany()).isEqualTo(UPDATED_INSURANCE_COMPANY);
        assertThat(testInsurancePurchase.getInsuranceMoney()).isEqualTo(UPDATED_INSURANCE_MONEY);
        assertThat(testInsurancePurchase.getPolicyNumber()).isEqualTo(UPDATED_POLICY_NUMBER);
        assertThat(testInsurancePurchase.getIbeginDate()).isEqualTo(UPDATED_IBEGIN_DATE);
        assertThat(testInsurancePurchase.getIfinishDate()).isEqualTo(UPDATED_IFINISH_DATE);
        assertThat(testInsurancePurchase.getIcompanyName()).isEqualTo(UPDATED_ICOMPANY_NAME);
        assertThat(testInsurancePurchase.getCommercialCompany()).isEqualTo(UPDATED_COMMERCIAL_COMPANY);
        assertThat(testInsurancePurchase.getCommercialMoney()).isEqualTo(UPDATED_COMMERCIAL_MONEY);
        assertThat(testInsurancePurchase.getCommercialNumber()).isEqualTo(UPDATED_COMMERCIAL_NUMBER);
        assertThat(testInsurancePurchase.getCbeginDate()).isEqualTo(UPDATED_CBEGIN_DATE);
        assertThat(testInsurancePurchase.getCfinishDate()).isEqualTo(UPDATED_CFINISH_DATE);
        assertThat(testInsurancePurchase.getCcompanyName()).isEqualTo(UPDATED_CCOMPANY_NAME);
        assertThat(testInsurancePurchase.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testInsurancePurchase.getIphotograph()).isEqualTo(UPDATED_IPHOTOGRAPH);
        assertThat(testInsurancePurchase.getCphotograph()).isEqualTo(UPDATED_CPHOTOGRAPH);
        assertThat(testInsurancePurchase.getPurchaseTime()).isEqualTo(UPDATED_PURCHASE_TIME);
        assertThat(testInsurancePurchase.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testInsurancePurchase.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testInsurancePurchase.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testInsurancePurchase.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingInsurancePurchase() throws Exception {
        int databaseSizeBeforeUpdate = insurancePurchaseRepository.findAll().size();

        // Create the InsurancePurchase

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInsurancePurchaseMockMvc.perform(put("/api/insurance-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insurancePurchase)))
            .andExpect(status().isCreated());

        // Validate the InsurancePurchase in the database
        List<InsurancePurchase> insurancePurchaseList = insurancePurchaseRepository.findAll();
        assertThat(insurancePurchaseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInsurancePurchase() throws Exception {
        // Initialize the database
        insurancePurchaseService.save(insurancePurchase);

        int databaseSizeBeforeDelete = insurancePurchaseRepository.findAll().size();

        // Get the insurancePurchase
        restInsurancePurchaseMockMvc.perform(delete("/api/insurance-purchases/{id}", insurancePurchase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InsurancePurchase> insurancePurchaseList = insurancePurchaseRepository.findAll();
        assertThat(insurancePurchaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsurancePurchase.class);
    }
}

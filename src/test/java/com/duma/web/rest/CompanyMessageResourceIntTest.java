package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.CompanyMessage;
import com.duma.repository.CompanyMessageRepository;
import com.duma.service.CompanyMessageService;
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
 * Test class for the CompanyMessageResource REST controller.
 *
 * @see CompanyMessageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class CompanyMessageResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_LOGO = "BBBBBBBBBB";

    private static final Long DEFAULT_COMPANY_PRINCIPAL_ID = 1L;
    private static final Long UPDATED_COMPANY_PRINCIPAL_ID = 2L;

    private static final String DEFAULT_COMPANY_PRINCIPAL = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_PRINCIPAL = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_PRINCIPALPHONE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_PRINCIPALPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OPENING_BANK = "AAAAAAAAAA";
    private static final String UPDATED_OPENING_BANK = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TAX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRALS = "AAAAAAAAAA";
    private static final String UPDATED_REFERRALS = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRALS_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_REFERRALS_PHONE = "BBBBBBBBBB";

    private static final Long DEFAULT_REFERRALS_AUTHORIZATION_ID = 1L;
    private static final Long UPDATED_REFERRALS_AUTHORIZATION_ID = 2L;

    private static final Long DEFAULT_AUTHORIZATION_ID = 1L;
    private static final Long UPDATED_AUTHORIZATION_ID = 2L;

    private static final String DEFAULT_AUTHORIZATION_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORIZATION_COMPANY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private CompanyMessageRepository companyMessageRepository;

    @Autowired
    private CompanyMessageService companyMessageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCompanyMessageMockMvc;

    private CompanyMessage companyMessage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CompanyMessageResource companyMessageResource = new CompanyMessageResource(companyMessageService);
        this.restCompanyMessageMockMvc = MockMvcBuilders.standaloneSetup(companyMessageResource)
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
    public static CompanyMessage createEntity(EntityManager em) {
        CompanyMessage companyMessage = new CompanyMessage()
                .companyName(DEFAULT_COMPANY_NAME)
                .companyLogo(DEFAULT_COMPANY_LOGO)
                .companyPrincipalId(DEFAULT_COMPANY_PRINCIPAL_ID)
                .companyPrincipal(DEFAULT_COMPANY_PRINCIPAL)
                .companyPrincipalphone(DEFAULT_COMPANY_PRINCIPALPHONE)
                .site(DEFAULT_SITE)
                .accountName(DEFAULT_ACCOUNT_NAME)
                .openingBank(DEFAULT_OPENING_BANK)
                .phone(DEFAULT_PHONE)
                .taxNumber(DEFAULT_TAX_NUMBER)
                .referrals(DEFAULT_REFERRALS)
                .referralsPhone(DEFAULT_REFERRALS_PHONE)
                .referralsAuthorizationId(DEFAULT_REFERRALS_AUTHORIZATION_ID)
                .authorizationId(DEFAULT_AUTHORIZATION_ID)
                .authorizationCompanyName(DEFAULT_AUTHORIZATION_COMPANY_NAME)
                .status(DEFAULT_STATUS);
        return companyMessage;
    }

    @Before
    public void initTest() {
        companyMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompanyMessage() throws Exception {
        int databaseSizeBeforeCreate = companyMessageRepository.findAll().size();

        // Create the CompanyMessage

        restCompanyMessageMockMvc.perform(post("/api/company-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyMessage)))
            .andExpect(status().isCreated());

        // Validate the CompanyMessage in the database
        List<CompanyMessage> companyMessageList = companyMessageRepository.findAll();
        assertThat(companyMessageList).hasSize(databaseSizeBeforeCreate + 1);
        CompanyMessage testCompanyMessage = companyMessageList.get(companyMessageList.size() - 1);
        assertThat(testCompanyMessage.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testCompanyMessage.getCompanyLogo()).isEqualTo(DEFAULT_COMPANY_LOGO);
        assertThat(testCompanyMessage.getCompanyPrincipalId()).isEqualTo(DEFAULT_COMPANY_PRINCIPAL_ID);
        assertThat(testCompanyMessage.getCompanyPrincipal()).isEqualTo(DEFAULT_COMPANY_PRINCIPAL);
        assertThat(testCompanyMessage.getCompanyPrincipalphone()).isEqualTo(DEFAULT_COMPANY_PRINCIPALPHONE);
        assertThat(testCompanyMessage.getSite()).isEqualTo(DEFAULT_SITE);
        assertThat(testCompanyMessage.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testCompanyMessage.getOpeningBank()).isEqualTo(DEFAULT_OPENING_BANK);
        assertThat(testCompanyMessage.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCompanyMessage.getTaxNumber()).isEqualTo(DEFAULT_TAX_NUMBER);
        assertThat(testCompanyMessage.getReferrals()).isEqualTo(DEFAULT_REFERRALS);
        assertThat(testCompanyMessage.getReferralsPhone()).isEqualTo(DEFAULT_REFERRALS_PHONE);
        assertThat(testCompanyMessage.getReferralsAuthorizationId()).isEqualTo(DEFAULT_REFERRALS_AUTHORIZATION_ID);
        assertThat(testCompanyMessage.getAuthorizationId()).isEqualTo(DEFAULT_AUTHORIZATION_ID);
        assertThat(testCompanyMessage.getAuthorizationCompanyName()).isEqualTo(DEFAULT_AUTHORIZATION_COMPANY_NAME);
        assertThat(testCompanyMessage.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCompanyMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyMessageRepository.findAll().size();

        // Create the CompanyMessage with an existing ID
        CompanyMessage existingCompanyMessage = new CompanyMessage();
        existingCompanyMessage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyMessageMockMvc.perform(post("/api/company-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCompanyMessage)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CompanyMessage> companyMessageList = companyMessageRepository.findAll();
        assertThat(companyMessageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCompanyMessages() throws Exception {
        // Initialize the database
        companyMessageRepository.saveAndFlush(companyMessage);

        // Get all the companyMessageList
        restCompanyMessageMockMvc.perform(get("/api/company-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companyMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].companyLogo").value(hasItem(DEFAULT_COMPANY_LOGO.toString())))
            .andExpect(jsonPath("$.[*].companyPrincipalId").value(hasItem(DEFAULT_COMPANY_PRINCIPAL_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyPrincipal").value(hasItem(DEFAULT_COMPANY_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].companyPrincipalphone").value(hasItem(DEFAULT_COMPANY_PRINCIPALPHONE.toString())))
            .andExpect(jsonPath("$.[*].site").value(hasItem(DEFAULT_SITE.toString())))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME.toString())))
            .andExpect(jsonPath("$.[*].openingBank").value(hasItem(DEFAULT_OPENING_BANK.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].taxNumber").value(hasItem(DEFAULT_TAX_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].referrals").value(hasItem(DEFAULT_REFERRALS.toString())))
            .andExpect(jsonPath("$.[*].referralsPhone").value(hasItem(DEFAULT_REFERRALS_PHONE.toString())))
            .andExpect(jsonPath("$.[*].referralsAuthorizationId").value(hasItem(DEFAULT_REFERRALS_AUTHORIZATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].authorizationId").value(hasItem(DEFAULT_AUTHORIZATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].authorizationCompanyName").value(hasItem(DEFAULT_AUTHORIZATION_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getCompanyMessage() throws Exception {
        // Initialize the database
        companyMessageRepository.saveAndFlush(companyMessage);

        // Get the companyMessage
        restCompanyMessageMockMvc.perform(get("/api/company-messages/{id}", companyMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(companyMessage.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.companyLogo").value(DEFAULT_COMPANY_LOGO.toString()))
            .andExpect(jsonPath("$.companyPrincipalId").value(DEFAULT_COMPANY_PRINCIPAL_ID.intValue()))
            .andExpect(jsonPath("$.companyPrincipal").value(DEFAULT_COMPANY_PRINCIPAL.toString()))
            .andExpect(jsonPath("$.companyPrincipalphone").value(DEFAULT_COMPANY_PRINCIPALPHONE.toString()))
            .andExpect(jsonPath("$.site").value(DEFAULT_SITE.toString()))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME.toString()))
            .andExpect(jsonPath("$.openingBank").value(DEFAULT_OPENING_BANK.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.taxNumber").value(DEFAULT_TAX_NUMBER.toString()))
            .andExpect(jsonPath("$.referrals").value(DEFAULT_REFERRALS.toString()))
            .andExpect(jsonPath("$.referralsPhone").value(DEFAULT_REFERRALS_PHONE.toString()))
            .andExpect(jsonPath("$.referralsAuthorizationId").value(DEFAULT_REFERRALS_AUTHORIZATION_ID.intValue()))
            .andExpect(jsonPath("$.authorizationId").value(DEFAULT_AUTHORIZATION_ID.intValue()))
            .andExpect(jsonPath("$.authorizationCompanyName").value(DEFAULT_AUTHORIZATION_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingCompanyMessage() throws Exception {
        // Get the companyMessage
        restCompanyMessageMockMvc.perform(get("/api/company-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompanyMessage() throws Exception {
        // Initialize the database
        companyMessageService.save(companyMessage);

        int databaseSizeBeforeUpdate = companyMessageRepository.findAll().size();

        // Update the companyMessage
        CompanyMessage updatedCompanyMessage = companyMessageRepository.findOne(companyMessage.getId());
        updatedCompanyMessage
                .companyName(UPDATED_COMPANY_NAME)
                .companyLogo(UPDATED_COMPANY_LOGO)
                .companyPrincipalId(UPDATED_COMPANY_PRINCIPAL_ID)
                .companyPrincipal(UPDATED_COMPANY_PRINCIPAL)
                .companyPrincipalphone(UPDATED_COMPANY_PRINCIPALPHONE)
                .site(UPDATED_SITE)
                .accountName(UPDATED_ACCOUNT_NAME)
                .openingBank(UPDATED_OPENING_BANK)
                .phone(UPDATED_PHONE)
                .taxNumber(UPDATED_TAX_NUMBER)
                .referrals(UPDATED_REFERRALS)
                .referralsPhone(UPDATED_REFERRALS_PHONE)
                .referralsAuthorizationId(UPDATED_REFERRALS_AUTHORIZATION_ID)
                .authorizationId(UPDATED_AUTHORIZATION_ID)
                .authorizationCompanyName(UPDATED_AUTHORIZATION_COMPANY_NAME)
                .status(UPDATED_STATUS);

        restCompanyMessageMockMvc.perform(put("/api/company-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCompanyMessage)))
            .andExpect(status().isOk());

        // Validate the CompanyMessage in the database
        List<CompanyMessage> companyMessageList = companyMessageRepository.findAll();
        assertThat(companyMessageList).hasSize(databaseSizeBeforeUpdate);
        CompanyMessage testCompanyMessage = companyMessageList.get(companyMessageList.size() - 1);
        assertThat(testCompanyMessage.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testCompanyMessage.getCompanyLogo()).isEqualTo(UPDATED_COMPANY_LOGO);
        assertThat(testCompanyMessage.getCompanyPrincipalId()).isEqualTo(UPDATED_COMPANY_PRINCIPAL_ID);
        assertThat(testCompanyMessage.getCompanyPrincipal()).isEqualTo(UPDATED_COMPANY_PRINCIPAL);
        assertThat(testCompanyMessage.getCompanyPrincipalphone()).isEqualTo(UPDATED_COMPANY_PRINCIPALPHONE);
        assertThat(testCompanyMessage.getSite()).isEqualTo(UPDATED_SITE);
        assertThat(testCompanyMessage.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testCompanyMessage.getOpeningBank()).isEqualTo(UPDATED_OPENING_BANK);
        assertThat(testCompanyMessage.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCompanyMessage.getTaxNumber()).isEqualTo(UPDATED_TAX_NUMBER);
        assertThat(testCompanyMessage.getReferrals()).isEqualTo(UPDATED_REFERRALS);
        assertThat(testCompanyMessage.getReferralsPhone()).isEqualTo(UPDATED_REFERRALS_PHONE);
        assertThat(testCompanyMessage.getReferralsAuthorizationId()).isEqualTo(UPDATED_REFERRALS_AUTHORIZATION_ID);
        assertThat(testCompanyMessage.getAuthorizationId()).isEqualTo(UPDATED_AUTHORIZATION_ID);
        assertThat(testCompanyMessage.getAuthorizationCompanyName()).isEqualTo(UPDATED_AUTHORIZATION_COMPANY_NAME);
        assertThat(testCompanyMessage.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCompanyMessage() throws Exception {
        int databaseSizeBeforeUpdate = companyMessageRepository.findAll().size();

        // Create the CompanyMessage

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompanyMessageMockMvc.perform(put("/api/company-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyMessage)))
            .andExpect(status().isCreated());

        // Validate the CompanyMessage in the database
        List<CompanyMessage> companyMessageList = companyMessageRepository.findAll();
        assertThat(companyMessageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCompanyMessage() throws Exception {
        // Initialize the database
        companyMessageService.save(companyMessage);

        int databaseSizeBeforeDelete = companyMessageRepository.findAll().size();

        // Get the companyMessage
        restCompanyMessageMockMvc.perform(delete("/api/company-messages/{id}", companyMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CompanyMessage> companyMessageList = companyMessageRepository.findAll();
        assertThat(companyMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyMessage.class);
    }
}

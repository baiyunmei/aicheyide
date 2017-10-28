package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.SupplierRecord;
import com.duma.repository.SupplierRecordRepository;
import com.duma.service.SupplierRecordService;
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
 * Test class for the SupplierRecordResource REST controller.
 *
 * @see SupplierRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class SupplierRecordResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_MNEMONIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MNEMONIC_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUPPLIER_STATUS = 1;
    private static final Integer UPDATED_SUPPLIER_STATUS = 2;

    private static final String DEFAULT_SUPPLIER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TAX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_OPEN_BANK = "AAAAAAAAAA";
    private static final String UPDATED_OPEN_BANK = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYTYPE = 1;
    private static final Integer UPDATED_PAYTYPE = 2;

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private SupplierRecordRepository supplierRecordRepository;

    @Autowired
    private SupplierRecordService supplierRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSupplierRecordMockMvc;

    private SupplierRecord supplierRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SupplierRecordResource supplierRecordResource = new SupplierRecordResource(supplierRecordService);
        this.restSupplierRecordMockMvc = MockMvcBuilders.standaloneSetup(supplierRecordResource)
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
    public static SupplierRecord createEntity(EntityManager em) {
        SupplierRecord supplierRecord = new SupplierRecord()
                .companyId(DEFAULT_COMPANY_ID)
                .mnemonicCode(DEFAULT_MNEMONIC_CODE)
                .supplierStatus(DEFAULT_SUPPLIER_STATUS)
                .supplierName(DEFAULT_SUPPLIER_NAME)
                .taxNumber(DEFAULT_TAX_NUMBER)
                .phone(DEFAULT_PHONE)
                .openBank(DEFAULT_OPEN_BANK)
                .bankAccount(DEFAULT_BANK_ACCOUNT)
                .unitAddress(DEFAULT_UNIT_ADDRESS)
                .contact(DEFAULT_CONTACT)
                .contactPhone(DEFAULT_CONTACT_PHONE)
                .paytype(DEFAULT_PAYTYPE)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME)
                .remark(DEFAULT_REMARK);
        return supplierRecord;
    }

    @Before
    public void initTest() {
        supplierRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupplierRecord() throws Exception {
        int databaseSizeBeforeCreate = supplierRecordRepository.findAll().size();

        // Create the SupplierRecord

        restSupplierRecordMockMvc.perform(post("/api/supplier-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierRecord)))
            .andExpect(status().isCreated());

        // Validate the SupplierRecord in the database
        List<SupplierRecord> supplierRecordList = supplierRecordRepository.findAll();
        assertThat(supplierRecordList).hasSize(databaseSizeBeforeCreate + 1);
        SupplierRecord testSupplierRecord = supplierRecordList.get(supplierRecordList.size() - 1);
        assertThat(testSupplierRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testSupplierRecord.getMnemonicCode()).isEqualTo(DEFAULT_MNEMONIC_CODE);
        assertThat(testSupplierRecord.getSupplierStatus()).isEqualTo(DEFAULT_SUPPLIER_STATUS);
        assertThat(testSupplierRecord.getSupplierName()).isEqualTo(DEFAULT_SUPPLIER_NAME);
        assertThat(testSupplierRecord.getTaxNumber()).isEqualTo(DEFAULT_TAX_NUMBER);
        assertThat(testSupplierRecord.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSupplierRecord.getOpenBank()).isEqualTo(DEFAULT_OPEN_BANK);
        assertThat(testSupplierRecord.getBankAccount()).isEqualTo(DEFAULT_BANK_ACCOUNT);
        assertThat(testSupplierRecord.getUnitAddress()).isEqualTo(DEFAULT_UNIT_ADDRESS);
        assertThat(testSupplierRecord.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testSupplierRecord.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testSupplierRecord.getPaytype()).isEqualTo(DEFAULT_PAYTYPE);
        assertThat(testSupplierRecord.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testSupplierRecord.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testSupplierRecord.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testSupplierRecord.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
        assertThat(testSupplierRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createSupplierRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supplierRecordRepository.findAll().size();

        // Create the SupplierRecord with an existing ID
        SupplierRecord existingSupplierRecord = new SupplierRecord();
        existingSupplierRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplierRecordMockMvc.perform(post("/api/supplier-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingSupplierRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<SupplierRecord> supplierRecordList = supplierRecordRepository.findAll();
        assertThat(supplierRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSupplierRecords() throws Exception {
        // Initialize the database
        supplierRecordRepository.saveAndFlush(supplierRecord);

        // Get all the supplierRecordList
        restSupplierRecordMockMvc.perform(get("/api/supplier-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplierRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].mnemonicCode").value(hasItem(DEFAULT_MNEMONIC_CODE.toString())))
            .andExpect(jsonPath("$.[*].supplierStatus").value(hasItem(DEFAULT_SUPPLIER_STATUS)))
            .andExpect(jsonPath("$.[*].supplierName").value(hasItem(DEFAULT_SUPPLIER_NAME.toString())))
            .andExpect(jsonPath("$.[*].taxNumber").value(hasItem(DEFAULT_TAX_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].openBank").value(hasItem(DEFAULT_OPEN_BANK.toString())))
            .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DEFAULT_BANK_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].unitAddress").value(hasItem(DEFAULT_UNIT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].contactPhone").value(hasItem(DEFAULT_CONTACT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].paytype").value(hasItem(DEFAULT_PAYTYPE)))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getSupplierRecord() throws Exception {
        // Initialize the database
        supplierRecordRepository.saveAndFlush(supplierRecord);

        // Get the supplierRecord
        restSupplierRecordMockMvc.perform(get("/api/supplier-records/{id}", supplierRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(supplierRecord.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.mnemonicCode").value(DEFAULT_MNEMONIC_CODE.toString()))
            .andExpect(jsonPath("$.supplierStatus").value(DEFAULT_SUPPLIER_STATUS))
            .andExpect(jsonPath("$.supplierName").value(DEFAULT_SUPPLIER_NAME.toString()))
            .andExpect(jsonPath("$.taxNumber").value(DEFAULT_TAX_NUMBER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.openBank").value(DEFAULT_OPEN_BANK.toString()))
            .andExpect(jsonPath("$.bankAccount").value(DEFAULT_BANK_ACCOUNT.toString()))
            .andExpect(jsonPath("$.unitAddress").value(DEFAULT_UNIT_ADDRESS.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.toString()))
            .andExpect(jsonPath("$.contactPhone").value(DEFAULT_CONTACT_PHONE.toString()))
            .andExpect(jsonPath("$.paytype").value(DEFAULT_PAYTYPE))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSupplierRecord() throws Exception {
        // Get the supplierRecord
        restSupplierRecordMockMvc.perform(get("/api/supplier-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupplierRecord() throws Exception {
        // Initialize the database
        supplierRecordService.save(supplierRecord);

        int databaseSizeBeforeUpdate = supplierRecordRepository.findAll().size();

        // Update the supplierRecord
        SupplierRecord updatedSupplierRecord = supplierRecordRepository.findOne(supplierRecord.getId());
        updatedSupplierRecord
                .companyId(UPDATED_COMPANY_ID)
                .mnemonicCode(UPDATED_MNEMONIC_CODE)
                .supplierStatus(UPDATED_SUPPLIER_STATUS)
                .supplierName(UPDATED_SUPPLIER_NAME)
                .taxNumber(UPDATED_TAX_NUMBER)
                .phone(UPDATED_PHONE)
                .openBank(UPDATED_OPEN_BANK)
                .bankAccount(UPDATED_BANK_ACCOUNT)
                .unitAddress(UPDATED_UNIT_ADDRESS)
                .contact(UPDATED_CONTACT)
                .contactPhone(UPDATED_CONTACT_PHONE)
                .paytype(UPDATED_PAYTYPE)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME)
                .remark(UPDATED_REMARK);

        restSupplierRecordMockMvc.perform(put("/api/supplier-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSupplierRecord)))
            .andExpect(status().isOk());

        // Validate the SupplierRecord in the database
        List<SupplierRecord> supplierRecordList = supplierRecordRepository.findAll();
        assertThat(supplierRecordList).hasSize(databaseSizeBeforeUpdate);
        SupplierRecord testSupplierRecord = supplierRecordList.get(supplierRecordList.size() - 1);
        assertThat(testSupplierRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testSupplierRecord.getMnemonicCode()).isEqualTo(UPDATED_MNEMONIC_CODE);
        assertThat(testSupplierRecord.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplierRecord.getSupplierName()).isEqualTo(UPDATED_SUPPLIER_NAME);
        assertThat(testSupplierRecord.getTaxNumber()).isEqualTo(UPDATED_TAX_NUMBER);
        assertThat(testSupplierRecord.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSupplierRecord.getOpenBank()).isEqualTo(UPDATED_OPEN_BANK);
        assertThat(testSupplierRecord.getBankAccount()).isEqualTo(UPDATED_BANK_ACCOUNT);
        assertThat(testSupplierRecord.getUnitAddress()).isEqualTo(UPDATED_UNIT_ADDRESS);
        assertThat(testSupplierRecord.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testSupplierRecord.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testSupplierRecord.getPaytype()).isEqualTo(UPDATED_PAYTYPE);
        assertThat(testSupplierRecord.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testSupplierRecord.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testSupplierRecord.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testSupplierRecord.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
        assertThat(testSupplierRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingSupplierRecord() throws Exception {
        int databaseSizeBeforeUpdate = supplierRecordRepository.findAll().size();

        // Create the SupplierRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSupplierRecordMockMvc.perform(put("/api/supplier-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(supplierRecord)))
            .andExpect(status().isCreated());

        // Validate the SupplierRecord in the database
        List<SupplierRecord> supplierRecordList = supplierRecordRepository.findAll();
        assertThat(supplierRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSupplierRecord() throws Exception {
        // Initialize the database
        supplierRecordService.save(supplierRecord);

        int databaseSizeBeforeDelete = supplierRecordRepository.findAll().size();

        // Get the supplierRecord
        restSupplierRecordMockMvc.perform(delete("/api/supplier-records/{id}", supplierRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SupplierRecord> supplierRecordList = supplierRecordRepository.findAll();
        assertThat(supplierRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierRecord.class);
    }
}

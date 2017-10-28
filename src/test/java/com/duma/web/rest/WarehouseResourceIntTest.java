package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.Warehouse;
import com.duma.repository.WarehouseRepository;
import com.duma.service.WarehouseService;
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
 * Test class for the WarehouseResource REST controller.
 *
 * @see WarehouseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class WarehouseResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_MNEMONIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MNEMONIC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_PRINCIPAL = "AAAAAAAAAA";
    private static final String UPDATED_PRINCIPAL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWarehouseMockMvc;

    private Warehouse warehouse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WarehouseResource warehouseResource = new WarehouseResource(warehouseService);
        this.restWarehouseMockMvc = MockMvcBuilders.standaloneSetup(warehouseResource)
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
    public static Warehouse createEntity(EntityManager em) {
        Warehouse warehouse = new Warehouse()
                .companyId(DEFAULT_COMPANY_ID)
                .mnemonicCode(DEFAULT_MNEMONIC_CODE)
                .name(DEFAULT_NAME)
                .site(DEFAULT_SITE)
                .principal(DEFAULT_PRINCIPAL)
                .phone(DEFAULT_PHONE)
                .remark(DEFAULT_REMARK)
                .status(DEFAULT_STATUS);
        return warehouse;
    }

    @Before
    public void initTest() {
        warehouse = createEntity(em);
    }

    @Test
    @Transactional
    public void createWarehouse() throws Exception {
        int databaseSizeBeforeCreate = warehouseRepository.findAll().size();

        // Create the Warehouse

        restWarehouseMockMvc.perform(post("/api/warehouses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(warehouse)))
            .andExpect(status().isCreated());

        // Validate the Warehouse in the database
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        assertThat(warehouseList).hasSize(databaseSizeBeforeCreate + 1);
        Warehouse testWarehouse = warehouseList.get(warehouseList.size() - 1);
        assertThat(testWarehouse.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testWarehouse.getMnemonicCode()).isEqualTo(DEFAULT_MNEMONIC_CODE);
        assertThat(testWarehouse.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWarehouse.getSite()).isEqualTo(DEFAULT_SITE);
        assertThat(testWarehouse.getPrincipal()).isEqualTo(DEFAULT_PRINCIPAL);
        assertThat(testWarehouse.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testWarehouse.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testWarehouse.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createWarehouseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = warehouseRepository.findAll().size();

        // Create the Warehouse with an existing ID
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWarehouseMockMvc.perform(post("/api/warehouses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWarehouse)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        assertThat(warehouseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWarehouses() throws Exception {
        // Initialize the database
        warehouseRepository.saveAndFlush(warehouse);

        // Get all the warehouseList
        restWarehouseMockMvc.perform(get("/api/warehouses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(warehouse.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].mnemonicCode").value(hasItem(DEFAULT_MNEMONIC_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].site").value(hasItem(DEFAULT_SITE.toString())))
            .andExpect(jsonPath("$.[*].principal").value(hasItem(DEFAULT_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getWarehouse() throws Exception {
        // Initialize the database
        warehouseRepository.saveAndFlush(warehouse);

        // Get the warehouse
        restWarehouseMockMvc.perform(get("/api/warehouses/{id}", warehouse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(warehouse.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.mnemonicCode").value(DEFAULT_MNEMONIC_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.site").value(DEFAULT_SITE.toString()))
            .andExpect(jsonPath("$.principal").value(DEFAULT_PRINCIPAL.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingWarehouse() throws Exception {
        // Get the warehouse
        restWarehouseMockMvc.perform(get("/api/warehouses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWarehouse() throws Exception {
        // Initialize the database
        warehouseService.save(warehouse);

        int databaseSizeBeforeUpdate = warehouseRepository.findAll().size();

        // Update the warehouse
        Warehouse updatedWarehouse = warehouseRepository.findOne(warehouse.getId());
        updatedWarehouse
                .companyId(UPDATED_COMPANY_ID)
                .mnemonicCode(UPDATED_MNEMONIC_CODE)
                .name(UPDATED_NAME)
                .site(UPDATED_SITE)
                .principal(UPDATED_PRINCIPAL)
                .phone(UPDATED_PHONE)
                .remark(UPDATED_REMARK)
                .status(UPDATED_STATUS);

        restWarehouseMockMvc.perform(put("/api/warehouses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWarehouse)))
            .andExpect(status().isOk());

        // Validate the Warehouse in the database
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        assertThat(warehouseList).hasSize(databaseSizeBeforeUpdate);
        Warehouse testWarehouse = warehouseList.get(warehouseList.size() - 1);
        assertThat(testWarehouse.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testWarehouse.getMnemonicCode()).isEqualTo(UPDATED_MNEMONIC_CODE);
        assertThat(testWarehouse.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWarehouse.getSite()).isEqualTo(UPDATED_SITE);
        assertThat(testWarehouse.getPrincipal()).isEqualTo(UPDATED_PRINCIPAL);
        assertThat(testWarehouse.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testWarehouse.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testWarehouse.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingWarehouse() throws Exception {
        int databaseSizeBeforeUpdate = warehouseRepository.findAll().size();

        // Create the Warehouse

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWarehouseMockMvc.perform(put("/api/warehouses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(warehouse)))
            .andExpect(status().isCreated());

        // Validate the Warehouse in the database
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        assertThat(warehouseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWarehouse() throws Exception {
        // Initialize the database
        warehouseService.save(warehouse);

        int databaseSizeBeforeDelete = warehouseRepository.findAll().size();

        // Get the warehouse
        restWarehouseMockMvc.perform(delete("/api/warehouses/{id}", warehouse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        assertThat(warehouseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Warehouse.class);
    }
}

package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.CommodityData;
import com.duma.repository.CommodityDataRepository;
import com.duma.service.CommodityDataService;
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
 * Test class for the CommodityDataResource REST controller.
 *
 * @see CommodityDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class CommodityDataResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_MNEMONIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MNEMONIC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALES_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLOUR = "AAAAAAAAAA";
    private static final String UPDATED_COLOUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    private static final String DEFAULT_COMMODITY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COMMODITY_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_NORMAL_PRICE = 1D;
    private static final Double UPDATED_NORMAL_PRICE = 2D;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private CommodityDataRepository commodityDataRepository;

    @Autowired
    private CommodityDataService commodityDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCommodityDataMockMvc;

    private CommodityData commodityData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CommodityDataResource commodityDataResource = new CommodityDataResource(commodityDataService);
        this.restCommodityDataMockMvc = MockMvcBuilders.standaloneSetup(commodityDataResource)
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
    public static CommodityData createEntity(EntityManager em) {
        CommodityData commodityData = new CommodityData()
                .companyId(DEFAULT_COMPANY_ID)
                .mnemonicCode(DEFAULT_MNEMONIC_CODE)
                .brand(DEFAULT_BRAND)
                .salesName(DEFAULT_SALES_NAME)
                .colour(DEFAULT_COLOUR)
                .size(DEFAULT_SIZE)
                .commodityType(DEFAULT_COMMODITY_TYPE)
                .normalPrice(DEFAULT_NORMAL_PRICE)
                .remark(DEFAULT_REMARK)
                .status(DEFAULT_STATUS);
        return commodityData;
    }

    @Before
    public void initTest() {
        commodityData = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommodityData() throws Exception {
        int databaseSizeBeforeCreate = commodityDataRepository.findAll().size();

        // Create the CommodityData

        restCommodityDataMockMvc.perform(post("/api/commodity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityData)))
            .andExpect(status().isCreated());

        // Validate the CommodityData in the database
        List<CommodityData> commodityDataList = commodityDataRepository.findAll();
        assertThat(commodityDataList).hasSize(databaseSizeBeforeCreate + 1);
        CommodityData testCommodityData = commodityDataList.get(commodityDataList.size() - 1);
        assertThat(testCommodityData.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testCommodityData.getMnemonicCode()).isEqualTo(DEFAULT_MNEMONIC_CODE);
        assertThat(testCommodityData.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testCommodityData.getSalesName()).isEqualTo(DEFAULT_SALES_NAME);
        assertThat(testCommodityData.getColour()).isEqualTo(DEFAULT_COLOUR);
        assertThat(testCommodityData.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testCommodityData.getCommodityType()).isEqualTo(DEFAULT_COMMODITY_TYPE);
        assertThat(testCommodityData.getNormalPrice()).isEqualTo(DEFAULT_NORMAL_PRICE);
        assertThat(testCommodityData.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testCommodityData.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCommodityDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commodityDataRepository.findAll().size();

        // Create the CommodityData with an existing ID
        CommodityData existingCommodityData = new CommodityData();
        existingCommodityData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommodityDataMockMvc.perform(post("/api/commodity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCommodityData)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CommodityData> commodityDataList = commodityDataRepository.findAll();
        assertThat(commodityDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCommodityData() throws Exception {
        // Initialize the database
        commodityDataRepository.saveAndFlush(commodityData);

        // Get all the commodityDataList
        restCommodityDataMockMvc.perform(get("/api/commodity-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commodityData.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].mnemonicCode").value(hasItem(DEFAULT_MNEMONIC_CODE.toString())))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND.toString())))
            .andExpect(jsonPath("$.[*].salesName").value(hasItem(DEFAULT_SALES_NAME.toString())))
            .andExpect(jsonPath("$.[*].colour").value(hasItem(DEFAULT_COLOUR.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].commodityType").value(hasItem(DEFAULT_COMMODITY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].normalPrice").value(hasItem(DEFAULT_NORMAL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getCommodityData() throws Exception {
        // Initialize the database
        commodityDataRepository.saveAndFlush(commodityData);

        // Get the commodityData
        restCommodityDataMockMvc.perform(get("/api/commodity-data/{id}", commodityData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commodityData.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.mnemonicCode").value(DEFAULT_MNEMONIC_CODE.toString()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND.toString()))
            .andExpect(jsonPath("$.salesName").value(DEFAULT_SALES_NAME.toString()))
            .andExpect(jsonPath("$.colour").value(DEFAULT_COLOUR.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.commodityType").value(DEFAULT_COMMODITY_TYPE.toString()))
            .andExpect(jsonPath("$.normalPrice").value(DEFAULT_NORMAL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingCommodityData() throws Exception {
        // Get the commodityData
        restCommodityDataMockMvc.perform(get("/api/commodity-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommodityData() throws Exception {
        // Initialize the database
        commodityDataService.save(commodityData);

        int databaseSizeBeforeUpdate = commodityDataRepository.findAll().size();

        // Update the commodityData
        CommodityData updatedCommodityData = commodityDataRepository.findOne(commodityData.getId());
        updatedCommodityData
                .companyId(UPDATED_COMPANY_ID)
                .mnemonicCode(UPDATED_MNEMONIC_CODE)
                .brand(UPDATED_BRAND)
                .salesName(UPDATED_SALES_NAME)
                .colour(UPDATED_COLOUR)
                .size(UPDATED_SIZE)
                .commodityType(UPDATED_COMMODITY_TYPE)
                .normalPrice(UPDATED_NORMAL_PRICE)
                .remark(UPDATED_REMARK)
                .status(UPDATED_STATUS);

        restCommodityDataMockMvc.perform(put("/api/commodity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommodityData)))
            .andExpect(status().isOk());

        // Validate the CommodityData in the database
        List<CommodityData> commodityDataList = commodityDataRepository.findAll();
        assertThat(commodityDataList).hasSize(databaseSizeBeforeUpdate);
        CommodityData testCommodityData = commodityDataList.get(commodityDataList.size() - 1);
        assertThat(testCommodityData.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testCommodityData.getMnemonicCode()).isEqualTo(UPDATED_MNEMONIC_CODE);
        assertThat(testCommodityData.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testCommodityData.getSalesName()).isEqualTo(UPDATED_SALES_NAME);
        assertThat(testCommodityData.getColour()).isEqualTo(UPDATED_COLOUR);
        assertThat(testCommodityData.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testCommodityData.getCommodityType()).isEqualTo(UPDATED_COMMODITY_TYPE);
        assertThat(testCommodityData.getNormalPrice()).isEqualTo(UPDATED_NORMAL_PRICE);
        assertThat(testCommodityData.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCommodityData.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCommodityData() throws Exception {
        int databaseSizeBeforeUpdate = commodityDataRepository.findAll().size();

        // Create the CommodityData

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCommodityDataMockMvc.perform(put("/api/commodity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityData)))
            .andExpect(status().isCreated());

        // Validate the CommodityData in the database
        List<CommodityData> commodityDataList = commodityDataRepository.findAll();
        assertThat(commodityDataList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCommodityData() throws Exception {
        // Initialize the database
        commodityDataService.save(commodityData);

        int databaseSizeBeforeDelete = commodityDataRepository.findAll().size();

        // Get the commodityData
        restCommodityDataMockMvc.perform(delete("/api/commodity-data/{id}", commodityData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CommodityData> commodityDataList = commodityDataRepository.findAll();
        assertThat(commodityDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommodityData.class);
    }
}

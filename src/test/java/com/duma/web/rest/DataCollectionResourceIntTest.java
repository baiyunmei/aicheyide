package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.DataCollection;
import com.duma.repository.DataCollectionRepository;
import com.duma.service.DataCollectionService;
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
 * Test class for the DataCollectionResource REST controller.
 *
 * @see DataCollectionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class DataCollectionResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    @Autowired
    private DataCollectionRepository dataCollectionRepository;

    @Autowired
    private DataCollectionService dataCollectionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDataCollectionMockMvc;

    private DataCollection dataCollection;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DataCollectionResource dataCollectionResource = new DataCollectionResource(dataCollectionService);
        this.restDataCollectionMockMvc = MockMvcBuilders.standaloneSetup(dataCollectionResource)
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
    public static DataCollection createEntity(EntityManager em) {
        DataCollection dataCollection = new DataCollection()
                .driverId(DEFAULT_DRIVER_ID)
                .orderId(DEFAULT_ORDER_ID)
                .data(DEFAULT_DATA);
        return dataCollection;
    }

    @Before
    public void initTest() {
        dataCollection = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataCollection() throws Exception {
        int databaseSizeBeforeCreate = dataCollectionRepository.findAll().size();

        // Create the DataCollection

        restDataCollectionMockMvc.perform(post("/api/data-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataCollection)))
            .andExpect(status().isCreated());

        // Validate the DataCollection in the database
        List<DataCollection> dataCollectionList = dataCollectionRepository.findAll();
        assertThat(dataCollectionList).hasSize(databaseSizeBeforeCreate + 1);
        DataCollection testDataCollection = dataCollectionList.get(dataCollectionList.size() - 1);
        assertThat(testDataCollection.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testDataCollection.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testDataCollection.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createDataCollectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataCollectionRepository.findAll().size();

        // Create the DataCollection with an existing ID
        DataCollection existingDataCollection = new DataCollection();
        existingDataCollection.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataCollectionMockMvc.perform(post("/api/data-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDataCollection)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DataCollection> dataCollectionList = dataCollectionRepository.findAll();
        assertThat(dataCollectionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDataCollections() throws Exception {
        // Initialize the database
        dataCollectionRepository.saveAndFlush(dataCollection);

        // Get all the dataCollectionList
        restDataCollectionMockMvc.perform(get("/api/data-collections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataCollection.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }

    @Test
    @Transactional
    public void getDataCollection() throws Exception {
        // Initialize the database
        dataCollectionRepository.saveAndFlush(dataCollection);

        // Get the dataCollection
        restDataCollectionMockMvc.perform(get("/api/data-collections/{id}", dataCollection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dataCollection.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDataCollection() throws Exception {
        // Get the dataCollection
        restDataCollectionMockMvc.perform(get("/api/data-collections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataCollection() throws Exception {
        // Initialize the database
        dataCollectionService.save(dataCollection);

        int databaseSizeBeforeUpdate = dataCollectionRepository.findAll().size();

        // Update the dataCollection
        DataCollection updatedDataCollection = dataCollectionRepository.findOne(dataCollection.getId());
        updatedDataCollection
                .driverId(UPDATED_DRIVER_ID)
                .orderId(UPDATED_ORDER_ID)
                .data(UPDATED_DATA);

        restDataCollectionMockMvc.perform(put("/api/data-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDataCollection)))
            .andExpect(status().isOk());

        // Validate the DataCollection in the database
        List<DataCollection> dataCollectionList = dataCollectionRepository.findAll();
        assertThat(dataCollectionList).hasSize(databaseSizeBeforeUpdate);
        DataCollection testDataCollection = dataCollectionList.get(dataCollectionList.size() - 1);
        assertThat(testDataCollection.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testDataCollection.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testDataCollection.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingDataCollection() throws Exception {
        int databaseSizeBeforeUpdate = dataCollectionRepository.findAll().size();

        // Create the DataCollection

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDataCollectionMockMvc.perform(put("/api/data-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataCollection)))
            .andExpect(status().isCreated());

        // Validate the DataCollection in the database
        List<DataCollection> dataCollectionList = dataCollectionRepository.findAll();
        assertThat(dataCollectionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDataCollection() throws Exception {
        // Initialize the database
        dataCollectionService.save(dataCollection);

        int databaseSizeBeforeDelete = dataCollectionRepository.findAll().size();

        // Get the dataCollection
        restDataCollectionMockMvc.perform(delete("/api/data-collections/{id}", dataCollection.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DataCollection> dataCollectionList = dataCollectionRepository.findAll();
        assertThat(dataCollectionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataCollection.class);
    }
}

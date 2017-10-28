package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.ReceiveVehicle;
import com.duma.repository.ReceiveVehicleRepository;
import com.duma.service.ReceiveVehicleService;
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
 * Test class for the ReceiveVehicleResource REST controller.
 *
 * @see ReceiveVehicleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class ReceiveVehicleResourceIntTest {

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_INFORM_VEHICLE_TIME = 1L;
    private static final Long UPDATED_INFORM_VEHICLE_TIME = 2L;

    private static final String DEFAULT_INFORM_DATA = "AAAAAAAAAA";
    private static final String UPDATED_INFORM_DATA = "BBBBBBBBBB";

    private static final Long DEFAULT_INFORM_VEHICLE_DATE = 1L;
    private static final Long UPDATED_INFORM_VEHICLE_DATE = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_PARK_WAREHOUSE = "AAAAAAAAAA";
    private static final String UPDATED_PARK_WAREHOUSE = "BBBBBBBBBB";

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    @Autowired
    private ReceiveVehicleRepository receiveVehicleRepository;

    @Autowired
    private ReceiveVehicleService receiveVehicleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReceiveVehicleMockMvc;

    private ReceiveVehicle receiveVehicle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReceiveVehicleResource receiveVehicleResource = new ReceiveVehicleResource(receiveVehicleService);
        this.restReceiveVehicleMockMvc = MockMvcBuilders.standaloneSetup(receiveVehicleResource)
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
    public static ReceiveVehicle createEntity(EntityManager em) {
        ReceiveVehicle receiveVehicle = new ReceiveVehicle()
                .vehicleId(DEFAULT_VEHICLE_ID)
                .informVehicleTime(DEFAULT_INFORM_VEHICLE_TIME)
                .informData(DEFAULT_INFORM_DATA)
                .informVehicleDate(DEFAULT_INFORM_VEHICLE_DATE)
                .remark(DEFAULT_REMARK)
                .parkWarehouse(DEFAULT_PARK_WAREHOUSE)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME);
        return receiveVehicle;
    }

    @Before
    public void initTest() {
        receiveVehicle = createEntity(em);
    }

    @Test
    @Transactional
    public void createReceiveVehicle() throws Exception {
        int databaseSizeBeforeCreate = receiveVehicleRepository.findAll().size();

        // Create the ReceiveVehicle

        restReceiveVehicleMockMvc.perform(post("/api/receive-vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receiveVehicle)))
            .andExpect(status().isCreated());

        // Validate the ReceiveVehicle in the database
        List<ReceiveVehicle> receiveVehicleList = receiveVehicleRepository.findAll();
        assertThat(receiveVehicleList).hasSize(databaseSizeBeforeCreate + 1);
        ReceiveVehicle testReceiveVehicle = receiveVehicleList.get(receiveVehicleList.size() - 1);
        assertThat(testReceiveVehicle.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testReceiveVehicle.getInformVehicleTime()).isEqualTo(DEFAULT_INFORM_VEHICLE_TIME);
        assertThat(testReceiveVehicle.getInformData()).isEqualTo(DEFAULT_INFORM_DATA);
        assertThat(testReceiveVehicle.getInformVehicleDate()).isEqualTo(DEFAULT_INFORM_VEHICLE_DATE);
        assertThat(testReceiveVehicle.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testReceiveVehicle.getParkWarehouse()).isEqualTo(DEFAULT_PARK_WAREHOUSE);
        assertThat(testReceiveVehicle.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testReceiveVehicle.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testReceiveVehicle.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testReceiveVehicle.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void createReceiveVehicleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = receiveVehicleRepository.findAll().size();

        // Create the ReceiveVehicle with an existing ID
        ReceiveVehicle existingReceiveVehicle = new ReceiveVehicle();
        existingReceiveVehicle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceiveVehicleMockMvc.perform(post("/api/receive-vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingReceiveVehicle)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ReceiveVehicle> receiveVehicleList = receiveVehicleRepository.findAll();
        assertThat(receiveVehicleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReceiveVehicles() throws Exception {
        // Initialize the database
        receiveVehicleRepository.saveAndFlush(receiveVehicle);

        // Get all the receiveVehicleList
        restReceiveVehicleMockMvc.perform(get("/api/receive-vehicles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(receiveVehicle.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].informVehicleTime").value(hasItem(DEFAULT_INFORM_VEHICLE_TIME.intValue())))
            .andExpect(jsonPath("$.[*].informData").value(hasItem(DEFAULT_INFORM_DATA.toString())))
            .andExpect(jsonPath("$.[*].informVehicleDate").value(hasItem(DEFAULT_INFORM_VEHICLE_DATE.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].parkWarehouse").value(hasItem(DEFAULT_PARK_WAREHOUSE.toString())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())));
    }

    @Test
    @Transactional
    public void getReceiveVehicle() throws Exception {
        // Initialize the database
        receiveVehicleRepository.saveAndFlush(receiveVehicle);

        // Get the receiveVehicle
        restReceiveVehicleMockMvc.perform(get("/api/receive-vehicles/{id}", receiveVehicle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(receiveVehicle.getId().intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.informVehicleTime").value(DEFAULT_INFORM_VEHICLE_TIME.intValue()))
            .andExpect(jsonPath("$.informData").value(DEFAULT_INFORM_DATA.toString()))
            .andExpect(jsonPath("$.informVehicleDate").value(DEFAULT_INFORM_VEHICLE_DATE.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.parkWarehouse").value(DEFAULT_PARK_WAREHOUSE.toString()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReceiveVehicle() throws Exception {
        // Get the receiveVehicle
        restReceiveVehicleMockMvc.perform(get("/api/receive-vehicles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReceiveVehicle() throws Exception {
        // Initialize the database
        receiveVehicleService.save(receiveVehicle);

        int databaseSizeBeforeUpdate = receiveVehicleRepository.findAll().size();

        // Update the receiveVehicle
        ReceiveVehicle updatedReceiveVehicle = receiveVehicleRepository.findOne(receiveVehicle.getId());
        updatedReceiveVehicle
                .vehicleId(UPDATED_VEHICLE_ID)
                .informVehicleTime(UPDATED_INFORM_VEHICLE_TIME)
                .informData(UPDATED_INFORM_DATA)
                .informVehicleDate(UPDATED_INFORM_VEHICLE_DATE)
                .remark(UPDATED_REMARK)
                .parkWarehouse(UPDATED_PARK_WAREHOUSE)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME);

        restReceiveVehicleMockMvc.perform(put("/api/receive-vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReceiveVehicle)))
            .andExpect(status().isOk());

        // Validate the ReceiveVehicle in the database
        List<ReceiveVehicle> receiveVehicleList = receiveVehicleRepository.findAll();
        assertThat(receiveVehicleList).hasSize(databaseSizeBeforeUpdate);
        ReceiveVehicle testReceiveVehicle = receiveVehicleList.get(receiveVehicleList.size() - 1);
        assertThat(testReceiveVehicle.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testReceiveVehicle.getInformVehicleTime()).isEqualTo(UPDATED_INFORM_VEHICLE_TIME);
        assertThat(testReceiveVehicle.getInformData()).isEqualTo(UPDATED_INFORM_DATA);
        assertThat(testReceiveVehicle.getInformVehicleDate()).isEqualTo(UPDATED_INFORM_VEHICLE_DATE);
        assertThat(testReceiveVehicle.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testReceiveVehicle.getParkWarehouse()).isEqualTo(UPDATED_PARK_WAREHOUSE);
        assertThat(testReceiveVehicle.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testReceiveVehicle.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testReceiveVehicle.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testReceiveVehicle.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingReceiveVehicle() throws Exception {
        int databaseSizeBeforeUpdate = receiveVehicleRepository.findAll().size();

        // Create the ReceiveVehicle

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReceiveVehicleMockMvc.perform(put("/api/receive-vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receiveVehicle)))
            .andExpect(status().isCreated());

        // Validate the ReceiveVehicle in the database
        List<ReceiveVehicle> receiveVehicleList = receiveVehicleRepository.findAll();
        assertThat(receiveVehicleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteReceiveVehicle() throws Exception {
        // Initialize the database
        receiveVehicleService.save(receiveVehicle);

        int databaseSizeBeforeDelete = receiveVehicleRepository.findAll().size();

        // Get the receiveVehicle
        restReceiveVehicleMockMvc.perform(delete("/api/receive-vehicles/{id}", receiveVehicle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ReceiveVehicle> receiveVehicleList = receiveVehicleRepository.findAll();
        assertThat(receiveVehicleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceiveVehicle.class);
    }
}

package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.Vehicle;
import com.duma.repository.VehicleRepository;
import com.duma.service.VehicleService;
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
 * Test class for the VehicleResource REST controller.
 *
 * @see VehicleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class VehicleResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_ENGINE = "AAAAAAAAAA";
    private static final String UPDATED_ENGINE = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_SHELF = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_SHELF = "BBBBBBBBBB";

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_COLOUR = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_COLOUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_STOP_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_STOP_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_BRAND_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVehicleMockMvc;

    private Vehicle vehicle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VehicleResource vehicleResource = new VehicleResource(vehicleService);
        this.restVehicleMockMvc = MockMvcBuilders.standaloneSetup(vehicleResource)
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
    public static Vehicle createEntity(EntityManager em) {
        Vehicle vehicle = new Vehicle()
                .companyId(DEFAULT_COMPANY_ID)
                .engine(DEFAULT_ENGINE)
                .vehicleShelf(DEFAULT_VEHICLE_SHELF)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverId(DEFAULT_DRIVER_ID)
                .driverName(DEFAULT_DRIVER_NAME)
                .vehicleColour(DEFAULT_VEHICLE_COLOUR)
                .status(DEFAULT_STATUS)
                .stopLocation(DEFAULT_STOP_LOCATION)
                .brandType(DEFAULT_BRAND_TYPE)
                .company(DEFAULT_COMPANY)
                .vehicleType(DEFAULT_VEHICLE_TYPE)
                .remark(DEFAULT_REMARK);
        return vehicle;
    }

    @Before
    public void initTest() {
        vehicle = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicle() throws Exception {
        int databaseSizeBeforeCreate = vehicleRepository.findAll().size();

        // Create the Vehicle

        restVehicleMockMvc.perform(post("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicle)))
            .andExpect(status().isCreated());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeCreate + 1);
        Vehicle testVehicle = vehicleList.get(vehicleList.size() - 1);
        assertThat(testVehicle.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testVehicle.getEngine()).isEqualTo(DEFAULT_ENGINE);
        assertThat(testVehicle.getVehicleShelf()).isEqualTo(DEFAULT_VEHICLE_SHELF);
        assertThat(testVehicle.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testVehicle.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testVehicle.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testVehicle.getVehicleColour()).isEqualTo(DEFAULT_VEHICLE_COLOUR);
        assertThat(testVehicle.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVehicle.getStopLocation()).isEqualTo(DEFAULT_STOP_LOCATION);
        assertThat(testVehicle.getBrandType()).isEqualTo(DEFAULT_BRAND_TYPE);
        assertThat(testVehicle.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testVehicle.getVehicleType()).isEqualTo(DEFAULT_VEHICLE_TYPE);
        assertThat(testVehicle.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createVehicleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehicleRepository.findAll().size();

        // Create the Vehicle with an existing ID
        Vehicle existingVehicle = new Vehicle();
        existingVehicle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicleMockMvc.perform(post("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingVehicle)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVehicles() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        // Get all the vehicleList
        restVehicleMockMvc.perform(get("/api/vehicles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicle.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].engine").value(hasItem(DEFAULT_ENGINE.toString())))
            .andExpect(jsonPath("$.[*].vehicleShelf").value(hasItem(DEFAULT_VEHICLE_SHELF.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].vehicleColour").value(hasItem(DEFAULT_VEHICLE_COLOUR.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].stopLocation").value(hasItem(DEFAULT_STOP_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].brandType").value(hasItem(DEFAULT_BRAND_TYPE.toString())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].vehicleType").value(hasItem(DEFAULT_VEHICLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        // Get the vehicle
        restVehicleMockMvc.perform(get("/api/vehicles/{id}", vehicle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vehicle.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.engine").value(DEFAULT_ENGINE.toString()))
            .andExpect(jsonPath("$.vehicleShelf").value(DEFAULT_VEHICLE_SHELF.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.vehicleColour").value(DEFAULT_VEHICLE_COLOUR.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.stopLocation").value(DEFAULT_STOP_LOCATION.toString()))
            .andExpect(jsonPath("$.brandType").value(DEFAULT_BRAND_TYPE.toString()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY.toString()))
            .andExpect(jsonPath("$.vehicleType").value(DEFAULT_VEHICLE_TYPE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVehicle() throws Exception {
        // Get the vehicle
        restVehicleMockMvc.perform(get("/api/vehicles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicle() throws Exception {
        // Initialize the database
        vehicleService.save(vehicle);

        int databaseSizeBeforeUpdate = vehicleRepository.findAll().size();

        // Update the vehicle
        Vehicle updatedVehicle = vehicleRepository.findOne(vehicle.getId());
        updatedVehicle
                .companyId(UPDATED_COMPANY_ID)
                .engine(UPDATED_ENGINE)
                .vehicleShelf(UPDATED_VEHICLE_SHELF)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverId(UPDATED_DRIVER_ID)
                .driverName(UPDATED_DRIVER_NAME)
                .vehicleColour(UPDATED_VEHICLE_COLOUR)
                .status(UPDATED_STATUS)
                .stopLocation(UPDATED_STOP_LOCATION)
                .brandType(UPDATED_BRAND_TYPE)
                .company(UPDATED_COMPANY)
                .vehicleType(UPDATED_VEHICLE_TYPE)
                .remark(UPDATED_REMARK);

        restVehicleMockMvc.perform(put("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVehicle)))
            .andExpect(status().isOk());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeUpdate);
        Vehicle testVehicle = vehicleList.get(vehicleList.size() - 1);
        assertThat(testVehicle.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testVehicle.getEngine()).isEqualTo(UPDATED_ENGINE);
        assertThat(testVehicle.getVehicleShelf()).isEqualTo(UPDATED_VEHICLE_SHELF);
        assertThat(testVehicle.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testVehicle.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testVehicle.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testVehicle.getVehicleColour()).isEqualTo(UPDATED_VEHICLE_COLOUR);
        assertThat(testVehicle.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVehicle.getStopLocation()).isEqualTo(UPDATED_STOP_LOCATION);
        assertThat(testVehicle.getBrandType()).isEqualTo(UPDATED_BRAND_TYPE);
        assertThat(testVehicle.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testVehicle.getVehicleType()).isEqualTo(UPDATED_VEHICLE_TYPE);
        assertThat(testVehicle.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicle() throws Exception {
        int databaseSizeBeforeUpdate = vehicleRepository.findAll().size();

        // Create the Vehicle

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVehicleMockMvc.perform(put("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicle)))
            .andExpect(status().isCreated());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVehicle() throws Exception {
        // Initialize the database
        vehicleService.save(vehicle);

        int databaseSizeBeforeDelete = vehicleRepository.findAll().size();

        // Get the vehicle
        restVehicleMockMvc.perform(delete("/api/vehicles/{id}", vehicle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehicle.class);
    }
}

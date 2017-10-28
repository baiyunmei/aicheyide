package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.DriverMate;
import com.duma.repository.DriverMateRepository;
import com.duma.service.DriverMateService;
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
 * Test class for the DriverMateResource REST controller.
 *
 * @see DriverMateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class DriverMateResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private DriverMateRepository driverMateRepository;

    @Autowired
    private DriverMateService driverMateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDriverMateMockMvc;

    private DriverMate driverMate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DriverMateResource driverMateResource = new DriverMateResource(driverMateService);
        this.restDriverMateMockMvc = MockMvcBuilders.standaloneSetup(driverMateResource)
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
    public static DriverMate createEntity(EntityManager em) {
        DriverMate driverMate = new DriverMate()
                .driverId(DEFAULT_DRIVER_ID)
                .name(DEFAULT_NAME)
                .sex(DEFAULT_SEX)
                .phone(DEFAULT_PHONE)
                .certificateType(DEFAULT_CERTIFICATE_TYPE)
                .certificatePhone(DEFAULT_CERTIFICATE_PHONE)
                .location(DEFAULT_LOCATION)
                .unitName(DEFAULT_UNIT_NAME)
                .residentialAddress(DEFAULT_RESIDENTIAL_ADDRESS)
                .unitAddress(DEFAULT_UNIT_ADDRESS);
        return driverMate;
    }

    @Before
    public void initTest() {
        driverMate = createEntity(em);
    }

    @Test
    @Transactional
    public void createDriverMate() throws Exception {
        int databaseSizeBeforeCreate = driverMateRepository.findAll().size();

        // Create the DriverMate

        restDriverMateMockMvc.perform(post("/api/driver-mates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(driverMate)))
            .andExpect(status().isCreated());

        // Validate the DriverMate in the database
        List<DriverMate> driverMateList = driverMateRepository.findAll();
        assertThat(driverMateList).hasSize(databaseSizeBeforeCreate + 1);
        DriverMate testDriverMate = driverMateList.get(driverMateList.size() - 1);
        assertThat(testDriverMate.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testDriverMate.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDriverMate.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testDriverMate.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testDriverMate.getCertificateType()).isEqualTo(DEFAULT_CERTIFICATE_TYPE);
        assertThat(testDriverMate.getCertificatePhone()).isEqualTo(DEFAULT_CERTIFICATE_PHONE);
        assertThat(testDriverMate.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testDriverMate.getUnitName()).isEqualTo(DEFAULT_UNIT_NAME);
        assertThat(testDriverMate.getResidentialAddress()).isEqualTo(DEFAULT_RESIDENTIAL_ADDRESS);
        assertThat(testDriverMate.getUnitAddress()).isEqualTo(DEFAULT_UNIT_ADDRESS);
    }

    @Test
    @Transactional
    public void createDriverMateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = driverMateRepository.findAll().size();

        // Create the DriverMate with an existing ID
        DriverMate existingDriverMate = new DriverMate();
        existingDriverMate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDriverMateMockMvc.perform(post("/api/driver-mates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDriverMate)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DriverMate> driverMateList = driverMateRepository.findAll();
        assertThat(driverMateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDriverMates() throws Exception {
        // Initialize the database
        driverMateRepository.saveAndFlush(driverMate);

        // Get all the driverMateList
        restDriverMateMockMvc.perform(get("/api/driver-mates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(driverMate.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].certificateType").value(hasItem(DEFAULT_CERTIFICATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].certificatePhone").value(hasItem(DEFAULT_CERTIFICATE_PHONE.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].unitName").value(hasItem(DEFAULT_UNIT_NAME.toString())))
            .andExpect(jsonPath("$.[*].residentialAddress").value(hasItem(DEFAULT_RESIDENTIAL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].unitAddress").value(hasItem(DEFAULT_UNIT_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void getDriverMate() throws Exception {
        // Initialize the database
        driverMateRepository.saveAndFlush(driverMate);

        // Get the driverMate
        restDriverMateMockMvc.perform(get("/api/driver-mates/{id}", driverMate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(driverMate.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.certificateType").value(DEFAULT_CERTIFICATE_TYPE.toString()))
            .andExpect(jsonPath("$.certificatePhone").value(DEFAULT_CERTIFICATE_PHONE.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.unitName").value(DEFAULT_UNIT_NAME.toString()))
            .andExpect(jsonPath("$.residentialAddress").value(DEFAULT_RESIDENTIAL_ADDRESS.toString()))
            .andExpect(jsonPath("$.unitAddress").value(DEFAULT_UNIT_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDriverMate() throws Exception {
        // Get the driverMate
        restDriverMateMockMvc.perform(get("/api/driver-mates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDriverMate() throws Exception {
        // Initialize the database
        driverMateService.save(driverMate);

        int databaseSizeBeforeUpdate = driverMateRepository.findAll().size();

        // Update the driverMate
        DriverMate updatedDriverMate = driverMateRepository.findOne(driverMate.getId());
        updatedDriverMate
                .driverId(UPDATED_DRIVER_ID)
                .name(UPDATED_NAME)
                .sex(UPDATED_SEX)
                .phone(UPDATED_PHONE)
                .certificateType(UPDATED_CERTIFICATE_TYPE)
                .certificatePhone(UPDATED_CERTIFICATE_PHONE)
                .location(UPDATED_LOCATION)
                .unitName(UPDATED_UNIT_NAME)
                .residentialAddress(UPDATED_RESIDENTIAL_ADDRESS)
                .unitAddress(UPDATED_UNIT_ADDRESS);

        restDriverMateMockMvc.perform(put("/api/driver-mates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDriverMate)))
            .andExpect(status().isOk());

        // Validate the DriverMate in the database
        List<DriverMate> driverMateList = driverMateRepository.findAll();
        assertThat(driverMateList).hasSize(databaseSizeBeforeUpdate);
        DriverMate testDriverMate = driverMateList.get(driverMateList.size() - 1);
        assertThat(testDriverMate.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testDriverMate.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDriverMate.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testDriverMate.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testDriverMate.getCertificateType()).isEqualTo(UPDATED_CERTIFICATE_TYPE);
        assertThat(testDriverMate.getCertificatePhone()).isEqualTo(UPDATED_CERTIFICATE_PHONE);
        assertThat(testDriverMate.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testDriverMate.getUnitName()).isEqualTo(UPDATED_UNIT_NAME);
        assertThat(testDriverMate.getResidentialAddress()).isEqualTo(UPDATED_RESIDENTIAL_ADDRESS);
        assertThat(testDriverMate.getUnitAddress()).isEqualTo(UPDATED_UNIT_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingDriverMate() throws Exception {
        int databaseSizeBeforeUpdate = driverMateRepository.findAll().size();

        // Create the DriverMate

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDriverMateMockMvc.perform(put("/api/driver-mates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(driverMate)))
            .andExpect(status().isCreated());

        // Validate the DriverMate in the database
        List<DriverMate> driverMateList = driverMateRepository.findAll();
        assertThat(driverMateList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDriverMate() throws Exception {
        // Initialize the database
        driverMateService.save(driverMate);

        int databaseSizeBeforeDelete = driverMateRepository.findAll().size();

        // Get the driverMate
        restDriverMateMockMvc.perform(delete("/api/driver-mates/{id}", driverMate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DriverMate> driverMateList = driverMateRepository.findAll();
        assertThat(driverMateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DriverMate.class);
    }
}

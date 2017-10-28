package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.GPSRefit;
import com.duma.repository.GPSRefitRepository;
import com.duma.service.GPSRefitService;
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
 * Test class for the GPSRefitResource REST controller.
 *
 * @see GPSRefitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class GPSRefitResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_RECEIPT_DATE = 1L;
    private static final Long UPDATED_RECEIPT_DATE = 2L;

    private static final Long DEFAULT_OPERATING_DATE = 1L;
    private static final Long UPDATED_OPERATING_DATE = 2L;

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_VALIDATE_TIME = 1L;
    private static final Long UPDATED_VALIDATE_TIME = 2L;

    private static final Integer DEFAULT_KILOMETRE = 1;
    private static final Integer UPDATED_KILOMETRE = 2;

    private static final Integer DEFAULT_DAMAGE = 1;
    private static final Integer UPDATED_DAMAGE = 2;

    private static final String DEFAULT_DESCRIBES = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIBES = "BBBBBBBBBB";

    private static final String DEFAULT_DAMAGEP_ICTURE = "AAAAAAAAAA";
    private static final String UPDATED_DAMAGEP_ICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private GPSRefitRepository gPSRefitRepository;

    @Autowired
    private GPSRefitService gPSRefitService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGPSRefitMockMvc;

    private GPSRefit gPSRefit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GPSRefitResource gPSRefitResource = new GPSRefitResource(gPSRefitService);
        this.restGPSRefitMockMvc = MockMvcBuilders.standaloneSetup(gPSRefitResource)
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
    public static GPSRefit createEntity(EntityManager em) {
        GPSRefit gPSRefit = new GPSRefit()
                .driverId(DEFAULT_DRIVER_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .receiptNumber(DEFAULT_RECEIPT_NUMBER)
                .receiptDate(DEFAULT_RECEIPT_DATE)
                .operatingDate(DEFAULT_OPERATING_DATE)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .validateTime(DEFAULT_VALIDATE_TIME)
                .kilometre(DEFAULT_KILOMETRE)
                .damage(DEFAULT_DAMAGE)
                .describes(DEFAULT_DESCRIBES)
                .damagepIcture(DEFAULT_DAMAGEP_ICTURE)
                .remark(DEFAULT_REMARK);
        return gPSRefit;
    }

    @Before
    public void initTest() {
        gPSRefit = createEntity(em);
    }

    @Test
    @Transactional
    public void createGPSRefit() throws Exception {
        int databaseSizeBeforeCreate = gPSRefitRepository.findAll().size();

        // Create the GPSRefit

        restGPSRefitMockMvc.perform(post("/api/g-ps-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gPSRefit)))
            .andExpect(status().isCreated());

        // Validate the GPSRefit in the database
        List<GPSRefit> gPSRefitList = gPSRefitRepository.findAll();
        assertThat(gPSRefitList).hasSize(databaseSizeBeforeCreate + 1);
        GPSRefit testGPSRefit = gPSRefitList.get(gPSRefitList.size() - 1);
        assertThat(testGPSRefit.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testGPSRefit.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testGPSRefit.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testGPSRefit.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testGPSRefit.getReceiptDate()).isEqualTo(DEFAULT_RECEIPT_DATE);
        assertThat(testGPSRefit.getOperatingDate()).isEqualTo(DEFAULT_OPERATING_DATE);
        assertThat(testGPSRefit.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testGPSRefit.getValidateTime()).isEqualTo(DEFAULT_VALIDATE_TIME);
        assertThat(testGPSRefit.getKilometre()).isEqualTo(DEFAULT_KILOMETRE);
        assertThat(testGPSRefit.getDamage()).isEqualTo(DEFAULT_DAMAGE);
        assertThat(testGPSRefit.getDescribes()).isEqualTo(DEFAULT_DESCRIBES);
        assertThat(testGPSRefit.getDamagepIcture()).isEqualTo(DEFAULT_DAMAGEP_ICTURE);
        assertThat(testGPSRefit.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createGPSRefitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gPSRefitRepository.findAll().size();

        // Create the GPSRefit with an existing ID
        GPSRefit existingGPSRefit = new GPSRefit();
        existingGPSRefit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGPSRefitMockMvc.perform(post("/api/g-ps-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingGPSRefit)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<GPSRefit> gPSRefitList = gPSRefitRepository.findAll();
        assertThat(gPSRefitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGPSRefits() throws Exception {
        // Initialize the database
        gPSRefitRepository.saveAndFlush(gPSRefit);

        // Get all the gPSRefitList
        restGPSRefitMockMvc.perform(get("/api/g-ps-refits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gPSRefit.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].receiptDate").value(hasItem(DEFAULT_RECEIPT_DATE.intValue())))
            .andExpect(jsonPath("$.[*].operatingDate").value(hasItem(DEFAULT_OPERATING_DATE.intValue())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].validateTime").value(hasItem(DEFAULT_VALIDATE_TIME.intValue())))
            .andExpect(jsonPath("$.[*].kilometre").value(hasItem(DEFAULT_KILOMETRE)))
            .andExpect(jsonPath("$.[*].damage").value(hasItem(DEFAULT_DAMAGE)))
            .andExpect(jsonPath("$.[*].describes").value(hasItem(DEFAULT_DESCRIBES.toString())))
            .andExpect(jsonPath("$.[*].damagepIcture").value(hasItem(DEFAULT_DAMAGEP_ICTURE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getGPSRefit() throws Exception {
        // Initialize the database
        gPSRefitRepository.saveAndFlush(gPSRefit);

        // Get the gPSRefit
        restGPSRefitMockMvc.perform(get("/api/g-ps-refits/{id}", gPSRefit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gPSRefit.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER.toString()))
            .andExpect(jsonPath("$.receiptDate").value(DEFAULT_RECEIPT_DATE.intValue()))
            .andExpect(jsonPath("$.operatingDate").value(DEFAULT_OPERATING_DATE.intValue()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.validateTime").value(DEFAULT_VALIDATE_TIME.intValue()))
            .andExpect(jsonPath("$.kilometre").value(DEFAULT_KILOMETRE))
            .andExpect(jsonPath("$.damage").value(DEFAULT_DAMAGE))
            .andExpect(jsonPath("$.describes").value(DEFAULT_DESCRIBES.toString()))
            .andExpect(jsonPath("$.damagepIcture").value(DEFAULT_DAMAGEP_ICTURE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGPSRefit() throws Exception {
        // Get the gPSRefit
        restGPSRefitMockMvc.perform(get("/api/g-ps-refits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGPSRefit() throws Exception {
        // Initialize the database
        gPSRefitService.save(gPSRefit);

        int databaseSizeBeforeUpdate = gPSRefitRepository.findAll().size();

        // Update the gPSRefit
        GPSRefit updatedGPSRefit = gPSRefitRepository.findOne(gPSRefit.getId());
        updatedGPSRefit
                .driverId(UPDATED_DRIVER_ID)
                .companyId(UPDATED_COMPANY_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .receiptNumber(UPDATED_RECEIPT_NUMBER)
                .receiptDate(UPDATED_RECEIPT_DATE)
                .operatingDate(UPDATED_OPERATING_DATE)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .validateTime(UPDATED_VALIDATE_TIME)
                .kilometre(UPDATED_KILOMETRE)
                .damage(UPDATED_DAMAGE)
                .describes(UPDATED_DESCRIBES)
                .damagepIcture(UPDATED_DAMAGEP_ICTURE)
                .remark(UPDATED_REMARK);

        restGPSRefitMockMvc.perform(put("/api/g-ps-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGPSRefit)))
            .andExpect(status().isOk());

        // Validate the GPSRefit in the database
        List<GPSRefit> gPSRefitList = gPSRefitRepository.findAll();
        assertThat(gPSRefitList).hasSize(databaseSizeBeforeUpdate);
        GPSRefit testGPSRefit = gPSRefitList.get(gPSRefitList.size() - 1);
        assertThat(testGPSRefit.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testGPSRefit.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testGPSRefit.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testGPSRefit.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testGPSRefit.getReceiptDate()).isEqualTo(UPDATED_RECEIPT_DATE);
        assertThat(testGPSRefit.getOperatingDate()).isEqualTo(UPDATED_OPERATING_DATE);
        assertThat(testGPSRefit.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testGPSRefit.getValidateTime()).isEqualTo(UPDATED_VALIDATE_TIME);
        assertThat(testGPSRefit.getKilometre()).isEqualTo(UPDATED_KILOMETRE);
        assertThat(testGPSRefit.getDamage()).isEqualTo(UPDATED_DAMAGE);
        assertThat(testGPSRefit.getDescribes()).isEqualTo(UPDATED_DESCRIBES);
        assertThat(testGPSRefit.getDamagepIcture()).isEqualTo(UPDATED_DAMAGEP_ICTURE);
        assertThat(testGPSRefit.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingGPSRefit() throws Exception {
        int databaseSizeBeforeUpdate = gPSRefitRepository.findAll().size();

        // Create the GPSRefit

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGPSRefitMockMvc.perform(put("/api/g-ps-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gPSRefit)))
            .andExpect(status().isCreated());

        // Validate the GPSRefit in the database
        List<GPSRefit> gPSRefitList = gPSRefitRepository.findAll();
        assertThat(gPSRefitList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGPSRefit() throws Exception {
        // Initialize the database
        gPSRefitService.save(gPSRefit);

        int databaseSizeBeforeDelete = gPSRefitRepository.findAll().size();

        // Get the gPSRefit
        restGPSRefitMockMvc.perform(delete("/api/g-ps-refits/{id}", gPSRefit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GPSRefit> gPSRefitList = gPSRefitRepository.findAll();
        assertThat(gPSRefitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GPSRefit.class);
    }
}

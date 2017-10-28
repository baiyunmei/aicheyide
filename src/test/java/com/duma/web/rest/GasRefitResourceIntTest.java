package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.GasRefit;
import com.duma.repository.GasRefitRepository;
import com.duma.service.GasRefitService;
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
 * Test class for the GasRefitResource REST controller.
 *
 * @see GasRefitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class GasRefitResourceIntTest {

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Integer DEFAULT_REFIT = 1;
    private static final Integer UPDATED_REFIT = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_REFIT_RECORD = "AAAAAAAAAA";
    private static final String UPDATED_REFIT_RECORD = "BBBBBBBBBB";

    private static final Long DEFAULT_REFIT_TIME = 1L;
    private static final Long UPDATED_REFIT_TIME = 2L;

    private static final String DEFAULT_REFIT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_REFIT_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    @Autowired
    private GasRefitRepository gasRefitRepository;

    @Autowired
    private GasRefitService gasRefitService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGasRefitMockMvc;

    private GasRefit gasRefit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GasRefitResource gasRefitResource = new GasRefitResource(gasRefitService);
        this.restGasRefitMockMvc = MockMvcBuilders.standaloneSetup(gasRefitResource)
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
    public static GasRefit createEntity(EntityManager em) {
        GasRefit gasRefit = new GasRefit()
                .vehicleId(DEFAULT_VEHICLE_ID)
                .refit(DEFAULT_REFIT)
                .remark(DEFAULT_REMARK)
                .refitRecord(DEFAULT_REFIT_RECORD)
                .refitTime(DEFAULT_REFIT_TIME)
                .refitUnit(DEFAULT_REFIT_UNIT)
                .phone(DEFAULT_PHONE)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME);
        return gasRefit;
    }

    @Before
    public void initTest() {
        gasRefit = createEntity(em);
    }

    @Test
    @Transactional
    public void createGasRefit() throws Exception {
        int databaseSizeBeforeCreate = gasRefitRepository.findAll().size();

        // Create the GasRefit

        restGasRefitMockMvc.perform(post("/api/gas-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gasRefit)))
            .andExpect(status().isCreated());

        // Validate the GasRefit in the database
        List<GasRefit> gasRefitList = gasRefitRepository.findAll();
        assertThat(gasRefitList).hasSize(databaseSizeBeforeCreate + 1);
        GasRefit testGasRefit = gasRefitList.get(gasRefitList.size() - 1);
        assertThat(testGasRefit.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testGasRefit.getRefit()).isEqualTo(DEFAULT_REFIT);
        assertThat(testGasRefit.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testGasRefit.getRefitRecord()).isEqualTo(DEFAULT_REFIT_RECORD);
        assertThat(testGasRefit.getRefitTime()).isEqualTo(DEFAULT_REFIT_TIME);
        assertThat(testGasRefit.getRefitUnit()).isEqualTo(DEFAULT_REFIT_UNIT);
        assertThat(testGasRefit.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testGasRefit.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testGasRefit.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testGasRefit.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testGasRefit.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void createGasRefitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gasRefitRepository.findAll().size();

        // Create the GasRefit with an existing ID
        GasRefit existingGasRefit = new GasRefit();
        existingGasRefit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGasRefitMockMvc.perform(post("/api/gas-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingGasRefit)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<GasRefit> gasRefitList = gasRefitRepository.findAll();
        assertThat(gasRefitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGasRefits() throws Exception {
        // Initialize the database
        gasRefitRepository.saveAndFlush(gasRefit);

        // Get all the gasRefitList
        restGasRefitMockMvc.perform(get("/api/gas-refits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gasRefit.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].refit").value(hasItem(DEFAULT_REFIT)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].refitRecord").value(hasItem(DEFAULT_REFIT_RECORD.toString())))
            .andExpect(jsonPath("$.[*].refitTime").value(hasItem(DEFAULT_REFIT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].refitUnit").value(hasItem(DEFAULT_REFIT_UNIT.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())));
    }

    @Test
    @Transactional
    public void getGasRefit() throws Exception {
        // Initialize the database
        gasRefitRepository.saveAndFlush(gasRefit);

        // Get the gasRefit
        restGasRefitMockMvc.perform(get("/api/gas-refits/{id}", gasRefit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gasRefit.getId().intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.refit").value(DEFAULT_REFIT))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.refitRecord").value(DEFAULT_REFIT_RECORD.toString()))
            .andExpect(jsonPath("$.refitTime").value(DEFAULT_REFIT_TIME.intValue()))
            .andExpect(jsonPath("$.refitUnit").value(DEFAULT_REFIT_UNIT.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGasRefit() throws Exception {
        // Get the gasRefit
        restGasRefitMockMvc.perform(get("/api/gas-refits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGasRefit() throws Exception {
        // Initialize the database
        gasRefitService.save(gasRefit);

        int databaseSizeBeforeUpdate = gasRefitRepository.findAll().size();

        // Update the gasRefit
        GasRefit updatedGasRefit = gasRefitRepository.findOne(gasRefit.getId());
        updatedGasRefit
                .vehicleId(UPDATED_VEHICLE_ID)
                .refit(UPDATED_REFIT)
                .remark(UPDATED_REMARK)
                .refitRecord(UPDATED_REFIT_RECORD)
                .refitTime(UPDATED_REFIT_TIME)
                .refitUnit(UPDATED_REFIT_UNIT)
                .phone(UPDATED_PHONE)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME);

        restGasRefitMockMvc.perform(put("/api/gas-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGasRefit)))
            .andExpect(status().isOk());

        // Validate the GasRefit in the database
        List<GasRefit> gasRefitList = gasRefitRepository.findAll();
        assertThat(gasRefitList).hasSize(databaseSizeBeforeUpdate);
        GasRefit testGasRefit = gasRefitList.get(gasRefitList.size() - 1);
        assertThat(testGasRefit.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testGasRefit.getRefit()).isEqualTo(UPDATED_REFIT);
        assertThat(testGasRefit.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testGasRefit.getRefitRecord()).isEqualTo(UPDATED_REFIT_RECORD);
        assertThat(testGasRefit.getRefitTime()).isEqualTo(UPDATED_REFIT_TIME);
        assertThat(testGasRefit.getRefitUnit()).isEqualTo(UPDATED_REFIT_UNIT);
        assertThat(testGasRefit.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testGasRefit.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testGasRefit.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testGasRefit.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testGasRefit.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingGasRefit() throws Exception {
        int databaseSizeBeforeUpdate = gasRefitRepository.findAll().size();

        // Create the GasRefit

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGasRefitMockMvc.perform(put("/api/gas-refits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gasRefit)))
            .andExpect(status().isCreated());

        // Validate the GasRefit in the database
        List<GasRefit> gasRefitList = gasRefitRepository.findAll();
        assertThat(gasRefitList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGasRefit() throws Exception {
        // Initialize the database
        gasRefitService.save(gasRefit);

        int databaseSizeBeforeDelete = gasRefitRepository.findAll().size();

        // Get the gasRefit
        restGasRefitMockMvc.perform(delete("/api/gas-refits/{id}", gasRefit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GasRefit> gasRefitList = gasRefitRepository.findAll();
        assertThat(gasRefitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GasRefit.class);
    }
}

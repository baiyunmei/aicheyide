package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.OnBrand;
import com.duma.repository.OnBrandRepository;
import com.duma.service.OnBrandService;
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
 * Test class for the OnBrandResource REST controller.
 *
 * @see OnBrandResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class OnBrandResourceIntTest {

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final String DEFAULT_GPSDEVICE = "AAAAAAAAAA";
    private static final String UPDATED_GPSDEVICE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_GPS_INSTALL = "AAAAAAAAAA";
    private static final String UPDATED_GPS_INSTALL = "BBBBBBBBBB";

    private static final Long DEFAULT_INSTALL_TIME = 1L;
    private static final Long UPDATED_INSTALL_TIME = 2L;

    private static final String DEFAULT_FOUNDER = "AAAAAAAAAA";
    private static final String UPDATED_FOUNDER = "BBBBBBBBBB";

    private static final Long DEFAULT_FOUNDER_TIME = 1L;
    private static final Long UPDATED_FOUNDER_TIME = 2L;

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER_TIME = 1L;
    private static final Long UPDATED_MODIFIER_TIME = 2L;

    @Autowired
    private OnBrandRepository onBrandRepository;

    @Autowired
    private OnBrandService onBrandService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOnBrandMockMvc;

    private OnBrand onBrand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OnBrandResource onBrandResource = new OnBrandResource(onBrandService);
        this.restOnBrandMockMvc = MockMvcBuilders.standaloneSetup(onBrandResource)
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
    public static OnBrand createEntity(EntityManager em) {
        OnBrand onBrand = new OnBrand()
                .vehicleId(DEFAULT_VEHICLE_ID)
                .gpsdevice(DEFAULT_GPSDEVICE)
                .remark(DEFAULT_REMARK)
                .gpsInstall(DEFAULT_GPS_INSTALL)
                .installTime(DEFAULT_INSTALL_TIME)
                .founder(DEFAULT_FOUNDER)
                .founderTime(DEFAULT_FOUNDER_TIME)
                .modifier(DEFAULT_MODIFIER)
                .modifierTime(DEFAULT_MODIFIER_TIME);
        return onBrand;
    }

    @Before
    public void initTest() {
        onBrand = createEntity(em);
    }

    @Test
    @Transactional
    public void createOnBrand() throws Exception {
        int databaseSizeBeforeCreate = onBrandRepository.findAll().size();

        // Create the OnBrand

        restOnBrandMockMvc.perform(post("/api/on-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onBrand)))
            .andExpect(status().isCreated());

        // Validate the OnBrand in the database
        List<OnBrand> onBrandList = onBrandRepository.findAll();
        assertThat(onBrandList).hasSize(databaseSizeBeforeCreate + 1);
        OnBrand testOnBrand = onBrandList.get(onBrandList.size() - 1);
        assertThat(testOnBrand.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testOnBrand.getGpsdevice()).isEqualTo(DEFAULT_GPSDEVICE);
        assertThat(testOnBrand.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testOnBrand.getGpsInstall()).isEqualTo(DEFAULT_GPS_INSTALL);
        assertThat(testOnBrand.getInstallTime()).isEqualTo(DEFAULT_INSTALL_TIME);
        assertThat(testOnBrand.getFounder()).isEqualTo(DEFAULT_FOUNDER);
        assertThat(testOnBrand.getFounderTime()).isEqualTo(DEFAULT_FOUNDER_TIME);
        assertThat(testOnBrand.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testOnBrand.getModifierTime()).isEqualTo(DEFAULT_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void createOnBrandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = onBrandRepository.findAll().size();

        // Create the OnBrand with an existing ID
        OnBrand existingOnBrand = new OnBrand();
        existingOnBrand.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnBrandMockMvc.perform(post("/api/on-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingOnBrand)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<OnBrand> onBrandList = onBrandRepository.findAll();
        assertThat(onBrandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOnBrands() throws Exception {
        // Initialize the database
        onBrandRepository.saveAndFlush(onBrand);

        // Get all the onBrandList
        restOnBrandMockMvc.perform(get("/api/on-brands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onBrand.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].gpsdevice").value(hasItem(DEFAULT_GPSDEVICE.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].gpsInstall").value(hasItem(DEFAULT_GPS_INSTALL.toString())))
            .andExpect(jsonPath("$.[*].installTime").value(hasItem(DEFAULT_INSTALL_TIME.intValue())))
            .andExpect(jsonPath("$.[*].founder").value(hasItem(DEFAULT_FOUNDER.toString())))
            .andExpect(jsonPath("$.[*].founderTime").value(hasItem(DEFAULT_FOUNDER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierTime").value(hasItem(DEFAULT_MODIFIER_TIME.intValue())));
    }

    @Test
    @Transactional
    public void getOnBrand() throws Exception {
        // Initialize the database
        onBrandRepository.saveAndFlush(onBrand);

        // Get the onBrand
        restOnBrandMockMvc.perform(get("/api/on-brands/{id}", onBrand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(onBrand.getId().intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.gpsdevice").value(DEFAULT_GPSDEVICE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.gpsInstall").value(DEFAULT_GPS_INSTALL.toString()))
            .andExpect(jsonPath("$.installTime").value(DEFAULT_INSTALL_TIME.intValue()))
            .andExpect(jsonPath("$.founder").value(DEFAULT_FOUNDER.toString()))
            .andExpect(jsonPath("$.founderTime").value(DEFAULT_FOUNDER_TIME.intValue()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierTime").value(DEFAULT_MODIFIER_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOnBrand() throws Exception {
        // Get the onBrand
        restOnBrandMockMvc.perform(get("/api/on-brands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnBrand() throws Exception {
        // Initialize the database
        onBrandService.save(onBrand);

        int databaseSizeBeforeUpdate = onBrandRepository.findAll().size();

        // Update the onBrand
        OnBrand updatedOnBrand = onBrandRepository.findOne(onBrand.getId());
        updatedOnBrand
                .vehicleId(UPDATED_VEHICLE_ID)
                .gpsdevice(UPDATED_GPSDEVICE)
                .remark(UPDATED_REMARK)
                .gpsInstall(UPDATED_GPS_INSTALL)
                .installTime(UPDATED_INSTALL_TIME)
                .founder(UPDATED_FOUNDER)
                .founderTime(UPDATED_FOUNDER_TIME)
                .modifier(UPDATED_MODIFIER)
                .modifierTime(UPDATED_MODIFIER_TIME);

        restOnBrandMockMvc.perform(put("/api/on-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOnBrand)))
            .andExpect(status().isOk());

        // Validate the OnBrand in the database
        List<OnBrand> onBrandList = onBrandRepository.findAll();
        assertThat(onBrandList).hasSize(databaseSizeBeforeUpdate);
        OnBrand testOnBrand = onBrandList.get(onBrandList.size() - 1);
        assertThat(testOnBrand.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testOnBrand.getGpsdevice()).isEqualTo(UPDATED_GPSDEVICE);
        assertThat(testOnBrand.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testOnBrand.getGpsInstall()).isEqualTo(UPDATED_GPS_INSTALL);
        assertThat(testOnBrand.getInstallTime()).isEqualTo(UPDATED_INSTALL_TIME);
        assertThat(testOnBrand.getFounder()).isEqualTo(UPDATED_FOUNDER);
        assertThat(testOnBrand.getFounderTime()).isEqualTo(UPDATED_FOUNDER_TIME);
        assertThat(testOnBrand.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testOnBrand.getModifierTime()).isEqualTo(UPDATED_MODIFIER_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingOnBrand() throws Exception {
        int databaseSizeBeforeUpdate = onBrandRepository.findAll().size();

        // Create the OnBrand

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOnBrandMockMvc.perform(put("/api/on-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onBrand)))
            .andExpect(status().isCreated());

        // Validate the OnBrand in the database
        List<OnBrand> onBrandList = onBrandRepository.findAll();
        assertThat(onBrandList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOnBrand() throws Exception {
        // Initialize the database
        onBrandService.save(onBrand);

        int databaseSizeBeforeDelete = onBrandRepository.findAll().size();

        // Get the onBrand
        restOnBrandMockMvc.perform(delete("/api/on-brands/{id}", onBrand.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OnBrand> onBrandList = onBrandRepository.findAll();
        assertThat(onBrandList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnBrand.class);
    }
}

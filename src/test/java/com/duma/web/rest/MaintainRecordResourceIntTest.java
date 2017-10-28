package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.MaintainRecord;
import com.duma.repository.MaintainRecordRepository;
import com.duma.service.MaintainRecordService;
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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MaintainRecordResource REST controller.
 *
 * @see MaintainRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class MaintainRecordResourceIntTest {

    private static final Long DEFAULT_DRIVER_ID = 1L;
    private static final Long UPDATED_DRIVER_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_MAINTAIN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MAINTAIN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PLATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_MAINTAIN_TIME = 1L;
    private static final Long UPDATED_MAINTAIN_TIME = 2L;

    private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY = new BigDecimal(2);

    private static final String DEFAULT_REPAIR_FACTORY = "AAAAAAAAAA";
    private static final String UPDATED_REPAIR_FACTORY = "BBBBBBBBBB";

    private static final Long DEFAULT_LEAVE_FACTORY_TIME = 1L;
    private static final Long UPDATED_LEAVE_FACTORY_TIME = 2L;

    private static final Integer DEFAULT_MAINTAIN_SKY = 1;
    private static final Integer UPDATED_MAINTAIN_SKY = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private MaintainRecordRepository maintainRecordRepository;

    @Autowired
    private MaintainRecordService maintainRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMaintainRecordMockMvc;

    private MaintainRecord maintainRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MaintainRecordResource maintainRecordResource = new MaintainRecordResource(maintainRecordService);
        this.restMaintainRecordMockMvc = MockMvcBuilders.standaloneSetup(maintainRecordResource)
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
    public static MaintainRecord createEntity(EntityManager em) {
        MaintainRecord maintainRecord = new MaintainRecord()
                .driverId(DEFAULT_DRIVER_ID)
                .vehicleId(DEFAULT_VEHICLE_ID)
                .companyId(DEFAULT_COMPANY_ID)
                .maintainNumber(DEFAULT_MAINTAIN_NUMBER)
                .plateNumber(DEFAULT_PLATE_NUMBER)
                .driverName(DEFAULT_DRIVER_NAME)
                .maintainTime(DEFAULT_MAINTAIN_TIME)
                .money(DEFAULT_MONEY)
                .repairFactory(DEFAULT_REPAIR_FACTORY)
                .leaveFactoryTime(DEFAULT_LEAVE_FACTORY_TIME)
                .maintainSky(DEFAULT_MAINTAIN_SKY)
                .remark(DEFAULT_REMARK);
        return maintainRecord;
    }

    @Before
    public void initTest() {
        maintainRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaintainRecord() throws Exception {
        int databaseSizeBeforeCreate = maintainRecordRepository.findAll().size();

        // Create the MaintainRecord

        restMaintainRecordMockMvc.perform(post("/api/maintain-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maintainRecord)))
            .andExpect(status().isCreated());

        // Validate the MaintainRecord in the database
        List<MaintainRecord> maintainRecordList = maintainRecordRepository.findAll();
        assertThat(maintainRecordList).hasSize(databaseSizeBeforeCreate + 1);
        MaintainRecord testMaintainRecord = maintainRecordList.get(maintainRecordList.size() - 1);
        assertThat(testMaintainRecord.getDriverId()).isEqualTo(DEFAULT_DRIVER_ID);
        assertThat(testMaintainRecord.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testMaintainRecord.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testMaintainRecord.getMaintainNumber()).isEqualTo(DEFAULT_MAINTAIN_NUMBER);
        assertThat(testMaintainRecord.getPlateNumber()).isEqualTo(DEFAULT_PLATE_NUMBER);
        assertThat(testMaintainRecord.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testMaintainRecord.getMaintainTime()).isEqualTo(DEFAULT_MAINTAIN_TIME);
        assertThat(testMaintainRecord.getMoney()).isEqualTo(DEFAULT_MONEY);
        assertThat(testMaintainRecord.getRepairFactory()).isEqualTo(DEFAULT_REPAIR_FACTORY);
        assertThat(testMaintainRecord.getLeaveFactoryTime()).isEqualTo(DEFAULT_LEAVE_FACTORY_TIME);
        assertThat(testMaintainRecord.getMaintainSky()).isEqualTo(DEFAULT_MAINTAIN_SKY);
        assertThat(testMaintainRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createMaintainRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maintainRecordRepository.findAll().size();

        // Create the MaintainRecord with an existing ID
        MaintainRecord existingMaintainRecord = new MaintainRecord();
        existingMaintainRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaintainRecordMockMvc.perform(post("/api/maintain-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMaintainRecord)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MaintainRecord> maintainRecordList = maintainRecordRepository.findAll();
        assertThat(maintainRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMaintainRecords() throws Exception {
        // Initialize the database
        maintainRecordRepository.saveAndFlush(maintainRecord);

        // Get all the maintainRecordList
        restMaintainRecordMockMvc.perform(get("/api/maintain-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maintainRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].driverId").value(hasItem(DEFAULT_DRIVER_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].maintainNumber").value(hasItem(DEFAULT_MAINTAIN_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].plateNumber").value(hasItem(DEFAULT_PLATE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].maintainTime").value(hasItem(DEFAULT_MAINTAIN_TIME.intValue())))
            .andExpect(jsonPath("$.[*].money").value(hasItem(DEFAULT_MONEY.intValue())))
            .andExpect(jsonPath("$.[*].repairFactory").value(hasItem(DEFAULT_REPAIR_FACTORY.toString())))
            .andExpect(jsonPath("$.[*].leaveFactoryTime").value(hasItem(DEFAULT_LEAVE_FACTORY_TIME.intValue())))
            .andExpect(jsonPath("$.[*].maintainSky").value(hasItem(DEFAULT_MAINTAIN_SKY)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getMaintainRecord() throws Exception {
        // Initialize the database
        maintainRecordRepository.saveAndFlush(maintainRecord);

        // Get the maintainRecord
        restMaintainRecordMockMvc.perform(get("/api/maintain-records/{id}", maintainRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(maintainRecord.getId().intValue()))
            .andExpect(jsonPath("$.driverId").value(DEFAULT_DRIVER_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.maintainNumber").value(DEFAULT_MAINTAIN_NUMBER.toString()))
            .andExpect(jsonPath("$.plateNumber").value(DEFAULT_PLATE_NUMBER.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.maintainTime").value(DEFAULT_MAINTAIN_TIME.intValue()))
            .andExpect(jsonPath("$.money").value(DEFAULT_MONEY.intValue()))
            .andExpect(jsonPath("$.repairFactory").value(DEFAULT_REPAIR_FACTORY.toString()))
            .andExpect(jsonPath("$.leaveFactoryTime").value(DEFAULT_LEAVE_FACTORY_TIME.intValue()))
            .andExpect(jsonPath("$.maintainSky").value(DEFAULT_MAINTAIN_SKY))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMaintainRecord() throws Exception {
        // Get the maintainRecord
        restMaintainRecordMockMvc.perform(get("/api/maintain-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaintainRecord() throws Exception {
        // Initialize the database
        maintainRecordService.save(maintainRecord);

        int databaseSizeBeforeUpdate = maintainRecordRepository.findAll().size();

        // Update the maintainRecord
        MaintainRecord updatedMaintainRecord = maintainRecordRepository.findOne(maintainRecord.getId());
        updatedMaintainRecord
                .driverId(UPDATED_DRIVER_ID)
                .vehicleId(UPDATED_VEHICLE_ID)
                .companyId(UPDATED_COMPANY_ID)
                .maintainNumber(UPDATED_MAINTAIN_NUMBER)
                .plateNumber(UPDATED_PLATE_NUMBER)
                .driverName(UPDATED_DRIVER_NAME)
                .maintainTime(UPDATED_MAINTAIN_TIME)
                .money(UPDATED_MONEY)
                .repairFactory(UPDATED_REPAIR_FACTORY)
                .leaveFactoryTime(UPDATED_LEAVE_FACTORY_TIME)
                .maintainSky(UPDATED_MAINTAIN_SKY)
                .remark(UPDATED_REMARK);

        restMaintainRecordMockMvc.perform(put("/api/maintain-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMaintainRecord)))
            .andExpect(status().isOk());

        // Validate the MaintainRecord in the database
        List<MaintainRecord> maintainRecordList = maintainRecordRepository.findAll();
        assertThat(maintainRecordList).hasSize(databaseSizeBeforeUpdate);
        MaintainRecord testMaintainRecord = maintainRecordList.get(maintainRecordList.size() - 1);
        assertThat(testMaintainRecord.getDriverId()).isEqualTo(UPDATED_DRIVER_ID);
        assertThat(testMaintainRecord.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testMaintainRecord.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testMaintainRecord.getMaintainNumber()).isEqualTo(UPDATED_MAINTAIN_NUMBER);
        assertThat(testMaintainRecord.getPlateNumber()).isEqualTo(UPDATED_PLATE_NUMBER);
        assertThat(testMaintainRecord.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testMaintainRecord.getMaintainTime()).isEqualTo(UPDATED_MAINTAIN_TIME);
        assertThat(testMaintainRecord.getMoney()).isEqualTo(UPDATED_MONEY);
        assertThat(testMaintainRecord.getRepairFactory()).isEqualTo(UPDATED_REPAIR_FACTORY);
        assertThat(testMaintainRecord.getLeaveFactoryTime()).isEqualTo(UPDATED_LEAVE_FACTORY_TIME);
        assertThat(testMaintainRecord.getMaintainSky()).isEqualTo(UPDATED_MAINTAIN_SKY);
        assertThat(testMaintainRecord.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingMaintainRecord() throws Exception {
        int databaseSizeBeforeUpdate = maintainRecordRepository.findAll().size();

        // Create the MaintainRecord

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMaintainRecordMockMvc.perform(put("/api/maintain-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maintainRecord)))
            .andExpect(status().isCreated());

        // Validate the MaintainRecord in the database
        List<MaintainRecord> maintainRecordList = maintainRecordRepository.findAll();
        assertThat(maintainRecordList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMaintainRecord() throws Exception {
        // Initialize the database
        maintainRecordService.save(maintainRecord);

        int databaseSizeBeforeDelete = maintainRecordRepository.findAll().size();

        // Get the maintainRecord
        restMaintainRecordMockMvc.perform(delete("/api/maintain-records/{id}", maintainRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MaintainRecord> maintainRecordList = maintainRecordRepository.findAll();
        assertThat(maintainRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaintainRecord.class);
    }
}

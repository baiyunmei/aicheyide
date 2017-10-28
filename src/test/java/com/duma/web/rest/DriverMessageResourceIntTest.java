package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.DriverMessage;
import com.duma.repository.DriverMessageRepository;
import com.duma.service.DriverMessageService;
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
 * Test class for the DriverMessageResource REST controller.
 *
 * @see DriverMessageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class DriverMessageResourceIntTest {

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_CERTIFICATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BORM_DATE = "AAAAAAAAAA";
    private static final String UPDATED_BORM_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final Integer DEFAULT_MARRIAGE_STATUS = 1;
    private static final Integer UPDATED_MARRIAGE_STATUS = 2;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EDUCATION_DEGREE = "AAAAAAAAAA";
    private static final String UPDATED_EDUCATION_DEGREE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTERED = "AAAAAAAAAA";
    private static final String UPDATED_REGISTERED = "BBBBBBBBBB";

    private static final String DEFAULT_CENSUS_REGISTER = "AAAAAAAAAA";
    private static final String UPDATED_CENSUS_REGISTER = "BBBBBBBBBB";

    private static final String DEFAULT_HOUSING_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_HOUSING_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_ADDRESS = "BBBBBBBBBB";

    private static final Double DEFAULT_HOUSE_LOAN = 1D;
    private static final Double UPDATED_HOUSE_LOAN = 2D;

    private static final String DEFAULT_DEADLINE = "AAAAAAAAAA";
    private static final String UPDATED_DEADLINE = "BBBBBBBBBB";

    private static final String DEFAULT_MONTHLY = "AAAAAAAAAA";
    private static final String UPDATED_MONTHLY = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_DWELL_TIME = 1L;
    private static final Long UPDATED_DWELL_TIME = 2L;

    private static final String DEFAULT_IDENTITY_FRONT = "AAAAAAAAAA";
    private static final String UPDATED_IDENTITY_FRONT = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTITY_REVERSE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTITY_REVERSE = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVING_FRONT = "AAAAAAAAAA";
    private static final String UPDATED_DRIVING_FRONT = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVING_REVERSE = "AAAAAAAAAA";
    private static final String UPDATED_DRIVING_REVERSE = "BBBBBBBBBB";

    @Autowired
    private DriverMessageRepository driverMessageRepository;

    @Autowired
    private DriverMessageService driverMessageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDriverMessageMockMvc;

    private DriverMessage driverMessage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DriverMessageResource driverMessageResource = new DriverMessageResource(driverMessageService);
        this.restDriverMessageMockMvc = MockMvcBuilders.standaloneSetup(driverMessageResource)
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
    public static DriverMessage createEntity(EntityManager em) {
        DriverMessage driverMessage = new DriverMessage()
                .companyId(DEFAULT_COMPANY_ID)
                .certificateType(DEFAULT_CERTIFICATE_TYPE)
                .certificatePhone(DEFAULT_CERTIFICATE_PHONE)
                .driverName(DEFAULT_DRIVER_NAME)
                .bormDate(DEFAULT_BORM_DATE)
                .sex(DEFAULT_SEX)
                .marriageStatus(DEFAULT_MARRIAGE_STATUS)
                .phone(DEFAULT_PHONE)
                .educationDegree(DEFAULT_EDUCATION_DEGREE)
                .registered(DEFAULT_REGISTERED)
                .censusRegister(DEFAULT_CENSUS_REGISTER)
                .housingType(DEFAULT_HOUSING_TYPE)
                .contactAddress(DEFAULT_CONTACT_ADDRESS)
                .houseLoan(DEFAULT_HOUSE_LOAN)
                .deadline(DEFAULT_DEADLINE)
                .monthly(DEFAULT_MONTHLY)
                .residentialAddress(DEFAULT_RESIDENTIAL_ADDRESS)
                .dwellTime(DEFAULT_DWELL_TIME)
                .identityFront(DEFAULT_IDENTITY_FRONT)
                .identityReverse(DEFAULT_IDENTITY_REVERSE)
                .drivingFront(DEFAULT_DRIVING_FRONT)
                .drivingReverse(DEFAULT_DRIVING_REVERSE);
        return driverMessage;
    }

    @Before
    public void initTest() {
        driverMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createDriverMessage() throws Exception {
        int databaseSizeBeforeCreate = driverMessageRepository.findAll().size();

        // Create the DriverMessage

        restDriverMessageMockMvc.perform(post("/api/driver-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(driverMessage)))
            .andExpect(status().isCreated());

        // Validate the DriverMessage in the database
        List<DriverMessage> driverMessageList = driverMessageRepository.findAll();
        assertThat(driverMessageList).hasSize(databaseSizeBeforeCreate + 1);
        DriverMessage testDriverMessage = driverMessageList.get(driverMessageList.size() - 1);
        assertThat(testDriverMessage.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testDriverMessage.getCertificateType()).isEqualTo(DEFAULT_CERTIFICATE_TYPE);
        assertThat(testDriverMessage.getCertificatePhone()).isEqualTo(DEFAULT_CERTIFICATE_PHONE);
        assertThat(testDriverMessage.getDriverName()).isEqualTo(DEFAULT_DRIVER_NAME);
        assertThat(testDriverMessage.getBormDate()).isEqualTo(DEFAULT_BORM_DATE);
        assertThat(testDriverMessage.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testDriverMessage.getMarriageStatus()).isEqualTo(DEFAULT_MARRIAGE_STATUS);
        assertThat(testDriverMessage.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testDriverMessage.getEducationDegree()).isEqualTo(DEFAULT_EDUCATION_DEGREE);
        assertThat(testDriverMessage.getRegistered()).isEqualTo(DEFAULT_REGISTERED);
        assertThat(testDriverMessage.getCensusRegister()).isEqualTo(DEFAULT_CENSUS_REGISTER);
        assertThat(testDriverMessage.getHousingType()).isEqualTo(DEFAULT_HOUSING_TYPE);
        assertThat(testDriverMessage.getContactAddress()).isEqualTo(DEFAULT_CONTACT_ADDRESS);
        assertThat(testDriverMessage.getHouseLoan()).isEqualTo(DEFAULT_HOUSE_LOAN);
        assertThat(testDriverMessage.getDeadline()).isEqualTo(DEFAULT_DEADLINE);
        assertThat(testDriverMessage.getMonthly()).isEqualTo(DEFAULT_MONTHLY);
        assertThat(testDriverMessage.getResidentialAddress()).isEqualTo(DEFAULT_RESIDENTIAL_ADDRESS);
        assertThat(testDriverMessage.getDwellTime()).isEqualTo(DEFAULT_DWELL_TIME);
        assertThat(testDriverMessage.getIdentityFront()).isEqualTo(DEFAULT_IDENTITY_FRONT);
        assertThat(testDriverMessage.getIdentityReverse()).isEqualTo(DEFAULT_IDENTITY_REVERSE);
        assertThat(testDriverMessage.getDrivingFront()).isEqualTo(DEFAULT_DRIVING_FRONT);
        assertThat(testDriverMessage.getDrivingReverse()).isEqualTo(DEFAULT_DRIVING_REVERSE);
    }

    @Test
    @Transactional
    public void createDriverMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = driverMessageRepository.findAll().size();

        // Create the DriverMessage with an existing ID
        DriverMessage existingDriverMessage = new DriverMessage();
        existingDriverMessage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDriverMessageMockMvc.perform(post("/api/driver-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDriverMessage)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DriverMessage> driverMessageList = driverMessageRepository.findAll();
        assertThat(driverMessageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDriverMessages() throws Exception {
        // Initialize the database
        driverMessageRepository.saveAndFlush(driverMessage);

        // Get all the driverMessageList
        restDriverMessageMockMvc.perform(get("/api/driver-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(driverMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].certificateType").value(hasItem(DEFAULT_CERTIFICATE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].certificatePhone").value(hasItem(DEFAULT_CERTIFICATE_PHONE.toString())))
            .andExpect(jsonPath("$.[*].driverName").value(hasItem(DEFAULT_DRIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].bormDate").value(hasItem(DEFAULT_BORM_DATE.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].marriageStatus").value(hasItem(DEFAULT_MARRIAGE_STATUS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].educationDegree").value(hasItem(DEFAULT_EDUCATION_DEGREE.toString())))
            .andExpect(jsonPath("$.[*].registered").value(hasItem(DEFAULT_REGISTERED.toString())))
            .andExpect(jsonPath("$.[*].censusRegister").value(hasItem(DEFAULT_CENSUS_REGISTER.toString())))
            .andExpect(jsonPath("$.[*].housingType").value(hasItem(DEFAULT_HOUSING_TYPE.toString())))
            .andExpect(jsonPath("$.[*].contactAddress").value(hasItem(DEFAULT_CONTACT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].houseLoan").value(hasItem(DEFAULT_HOUSE_LOAN.doubleValue())))
            .andExpect(jsonPath("$.[*].deadline").value(hasItem(DEFAULT_DEADLINE.toString())))
            .andExpect(jsonPath("$.[*].monthly").value(hasItem(DEFAULT_MONTHLY.toString())))
            .andExpect(jsonPath("$.[*].residentialAddress").value(hasItem(DEFAULT_RESIDENTIAL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].dwellTime").value(hasItem(DEFAULT_DWELL_TIME.intValue())))
            .andExpect(jsonPath("$.[*].identityFront").value(hasItem(DEFAULT_IDENTITY_FRONT.toString())))
            .andExpect(jsonPath("$.[*].identityReverse").value(hasItem(DEFAULT_IDENTITY_REVERSE.toString())))
            .andExpect(jsonPath("$.[*].drivingFront").value(hasItem(DEFAULT_DRIVING_FRONT.toString())))
            .andExpect(jsonPath("$.[*].drivingReverse").value(hasItem(DEFAULT_DRIVING_REVERSE.toString())));
    }

    @Test
    @Transactional
    public void getDriverMessage() throws Exception {
        // Initialize the database
        driverMessageRepository.saveAndFlush(driverMessage);

        // Get the driverMessage
        restDriverMessageMockMvc.perform(get("/api/driver-messages/{id}", driverMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(driverMessage.getId().intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.certificateType").value(DEFAULT_CERTIFICATE_TYPE.toString()))
            .andExpect(jsonPath("$.certificatePhone").value(DEFAULT_CERTIFICATE_PHONE.toString()))
            .andExpect(jsonPath("$.driverName").value(DEFAULT_DRIVER_NAME.toString()))
            .andExpect(jsonPath("$.bormDate").value(DEFAULT_BORM_DATE.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.marriageStatus").value(DEFAULT_MARRIAGE_STATUS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.educationDegree").value(DEFAULT_EDUCATION_DEGREE.toString()))
            .andExpect(jsonPath("$.registered").value(DEFAULT_REGISTERED.toString()))
            .andExpect(jsonPath("$.censusRegister").value(DEFAULT_CENSUS_REGISTER.toString()))
            .andExpect(jsonPath("$.housingType").value(DEFAULT_HOUSING_TYPE.toString()))
            .andExpect(jsonPath("$.contactAddress").value(DEFAULT_CONTACT_ADDRESS.toString()))
            .andExpect(jsonPath("$.houseLoan").value(DEFAULT_HOUSE_LOAN.doubleValue()))
            .andExpect(jsonPath("$.deadline").value(DEFAULT_DEADLINE.toString()))
            .andExpect(jsonPath("$.monthly").value(DEFAULT_MONTHLY.toString()))
            .andExpect(jsonPath("$.residentialAddress").value(DEFAULT_RESIDENTIAL_ADDRESS.toString()))
            .andExpect(jsonPath("$.dwellTime").value(DEFAULT_DWELL_TIME.intValue()))
            .andExpect(jsonPath("$.identityFront").value(DEFAULT_IDENTITY_FRONT.toString()))
            .andExpect(jsonPath("$.identityReverse").value(DEFAULT_IDENTITY_REVERSE.toString()))
            .andExpect(jsonPath("$.drivingFront").value(DEFAULT_DRIVING_FRONT.toString()))
            .andExpect(jsonPath("$.drivingReverse").value(DEFAULT_DRIVING_REVERSE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDriverMessage() throws Exception {
        // Get the driverMessage
        restDriverMessageMockMvc.perform(get("/api/driver-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDriverMessage() throws Exception {
        // Initialize the database
        driverMessageService.save(driverMessage);

        int databaseSizeBeforeUpdate = driverMessageRepository.findAll().size();

        // Update the driverMessage
        DriverMessage updatedDriverMessage = driverMessageRepository.findOne(driverMessage.getId());
        updatedDriverMessage
                .companyId(UPDATED_COMPANY_ID)
                .certificateType(UPDATED_CERTIFICATE_TYPE)
                .certificatePhone(UPDATED_CERTIFICATE_PHONE)
                .driverName(UPDATED_DRIVER_NAME)
                .bormDate(UPDATED_BORM_DATE)
                .sex(UPDATED_SEX)
                .marriageStatus(UPDATED_MARRIAGE_STATUS)
                .phone(UPDATED_PHONE)
                .educationDegree(UPDATED_EDUCATION_DEGREE)
                .registered(UPDATED_REGISTERED)
                .censusRegister(UPDATED_CENSUS_REGISTER)
                .housingType(UPDATED_HOUSING_TYPE)
                .contactAddress(UPDATED_CONTACT_ADDRESS)
                .houseLoan(UPDATED_HOUSE_LOAN)
                .deadline(UPDATED_DEADLINE)
                .monthly(UPDATED_MONTHLY)
                .residentialAddress(UPDATED_RESIDENTIAL_ADDRESS)
                .dwellTime(UPDATED_DWELL_TIME)
                .identityFront(UPDATED_IDENTITY_FRONT)
                .identityReverse(UPDATED_IDENTITY_REVERSE)
                .drivingFront(UPDATED_DRIVING_FRONT)
                .drivingReverse(UPDATED_DRIVING_REVERSE);

        restDriverMessageMockMvc.perform(put("/api/driver-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDriverMessage)))
            .andExpect(status().isOk());

        // Validate the DriverMessage in the database
        List<DriverMessage> driverMessageList = driverMessageRepository.findAll();
        assertThat(driverMessageList).hasSize(databaseSizeBeforeUpdate);
        DriverMessage testDriverMessage = driverMessageList.get(driverMessageList.size() - 1);
        assertThat(testDriverMessage.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testDriverMessage.getCertificateType()).isEqualTo(UPDATED_CERTIFICATE_TYPE);
        assertThat(testDriverMessage.getCertificatePhone()).isEqualTo(UPDATED_CERTIFICATE_PHONE);
        assertThat(testDriverMessage.getDriverName()).isEqualTo(UPDATED_DRIVER_NAME);
        assertThat(testDriverMessage.getBormDate()).isEqualTo(UPDATED_BORM_DATE);
        assertThat(testDriverMessage.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testDriverMessage.getMarriageStatus()).isEqualTo(UPDATED_MARRIAGE_STATUS);
        assertThat(testDriverMessage.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testDriverMessage.getEducationDegree()).isEqualTo(UPDATED_EDUCATION_DEGREE);
        assertThat(testDriverMessage.getRegistered()).isEqualTo(UPDATED_REGISTERED);
        assertThat(testDriverMessage.getCensusRegister()).isEqualTo(UPDATED_CENSUS_REGISTER);
        assertThat(testDriverMessage.getHousingType()).isEqualTo(UPDATED_HOUSING_TYPE);
        assertThat(testDriverMessage.getContactAddress()).isEqualTo(UPDATED_CONTACT_ADDRESS);
        assertThat(testDriverMessage.getHouseLoan()).isEqualTo(UPDATED_HOUSE_LOAN);
        assertThat(testDriverMessage.getDeadline()).isEqualTo(UPDATED_DEADLINE);
        assertThat(testDriverMessage.getMonthly()).isEqualTo(UPDATED_MONTHLY);
        assertThat(testDriverMessage.getResidentialAddress()).isEqualTo(UPDATED_RESIDENTIAL_ADDRESS);
        assertThat(testDriverMessage.getDwellTime()).isEqualTo(UPDATED_DWELL_TIME);
        assertThat(testDriverMessage.getIdentityFront()).isEqualTo(UPDATED_IDENTITY_FRONT);
        assertThat(testDriverMessage.getIdentityReverse()).isEqualTo(UPDATED_IDENTITY_REVERSE);
        assertThat(testDriverMessage.getDrivingFront()).isEqualTo(UPDATED_DRIVING_FRONT);
        assertThat(testDriverMessage.getDrivingReverse()).isEqualTo(UPDATED_DRIVING_REVERSE);
    }

    @Test
    @Transactional
    public void updateNonExistingDriverMessage() throws Exception {
        int databaseSizeBeforeUpdate = driverMessageRepository.findAll().size();

        // Create the DriverMessage

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDriverMessageMockMvc.perform(put("/api/driver-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(driverMessage)))
            .andExpect(status().isCreated());

        // Validate the DriverMessage in the database
        List<DriverMessage> driverMessageList = driverMessageRepository.findAll();
        assertThat(driverMessageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDriverMessage() throws Exception {
        // Initialize the database
        driverMessageService.save(driverMessage);

        int databaseSizeBeforeDelete = driverMessageRepository.findAll().size();

        // Get the driverMessage
        restDriverMessageMockMvc.perform(delete("/api/driver-messages/{id}", driverMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DriverMessage> driverMessageList = driverMessageRepository.findAll();
        assertThat(driverMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DriverMessage.class);
    }
}

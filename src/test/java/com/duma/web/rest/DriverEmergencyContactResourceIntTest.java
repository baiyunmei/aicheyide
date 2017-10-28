package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.DriverEmergencyContact;
import com.duma.repository.DriverEmergencyContactRepository;
import com.duma.service.DriverEmergencyContactService;
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
 * Test class for the DriverEmergencyContactResource REST controller.
 *
 * @see DriverEmergencyContactResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class DriverEmergencyContactResourceIntTest {

    private static final String DEFAULT_NAME_1 = "AAAAAAAAAA";
    private static final String UPDATED_NAME_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RELATION_1 = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RELATION_2 = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_2 = "BBBBBBBBBB";

    @Autowired
    private DriverEmergencyContactRepository driverEmergencyContactRepository;

    @Autowired
    private DriverEmergencyContactService driverEmergencyContactService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDriverEmergencyContactMockMvc;

    private DriverEmergencyContact driverEmergencyContact;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DriverEmergencyContactResource driverEmergencyContactResource = new DriverEmergencyContactResource(driverEmergencyContactService);
        this.restDriverEmergencyContactMockMvc = MockMvcBuilders.standaloneSetup(driverEmergencyContactResource)
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
    public static DriverEmergencyContact createEntity(EntityManager em) {
        DriverEmergencyContact driverEmergencyContact = new DriverEmergencyContact()
                .name1(DEFAULT_NAME_1)
                .residentialAddress1(DEFAULT_RESIDENTIAL_ADDRESS_1)
                .phone1(DEFAULT_PHONE_1)
                .relation1(DEFAULT_RELATION_1)
                .name2(DEFAULT_NAME_2)
                .residentialAddress2(DEFAULT_RESIDENTIAL_ADDRESS_2)
                .phone2(DEFAULT_PHONE_2)
                .relation2(DEFAULT_RELATION_2);
        return driverEmergencyContact;
    }

    @Before
    public void initTest() {
        driverEmergencyContact = createEntity(em);
    }

    @Test
    @Transactional
    public void createDriverEmergencyContact() throws Exception {
        int databaseSizeBeforeCreate = driverEmergencyContactRepository.findAll().size();

        // Create the DriverEmergencyContact

        restDriverEmergencyContactMockMvc.perform(post("/api/driver-emergency-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(driverEmergencyContact)))
            .andExpect(status().isCreated());

        // Validate the DriverEmergencyContact in the database
        List<DriverEmergencyContact> driverEmergencyContactList = driverEmergencyContactRepository.findAll();
        assertThat(driverEmergencyContactList).hasSize(databaseSizeBeforeCreate + 1);
        DriverEmergencyContact testDriverEmergencyContact = driverEmergencyContactList.get(driverEmergencyContactList.size() - 1);
        assertThat(testDriverEmergencyContact.getName1()).isEqualTo(DEFAULT_NAME_1);
        assertThat(testDriverEmergencyContact.getResidentialAddress1()).isEqualTo(DEFAULT_RESIDENTIAL_ADDRESS_1);
        assertThat(testDriverEmergencyContact.getPhone1()).isEqualTo(DEFAULT_PHONE_1);
        assertThat(testDriverEmergencyContact.getRelation1()).isEqualTo(DEFAULT_RELATION_1);
        assertThat(testDriverEmergencyContact.getName2()).isEqualTo(DEFAULT_NAME_2);
        assertThat(testDriverEmergencyContact.getResidentialAddress2()).isEqualTo(DEFAULT_RESIDENTIAL_ADDRESS_2);
        assertThat(testDriverEmergencyContact.getPhone2()).isEqualTo(DEFAULT_PHONE_2);
        assertThat(testDriverEmergencyContact.getRelation2()).isEqualTo(DEFAULT_RELATION_2);
    }

    @Test
    @Transactional
    public void createDriverEmergencyContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = driverEmergencyContactRepository.findAll().size();

        // Create the DriverEmergencyContact with an existing ID
        DriverEmergencyContact existingDriverEmergencyContact = new DriverEmergencyContact();
        existingDriverEmergencyContact.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDriverEmergencyContactMockMvc.perform(post("/api/driver-emergency-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDriverEmergencyContact)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DriverEmergencyContact> driverEmergencyContactList = driverEmergencyContactRepository.findAll();
        assertThat(driverEmergencyContactList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDriverEmergencyContacts() throws Exception {
        // Initialize the database
        driverEmergencyContactRepository.saveAndFlush(driverEmergencyContact);

        // Get all the driverEmergencyContactList
        restDriverEmergencyContactMockMvc.perform(get("/api/driver-emergency-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(driverEmergencyContact.getId().intValue())))
            .andExpect(jsonPath("$.[*].name1").value(hasItem(DEFAULT_NAME_1.toString())))
            .andExpect(jsonPath("$.[*].residentialAddress1").value(hasItem(DEFAULT_RESIDENTIAL_ADDRESS_1.toString())))
            .andExpect(jsonPath("$.[*].phone1").value(hasItem(DEFAULT_PHONE_1.toString())))
            .andExpect(jsonPath("$.[*].relation1").value(hasItem(DEFAULT_RELATION_1.toString())))
            .andExpect(jsonPath("$.[*].name2").value(hasItem(DEFAULT_NAME_2.toString())))
            .andExpect(jsonPath("$.[*].residentialAddress2").value(hasItem(DEFAULT_RESIDENTIAL_ADDRESS_2.toString())))
            .andExpect(jsonPath("$.[*].phone2").value(hasItem(DEFAULT_PHONE_2.toString())))
            .andExpect(jsonPath("$.[*].relation2").value(hasItem(DEFAULT_RELATION_2.toString())));
    }

    @Test
    @Transactional
    public void getDriverEmergencyContact() throws Exception {
        // Initialize the database
        driverEmergencyContactRepository.saveAndFlush(driverEmergencyContact);

        // Get the driverEmergencyContact
        restDriverEmergencyContactMockMvc.perform(get("/api/driver-emergency-contacts/{id}", driverEmergencyContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(driverEmergencyContact.getId().intValue()))
            .andExpect(jsonPath("$.name1").value(DEFAULT_NAME_1.toString()))
            .andExpect(jsonPath("$.residentialAddress1").value(DEFAULT_RESIDENTIAL_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.phone1").value(DEFAULT_PHONE_1.toString()))
            .andExpect(jsonPath("$.relation1").value(DEFAULT_RELATION_1.toString()))
            .andExpect(jsonPath("$.name2").value(DEFAULT_NAME_2.toString()))
            .andExpect(jsonPath("$.residentialAddress2").value(DEFAULT_RESIDENTIAL_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.phone2").value(DEFAULT_PHONE_2.toString()))
            .andExpect(jsonPath("$.relation2").value(DEFAULT_RELATION_2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDriverEmergencyContact() throws Exception {
        // Get the driverEmergencyContact
        restDriverEmergencyContactMockMvc.perform(get("/api/driver-emergency-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDriverEmergencyContact() throws Exception {
        // Initialize the database
        driverEmergencyContactService.save(driverEmergencyContact);

        int databaseSizeBeforeUpdate = driverEmergencyContactRepository.findAll().size();

        // Update the driverEmergencyContact
        DriverEmergencyContact updatedDriverEmergencyContact = driverEmergencyContactRepository.findOne(driverEmergencyContact.getId());
        updatedDriverEmergencyContact
                .name1(UPDATED_NAME_1)
                .residentialAddress1(UPDATED_RESIDENTIAL_ADDRESS_1)
                .phone1(UPDATED_PHONE_1)
                .relation1(UPDATED_RELATION_1)
                .name2(UPDATED_NAME_2)
                .residentialAddress2(UPDATED_RESIDENTIAL_ADDRESS_2)
                .phone2(UPDATED_PHONE_2)
                .relation2(UPDATED_RELATION_2);

        restDriverEmergencyContactMockMvc.perform(put("/api/driver-emergency-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDriverEmergencyContact)))
            .andExpect(status().isOk());

        // Validate the DriverEmergencyContact in the database
        List<DriverEmergencyContact> driverEmergencyContactList = driverEmergencyContactRepository.findAll();
        assertThat(driverEmergencyContactList).hasSize(databaseSizeBeforeUpdate);
        DriverEmergencyContact testDriverEmergencyContact = driverEmergencyContactList.get(driverEmergencyContactList.size() - 1);
        assertThat(testDriverEmergencyContact.getName1()).isEqualTo(UPDATED_NAME_1);
        assertThat(testDriverEmergencyContact.getResidentialAddress1()).isEqualTo(UPDATED_RESIDENTIAL_ADDRESS_1);
        assertThat(testDriverEmergencyContact.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testDriverEmergencyContact.getRelation1()).isEqualTo(UPDATED_RELATION_1);
        assertThat(testDriverEmergencyContact.getName2()).isEqualTo(UPDATED_NAME_2);
        assertThat(testDriverEmergencyContact.getResidentialAddress2()).isEqualTo(UPDATED_RESIDENTIAL_ADDRESS_2);
        assertThat(testDriverEmergencyContact.getPhone2()).isEqualTo(UPDATED_PHONE_2);
        assertThat(testDriverEmergencyContact.getRelation2()).isEqualTo(UPDATED_RELATION_2);
    }

    @Test
    @Transactional
    public void updateNonExistingDriverEmergencyContact() throws Exception {
        int databaseSizeBeforeUpdate = driverEmergencyContactRepository.findAll().size();

        // Create the DriverEmergencyContact

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDriverEmergencyContactMockMvc.perform(put("/api/driver-emergency-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(driverEmergencyContact)))
            .andExpect(status().isCreated());

        // Validate the DriverEmergencyContact in the database
        List<DriverEmergencyContact> driverEmergencyContactList = driverEmergencyContactRepository.findAll();
        assertThat(driverEmergencyContactList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDriverEmergencyContact() throws Exception {
        // Initialize the database
        driverEmergencyContactService.save(driverEmergencyContact);

        int databaseSizeBeforeDelete = driverEmergencyContactRepository.findAll().size();

        // Get the driverEmergencyContact
        restDriverEmergencyContactMockMvc.perform(delete("/api/driver-emergency-contacts/{id}", driverEmergencyContact.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DriverEmergencyContact> driverEmergencyContactList = driverEmergencyContactRepository.findAll();
        assertThat(driverEmergencyContactList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DriverEmergencyContact.class);
    }
}

package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.FormalContract;
import com.duma.repository.FormalContractRepository;
import com.duma.service.FormalContractService;
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
 * Test class for the FormalContractResource REST controller.
 *
 * @see FormalContractResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class FormalContractResourceIntTest {

    private static final Long DEFAULT_ORDER_ID = 1L;
    private static final Long UPDATED_ORDER_ID = 2L;

    private static final String DEFAULT_CONTRACT_NUMBERING = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_NUMBERING = "BBBBBBBBBB";

    private static final String DEFAULT_VIDEO = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT = "BBBBBBBBBB";

    private static final String DEFAULT_APPLY = "AAAAAAAAAA";
    private static final String UPDATED_APPLY = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIAL = "AAAAAAAAAA";
    private static final String UPDATED_SPECIAL = "BBBBBBBBBB";

    @Autowired
    private FormalContractRepository formalContractRepository;

    @Autowired
    private FormalContractService formalContractService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFormalContractMockMvc;

    private FormalContract formalContract;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FormalContractResource formalContractResource = new FormalContractResource(formalContractService);
        this.restFormalContractMockMvc = MockMvcBuilders.standaloneSetup(formalContractResource)
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
    public static FormalContract createEntity(EntityManager em) {
        FormalContract formalContract = new FormalContract()
                .orderId(DEFAULT_ORDER_ID)
                .contractNumbering(DEFAULT_CONTRACT_NUMBERING)
                .video(DEFAULT_VIDEO)
                .credit(DEFAULT_CREDIT)
                .apply(DEFAULT_APPLY)
                .special(DEFAULT_SPECIAL);
        return formalContract;
    }

    @Before
    public void initTest() {
        formalContract = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormalContract() throws Exception {
        int databaseSizeBeforeCreate = formalContractRepository.findAll().size();

        // Create the FormalContract

        restFormalContractMockMvc.perform(post("/api/formal-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formalContract)))
            .andExpect(status().isCreated());

        // Validate the FormalContract in the database
        List<FormalContract> formalContractList = formalContractRepository.findAll();
        assertThat(formalContractList).hasSize(databaseSizeBeforeCreate + 1);
        FormalContract testFormalContract = formalContractList.get(formalContractList.size() - 1);
        assertThat(testFormalContract.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testFormalContract.getContractNumbering()).isEqualTo(DEFAULT_CONTRACT_NUMBERING);
        assertThat(testFormalContract.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testFormalContract.getCredit()).isEqualTo(DEFAULT_CREDIT);
        assertThat(testFormalContract.getApply()).isEqualTo(DEFAULT_APPLY);
        assertThat(testFormalContract.getSpecial()).isEqualTo(DEFAULT_SPECIAL);
    }

    @Test
    @Transactional
    public void createFormalContractWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formalContractRepository.findAll().size();

        // Create the FormalContract with an existing ID
        FormalContract existingFormalContract = new FormalContract();
        existingFormalContract.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormalContractMockMvc.perform(post("/api/formal-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingFormalContract)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FormalContract> formalContractList = formalContractRepository.findAll();
        assertThat(formalContractList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFormalContracts() throws Exception {
        // Initialize the database
        formalContractRepository.saveAndFlush(formalContract);

        // Get all the formalContractList
        restFormalContractMockMvc.perform(get("/api/formal-contracts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formalContract.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].contractNumbering").value(hasItem(DEFAULT_CONTRACT_NUMBERING.toString())))
            .andExpect(jsonPath("$.[*].video").value(hasItem(DEFAULT_VIDEO.toString())))
            .andExpect(jsonPath("$.[*].credit").value(hasItem(DEFAULT_CREDIT.toString())))
            .andExpect(jsonPath("$.[*].apply").value(hasItem(DEFAULT_APPLY.toString())))
            .andExpect(jsonPath("$.[*].special").value(hasItem(DEFAULT_SPECIAL.toString())));
    }

    @Test
    @Transactional
    public void getFormalContract() throws Exception {
        // Initialize the database
        formalContractRepository.saveAndFlush(formalContract);

        // Get the formalContract
        restFormalContractMockMvc.perform(get("/api/formal-contracts/{id}", formalContract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formalContract.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.contractNumbering").value(DEFAULT_CONTRACT_NUMBERING.toString()))
            .andExpect(jsonPath("$.video").value(DEFAULT_VIDEO.toString()))
            .andExpect(jsonPath("$.credit").value(DEFAULT_CREDIT.toString()))
            .andExpect(jsonPath("$.apply").value(DEFAULT_APPLY.toString()))
            .andExpect(jsonPath("$.special").value(DEFAULT_SPECIAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFormalContract() throws Exception {
        // Get the formalContract
        restFormalContractMockMvc.perform(get("/api/formal-contracts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormalContract() throws Exception {
        // Initialize the database
        formalContractService.save(formalContract);

        int databaseSizeBeforeUpdate = formalContractRepository.findAll().size();

        // Update the formalContract
        FormalContract updatedFormalContract = formalContractRepository.findOne(formalContract.getId());
        updatedFormalContract
                .orderId(UPDATED_ORDER_ID)
                .contractNumbering(UPDATED_CONTRACT_NUMBERING)
                .video(UPDATED_VIDEO)
                .credit(UPDATED_CREDIT)
                .apply(UPDATED_APPLY)
                .special(UPDATED_SPECIAL);

        restFormalContractMockMvc.perform(put("/api/formal-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormalContract)))
            .andExpect(status().isOk());

        // Validate the FormalContract in the database
        List<FormalContract> formalContractList = formalContractRepository.findAll();
        assertThat(formalContractList).hasSize(databaseSizeBeforeUpdate);
        FormalContract testFormalContract = formalContractList.get(formalContractList.size() - 1);
        assertThat(testFormalContract.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testFormalContract.getContractNumbering()).isEqualTo(UPDATED_CONTRACT_NUMBERING);
        assertThat(testFormalContract.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testFormalContract.getCredit()).isEqualTo(UPDATED_CREDIT);
        assertThat(testFormalContract.getApply()).isEqualTo(UPDATED_APPLY);
        assertThat(testFormalContract.getSpecial()).isEqualTo(UPDATED_SPECIAL);
    }

    @Test
    @Transactional
    public void updateNonExistingFormalContract() throws Exception {
        int databaseSizeBeforeUpdate = formalContractRepository.findAll().size();

        // Create the FormalContract

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFormalContractMockMvc.perform(put("/api/formal-contracts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formalContract)))
            .andExpect(status().isCreated());

        // Validate the FormalContract in the database
        List<FormalContract> formalContractList = formalContractRepository.findAll();
        assertThat(formalContractList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFormalContract() throws Exception {
        // Initialize the database
        formalContractService.save(formalContract);

        int databaseSizeBeforeDelete = formalContractRepository.findAll().size();

        // Get the formalContract
        restFormalContractMockMvc.perform(delete("/api/formal-contracts/{id}", formalContract.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FormalContract> formalContractList = formalContractRepository.findAll();
        assertThat(formalContractList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormalContract.class);
    }
}

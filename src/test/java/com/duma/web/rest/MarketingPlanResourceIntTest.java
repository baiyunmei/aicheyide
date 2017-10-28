package com.duma.web.rest;

import com.duma.AicheyideApp;

import com.duma.domain.MarketingPlan;
import com.duma.repository.MarketingPlanRepository;
import com.duma.service.MarketingPlanService;
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
 * Test class for the MarketingPlanResource REST controller.
 *
 * @see MarketingPlanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AicheyideApp.class)
public class MarketingPlanResourceIntTest {

    @Autowired
    private MarketingPlanRepository marketingPlanRepository;

    @Autowired
    private MarketingPlanService marketingPlanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMarketingPlanMockMvc;

    private MarketingPlan marketingPlan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MarketingPlanResource marketingPlanResource = new MarketingPlanResource(marketingPlanService);
        this.restMarketingPlanMockMvc = MockMvcBuilders.standaloneSetup(marketingPlanResource)
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
    public static MarketingPlan createEntity(EntityManager em) {
        MarketingPlan marketingPlan = new MarketingPlan();
        return marketingPlan;
    }

    @Before
    public void initTest() {
        marketingPlan = createEntity(em);
    }

    @Test
    @Transactional
    public void createMarketingPlan() throws Exception {
        int databaseSizeBeforeCreate = marketingPlanRepository.findAll().size();

        // Create the MarketingPlan

        restMarketingPlanMockMvc.perform(post("/api/marketing-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketingPlan)))
            .andExpect(status().isCreated());

        // Validate the MarketingPlan in the database
        List<MarketingPlan> marketingPlanList = marketingPlanRepository.findAll();
        assertThat(marketingPlanList).hasSize(databaseSizeBeforeCreate + 1);
        MarketingPlan testMarketingPlan = marketingPlanList.get(marketingPlanList.size() - 1);
    }

    @Test
    @Transactional
    public void createMarketingPlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = marketingPlanRepository.findAll().size();

        // Create the MarketingPlan with an existing ID
        MarketingPlan existingMarketingPlan = new MarketingPlan();
        existingMarketingPlan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarketingPlanMockMvc.perform(post("/api/marketing-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMarketingPlan)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MarketingPlan> marketingPlanList = marketingPlanRepository.findAll();
        assertThat(marketingPlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMarketingPlans() throws Exception {
        // Initialize the database
        marketingPlanRepository.saveAndFlush(marketingPlan);

        // Get all the marketingPlanList
        restMarketingPlanMockMvc.perform(get("/api/marketing-plans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(marketingPlan.getId().intValue())));
    }

    @Test
    @Transactional
    public void getMarketingPlan() throws Exception {
        // Initialize the database
        marketingPlanRepository.saveAndFlush(marketingPlan);

        // Get the marketingPlan
        restMarketingPlanMockMvc.perform(get("/api/marketing-plans/{id}", marketingPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(marketingPlan.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMarketingPlan() throws Exception {
        // Get the marketingPlan
        restMarketingPlanMockMvc.perform(get("/api/marketing-plans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMarketingPlan() throws Exception {
        // Initialize the database
        marketingPlanService.save(marketingPlan);

        int databaseSizeBeforeUpdate = marketingPlanRepository.findAll().size();

        // Update the marketingPlan
        MarketingPlan updatedMarketingPlan = marketingPlanRepository.findOne(marketingPlan.getId());

        restMarketingPlanMockMvc.perform(put("/api/marketing-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMarketingPlan)))
            .andExpect(status().isOk());

        // Validate the MarketingPlan in the database
        List<MarketingPlan> marketingPlanList = marketingPlanRepository.findAll();
        assertThat(marketingPlanList).hasSize(databaseSizeBeforeUpdate);
        MarketingPlan testMarketingPlan = marketingPlanList.get(marketingPlanList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingMarketingPlan() throws Exception {
        int databaseSizeBeforeUpdate = marketingPlanRepository.findAll().size();

        // Create the MarketingPlan

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMarketingPlanMockMvc.perform(put("/api/marketing-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marketingPlan)))
            .andExpect(status().isCreated());

        // Validate the MarketingPlan in the database
        List<MarketingPlan> marketingPlanList = marketingPlanRepository.findAll();
        assertThat(marketingPlanList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMarketingPlan() throws Exception {
        // Initialize the database
        marketingPlanService.save(marketingPlan);

        int databaseSizeBeforeDelete = marketingPlanRepository.findAll().size();

        // Get the marketingPlan
        restMarketingPlanMockMvc.perform(delete("/api/marketing-plans/{id}", marketingPlan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MarketingPlan> marketingPlanList = marketingPlanRepository.findAll();
        assertThat(marketingPlanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarketingPlan.class);
    }
}

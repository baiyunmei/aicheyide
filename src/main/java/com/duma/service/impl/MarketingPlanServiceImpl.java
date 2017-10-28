package com.duma.service.impl;

import com.duma.service.MarketingPlanService;
import com.duma.domain.MarketingPlan;
import com.duma.repository.MarketingPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing MarketingPlan.
 */
@Service
@Transactional
public class MarketingPlanServiceImpl implements MarketingPlanService{

    private final Logger log = LoggerFactory.getLogger(MarketingPlanServiceImpl.class);
    
    private final MarketingPlanRepository marketingPlanRepository;

    public MarketingPlanServiceImpl(MarketingPlanRepository marketingPlanRepository) {
        this.marketingPlanRepository = marketingPlanRepository;
    }

    /**
     * Save a marketingPlan.
     *
     * @param marketingPlan the entity to save
     * @return the persisted entity
     */
    @Override
    public MarketingPlan save(MarketingPlan marketingPlan) {
        log.debug("Request to save MarketingPlan : {}", marketingPlan);
        MarketingPlan result = marketingPlanRepository.save(marketingPlan);
        return result;
    }

    /**
     *  Get all the marketingPlans.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MarketingPlan> findAll(Pageable pageable) {
        log.debug("Request to get all MarketingPlans");
        Page<MarketingPlan> result = marketingPlanRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one marketingPlan by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MarketingPlan findOne(Long id) {
        log.debug("Request to get MarketingPlan : {}", id);
        MarketingPlan marketingPlan = marketingPlanRepository.findOne(id);
        return marketingPlan;
    }

    /**
     *  Delete the  marketingPlan by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MarketingPlan : {}", id);
        marketingPlanRepository.delete(id);
    }
}

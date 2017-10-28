package com.duma.service;

import com.duma.domain.MarketingPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing MarketingPlan.
 */
public interface MarketingPlanService {

    /**
     * Save a marketingPlan.
     *
     * @param marketingPlan the entity to save
     * @return the persisted entity
     */
    MarketingPlan save(MarketingPlan marketingPlan);

    /**
     *  Get all the marketingPlans.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MarketingPlan> findAll(Pageable pageable);

    /**
     *  Get the "id" marketingPlan.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MarketingPlan findOne(Long id);

    /**
     *  Delete the "id" marketingPlan.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

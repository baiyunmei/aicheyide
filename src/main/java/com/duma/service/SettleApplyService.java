package com.duma.service;

import com.duma.domain.SettleApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing SettleApply.
 */
public interface SettleApplyService {

    /**
     * Save a settleApply.
     *
     * @param settleApply the entity to save
     * @return the persisted entity
     */
    SettleApply save(SettleApply settleApply);

    /**
     *  Get all the settleApplies.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<SettleApply> findAll(Pageable pageable);

    /**
     *  Get the "id" settleApply.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SettleApply findOne(Long id);

    /**
     *  Delete the "id" settleApply.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

package com.duma.service;

import com.duma.domain.RetreatDeposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing RetreatDeposit.
 */
public interface RetreatDepositService {

    /**
     * Save a retreatDeposit.
     *
     * @param retreatDeposit the entity to save
     * @return the persisted entity
     */
    RetreatDeposit save(RetreatDeposit retreatDeposit);

    /**
     *  Get all the retreatDeposits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RetreatDeposit> findAll(Pageable pageable);

    /**
     *  Get the "id" retreatDeposit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RetreatDeposit findOne(Long id);

    /**
     *  Delete the "id" retreatDeposit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

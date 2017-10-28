package com.duma.service;

import com.duma.domain.DepositContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing DepositContract.
 */
public interface DepositContractService {

    /**
     * Save a depositContract.
     *
     * @param depositContract the entity to save
     * @return the persisted entity
     */
    DepositContract save(DepositContract depositContract);

    /**
     *  Get all the depositContracts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DepositContract> findAll(Pageable pageable);

    /**
     *  Get the "id" depositContract.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DepositContract findOne(Long id);

    /**
     *  Delete the "id" depositContract.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

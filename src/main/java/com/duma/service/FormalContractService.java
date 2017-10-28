package com.duma.service;

import com.duma.domain.FormalContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing FormalContract.
 */
public interface FormalContractService {

    /**
     * Save a formalContract.
     *
     * @param formalContract the entity to save
     * @return the persisted entity
     */
    FormalContract save(FormalContract formalContract);

    /**
     *  Get all the formalContracts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<FormalContract> findAll(Pageable pageable);

    /**
     *  Get the "id" formalContract.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FormalContract findOne(Long id);

    /**
     *  Delete the "id" formalContract.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

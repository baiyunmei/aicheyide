package com.duma.service;

import com.duma.domain.MonthlyManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing MonthlyManagement.
 */
public interface MonthlyManagementService {

    /**
     * Save a monthlyManagement.
     *
     * @param monthlyManagement the entity to save
     * @return the persisted entity
     */
    MonthlyManagement save(MonthlyManagement monthlyManagement);

    /**
     *  Get all the monthlyManagements.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MonthlyManagement> findAll(Pageable pageable);

    /**
     *  Get the "id" monthlyManagement.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MonthlyManagement findOne(Long id);

    /**
     *  Delete the "id" monthlyManagement.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

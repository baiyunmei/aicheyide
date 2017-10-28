package com.duma.service;

import com.duma.domain.OverdueManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing OverdueManagement.
 */
public interface OverdueManagementService {

    /**
     * Save a overdueManagement.
     *
     * @param overdueManagement the entity to save
     * @return the persisted entity
     */
    OverdueManagement save(OverdueManagement overdueManagement);

    /**
     *  Get all the overdueManagements.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OverdueManagement> findAll(Pageable pageable);

    /**
     *  Get the "id" overdueManagement.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OverdueManagement findOne(Long id);

    /**
     *  Delete the "id" overdueManagement.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

package com.duma.service;

import com.duma.domain.RentalManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing RentalManagement.
 */
public interface RentalManagementService {

    /**
     * Save a rentalManagement.
     *
     * @param rentalManagement the entity to save
     * @return the persisted entity
     */
    RentalManagement save(RentalManagement rentalManagement);

    /**
     *  Get all the rentalManagements.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RentalManagement> findAll(Pageable pageable);

    /**
     *  Get the "id" rentalManagement.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RentalManagement findOne(Long id);

    /**
     *  Delete the "id" rentalManagement.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

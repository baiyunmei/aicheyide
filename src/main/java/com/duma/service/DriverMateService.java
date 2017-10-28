package com.duma.service;

import com.duma.domain.DriverMate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing DriverMate.
 */
public interface DriverMateService {

    /**
     * Save a driverMate.
     *
     * @param driverMate the entity to save
     * @return the persisted entity
     */
    DriverMate save(DriverMate driverMate);

    /**
     *  Get all the driverMates.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DriverMate> findAll(Pageable pageable);

    /**
     *  Get the "id" driverMate.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DriverMate findOne(Long id);

    /**
     *  Delete the "id" driverMate.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

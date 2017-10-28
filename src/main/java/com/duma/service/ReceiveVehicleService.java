package com.duma.service;

import com.duma.domain.ReceiveVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing ReceiveVehicle.
 */
public interface ReceiveVehicleService {

    /**
     * Save a receiveVehicle.
     *
     * @param receiveVehicle the entity to save
     * @return the persisted entity
     */
    ReceiveVehicle save(ReceiveVehicle receiveVehicle);

    /**
     *  Get all the receiveVehicles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ReceiveVehicle> findAll(Pageable pageable);

    /**
     *  Get the "id" receiveVehicle.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ReceiveVehicle findOne(Long id);

    /**
     *  Delete the "id" receiveVehicle.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

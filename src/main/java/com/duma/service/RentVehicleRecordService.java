package com.duma.service;

import com.duma.domain.RentVehicleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing RentVehicleRecord.
 */
public interface RentVehicleRecordService {

    /**
     * Save a rentVehicleRecord.
     *
     * @param rentVehicleRecord the entity to save
     * @return the persisted entity
     */
    RentVehicleRecord save(RentVehicleRecord rentVehicleRecord);

    /**
     *  Get all the rentVehicleRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RentVehicleRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" rentVehicleRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RentVehicleRecord findOne(Long id);

    /**
     *  Delete the "id" rentVehicleRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

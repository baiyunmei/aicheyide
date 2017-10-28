package com.duma.service;

import com.duma.domain.BuyVehicleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing BuyVehicleRecord.
 */
public interface BuyVehicleRecordService {

    /**
     * Save a buyVehicleRecord.
     *
     * @param buyVehicleRecord the entity to save
     * @return the persisted entity
     */
    BuyVehicleRecord save(BuyVehicleRecord buyVehicleRecord);

    /**
     *  Get all the buyVehicleRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<BuyVehicleRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" buyVehicleRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BuyVehicleRecord findOne(Long id);

    /**
     *  Delete the "id" buyVehicleRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

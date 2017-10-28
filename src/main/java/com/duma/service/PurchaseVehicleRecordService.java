package com.duma.service;

import com.duma.domain.PurchaseVehicleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing PurchaseVehicleRecord.
 */
public interface PurchaseVehicleRecordService {

    /**
     * Save a purchaseVehicleRecord.
     *
     * @param purchaseVehicleRecord the entity to save
     * @return the persisted entity
     */
    PurchaseVehicleRecord save(PurchaseVehicleRecord purchaseVehicleRecord);

    /**
     *  Get all the purchaseVehicleRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PurchaseVehicleRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" purchaseVehicleRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PurchaseVehicleRecord findOne(Long id);

    /**
     *  Delete the "id" purchaseVehicleRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

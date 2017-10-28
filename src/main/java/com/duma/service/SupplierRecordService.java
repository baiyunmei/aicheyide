package com.duma.service;

import com.duma.domain.SupplierRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing SupplierRecord.
 */
public interface SupplierRecordService {

    /**
     * Save a supplierRecord.
     *
     * @param supplierRecord the entity to save
     * @return the persisted entity
     */
    SupplierRecord save(SupplierRecord supplierRecord);

    /**
     *  Get all the supplierRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<SupplierRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" supplierRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SupplierRecord findOne(Long id);

    /**
     *  Delete the "id" supplierRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

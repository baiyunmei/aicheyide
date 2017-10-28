package com.duma.service;

import com.duma.domain.RefundRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing RefundRecord.
 */
public interface RefundRecordService {

    /**
     * Save a refundRecord.
     *
     * @param refundRecord the entity to save
     * @return the persisted entity
     */
    RefundRecord save(RefundRecord refundRecord);

    /**
     *  Get all the refundRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RefundRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" refundRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RefundRecord findOne(Long id);

    /**
     *  Delete the "id" refundRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

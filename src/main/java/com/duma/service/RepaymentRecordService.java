package com.duma.service;

import com.duma.domain.RepaymentRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing RepaymentRecord.
 */
public interface RepaymentRecordService {

    /**
     * Save a repaymentRecord.
     *
     * @param repaymentRecord the entity to save
     * @return the persisted entity
     */
    RepaymentRecord save(RepaymentRecord repaymentRecord);

    /**
     *  Get all the repaymentRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RepaymentRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" repaymentRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RepaymentRecord findOne(Long id);

    /**
     *  Delete the "id" repaymentRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

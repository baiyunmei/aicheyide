package com.duma.service;

import com.duma.domain.PolicyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing PolicyRecord.
 */
public interface PolicyRecordService {

    /**
     * Save a policyRecord.
     *
     * @param policyRecord the entity to save
     * @return the persisted entity
     */
    PolicyRecord save(PolicyRecord policyRecord);

    /**
     *  Get all the policyRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PolicyRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" policyRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PolicyRecord findOne(Long id);

    /**
     *  Delete the "id" policyRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

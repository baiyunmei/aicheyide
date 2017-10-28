package com.duma.service;

import com.duma.domain.ContractMarginRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing ContractMarginRecord.
 */
public interface ContractMarginRecordService {

    /**
     * Save a contractMarginRecord.
     *
     * @param contractMarginRecord the entity to save
     * @return the persisted entity
     */
    ContractMarginRecord save(ContractMarginRecord contractMarginRecord);

    /**
     *  Get all the contractMarginRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ContractMarginRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" contractMarginRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ContractMarginRecord findOne(Long id);

    /**
     *  Delete the "id" contractMarginRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

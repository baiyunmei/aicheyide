package com.duma.service;

import com.duma.domain.IllegalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing IllegalRecord.
 */
public interface IllegalRecordService {

    /**
     * Save a illegalRecord.
     *
     * @param illegalRecord the entity to save
     * @return the persisted entity
     */
    IllegalRecord save(IllegalRecord illegalRecord);

    /**
     *  Get all the illegalRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<IllegalRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" illegalRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    IllegalRecord findOne(Long id);

    /**
     *  Delete the "id" illegalRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

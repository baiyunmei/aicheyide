package com.duma.service;

import com.duma.domain.ValidateRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing ValidateRecord.
 */
public interface ValidateRecordService {

    /**
     * Save a validateRecord.
     *
     * @param validateRecord the entity to save
     * @return the persisted entity
     */
    ValidateRecord save(ValidateRecord validateRecord);

    /**
     *  Get all the validateRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ValidateRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" validateRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ValidateRecord findOne(Long id);

    /**
     *  Delete the "id" validateRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

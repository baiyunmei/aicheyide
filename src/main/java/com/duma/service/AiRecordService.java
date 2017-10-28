package com.duma.service;

import com.duma.domain.AiRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing AiRecord.
 */
public interface AiRecordService {

    /**
     * Save a aiRecord.
     *
     * @param aiRecord the entity to save
     * @return the persisted entity
     */
    AiRecord save(AiRecord aiRecord);

    /**
     *  Get all the aiRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AiRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" aiRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AiRecord findOne(Long id);

    /**
     *  Delete the "id" aiRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

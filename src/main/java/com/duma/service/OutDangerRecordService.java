package com.duma.service;

import com.duma.domain.OutDangerRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing OutDangerRecord.
 */
public interface OutDangerRecordService {

    /**
     * Save a outDangerRecord.
     *
     * @param outDangerRecord the entity to save
     * @return the persisted entity
     */
    OutDangerRecord save(OutDangerRecord outDangerRecord);

    /**
     *  Get all the outDangerRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OutDangerRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" outDangerRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OutDangerRecord findOne(Long id);

    /**
     *  Delete the "id" outDangerRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

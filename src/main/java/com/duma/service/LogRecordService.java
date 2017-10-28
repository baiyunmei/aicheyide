package com.duma.service;

import com.duma.domain.LogRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing LogRecord.
 */
public interface LogRecordService {

    /**
     * Save a logRecord.
     *
     * @param logRecord the entity to save
     * @return the persisted entity
     */
    LogRecord save(LogRecord logRecord);

    /**
     *  Get all the logRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<LogRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" logRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LogRecord findOne(Long id);

    /**
     *  Delete the "id" logRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

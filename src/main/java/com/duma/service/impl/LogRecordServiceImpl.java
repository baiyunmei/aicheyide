package com.duma.service.impl;

import com.duma.service.LogRecordService;
import com.duma.domain.LogRecord;
import com.duma.repository.LogRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing LogRecord.
 */
@Service
@Transactional
public class LogRecordServiceImpl implements LogRecordService{

    private final Logger log = LoggerFactory.getLogger(LogRecordServiceImpl.class);
    
    private final LogRecordRepository logRecordRepository;

    public LogRecordServiceImpl(LogRecordRepository logRecordRepository) {
        this.logRecordRepository = logRecordRepository;
    }

    /**
     * Save a logRecord.
     *
     * @param logRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public LogRecord save(LogRecord logRecord) {
        log.debug("Request to save LogRecord : {}", logRecord);
        LogRecord result = logRecordRepository.save(logRecord);
        return result;
    }

    /**
     *  Get all the logRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LogRecord> findAll(Pageable pageable) {
        log.debug("Request to get all LogRecords");
        Page<LogRecord> result = logRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one logRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public LogRecord findOne(Long id) {
        log.debug("Request to get LogRecord : {}", id);
        LogRecord logRecord = logRecordRepository.findOne(id);
        return logRecord;
    }

    /**
     *  Delete the  logRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LogRecord : {}", id);
        logRecordRepository.delete(id);
    }
}

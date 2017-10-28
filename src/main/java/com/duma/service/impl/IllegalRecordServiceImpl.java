package com.duma.service.impl;

import com.duma.service.IllegalRecordService;
import com.duma.domain.IllegalRecord;
import com.duma.repository.IllegalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing IllegalRecord.
 */
@Service
@Transactional
public class IllegalRecordServiceImpl implements IllegalRecordService{

    private final Logger log = LoggerFactory.getLogger(IllegalRecordServiceImpl.class);
    
    private final IllegalRecordRepository illegalRecordRepository;

    public IllegalRecordServiceImpl(IllegalRecordRepository illegalRecordRepository) {
        this.illegalRecordRepository = illegalRecordRepository;
    }

    /**
     * Save a illegalRecord.
     *
     * @param illegalRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public IllegalRecord save(IllegalRecord illegalRecord) {
        log.debug("Request to save IllegalRecord : {}", illegalRecord);
        IllegalRecord result = illegalRecordRepository.save(illegalRecord);
        return result;
    }

    /**
     *  Get all the illegalRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IllegalRecord> findAll(Pageable pageable) {
        log.debug("Request to get all IllegalRecords");
        Page<IllegalRecord> result = illegalRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one illegalRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public IllegalRecord findOne(Long id) {
        log.debug("Request to get IllegalRecord : {}", id);
        IllegalRecord illegalRecord = illegalRecordRepository.findOne(id);
        return illegalRecord;
    }

    /**
     *  Delete the  illegalRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IllegalRecord : {}", id);
        illegalRecordRepository.delete(id);
    }
}

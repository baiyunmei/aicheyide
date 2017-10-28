package com.duma.service.impl;

import com.duma.service.OutDangerRecordService;
import com.duma.domain.OutDangerRecord;
import com.duma.repository.OutDangerRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing OutDangerRecord.
 */
@Service
@Transactional
public class OutDangerRecordServiceImpl implements OutDangerRecordService{

    private final Logger log = LoggerFactory.getLogger(OutDangerRecordServiceImpl.class);
    
    private final OutDangerRecordRepository outDangerRecordRepository;

    public OutDangerRecordServiceImpl(OutDangerRecordRepository outDangerRecordRepository) {
        this.outDangerRecordRepository = outDangerRecordRepository;
    }

    /**
     * Save a outDangerRecord.
     *
     * @param outDangerRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public OutDangerRecord save(OutDangerRecord outDangerRecord) {
        log.debug("Request to save OutDangerRecord : {}", outDangerRecord);
        OutDangerRecord result = outDangerRecordRepository.save(outDangerRecord);
        return result;
    }

    /**
     *  Get all the outDangerRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OutDangerRecord> findAll(Pageable pageable) {
        log.debug("Request to get all OutDangerRecords");
        Page<OutDangerRecord> result = outDangerRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one outDangerRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OutDangerRecord findOne(Long id) {
        log.debug("Request to get OutDangerRecord : {}", id);
        OutDangerRecord outDangerRecord = outDangerRecordRepository.findOne(id);
        return outDangerRecord;
    }

    /**
     *  Delete the  outDangerRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OutDangerRecord : {}", id);
        outDangerRecordRepository.delete(id);
    }
}

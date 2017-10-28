package com.duma.service.impl;

import com.duma.service.AiRecordService;
import com.duma.domain.AiRecord;
import com.duma.repository.AiRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing AiRecord.
 */
@Service
@Transactional
public class AiRecordServiceImpl implements AiRecordService{

    private final Logger log = LoggerFactory.getLogger(AiRecordServiceImpl.class);
    
    private final AiRecordRepository aiRecordRepository;

    public AiRecordServiceImpl(AiRecordRepository aiRecordRepository) {
        this.aiRecordRepository = aiRecordRepository;
    }

    /**
     * Save a aiRecord.
     *
     * @param aiRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public AiRecord save(AiRecord aiRecord) {
        log.debug("Request to save AiRecord : {}", aiRecord);
        AiRecord result = aiRecordRepository.save(aiRecord);
        return result;
    }

    /**
     *  Get all the aiRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AiRecord> findAll(Pageable pageable) {
        log.debug("Request to get all AiRecords");
        Page<AiRecord> result = aiRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one aiRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AiRecord findOne(Long id) {
        log.debug("Request to get AiRecord : {}", id);
        AiRecord aiRecord = aiRecordRepository.findOne(id);
        return aiRecord;
    }

    /**
     *  Delete the  aiRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AiRecord : {}", id);
        aiRecordRepository.delete(id);
    }
}

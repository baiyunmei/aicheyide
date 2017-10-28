package com.duma.service.impl;

import com.duma.service.PostponeRecordService;
import com.duma.domain.PostponeRecord;
import com.duma.repository.PostponeRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing PostponeRecord.
 */
@Service
@Transactional
public class PostponeRecordServiceImpl implements PostponeRecordService{

    private final Logger log = LoggerFactory.getLogger(PostponeRecordServiceImpl.class);
    
    private final PostponeRecordRepository postponeRecordRepository;

    public PostponeRecordServiceImpl(PostponeRecordRepository postponeRecordRepository) {
        this.postponeRecordRepository = postponeRecordRepository;
    }

    /**
     * Save a postponeRecord.
     *
     * @param postponeRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public PostponeRecord save(PostponeRecord postponeRecord) {
        log.debug("Request to save PostponeRecord : {}", postponeRecord);
        PostponeRecord result = postponeRecordRepository.save(postponeRecord);
        return result;
    }

    /**
     *  Get all the postponeRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PostponeRecord> findAll(Pageable pageable) {
        log.debug("Request to get all PostponeRecords");
        Page<PostponeRecord> result = postponeRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one postponeRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PostponeRecord findOne(Long id) {
        log.debug("Request to get PostponeRecord : {}", id);
        PostponeRecord postponeRecord = postponeRecordRepository.findOne(id);
        return postponeRecord;
    }

    /**
     *  Delete the  postponeRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PostponeRecord : {}", id);
        postponeRecordRepository.delete(id);
    }
}

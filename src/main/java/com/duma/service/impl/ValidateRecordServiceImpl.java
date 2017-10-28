package com.duma.service.impl;

import com.duma.service.ValidateRecordService;
import com.duma.domain.ValidateRecord;
import com.duma.repository.ValidateRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing ValidateRecord.
 */
@Service
@Transactional
public class ValidateRecordServiceImpl implements ValidateRecordService{

    private final Logger log = LoggerFactory.getLogger(ValidateRecordServiceImpl.class);
    
    private final ValidateRecordRepository validateRecordRepository;

    public ValidateRecordServiceImpl(ValidateRecordRepository validateRecordRepository) {
        this.validateRecordRepository = validateRecordRepository;
    }

    /**
     * Save a validateRecord.
     *
     * @param validateRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public ValidateRecord save(ValidateRecord validateRecord) {
        log.debug("Request to save ValidateRecord : {}", validateRecord);
        ValidateRecord result = validateRecordRepository.save(validateRecord);
        return result;
    }

    /**
     *  Get all the validateRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ValidateRecord> findAll(Pageable pageable) {
        log.debug("Request to get all ValidateRecords");
        Page<ValidateRecord> result = validateRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one validateRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ValidateRecord findOne(Long id) {
        log.debug("Request to get ValidateRecord : {}", id);
        ValidateRecord validateRecord = validateRecordRepository.findOne(id);
        return validateRecord;
    }

    /**
     *  Delete the  validateRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ValidateRecord : {}", id);
        validateRecordRepository.delete(id);
    }
}

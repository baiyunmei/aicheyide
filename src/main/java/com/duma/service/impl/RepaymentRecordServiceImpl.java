package com.duma.service.impl;

import com.duma.service.RepaymentRecordService;
import com.duma.domain.RepaymentRecord;
import com.duma.repository.RepaymentRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing RepaymentRecord.
 */
@Service
@Transactional
public class RepaymentRecordServiceImpl implements RepaymentRecordService{

    private final Logger log = LoggerFactory.getLogger(RepaymentRecordServiceImpl.class);
    
    private final RepaymentRecordRepository repaymentRecordRepository;

    public RepaymentRecordServiceImpl(RepaymentRecordRepository repaymentRecordRepository) {
        this.repaymentRecordRepository = repaymentRecordRepository;
    }

    /**
     * Save a repaymentRecord.
     *
     * @param repaymentRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public RepaymentRecord save(RepaymentRecord repaymentRecord) {
        log.debug("Request to save RepaymentRecord : {}", repaymentRecord);
        RepaymentRecord result = repaymentRecordRepository.save(repaymentRecord);
        return result;
    }

    /**
     *  Get all the repaymentRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RepaymentRecord> findAll(Pageable pageable) {
        log.debug("Request to get all RepaymentRecords");
        Page<RepaymentRecord> result = repaymentRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one repaymentRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RepaymentRecord findOne(Long id) {
        log.debug("Request to get RepaymentRecord : {}", id);
        RepaymentRecord repaymentRecord = repaymentRecordRepository.findOne(id);
        return repaymentRecord;
    }

    /**
     *  Delete the  repaymentRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RepaymentRecord : {}", id);
        repaymentRecordRepository.delete(id);
    }
}

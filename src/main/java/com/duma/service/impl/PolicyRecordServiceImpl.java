package com.duma.service.impl;

import com.duma.service.PolicyRecordService;
import com.duma.domain.PolicyRecord;
import com.duma.repository.PolicyRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing PolicyRecord.
 */
@Service
@Transactional
public class PolicyRecordServiceImpl implements PolicyRecordService{

    private final Logger log = LoggerFactory.getLogger(PolicyRecordServiceImpl.class);
    
    private final PolicyRecordRepository policyRecordRepository;

    public PolicyRecordServiceImpl(PolicyRecordRepository policyRecordRepository) {
        this.policyRecordRepository = policyRecordRepository;
    }

    /**
     * Save a policyRecord.
     *
     * @param policyRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public PolicyRecord save(PolicyRecord policyRecord) {
        log.debug("Request to save PolicyRecord : {}", policyRecord);
        PolicyRecord result = policyRecordRepository.save(policyRecord);
        return result;
    }

    /**
     *  Get all the policyRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PolicyRecord> findAll(Pageable pageable) {
        log.debug("Request to get all PolicyRecords");
        Page<PolicyRecord> result = policyRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one policyRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PolicyRecord findOne(Long id) {
        log.debug("Request to get PolicyRecord : {}", id);
        PolicyRecord policyRecord = policyRecordRepository.findOne(id);
        return policyRecord;
    }

    /**
     *  Delete the  policyRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PolicyRecord : {}", id);
        policyRecordRepository.delete(id);
    }
}

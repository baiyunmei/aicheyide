package com.duma.service.impl;

import com.duma.service.ContractMarginRecordService;
import com.duma.domain.ContractMarginRecord;
import com.duma.repository.ContractMarginRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing ContractMarginRecord.
 */
@Service
@Transactional
public class ContractMarginRecordServiceImpl implements ContractMarginRecordService{

    private final Logger log = LoggerFactory.getLogger(ContractMarginRecordServiceImpl.class);
    
    private final ContractMarginRecordRepository contractMarginRecordRepository;

    public ContractMarginRecordServiceImpl(ContractMarginRecordRepository contractMarginRecordRepository) {
        this.contractMarginRecordRepository = contractMarginRecordRepository;
    }

    /**
     * Save a contractMarginRecord.
     *
     * @param contractMarginRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public ContractMarginRecord save(ContractMarginRecord contractMarginRecord) {
        log.debug("Request to save ContractMarginRecord : {}", contractMarginRecord);
        ContractMarginRecord result = contractMarginRecordRepository.save(contractMarginRecord);
        return result;
    }

    /**
     *  Get all the contractMarginRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContractMarginRecord> findAll(Pageable pageable) {
        log.debug("Request to get all ContractMarginRecords");
        Page<ContractMarginRecord> result = contractMarginRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one contractMarginRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ContractMarginRecord findOne(Long id) {
        log.debug("Request to get ContractMarginRecord : {}", id);
        ContractMarginRecord contractMarginRecord = contractMarginRecordRepository.findOne(id);
        return contractMarginRecord;
    }

    /**
     *  Delete the  contractMarginRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContractMarginRecord : {}", id);
        contractMarginRecordRepository.delete(id);
    }
}

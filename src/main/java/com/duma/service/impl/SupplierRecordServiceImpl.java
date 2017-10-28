package com.duma.service.impl;

import com.duma.service.SupplierRecordService;
import com.duma.domain.SupplierRecord;
import com.duma.repository.SupplierRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing SupplierRecord.
 */
@Service
@Transactional
public class SupplierRecordServiceImpl implements SupplierRecordService{

    private final Logger log = LoggerFactory.getLogger(SupplierRecordServiceImpl.class);
    
    private final SupplierRecordRepository supplierRecordRepository;

    public SupplierRecordServiceImpl(SupplierRecordRepository supplierRecordRepository) {
        this.supplierRecordRepository = supplierRecordRepository;
    }

    /**
     * Save a supplierRecord.
     *
     * @param supplierRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public SupplierRecord save(SupplierRecord supplierRecord) {
        log.debug("Request to save SupplierRecord : {}", supplierRecord);
        SupplierRecord result = supplierRecordRepository.save(supplierRecord);
        return result;
    }

    /**
     *  Get all the supplierRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SupplierRecord> findAll(Pageable pageable) {
        log.debug("Request to get all SupplierRecords");
        Page<SupplierRecord> result = supplierRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one supplierRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SupplierRecord findOne(Long id) {
        log.debug("Request to get SupplierRecord : {}", id);
        SupplierRecord supplierRecord = supplierRecordRepository.findOne(id);
        return supplierRecord;
    }

    /**
     *  Delete the  supplierRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SupplierRecord : {}", id);
        supplierRecordRepository.delete(id);
    }
}

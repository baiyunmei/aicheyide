package com.duma.service.impl;

import com.duma.service.PurchaseVehicleRecordService;
import com.duma.domain.PurchaseVehicleRecord;
import com.duma.repository.PurchaseVehicleRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing PurchaseVehicleRecord.
 */
@Service
@Transactional
public class PurchaseVehicleRecordServiceImpl implements PurchaseVehicleRecordService{

    private final Logger log = LoggerFactory.getLogger(PurchaseVehicleRecordServiceImpl.class);
    
    private final PurchaseVehicleRecordRepository purchaseVehicleRecordRepository;

    public PurchaseVehicleRecordServiceImpl(PurchaseVehicleRecordRepository purchaseVehicleRecordRepository) {
        this.purchaseVehicleRecordRepository = purchaseVehicleRecordRepository;
    }

    /**
     * Save a purchaseVehicleRecord.
     *
     * @param purchaseVehicleRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public PurchaseVehicleRecord save(PurchaseVehicleRecord purchaseVehicleRecord) {
        log.debug("Request to save PurchaseVehicleRecord : {}", purchaseVehicleRecord);
        PurchaseVehicleRecord result = purchaseVehicleRecordRepository.save(purchaseVehicleRecord);
        return result;
    }

    /**
     *  Get all the purchaseVehicleRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseVehicleRecord> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseVehicleRecords");
        Page<PurchaseVehicleRecord> result = purchaseVehicleRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one purchaseVehicleRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PurchaseVehicleRecord findOne(Long id) {
        log.debug("Request to get PurchaseVehicleRecord : {}", id);
        PurchaseVehicleRecord purchaseVehicleRecord = purchaseVehicleRecordRepository.findOne(id);
        return purchaseVehicleRecord;
    }

    /**
     *  Delete the  purchaseVehicleRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseVehicleRecord : {}", id);
        purchaseVehicleRecordRepository.delete(id);
    }
}

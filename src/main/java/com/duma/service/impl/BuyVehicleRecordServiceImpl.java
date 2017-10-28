package com.duma.service.impl;

import com.duma.service.BuyVehicleRecordService;
import com.duma.domain.BuyVehicleRecord;
import com.duma.repository.BuyVehicleRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing BuyVehicleRecord.
 */
@Service
@Transactional
public class BuyVehicleRecordServiceImpl implements BuyVehicleRecordService{

    private final Logger log = LoggerFactory.getLogger(BuyVehicleRecordServiceImpl.class);
    
    private final BuyVehicleRecordRepository buyVehicleRecordRepository;

    public BuyVehicleRecordServiceImpl(BuyVehicleRecordRepository buyVehicleRecordRepository) {
        this.buyVehicleRecordRepository = buyVehicleRecordRepository;
    }

    /**
     * Save a buyVehicleRecord.
     *
     * @param buyVehicleRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public BuyVehicleRecord save(BuyVehicleRecord buyVehicleRecord) {
        log.debug("Request to save BuyVehicleRecord : {}", buyVehicleRecord);
        BuyVehicleRecord result = buyVehicleRecordRepository.save(buyVehicleRecord);
        return result;
    }

    /**
     *  Get all the buyVehicleRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BuyVehicleRecord> findAll(Pageable pageable) {
        log.debug("Request to get all BuyVehicleRecords");
        Page<BuyVehicleRecord> result = buyVehicleRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one buyVehicleRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BuyVehicleRecord findOne(Long id) {
        log.debug("Request to get BuyVehicleRecord : {}", id);
        BuyVehicleRecord buyVehicleRecord = buyVehicleRecordRepository.findOne(id);
        return buyVehicleRecord;
    }

    /**
     *  Delete the  buyVehicleRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BuyVehicleRecord : {}", id);
        buyVehicleRecordRepository.delete(id);
    }
}

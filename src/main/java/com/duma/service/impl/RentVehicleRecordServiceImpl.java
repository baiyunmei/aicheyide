package com.duma.service.impl;

import com.duma.service.RentVehicleRecordService;
import com.duma.domain.RentVehicleRecord;
import com.duma.repository.RentVehicleRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing RentVehicleRecord.
 */
@Service
@Transactional
public class RentVehicleRecordServiceImpl implements RentVehicleRecordService{

    private final Logger log = LoggerFactory.getLogger(RentVehicleRecordServiceImpl.class);
    
    private final RentVehicleRecordRepository rentVehicleRecordRepository;

    public RentVehicleRecordServiceImpl(RentVehicleRecordRepository rentVehicleRecordRepository) {
        this.rentVehicleRecordRepository = rentVehicleRecordRepository;
    }

    /**
     * Save a rentVehicleRecord.
     *
     * @param rentVehicleRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public RentVehicleRecord save(RentVehicleRecord rentVehicleRecord) {
        log.debug("Request to save RentVehicleRecord : {}", rentVehicleRecord);
        RentVehicleRecord result = rentVehicleRecordRepository.save(rentVehicleRecord);
        return result;
    }

    /**
     *  Get all the rentVehicleRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RentVehicleRecord> findAll(Pageable pageable) {
        log.debug("Request to get all RentVehicleRecords");
        Page<RentVehicleRecord> result = rentVehicleRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one rentVehicleRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RentVehicleRecord findOne(Long id) {
        log.debug("Request to get RentVehicleRecord : {}", id);
        RentVehicleRecord rentVehicleRecord = rentVehicleRecordRepository.findOne(id);
        return rentVehicleRecord;
    }

    /**
     *  Delete the  rentVehicleRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RentVehicleRecord : {}", id);
        rentVehicleRecordRepository.delete(id);
    }
}

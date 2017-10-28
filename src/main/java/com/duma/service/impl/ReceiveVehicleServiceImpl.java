package com.duma.service.impl;

import com.duma.service.ReceiveVehicleService;
import com.duma.domain.ReceiveVehicle;
import com.duma.repository.ReceiveVehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing ReceiveVehicle.
 */
@Service
@Transactional
public class ReceiveVehicleServiceImpl implements ReceiveVehicleService{

    private final Logger log = LoggerFactory.getLogger(ReceiveVehicleServiceImpl.class);
    
    private final ReceiveVehicleRepository receiveVehicleRepository;

    public ReceiveVehicleServiceImpl(ReceiveVehicleRepository receiveVehicleRepository) {
        this.receiveVehicleRepository = receiveVehicleRepository;
    }

    /**
     * Save a receiveVehicle.
     *
     * @param receiveVehicle the entity to save
     * @return the persisted entity
     */
    @Override
    public ReceiveVehicle save(ReceiveVehicle receiveVehicle) {
        log.debug("Request to save ReceiveVehicle : {}", receiveVehicle);
        ReceiveVehicle result = receiveVehicleRepository.save(receiveVehicle);
        return result;
    }

    /**
     *  Get all the receiveVehicles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReceiveVehicle> findAll(Pageable pageable) {
        log.debug("Request to get all ReceiveVehicles");
        Page<ReceiveVehicle> result = receiveVehicleRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one receiveVehicle by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ReceiveVehicle findOne(Long id) {
        log.debug("Request to get ReceiveVehicle : {}", id);
        ReceiveVehicle receiveVehicle = receiveVehicleRepository.findOne(id);
        return receiveVehicle;
    }

    /**
     *  Delete the  receiveVehicle by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReceiveVehicle : {}", id);
        receiveVehicleRepository.delete(id);
    }
}

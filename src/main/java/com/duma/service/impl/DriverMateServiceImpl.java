package com.duma.service.impl;

import com.duma.service.DriverMateService;
import com.duma.domain.DriverMate;
import com.duma.repository.DriverMateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing DriverMate.
 */
@Service
@Transactional
public class DriverMateServiceImpl implements DriverMateService{

    private final Logger log = LoggerFactory.getLogger(DriverMateServiceImpl.class);
    
    private final DriverMateRepository driverMateRepository;

    public DriverMateServiceImpl(DriverMateRepository driverMateRepository) {
        this.driverMateRepository = driverMateRepository;
    }

    /**
     * Save a driverMate.
     *
     * @param driverMate the entity to save
     * @return the persisted entity
     */
    @Override
    public DriverMate save(DriverMate driverMate) {
        log.debug("Request to save DriverMate : {}", driverMate);
        DriverMate result = driverMateRepository.save(driverMate);
        return result;
    }

    /**
     *  Get all the driverMates.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DriverMate> findAll(Pageable pageable) {
        log.debug("Request to get all DriverMates");
        Page<DriverMate> result = driverMateRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one driverMate by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DriverMate findOne(Long id) {
        log.debug("Request to get DriverMate : {}", id);
        DriverMate driverMate = driverMateRepository.findOne(id);
        return driverMate;
    }

    /**
     *  Delete the  driverMate by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DriverMate : {}", id);
        driverMateRepository.delete(id);
    }
}

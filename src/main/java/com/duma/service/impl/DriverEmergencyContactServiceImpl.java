package com.duma.service.impl;

import com.duma.service.DriverEmergencyContactService;
import com.duma.domain.DriverEmergencyContact;
import com.duma.repository.DriverEmergencyContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing DriverEmergencyContact.
 */
@Service
@Transactional
public class DriverEmergencyContactServiceImpl implements DriverEmergencyContactService{

    private final Logger log = LoggerFactory.getLogger(DriverEmergencyContactServiceImpl.class);
    
    private final DriverEmergencyContactRepository driverEmergencyContactRepository;

    public DriverEmergencyContactServiceImpl(DriverEmergencyContactRepository driverEmergencyContactRepository) {
        this.driverEmergencyContactRepository = driverEmergencyContactRepository;
    }

    /**
     * Save a driverEmergencyContact.
     *
     * @param driverEmergencyContact the entity to save
     * @return the persisted entity
     */
    @Override
    public DriverEmergencyContact save(DriverEmergencyContact driverEmergencyContact) {
        log.debug("Request to save DriverEmergencyContact : {}", driverEmergencyContact);
        DriverEmergencyContact result = driverEmergencyContactRepository.save(driverEmergencyContact);
        return result;
    }

    /**
     *  Get all the driverEmergencyContacts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DriverEmergencyContact> findAll(Pageable pageable) {
        log.debug("Request to get all DriverEmergencyContacts");
        Page<DriverEmergencyContact> result = driverEmergencyContactRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one driverEmergencyContact by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DriverEmergencyContact findOne(Long id) {
        log.debug("Request to get DriverEmergencyContact : {}", id);
        DriverEmergencyContact driverEmergencyContact = driverEmergencyContactRepository.findOne(id);
        return driverEmergencyContact;
    }

    /**
     *  Delete the  driverEmergencyContact by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DriverEmergencyContact : {}", id);
        driverEmergencyContactRepository.delete(id);
    }
}

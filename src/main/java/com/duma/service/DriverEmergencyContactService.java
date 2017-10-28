package com.duma.service;

import com.duma.domain.DriverEmergencyContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing DriverEmergencyContact.
 */
public interface DriverEmergencyContactService {

    /**
     * Save a driverEmergencyContact.
     *
     * @param driverEmergencyContact the entity to save
     * @return the persisted entity
     */
    DriverEmergencyContact save(DriverEmergencyContact driverEmergencyContact);

    /**
     *  Get all the driverEmergencyContacts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DriverEmergencyContact> findAll(Pageable pageable);

    /**
     *  Get the "id" driverEmergencyContact.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DriverEmergencyContact findOne(Long id);

    /**
     *  Delete the "id" driverEmergencyContact.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

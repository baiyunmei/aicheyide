package com.duma.service;

import com.duma.domain.DriverMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing DriverMessage.
 */
public interface DriverMessageService {

    /**
     * Save a driverMessage.
     *
     * @param driverMessage the entity to save
     * @return the persisted entity
     */
    DriverMessage save(DriverMessage driverMessage);

    /**
     *  Get all the driverMessages.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DriverMessage> findAll(Pageable pageable);

    /**
     *  Get the "id" driverMessage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DriverMessage findOne(Long id);

    /**
     *  Delete the "id" driverMessage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

package com.duma.service.impl;

import com.duma.service.DriverMessageService;
import com.duma.domain.DriverMessage;
import com.duma.repository.DriverMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing DriverMessage.
 */
@Service
@Transactional
public class DriverMessageServiceImpl implements DriverMessageService{

    private final Logger log = LoggerFactory.getLogger(DriverMessageServiceImpl.class);
    
    private final DriverMessageRepository driverMessageRepository;

    public DriverMessageServiceImpl(DriverMessageRepository driverMessageRepository) {
        this.driverMessageRepository = driverMessageRepository;
    }

    /**
     * Save a driverMessage.
     *
     * @param driverMessage the entity to save
     * @return the persisted entity
     */
    @Override
    public DriverMessage save(DriverMessage driverMessage) {
        log.debug("Request to save DriverMessage : {}", driverMessage);
        DriverMessage result = driverMessageRepository.save(driverMessage);
        return result;
    }

    /**
     *  Get all the driverMessages.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DriverMessage> findAll(Pageable pageable) {
        log.debug("Request to get all DriverMessages");
        Page<DriverMessage> result = driverMessageRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one driverMessage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DriverMessage findOne(Long id) {
        log.debug("Request to get DriverMessage : {}", id);
        DriverMessage driverMessage = driverMessageRepository.findOne(id);
        return driverMessage;
    }

    /**
     *  Delete the  driverMessage by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DriverMessage : {}", id);
        driverMessageRepository.delete(id);
    }
}

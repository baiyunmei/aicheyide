package com.duma.service.impl;

import com.duma.service.RentalManagementService;
import com.duma.domain.RentalManagement;
import com.duma.repository.RentalManagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing RentalManagement.
 */
@Service
@Transactional
public class RentalManagementServiceImpl implements RentalManagementService{

    private final Logger log = LoggerFactory.getLogger(RentalManagementServiceImpl.class);
    
    private final RentalManagementRepository rentalManagementRepository;

    public RentalManagementServiceImpl(RentalManagementRepository rentalManagementRepository) {
        this.rentalManagementRepository = rentalManagementRepository;
    }

    /**
     * Save a rentalManagement.
     *
     * @param rentalManagement the entity to save
     * @return the persisted entity
     */
    @Override
    public RentalManagement save(RentalManagement rentalManagement) {
        log.debug("Request to save RentalManagement : {}", rentalManagement);
        RentalManagement result = rentalManagementRepository.save(rentalManagement);
        return result;
    }

    /**
     *  Get all the rentalManagements.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RentalManagement> findAll(Pageable pageable) {
        log.debug("Request to get all RentalManagements");
        Page<RentalManagement> result = rentalManagementRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one rentalManagement by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RentalManagement findOne(Long id) {
        log.debug("Request to get RentalManagement : {}", id);
        RentalManagement rentalManagement = rentalManagementRepository.findOne(id);
        return rentalManagement;
    }

    /**
     *  Delete the  rentalManagement by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RentalManagement : {}", id);
        rentalManagementRepository.delete(id);
    }
}

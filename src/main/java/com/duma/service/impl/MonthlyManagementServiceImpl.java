package com.duma.service.impl;

import com.duma.service.MonthlyManagementService;
import com.duma.domain.MonthlyManagement;
import com.duma.repository.MonthlyManagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing MonthlyManagement.
 */
@Service
@Transactional
public class MonthlyManagementServiceImpl implements MonthlyManagementService{

    private final Logger log = LoggerFactory.getLogger(MonthlyManagementServiceImpl.class);
    
    private final MonthlyManagementRepository monthlyManagementRepository;

    public MonthlyManagementServiceImpl(MonthlyManagementRepository monthlyManagementRepository) {
        this.monthlyManagementRepository = monthlyManagementRepository;
    }

    /**
     * Save a monthlyManagement.
     *
     * @param monthlyManagement the entity to save
     * @return the persisted entity
     */
    @Override
    public MonthlyManagement save(MonthlyManagement monthlyManagement) {
        log.debug("Request to save MonthlyManagement : {}", monthlyManagement);
        MonthlyManagement result = monthlyManagementRepository.save(monthlyManagement);
        return result;
    }

    /**
     *  Get all the monthlyManagements.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MonthlyManagement> findAll(Pageable pageable) {
        log.debug("Request to get all MonthlyManagements");
        Page<MonthlyManagement> result = monthlyManagementRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one monthlyManagement by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MonthlyManagement findOne(Long id) {
        log.debug("Request to get MonthlyManagement : {}", id);
        MonthlyManagement monthlyManagement = monthlyManagementRepository.findOne(id);
        return monthlyManagement;
    }

    /**
     *  Delete the  monthlyManagement by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonthlyManagement : {}", id);
        monthlyManagementRepository.delete(id);
    }
}

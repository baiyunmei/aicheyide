package com.duma.service.impl;

import com.duma.service.OverdueManagementService;
import com.duma.domain.OverdueManagement;
import com.duma.repository.OverdueManagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing OverdueManagement.
 */
@Service
@Transactional
public class OverdueManagementServiceImpl implements OverdueManagementService{

    private final Logger log = LoggerFactory.getLogger(OverdueManagementServiceImpl.class);
    
    private final OverdueManagementRepository overdueManagementRepository;

    public OverdueManagementServiceImpl(OverdueManagementRepository overdueManagementRepository) {
        this.overdueManagementRepository = overdueManagementRepository;
    }

    /**
     * Save a overdueManagement.
     *
     * @param overdueManagement the entity to save
     * @return the persisted entity
     */
    @Override
    public OverdueManagement save(OverdueManagement overdueManagement) {
        log.debug("Request to save OverdueManagement : {}", overdueManagement);
        OverdueManagement result = overdueManagementRepository.save(overdueManagement);
        return result;
    }

    /**
     *  Get all the overdueManagements.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OverdueManagement> findAll(Pageable pageable) {
        log.debug("Request to get all OverdueManagements");
        Page<OverdueManagement> result = overdueManagementRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one overdueManagement by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OverdueManagement findOne(Long id) {
        log.debug("Request to get OverdueManagement : {}", id);
        OverdueManagement overdueManagement = overdueManagementRepository.findOne(id);
        return overdueManagement;
    }

    /**
     *  Delete the  overdueManagement by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OverdueManagement : {}", id);
        overdueManagementRepository.delete(id);
    }
}

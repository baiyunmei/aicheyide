package com.duma.service.impl;

import com.duma.service.PleasePayeeAuditService;
import com.duma.domain.PleasePayeeAudit;
import com.duma.repository.PleasePayeeAuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing PleasePayeeAudit.
 */
@Service
@Transactional
public class PleasePayeeAuditServiceImpl implements PleasePayeeAuditService{

    private final Logger log = LoggerFactory.getLogger(PleasePayeeAuditServiceImpl.class);
    
    private final PleasePayeeAuditRepository pleasePayeeAuditRepository;

    public PleasePayeeAuditServiceImpl(PleasePayeeAuditRepository pleasePayeeAuditRepository) {
        this.pleasePayeeAuditRepository = pleasePayeeAuditRepository;
    }

    /**
     * Save a pleasePayeeAudit.
     *
     * @param pleasePayeeAudit the entity to save
     * @return the persisted entity
     */
    @Override
    public PleasePayeeAudit save(PleasePayeeAudit pleasePayeeAudit) {
        log.debug("Request to save PleasePayeeAudit : {}", pleasePayeeAudit);
        PleasePayeeAudit result = pleasePayeeAuditRepository.save(pleasePayeeAudit);
        return result;
    }

    /**
     *  Get all the pleasePayeeAudits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PleasePayeeAudit> findAll(Pageable pageable) {
        log.debug("Request to get all PleasePayeeAudits");
        Page<PleasePayeeAudit> result = pleasePayeeAuditRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one pleasePayeeAudit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PleasePayeeAudit findOne(Long id) {
        log.debug("Request to get PleasePayeeAudit : {}", id);
        PleasePayeeAudit pleasePayeeAudit = pleasePayeeAuditRepository.findOne(id);
        return pleasePayeeAudit;
    }

    /**
     *  Delete the  pleasePayeeAudit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PleasePayeeAudit : {}", id);
        pleasePayeeAuditRepository.delete(id);
    }
}

package com.duma.service.impl;

import com.duma.service.ClaimSettlementAuditService;
import com.duma.domain.ClaimSettlementAudit;
import com.duma.repository.ClaimSettlementAuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing ClaimSettlementAudit.
 */
@Service
@Transactional
public class ClaimSettlementAuditServiceImpl implements ClaimSettlementAuditService{

    private final Logger log = LoggerFactory.getLogger(ClaimSettlementAuditServiceImpl.class);
    
    private final ClaimSettlementAuditRepository claimSettlementAuditRepository;

    public ClaimSettlementAuditServiceImpl(ClaimSettlementAuditRepository claimSettlementAuditRepository) {
        this.claimSettlementAuditRepository = claimSettlementAuditRepository;
    }

    /**
     * Save a claimSettlementAudit.
     *
     * @param claimSettlementAudit the entity to save
     * @return the persisted entity
     */
    @Override
    public ClaimSettlementAudit save(ClaimSettlementAudit claimSettlementAudit) {
        log.debug("Request to save ClaimSettlementAudit : {}", claimSettlementAudit);
        ClaimSettlementAudit result = claimSettlementAuditRepository.save(claimSettlementAudit);
        return result;
    }

    /**
     *  Get all the claimSettlementAudits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ClaimSettlementAudit> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimSettlementAudits");
        Page<ClaimSettlementAudit> result = claimSettlementAuditRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one claimSettlementAudit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ClaimSettlementAudit findOne(Long id) {
        log.debug("Request to get ClaimSettlementAudit : {}", id);
        ClaimSettlementAudit claimSettlementAudit = claimSettlementAuditRepository.findOne(id);
        return claimSettlementAudit;
    }

    /**
     *  Delete the  claimSettlementAudit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimSettlementAudit : {}", id);
        claimSettlementAuditRepository.delete(id);
    }
}

package com.duma.service;

import com.duma.domain.ClaimSettlementAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing ClaimSettlementAudit.
 */
public interface ClaimSettlementAuditService {

    /**
     * Save a claimSettlementAudit.
     *
     * @param claimSettlementAudit the entity to save
     * @return the persisted entity
     */
    ClaimSettlementAudit save(ClaimSettlementAudit claimSettlementAudit);

    /**
     *  Get all the claimSettlementAudits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ClaimSettlementAudit> findAll(Pageable pageable);

    /**
     *  Get the "id" claimSettlementAudit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ClaimSettlementAudit findOne(Long id);

    /**
     *  Delete the "id" claimSettlementAudit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

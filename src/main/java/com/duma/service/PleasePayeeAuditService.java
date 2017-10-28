package com.duma.service;

import com.duma.domain.PleasePayeeAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing PleasePayeeAudit.
 */
public interface PleasePayeeAuditService {

    /**
     * Save a pleasePayeeAudit.
     *
     * @param pleasePayeeAudit the entity to save
     * @return the persisted entity
     */
    PleasePayeeAudit save(PleasePayeeAudit pleasePayeeAudit);

    /**
     *  Get all the pleasePayeeAudits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PleasePayeeAudit> findAll(Pageable pageable);

    /**
     *  Get the "id" pleasePayeeAudit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PleasePayeeAudit findOne(Long id);

    /**
     *  Delete the "id" pleasePayeeAudit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

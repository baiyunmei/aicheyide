package com.duma.service;

import com.duma.domain.AuthorizationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing AuthorizationRecord.
 */
public interface AuthorizationRecordService {

    /**
     * Save a authorizationRecord.
     *
     * @param authorizationRecord the entity to save
     * @return the persisted entity
     */
    AuthorizationRecord save(AuthorizationRecord authorizationRecord);

    /**
     *  Get all the authorizationRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AuthorizationRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" authorizationRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuthorizationRecord findOne(Long id);

    /**
     *  Delete the "id" authorizationRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

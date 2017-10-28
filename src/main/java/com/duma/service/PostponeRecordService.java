package com.duma.service;

import com.duma.domain.PostponeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing PostponeRecord.
 */
public interface PostponeRecordService {

    /**
     * Save a postponeRecord.
     *
     * @param postponeRecord the entity to save
     * @return the persisted entity
     */
    PostponeRecord save(PostponeRecord postponeRecord);

    /**
     *  Get all the postponeRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PostponeRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" postponeRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PostponeRecord findOne(Long id);

    /**
     *  Delete the "id" postponeRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

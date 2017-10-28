package com.duma.service;

import com.duma.domain.MaintainRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing MaintainRecord.
 */
public interface MaintainRecordService {

    /**
     * Save a maintainRecord.
     *
     * @param maintainRecord the entity to save
     * @return the persisted entity
     */
    MaintainRecord save(MaintainRecord maintainRecord);

    /**
     *  Get all the maintainRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MaintainRecord> findAll(Pageable pageable);

    /**
     *  Get the "id" maintainRecord.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MaintainRecord findOne(Long id);

    /**
     *  Delete the "id" maintainRecord.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

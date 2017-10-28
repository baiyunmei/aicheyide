package com.duma.service;

import com.duma.domain.OverdueGathering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing OverdueGathering.
 */
public interface OverdueGatheringService {

    /**
     * Save a overdueGathering.
     *
     * @param overdueGathering the entity to save
     * @return the persisted entity
     */
    OverdueGathering save(OverdueGathering overdueGathering);

    /**
     *  Get all the overdueGatherings.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OverdueGathering> findAll(Pageable pageable);

    /**
     *  Get the "id" overdueGathering.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OverdueGathering findOne(Long id);

    /**
     *  Delete the "id" overdueGathering.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

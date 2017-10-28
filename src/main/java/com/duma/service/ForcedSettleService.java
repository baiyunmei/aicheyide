package com.duma.service;

import com.duma.domain.ForcedSettle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing ForcedSettle.
 */
public interface ForcedSettleService {

    /**
     * Save a forcedSettle.
     *
     * @param forcedSettle the entity to save
     * @return the persisted entity
     */
    ForcedSettle save(ForcedSettle forcedSettle);

    /**
     *  Get all the forcedSettles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ForcedSettle> findAll(Pageable pageable);

    /**
     *  Get the "id" forcedSettle.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ForcedSettle findOne(Long id);

    /**
     *  Delete the "id" forcedSettle.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

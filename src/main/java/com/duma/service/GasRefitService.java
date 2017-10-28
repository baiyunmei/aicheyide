package com.duma.service;

import com.duma.domain.GasRefit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing GasRefit.
 */
public interface GasRefitService {

    /**
     * Save a gasRefit.
     *
     * @param gasRefit the entity to save
     * @return the persisted entity
     */
    GasRefit save(GasRefit gasRefit);

    /**
     *  Get all the gasRefits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<GasRefit> findAll(Pageable pageable);

    /**
     *  Get the "id" gasRefit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    GasRefit findOne(Long id);

    /**
     *  Delete the "id" gasRefit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

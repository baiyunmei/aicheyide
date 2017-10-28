package com.duma.service;

import com.duma.domain.GPSRefit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing GPSRefit.
 */
public interface GPSRefitService {

    /**
     * Save a gPSRefit.
     *
     * @param gPSRefit the entity to save
     * @return the persisted entity
     */
    GPSRefit save(GPSRefit gPSRefit);

    /**
     *  Get all the gPSRefits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<GPSRefit> findAll(Pageable pageable);

    /**
     *  Get the "id" gPSRefit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    GPSRefit findOne(Long id);

    /**
     *  Delete the "id" gPSRefit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

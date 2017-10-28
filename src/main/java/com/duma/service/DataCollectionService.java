package com.duma.service;

import com.duma.domain.DataCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing DataCollection.
 */
public interface DataCollectionService {

    /**
     * Save a dataCollection.
     *
     * @param dataCollection the entity to save
     * @return the persisted entity
     */
    DataCollection save(DataCollection dataCollection);

    /**
     *  Get all the dataCollections.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DataCollection> findAll(Pageable pageable);

    /**
     *  Get the "id" dataCollection.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DataCollection findOne(Long id);

    /**
     *  Delete the "id" dataCollection.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

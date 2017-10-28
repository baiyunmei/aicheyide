package com.duma.service.impl;

import com.duma.service.DataCollectionService;
import com.duma.domain.DataCollection;
import com.duma.repository.DataCollectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing DataCollection.
 */
@Service
@Transactional
public class DataCollectionServiceImpl implements DataCollectionService{

    private final Logger log = LoggerFactory.getLogger(DataCollectionServiceImpl.class);
    
    private final DataCollectionRepository dataCollectionRepository;

    public DataCollectionServiceImpl(DataCollectionRepository dataCollectionRepository) {
        this.dataCollectionRepository = dataCollectionRepository;
    }

    /**
     * Save a dataCollection.
     *
     * @param dataCollection the entity to save
     * @return the persisted entity
     */
    @Override
    public DataCollection save(DataCollection dataCollection) {
        log.debug("Request to save DataCollection : {}", dataCollection);
        DataCollection result = dataCollectionRepository.save(dataCollection);
        return result;
    }

    /**
     *  Get all the dataCollections.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DataCollection> findAll(Pageable pageable) {
        log.debug("Request to get all DataCollections");
        Page<DataCollection> result = dataCollectionRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one dataCollection by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DataCollection findOne(Long id) {
        log.debug("Request to get DataCollection : {}", id);
        DataCollection dataCollection = dataCollectionRepository.findOne(id);
        return dataCollection;
    }

    /**
     *  Delete the  dataCollection by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataCollection : {}", id);
        dataCollectionRepository.delete(id);
    }
}

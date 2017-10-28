package com.duma.service.impl;

import com.duma.service.GasRefitService;
import com.duma.domain.GasRefit;
import com.duma.repository.GasRefitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing GasRefit.
 */
@Service
@Transactional
public class GasRefitServiceImpl implements GasRefitService{

    private final Logger log = LoggerFactory.getLogger(GasRefitServiceImpl.class);
    
    private final GasRefitRepository gasRefitRepository;

    public GasRefitServiceImpl(GasRefitRepository gasRefitRepository) {
        this.gasRefitRepository = gasRefitRepository;
    }

    /**
     * Save a gasRefit.
     *
     * @param gasRefit the entity to save
     * @return the persisted entity
     */
    @Override
    public GasRefit save(GasRefit gasRefit) {
        log.debug("Request to save GasRefit : {}", gasRefit);
        GasRefit result = gasRefitRepository.save(gasRefit);
        return result;
    }

    /**
     *  Get all the gasRefits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GasRefit> findAll(Pageable pageable) {
        log.debug("Request to get all GasRefits");
        Page<GasRefit> result = gasRefitRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one gasRefit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GasRefit findOne(Long id) {
        log.debug("Request to get GasRefit : {}", id);
        GasRefit gasRefit = gasRefitRepository.findOne(id);
        return gasRefit;
    }

    /**
     *  Delete the  gasRefit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GasRefit : {}", id);
        gasRefitRepository.delete(id);
    }
}

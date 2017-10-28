package com.duma.service.impl;

import com.duma.service.OverdueGatheringService;
import com.duma.domain.OverdueGathering;
import com.duma.repository.OverdueGatheringRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing OverdueGathering.
 */
@Service
@Transactional
public class OverdueGatheringServiceImpl implements OverdueGatheringService{

    private final Logger log = LoggerFactory.getLogger(OverdueGatheringServiceImpl.class);
    
    private final OverdueGatheringRepository overdueGatheringRepository;

    public OverdueGatheringServiceImpl(OverdueGatheringRepository overdueGatheringRepository) {
        this.overdueGatheringRepository = overdueGatheringRepository;
    }

    /**
     * Save a overdueGathering.
     *
     * @param overdueGathering the entity to save
     * @return the persisted entity
     */
    @Override
    public OverdueGathering save(OverdueGathering overdueGathering) {
        log.debug("Request to save OverdueGathering : {}", overdueGathering);
        OverdueGathering result = overdueGatheringRepository.save(overdueGathering);
        return result;
    }

    /**
     *  Get all the overdueGatherings.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OverdueGathering> findAll(Pageable pageable) {
        log.debug("Request to get all OverdueGatherings");
        Page<OverdueGathering> result = overdueGatheringRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one overdueGathering by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OverdueGathering findOne(Long id) {
        log.debug("Request to get OverdueGathering : {}", id);
        OverdueGathering overdueGathering = overdueGatheringRepository.findOne(id);
        return overdueGathering;
    }

    /**
     *  Delete the  overdueGathering by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OverdueGathering : {}", id);
        overdueGatheringRepository.delete(id);
    }
}

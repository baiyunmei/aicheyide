package com.duma.service.impl;

import com.duma.service.ForcedSettleService;
import com.duma.domain.ForcedSettle;
import com.duma.repository.ForcedSettleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing ForcedSettle.
 */
@Service
@Transactional
public class ForcedSettleServiceImpl implements ForcedSettleService{

    private final Logger log = LoggerFactory.getLogger(ForcedSettleServiceImpl.class);
    
    private final ForcedSettleRepository forcedSettleRepository;

    public ForcedSettleServiceImpl(ForcedSettleRepository forcedSettleRepository) {
        this.forcedSettleRepository = forcedSettleRepository;
    }

    /**
     * Save a forcedSettle.
     *
     * @param forcedSettle the entity to save
     * @return the persisted entity
     */
    @Override
    public ForcedSettle save(ForcedSettle forcedSettle) {
        log.debug("Request to save ForcedSettle : {}", forcedSettle);
        ForcedSettle result = forcedSettleRepository.save(forcedSettle);
        return result;
    }

    /**
     *  Get all the forcedSettles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ForcedSettle> findAll(Pageable pageable) {
        log.debug("Request to get all ForcedSettles");
        Page<ForcedSettle> result = forcedSettleRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one forcedSettle by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ForcedSettle findOne(Long id) {
        log.debug("Request to get ForcedSettle : {}", id);
        ForcedSettle forcedSettle = forcedSettleRepository.findOne(id);
        return forcedSettle;
    }

    /**
     *  Delete the  forcedSettle by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ForcedSettle : {}", id);
        forcedSettleRepository.delete(id);
    }
}

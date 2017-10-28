package com.duma.service.impl;

import com.duma.service.SettleApplyService;
import com.duma.domain.SettleApply;
import com.duma.repository.SettleApplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing SettleApply.
 */
@Service
@Transactional
public class SettleApplyServiceImpl implements SettleApplyService{

    private final Logger log = LoggerFactory.getLogger(SettleApplyServiceImpl.class);
    
    private final SettleApplyRepository settleApplyRepository;

    public SettleApplyServiceImpl(SettleApplyRepository settleApplyRepository) {
        this.settleApplyRepository = settleApplyRepository;
    }

    /**
     * Save a settleApply.
     *
     * @param settleApply the entity to save
     * @return the persisted entity
     */
    @Override
    public SettleApply save(SettleApply settleApply) {
        log.debug("Request to save SettleApply : {}", settleApply);
        SettleApply result = settleApplyRepository.save(settleApply);
        return result;
    }

    /**
     *  Get all the settleApplies.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SettleApply> findAll(Pageable pageable) {
        log.debug("Request to get all SettleApplies");
        Page<SettleApply> result = settleApplyRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one settleApply by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SettleApply findOne(Long id) {
        log.debug("Request to get SettleApply : {}", id);
        SettleApply settleApply = settleApplyRepository.findOne(id);
        return settleApply;
    }

    /**
     *  Delete the  settleApply by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SettleApply : {}", id);
        settleApplyRepository.delete(id);
    }
}

package com.duma.service.impl;

import com.duma.service.RetreatDepositService;
import com.duma.domain.RetreatDeposit;
import com.duma.repository.RetreatDepositRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing RetreatDeposit.
 */
@Service
@Transactional
public class RetreatDepositServiceImpl implements RetreatDepositService{

    private final Logger log = LoggerFactory.getLogger(RetreatDepositServiceImpl.class);
    
    private final RetreatDepositRepository retreatDepositRepository;

    public RetreatDepositServiceImpl(RetreatDepositRepository retreatDepositRepository) {
        this.retreatDepositRepository = retreatDepositRepository;
    }

    /**
     * Save a retreatDeposit.
     *
     * @param retreatDeposit the entity to save
     * @return the persisted entity
     */
    @Override
    public RetreatDeposit save(RetreatDeposit retreatDeposit) {
        log.debug("Request to save RetreatDeposit : {}", retreatDeposit);
        RetreatDeposit result = retreatDepositRepository.save(retreatDeposit);
        return result;
    }

    /**
     *  Get all the retreatDeposits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RetreatDeposit> findAll(Pageable pageable) {
        log.debug("Request to get all RetreatDeposits");
        Page<RetreatDeposit> result = retreatDepositRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one retreatDeposit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RetreatDeposit findOne(Long id) {
        log.debug("Request to get RetreatDeposit : {}", id);
        RetreatDeposit retreatDeposit = retreatDepositRepository.findOne(id);
        return retreatDeposit;
    }

    /**
     *  Delete the  retreatDeposit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RetreatDeposit : {}", id);
        retreatDepositRepository.delete(id);
    }
}

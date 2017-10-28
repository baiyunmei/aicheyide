package com.duma.service.impl;

import com.duma.service.DepositContractService;
import com.duma.domain.DepositContract;
import com.duma.repository.DepositContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing DepositContract.
 */
@Service
@Transactional
public class DepositContractServiceImpl implements DepositContractService{

    private final Logger log = LoggerFactory.getLogger(DepositContractServiceImpl.class);
    
    private final DepositContractRepository depositContractRepository;

    public DepositContractServiceImpl(DepositContractRepository depositContractRepository) {
        this.depositContractRepository = depositContractRepository;
    }

    /**
     * Save a depositContract.
     *
     * @param depositContract the entity to save
     * @return the persisted entity
     */
    @Override
    public DepositContract save(DepositContract depositContract) {
        log.debug("Request to save DepositContract : {}", depositContract);
        DepositContract result = depositContractRepository.save(depositContract);
        return result;
    }

    /**
     *  Get all the depositContracts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DepositContract> findAll(Pageable pageable) {
        log.debug("Request to get all DepositContracts");
        Page<DepositContract> result = depositContractRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one depositContract by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DepositContract findOne(Long id) {
        log.debug("Request to get DepositContract : {}", id);
        DepositContract depositContract = depositContractRepository.findOne(id);
        return depositContract;
    }

    /**
     *  Delete the  depositContract by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DepositContract : {}", id);
        depositContractRepository.delete(id);
    }
}

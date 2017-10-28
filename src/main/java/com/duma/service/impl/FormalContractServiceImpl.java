package com.duma.service.impl;

import com.duma.service.FormalContractService;
import com.duma.domain.FormalContract;
import com.duma.repository.FormalContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing FormalContract.
 */
@Service
@Transactional
public class FormalContractServiceImpl implements FormalContractService{

    private final Logger log = LoggerFactory.getLogger(FormalContractServiceImpl.class);
    
    private final FormalContractRepository formalContractRepository;

    public FormalContractServiceImpl(FormalContractRepository formalContractRepository) {
        this.formalContractRepository = formalContractRepository;
    }

    /**
     * Save a formalContract.
     *
     * @param formalContract the entity to save
     * @return the persisted entity
     */
    @Override
    public FormalContract save(FormalContract formalContract) {
        log.debug("Request to save FormalContract : {}", formalContract);
        FormalContract result = formalContractRepository.save(formalContract);
        return result;
    }

    /**
     *  Get all the formalContracts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FormalContract> findAll(Pageable pageable) {
        log.debug("Request to get all FormalContracts");
        Page<FormalContract> result = formalContractRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one formalContract by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FormalContract findOne(Long id) {
        log.debug("Request to get FormalContract : {}", id);
        FormalContract formalContract = formalContractRepository.findOne(id);
        return formalContract;
    }

    /**
     *  Delete the  formalContract by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormalContract : {}", id);
        formalContractRepository.delete(id);
    }
}

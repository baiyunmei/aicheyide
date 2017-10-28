package com.duma.service.impl;

import com.duma.service.InsurancePurchaseService;
import com.duma.domain.InsurancePurchase;
import com.duma.repository.InsurancePurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing InsurancePurchase.
 */
@Service
@Transactional
public class InsurancePurchaseServiceImpl implements InsurancePurchaseService{

    private final Logger log = LoggerFactory.getLogger(InsurancePurchaseServiceImpl.class);
    
    private final InsurancePurchaseRepository insurancePurchaseRepository;

    public InsurancePurchaseServiceImpl(InsurancePurchaseRepository insurancePurchaseRepository) {
        this.insurancePurchaseRepository = insurancePurchaseRepository;
    }

    /**
     * Save a insurancePurchase.
     *
     * @param insurancePurchase the entity to save
     * @return the persisted entity
     */
    @Override
    public InsurancePurchase save(InsurancePurchase insurancePurchase) {
        log.debug("Request to save InsurancePurchase : {}", insurancePurchase);
        InsurancePurchase result = insurancePurchaseRepository.save(insurancePurchase);
        return result;
    }

    /**
     *  Get all the insurancePurchases.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InsurancePurchase> findAll(Pageable pageable) {
        log.debug("Request to get all InsurancePurchases");
        Page<InsurancePurchase> result = insurancePurchaseRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one insurancePurchase by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public InsurancePurchase findOne(Long id) {
        log.debug("Request to get InsurancePurchase : {}", id);
        InsurancePurchase insurancePurchase = insurancePurchaseRepository.findOne(id);
        return insurancePurchase;
    }

    /**
     *  Delete the  insurancePurchase by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InsurancePurchase : {}", id);
        insurancePurchaseRepository.delete(id);
    }
}

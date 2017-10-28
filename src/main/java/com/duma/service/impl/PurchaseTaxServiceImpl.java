package com.duma.service.impl;

import com.duma.service.PurchaseTaxService;
import com.duma.domain.PurchaseTax;
import com.duma.repository.PurchaseTaxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing PurchaseTax.
 */
@Service
@Transactional
public class PurchaseTaxServiceImpl implements PurchaseTaxService{

    private final Logger log = LoggerFactory.getLogger(PurchaseTaxServiceImpl.class);
    
    private final PurchaseTaxRepository purchaseTaxRepository;

    public PurchaseTaxServiceImpl(PurchaseTaxRepository purchaseTaxRepository) {
        this.purchaseTaxRepository = purchaseTaxRepository;
    }

    /**
     * Save a purchaseTax.
     *
     * @param purchaseTax the entity to save
     * @return the persisted entity
     */
    @Override
    public PurchaseTax save(PurchaseTax purchaseTax) {
        log.debug("Request to save PurchaseTax : {}", purchaseTax);
        PurchaseTax result = purchaseTaxRepository.save(purchaseTax);
        return result;
    }

    /**
     *  Get all the purchaseTaxes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseTax> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseTaxes");
        Page<PurchaseTax> result = purchaseTaxRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one purchaseTax by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PurchaseTax findOne(Long id) {
        log.debug("Request to get PurchaseTax : {}", id);
        PurchaseTax purchaseTax = purchaseTaxRepository.findOne(id);
        return purchaseTax;
    }

    /**
     *  Delete the  purchaseTax by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseTax : {}", id);
        purchaseTaxRepository.delete(id);
    }
}

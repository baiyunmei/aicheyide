package com.duma.service.impl;

import com.duma.service.DownPaymentService;
import com.duma.domain.DownPayment;
import com.duma.repository.DownPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing DownPayment.
 */
@Service
@Transactional
public class DownPaymentServiceImpl implements DownPaymentService{

    private final Logger log = LoggerFactory.getLogger(DownPaymentServiceImpl.class);
    
    private final DownPaymentRepository downPaymentRepository;

    public DownPaymentServiceImpl(DownPaymentRepository downPaymentRepository) {
        this.downPaymentRepository = downPaymentRepository;
    }

    /**
     * Save a downPayment.
     *
     * @param downPayment the entity to save
     * @return the persisted entity
     */
    @Override
    public DownPayment save(DownPayment downPayment) {
        log.debug("Request to save DownPayment : {}", downPayment);
        DownPayment result = downPaymentRepository.save(downPayment);
        return result;
    }

    /**
     *  Get all the downPayments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DownPayment> findAll(Pageable pageable) {
        log.debug("Request to get all DownPayments");
        Page<DownPayment> result = downPaymentRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one downPayment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DownPayment findOne(Long id) {
        log.debug("Request to get DownPayment : {}", id);
        DownPayment downPayment = downPaymentRepository.findOne(id);
        return downPayment;
    }

    /**
     *  Delete the  downPayment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DownPayment : {}", id);
        downPaymentRepository.delete(id);
    }
}

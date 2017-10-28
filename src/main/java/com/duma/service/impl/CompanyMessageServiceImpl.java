package com.duma.service.impl;

import com.duma.service.CompanyMessageService;
import com.duma.domain.CompanyMessage;
import com.duma.repository.CompanyMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing CompanyMessage.
 */
@Service
@Transactional
public class CompanyMessageServiceImpl implements CompanyMessageService{

    private final Logger log = LoggerFactory.getLogger(CompanyMessageServiceImpl.class);
    
    private final CompanyMessageRepository companyMessageRepository;

    public CompanyMessageServiceImpl(CompanyMessageRepository companyMessageRepository) {
        this.companyMessageRepository = companyMessageRepository;
    }

    /**
     * Save a companyMessage.
     *
     * @param companyMessage the entity to save
     * @return the persisted entity
     */
    @Override
    public CompanyMessage save(CompanyMessage companyMessage) {
        log.debug("Request to save CompanyMessage : {}", companyMessage);
        CompanyMessage result = companyMessageRepository.save(companyMessage);
        return result;
    }

    /**
     *  Get all the companyMessages.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyMessage> findAll(Pageable pageable) {
        log.debug("Request to get all CompanyMessages");
        Page<CompanyMessage> result = companyMessageRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one companyMessage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyMessage findOne(Long id) {
        log.debug("Request to get CompanyMessage : {}", id);
        CompanyMessage companyMessage = companyMessageRepository.findOne(id);
        return companyMessage;
    }

    /**
     *  Delete the  companyMessage by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompanyMessage : {}", id);
        companyMessageRepository.delete(id);
    }
}

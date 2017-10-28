package com.duma.service;

import com.duma.domain.CompanyMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing CompanyMessage.
 */
public interface CompanyMessageService {

    /**
     * Save a companyMessage.
     *
     * @param companyMessage the entity to save
     * @return the persisted entity
     */
    CompanyMessage save(CompanyMessage companyMessage);

    /**
     *  Get all the companyMessages.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CompanyMessage> findAll(Pageable pageable);

    /**
     *  Get the "id" companyMessage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CompanyMessage findOne(Long id);

    /**
     *  Delete the "id" companyMessage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

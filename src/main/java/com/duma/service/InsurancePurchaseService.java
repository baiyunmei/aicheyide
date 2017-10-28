package com.duma.service;

import com.duma.domain.InsurancePurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing InsurancePurchase.
 */
public interface InsurancePurchaseService {

    /**
     * Save a insurancePurchase.
     *
     * @param insurancePurchase the entity to save
     * @return the persisted entity
     */
    InsurancePurchase save(InsurancePurchase insurancePurchase);

    /**
     *  Get all the insurancePurchases.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<InsurancePurchase> findAll(Pageable pageable);

    /**
     *  Get the "id" insurancePurchase.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    InsurancePurchase findOne(Long id);

    /**
     *  Delete the "id" insurancePurchase.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

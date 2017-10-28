package com.duma.service;

import com.duma.domain.PurchaseTax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing PurchaseTax.
 */
public interface PurchaseTaxService {

    /**
     * Save a purchaseTax.
     *
     * @param purchaseTax the entity to save
     * @return the persisted entity
     */
    PurchaseTax save(PurchaseTax purchaseTax);

    /**
     *  Get all the purchaseTaxes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PurchaseTax> findAll(Pageable pageable);

    /**
     *  Get the "id" purchaseTax.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PurchaseTax findOne(Long id);

    /**
     *  Delete the "id" purchaseTax.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

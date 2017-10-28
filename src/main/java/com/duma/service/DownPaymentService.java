package com.duma.service;

import com.duma.domain.DownPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing DownPayment.
 */
public interface DownPaymentService {

    /**
     * Save a downPayment.
     *
     * @param downPayment the entity to save
     * @return the persisted entity
     */
    DownPayment save(DownPayment downPayment);

    /**
     *  Get all the downPayments.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DownPayment> findAll(Pageable pageable);

    /**
     *  Get the "id" downPayment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DownPayment findOne(Long id);

    /**
     *  Delete the "id" downPayment.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

package com.duma.service;

import com.duma.domain.OrderFrom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing OrderFrom.
 */
public interface OrderFromService {

    /**
     * Save a orderFrom.
     *
     * @param orderFrom the entity to save
     * @return the persisted entity
     */
    OrderFrom save(OrderFrom orderFrom);

    /**
     *  Get all the orderFroms.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OrderFrom> findAll(Pageable pageable);

    /**
     *  Get the "id" orderFrom.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OrderFrom findOne(Long id);

    /**
     *  Delete the "id" orderFrom.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

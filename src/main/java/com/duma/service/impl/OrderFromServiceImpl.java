package com.duma.service.impl;

import com.duma.service.OrderFromService;
import com.duma.domain.OrderFrom;
import com.duma.repository.OrderFromRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing OrderFrom.
 */
@Service
@Transactional
public class OrderFromServiceImpl implements OrderFromService{

    private final Logger log = LoggerFactory.getLogger(OrderFromServiceImpl.class);
    
    private final OrderFromRepository orderFromRepository;

    public OrderFromServiceImpl(OrderFromRepository orderFromRepository) {
        this.orderFromRepository = orderFromRepository;
    }

    /**
     * Save a orderFrom.
     *
     * @param orderFrom the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderFrom save(OrderFrom orderFrom) {
        log.debug("Request to save OrderFrom : {}", orderFrom);
        OrderFrom result = orderFromRepository.save(orderFrom);
        return result;
    }

    /**
     *  Get all the orderFroms.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderFrom> findAll(Pageable pageable) {
        log.debug("Request to get all OrderFroms");
        Page<OrderFrom> result = orderFromRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one orderFrom by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderFrom findOne(Long id) {
        log.debug("Request to get OrderFrom : {}", id);
        OrderFrom orderFrom = orderFromRepository.findOne(id);
        return orderFrom;
    }

    /**
     *  Delete the  orderFrom by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderFrom : {}", id);
        orderFromRepository.delete(id);
    }
}

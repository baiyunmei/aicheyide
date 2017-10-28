package com.duma.service.impl;

import com.duma.service.WarehouseService;
import com.duma.domain.Warehouse;
import com.duma.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Warehouse.
 */
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService{

    private final Logger log = LoggerFactory.getLogger(WarehouseServiceImpl.class);
    
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    /**
     * Save a warehouse.
     *
     * @param warehouse the entity to save
     * @return the persisted entity
     */
    @Override
    public Warehouse save(Warehouse warehouse) {
        log.debug("Request to save Warehouse : {}", warehouse);
        Warehouse result = warehouseRepository.save(warehouse);
        return result;
    }

    /**
     *  Get all the warehouses.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Warehouse> findAll(Pageable pageable) {
        log.debug("Request to get all Warehouses");
        Page<Warehouse> result = warehouseRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one warehouse by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Warehouse findOne(Long id) {
        log.debug("Request to get Warehouse : {}", id);
        Warehouse warehouse = warehouseRepository.findOne(id);
        return warehouse;
    }

    /**
     *  Delete the  warehouse by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Warehouse : {}", id);
        warehouseRepository.delete(id);
    }
}

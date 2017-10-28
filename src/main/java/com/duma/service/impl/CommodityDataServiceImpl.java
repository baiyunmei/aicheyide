package com.duma.service.impl;

import com.duma.service.CommodityDataService;
import com.duma.domain.CommodityData;
import com.duma.repository.CommodityDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing CommodityData.
 */
@Service
@Transactional
public class CommodityDataServiceImpl implements CommodityDataService{

    private final Logger log = LoggerFactory.getLogger(CommodityDataServiceImpl.class);
    
    private final CommodityDataRepository commodityDataRepository;

    public CommodityDataServiceImpl(CommodityDataRepository commodityDataRepository) {
        this.commodityDataRepository = commodityDataRepository;
    }

    /**
     * Save a commodityData.
     *
     * @param commodityData the entity to save
     * @return the persisted entity
     */
    @Override
    public CommodityData save(CommodityData commodityData) {
        log.debug("Request to save CommodityData : {}", commodityData);
        CommodityData result = commodityDataRepository.save(commodityData);
        return result;
    }

    /**
     *  Get all the commodityData.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommodityData> findAll(Pageable pageable) {
        log.debug("Request to get all CommodityData");
        Page<CommodityData> result = commodityDataRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one commodityData by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CommodityData findOne(Long id) {
        log.debug("Request to get CommodityData : {}", id);
        CommodityData commodityData = commodityDataRepository.findOne(id);
        return commodityData;
    }

    /**
     *  Delete the  commodityData by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommodityData : {}", id);
        commodityDataRepository.delete(id);
    }
}

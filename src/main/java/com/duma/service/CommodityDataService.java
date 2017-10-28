package com.duma.service;

import com.duma.domain.CommodityData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing CommodityData.
 */
public interface CommodityDataService {

    /**
     * Save a commodityData.
     *
     * @param commodityData the entity to save
     * @return the persisted entity
     */
    CommodityData save(CommodityData commodityData);

    /**
     *  Get all the commodityData.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CommodityData> findAll(Pageable pageable);

    /**
     *  Get the "id" commodityData.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CommodityData findOne(Long id);

    /**
     *  Delete the "id" commodityData.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

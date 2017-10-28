package com.duma.service;

import com.duma.domain.OnBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing OnBrand.
 */
public interface OnBrandService {

    /**
     * Save a onBrand.
     *
     * @param onBrand the entity to save
     * @return the persisted entity
     */
    OnBrand save(OnBrand onBrand);

    /**
     *  Get all the onBrands.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OnBrand> findAll(Pageable pageable);

    /**
     *  Get the "id" onBrand.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OnBrand findOne(Long id);

    /**
     *  Delete the "id" onBrand.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

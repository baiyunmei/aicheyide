package com.duma.service;

import com.duma.domain.AdditionalMaterials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing AdditionalMaterials.
 */
public interface AdditionalMaterialsService {

    /**
     * Save a additionalMaterials.
     *
     * @param additionalMaterials the entity to save
     * @return the persisted entity
     */
    AdditionalMaterials save(AdditionalMaterials additionalMaterials);

    /**
     *  Get all the additionalMaterials.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AdditionalMaterials> findAll(Pageable pageable);

    /**
     *  Get the "id" additionalMaterials.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AdditionalMaterials findOne(Long id);

    /**
     *  Delete the "id" additionalMaterials.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

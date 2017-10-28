package com.duma.service.impl;

import com.duma.service.AdditionalMaterialsService;
import com.duma.domain.AdditionalMaterials;
import com.duma.repository.AdditionalMaterialsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing AdditionalMaterials.
 */
@Service
@Transactional
public class AdditionalMaterialsServiceImpl implements AdditionalMaterialsService{

    private final Logger log = LoggerFactory.getLogger(AdditionalMaterialsServiceImpl.class);
    
    private final AdditionalMaterialsRepository additionalMaterialsRepository;

    public AdditionalMaterialsServiceImpl(AdditionalMaterialsRepository additionalMaterialsRepository) {
        this.additionalMaterialsRepository = additionalMaterialsRepository;
    }

    /**
     * Save a additionalMaterials.
     *
     * @param additionalMaterials the entity to save
     * @return the persisted entity
     */
    @Override
    public AdditionalMaterials save(AdditionalMaterials additionalMaterials) {
        log.debug("Request to save AdditionalMaterials : {}", additionalMaterials);
        AdditionalMaterials result = additionalMaterialsRepository.save(additionalMaterials);
        return result;
    }

    /**
     *  Get all the additionalMaterials.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdditionalMaterials> findAll(Pageable pageable) {
        log.debug("Request to get all AdditionalMaterials");
        Page<AdditionalMaterials> result = additionalMaterialsRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one additionalMaterials by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AdditionalMaterials findOne(Long id) {
        log.debug("Request to get AdditionalMaterials : {}", id);
        AdditionalMaterials additionalMaterials = additionalMaterialsRepository.findOne(id);
        return additionalMaterials;
    }

    /**
     *  Delete the  additionalMaterials by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdditionalMaterials : {}", id);
        additionalMaterialsRepository.delete(id);
    }
}

package com.duma.service.impl;

import com.duma.service.OnBrandService;
import com.duma.domain.OnBrand;
import com.duma.repository.OnBrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing OnBrand.
 */
@Service
@Transactional
public class OnBrandServiceImpl implements OnBrandService{

    private final Logger log = LoggerFactory.getLogger(OnBrandServiceImpl.class);
    
    private final OnBrandRepository onBrandRepository;

    public OnBrandServiceImpl(OnBrandRepository onBrandRepository) {
        this.onBrandRepository = onBrandRepository;
    }

    /**
     * Save a onBrand.
     *
     * @param onBrand the entity to save
     * @return the persisted entity
     */
    @Override
    public OnBrand save(OnBrand onBrand) {
        log.debug("Request to save OnBrand : {}", onBrand);
        OnBrand result = onBrandRepository.save(onBrand);
        return result;
    }

    /**
     *  Get all the onBrands.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OnBrand> findAll(Pageable pageable) {
        log.debug("Request to get all OnBrands");
        Page<OnBrand> result = onBrandRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one onBrand by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OnBrand findOne(Long id) {
        log.debug("Request to get OnBrand : {}", id);
        OnBrand onBrand = onBrandRepository.findOne(id);
        return onBrand;
    }

    /**
     *  Delete the  onBrand by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OnBrand : {}", id);
        onBrandRepository.delete(id);
    }
}

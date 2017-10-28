package com.duma.service.impl;

import com.duma.service.GPSRefitService;
import com.duma.domain.GPSRefit;
import com.duma.repository.GPSRefitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing GPSRefit.
 */
@Service
@Transactional
public class GPSRefitServiceImpl implements GPSRefitService{

    private final Logger log = LoggerFactory.getLogger(GPSRefitServiceImpl.class);
    
    private final GPSRefitRepository gPSRefitRepository;

    public GPSRefitServiceImpl(GPSRefitRepository gPSRefitRepository) {
        this.gPSRefitRepository = gPSRefitRepository;
    }

    /**
     * Save a gPSRefit.
     *
     * @param gPSRefit the entity to save
     * @return the persisted entity
     */
    @Override
    public GPSRefit save(GPSRefit gPSRefit) {
        log.debug("Request to save GPSRefit : {}", gPSRefit);
        GPSRefit result = gPSRefitRepository.save(gPSRefit);
        return result;
    }

    /**
     *  Get all the gPSRefits.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GPSRefit> findAll(Pageable pageable) {
        log.debug("Request to get all GPSRefits");
        Page<GPSRefit> result = gPSRefitRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one gPSRefit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GPSRefit findOne(Long id) {
        log.debug("Request to get GPSRefit : {}", id);
        GPSRefit gPSRefit = gPSRefitRepository.findOne(id);
        return gPSRefit;
    }

    /**
     *  Delete the  gPSRefit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GPSRefit : {}", id);
        gPSRefitRepository.delete(id);
    }
}

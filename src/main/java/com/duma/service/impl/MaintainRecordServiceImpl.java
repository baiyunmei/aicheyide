package com.duma.service.impl;

import com.duma.service.MaintainRecordService;
import com.duma.domain.MaintainRecord;
import com.duma.repository.MaintainRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing MaintainRecord.
 */
@Service
@Transactional
public class MaintainRecordServiceImpl implements MaintainRecordService{

    private final Logger log = LoggerFactory.getLogger(MaintainRecordServiceImpl.class);
    
    private final MaintainRecordRepository maintainRecordRepository;

    public MaintainRecordServiceImpl(MaintainRecordRepository maintainRecordRepository) {
        this.maintainRecordRepository = maintainRecordRepository;
    }

    /**
     * Save a maintainRecord.
     *
     * @param maintainRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public MaintainRecord save(MaintainRecord maintainRecord) {
        log.debug("Request to save MaintainRecord : {}", maintainRecord);
        MaintainRecord result = maintainRecordRepository.save(maintainRecord);
        return result;
    }

    /**
     *  Get all the maintainRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MaintainRecord> findAll(Pageable pageable) {
        log.debug("Request to get all MaintainRecords");
        Page<MaintainRecord> result = maintainRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one maintainRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MaintainRecord findOne(Long id) {
        log.debug("Request to get MaintainRecord : {}", id);
        MaintainRecord maintainRecord = maintainRecordRepository.findOne(id);
        return maintainRecord;
    }

    /**
     *  Delete the  maintainRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MaintainRecord : {}", id);
        maintainRecordRepository.delete(id);
    }
}

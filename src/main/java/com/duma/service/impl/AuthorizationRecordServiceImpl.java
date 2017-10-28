package com.duma.service.impl;

import com.duma.service.AuthorizationRecordService;
import com.duma.domain.AuthorizationRecord;
import com.duma.repository.AuthorizationRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing AuthorizationRecord.
 */
@Service
@Transactional
public class AuthorizationRecordServiceImpl implements AuthorizationRecordService{

    private final Logger log = LoggerFactory.getLogger(AuthorizationRecordServiceImpl.class);
    
    private final AuthorizationRecordRepository authorizationRecordRepository;

    public AuthorizationRecordServiceImpl(AuthorizationRecordRepository authorizationRecordRepository) {
        this.authorizationRecordRepository = authorizationRecordRepository;
    }

    /**
     * Save a authorizationRecord.
     *
     * @param authorizationRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public AuthorizationRecord save(AuthorizationRecord authorizationRecord) {
        log.debug("Request to save AuthorizationRecord : {}", authorizationRecord);
        AuthorizationRecord result = authorizationRecordRepository.save(authorizationRecord);
        return result;
    }

    /**
     *  Get all the authorizationRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuthorizationRecord> findAll(Pageable pageable) {
        log.debug("Request to get all AuthorizationRecords");
        Page<AuthorizationRecord> result = authorizationRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one authorizationRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuthorizationRecord findOne(Long id) {
        log.debug("Request to get AuthorizationRecord : {}", id);
        AuthorizationRecord authorizationRecord = authorizationRecordRepository.findOne(id);
        return authorizationRecord;
    }

    /**
     *  Delete the  authorizationRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuthorizationRecord : {}", id);
        authorizationRecordRepository.delete(id);
    }
}

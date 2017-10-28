package com.duma.service.impl;

import com.duma.service.RefundRecordService;
import com.duma.domain.RefundRecord;
import com.duma.repository.RefundRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing RefundRecord.
 */
@Service
@Transactional
public class RefundRecordServiceImpl implements RefundRecordService{

    private final Logger log = LoggerFactory.getLogger(RefundRecordServiceImpl.class);
    
    private final RefundRecordRepository refundRecordRepository;

    public RefundRecordServiceImpl(RefundRecordRepository refundRecordRepository) {
        this.refundRecordRepository = refundRecordRepository;
    }

    /**
     * Save a refundRecord.
     *
     * @param refundRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public RefundRecord save(RefundRecord refundRecord) {
        log.debug("Request to save RefundRecord : {}", refundRecord);
        RefundRecord result = refundRecordRepository.save(refundRecord);
        return result;
    }

    /**
     *  Get all the refundRecords.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RefundRecord> findAll(Pageable pageable) {
        log.debug("Request to get all RefundRecords");
        Page<RefundRecord> result = refundRecordRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one refundRecord by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RefundRecord findOne(Long id) {
        log.debug("Request to get RefundRecord : {}", id);
        RefundRecord refundRecord = refundRecordRepository.findOne(id);
        return refundRecord;
    }

    /**
     *  Delete the  refundRecord by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefundRecord : {}", id);
        refundRecordRepository.delete(id);
    }
}

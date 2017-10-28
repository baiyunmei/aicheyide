package com.duma.service.impl;

import com.duma.service.CreditReviewService;
import com.duma.domain.CreditReview;
import com.duma.repository.CreditReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing CreditReview.
 */
@Service
@Transactional
public class CreditReviewServiceImpl implements CreditReviewService{

    private final Logger log = LoggerFactory.getLogger(CreditReviewServiceImpl.class);
    
    private final CreditReviewRepository creditReviewRepository;

    public CreditReviewServiceImpl(CreditReviewRepository creditReviewRepository) {
        this.creditReviewRepository = creditReviewRepository;
    }

    /**
     * Save a creditReview.
     *
     * @param creditReview the entity to save
     * @return the persisted entity
     */
    @Override
    public CreditReview save(CreditReview creditReview) {
        log.debug("Request to save CreditReview : {}", creditReview);
        CreditReview result = creditReviewRepository.save(creditReview);
        return result;
    }

    /**
     *  Get all the creditReviews.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CreditReview> findAll(Pageable pageable) {
        log.debug("Request to get all CreditReviews");
        Page<CreditReview> result = creditReviewRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one creditReview by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CreditReview findOne(Long id) {
        log.debug("Request to get CreditReview : {}", id);
        CreditReview creditReview = creditReviewRepository.findOne(id);
        return creditReview;
    }

    /**
     *  Delete the  creditReview by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CreditReview : {}", id);
        creditReviewRepository.delete(id);
    }
}

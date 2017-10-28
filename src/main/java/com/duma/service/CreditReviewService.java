package com.duma.service;

import com.duma.domain.CreditReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing CreditReview.
 */
public interface CreditReviewService {

    /**
     * Save a creditReview.
     *
     * @param creditReview the entity to save
     * @return the persisted entity
     */
    CreditReview save(CreditReview creditReview);

    /**
     *  Get all the creditReviews.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CreditReview> findAll(Pageable pageable);

    /**
     *  Get the "id" creditReview.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CreditReview findOne(Long id);

    /**
     *  Delete the "id" creditReview.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

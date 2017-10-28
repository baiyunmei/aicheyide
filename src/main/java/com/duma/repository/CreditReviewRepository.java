package com.duma.repository;

import com.duma.domain.CreditReview;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CreditReview entity.
 */
@SuppressWarnings("unused")
public interface CreditReviewRepository extends JpaRepository<CreditReview,Long> {

}

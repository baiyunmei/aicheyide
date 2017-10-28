package com.duma.repository;

import com.duma.domain.RefundRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RefundRecord entity.
 */
@SuppressWarnings("unused")
public interface RefundRecordRepository extends JpaRepository<RefundRecord,Long> {

}

package com.duma.repository;

import com.duma.domain.PolicyRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PolicyRecord entity.
 */
@SuppressWarnings("unused")
public interface PolicyRecordRepository extends JpaRepository<PolicyRecord,Long> {

}

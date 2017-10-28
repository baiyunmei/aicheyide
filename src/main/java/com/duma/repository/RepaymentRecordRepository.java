package com.duma.repository;

import com.duma.domain.RepaymentRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RepaymentRecord entity.
 */
@SuppressWarnings("unused")
public interface RepaymentRecordRepository extends JpaRepository<RepaymentRecord,Long> {

}

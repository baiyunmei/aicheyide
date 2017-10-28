package com.duma.repository;

import com.duma.domain.ContractMarginRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ContractMarginRecord entity.
 */
@SuppressWarnings("unused")
public interface ContractMarginRecordRepository extends JpaRepository<ContractMarginRecord,Long> {

}

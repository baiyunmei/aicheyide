package com.duma.repository;

import com.duma.domain.SupplierRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SupplierRecord entity.
 */
@SuppressWarnings("unused")
public interface SupplierRecordRepository extends JpaRepository<SupplierRecord,Long> {

}

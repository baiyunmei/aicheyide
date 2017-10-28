package com.duma.repository;

import com.duma.domain.PurchaseVehicleRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PurchaseVehicleRecord entity.
 */
@SuppressWarnings("unused")
public interface PurchaseVehicleRecordRepository extends JpaRepository<PurchaseVehicleRecord,Long> {

}

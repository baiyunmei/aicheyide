package com.duma.repository;

import com.duma.domain.BuyVehicleRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BuyVehicleRecord entity.
 */
@SuppressWarnings("unused")
public interface BuyVehicleRecordRepository extends JpaRepository<BuyVehicleRecord,Long> {

}

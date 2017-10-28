package com.duma.repository;

import com.duma.domain.RentVehicleRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RentVehicleRecord entity.
 */
@SuppressWarnings("unused")
public interface RentVehicleRecordRepository extends JpaRepository<RentVehicleRecord,Long> {

}

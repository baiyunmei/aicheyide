package com.duma.repository;

import com.duma.domain.ReceiveVehicle;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ReceiveVehicle entity.
 */
@SuppressWarnings("unused")
public interface ReceiveVehicleRepository extends JpaRepository<ReceiveVehicle,Long> {

}

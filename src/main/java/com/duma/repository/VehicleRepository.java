package com.duma.repository;

import com.duma.domain.Vehicle;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Vehicle entity.
 */
@SuppressWarnings("unused")
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

}

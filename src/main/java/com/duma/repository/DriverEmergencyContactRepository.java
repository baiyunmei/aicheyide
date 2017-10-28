package com.duma.repository;

import com.duma.domain.DriverEmergencyContact;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DriverEmergencyContact entity.
 */
@SuppressWarnings("unused")
public interface DriverEmergencyContactRepository extends JpaRepository<DriverEmergencyContact,Long> {

}

package com.duma.repository;

import com.duma.domain.RentalManagement;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RentalManagement entity.
 */
@SuppressWarnings("unused")
public interface RentalManagementRepository extends JpaRepository<RentalManagement,Long> {

}

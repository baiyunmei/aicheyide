package com.duma.repository;

import com.duma.domain.MonthlyManagement;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MonthlyManagement entity.
 */
@SuppressWarnings("unused")
public interface MonthlyManagementRepository extends JpaRepository<MonthlyManagement,Long> {

}

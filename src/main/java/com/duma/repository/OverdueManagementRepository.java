package com.duma.repository;

import com.duma.domain.OverdueManagement;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OverdueManagement entity.
 */
@SuppressWarnings("unused")
public interface OverdueManagementRepository extends JpaRepository<OverdueManagement,Long> {

}

package com.duma.repository;

import com.duma.domain.DriverMate;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DriverMate entity.
 */
@SuppressWarnings("unused")
public interface DriverMateRepository extends JpaRepository<DriverMate,Long> {

}

package com.duma.repository;

import com.duma.domain.GPSRefit;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the GPSRefit entity.
 */
@SuppressWarnings("unused")
public interface GPSRefitRepository extends JpaRepository<GPSRefit,Long> {

}

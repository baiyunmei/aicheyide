package com.duma.repository;

import com.duma.domain.GasRefit;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the GasRefit entity.
 */
@SuppressWarnings("unused")
public interface GasRefitRepository extends JpaRepository<GasRefit,Long> {

}

package com.duma.repository;

import com.duma.domain.OverdueGathering;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OverdueGathering entity.
 */
@SuppressWarnings("unused")
public interface OverdueGatheringRepository extends JpaRepository<OverdueGathering,Long> {

}

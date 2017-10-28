package com.duma.repository;

import com.duma.domain.ForcedSettle;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ForcedSettle entity.
 */
@SuppressWarnings("unused")
public interface ForcedSettleRepository extends JpaRepository<ForcedSettle,Long> {

}

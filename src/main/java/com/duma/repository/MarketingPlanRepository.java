package com.duma.repository;

import com.duma.domain.MarketingPlan;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MarketingPlan entity.
 */
@SuppressWarnings("unused")
public interface MarketingPlanRepository extends JpaRepository<MarketingPlan,Long> {

}

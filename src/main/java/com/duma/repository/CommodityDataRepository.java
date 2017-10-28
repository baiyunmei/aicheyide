package com.duma.repository;

import com.duma.domain.CommodityData;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CommodityData entity.
 */
@SuppressWarnings("unused")
public interface CommodityDataRepository extends JpaRepository<CommodityData,Long> {

}

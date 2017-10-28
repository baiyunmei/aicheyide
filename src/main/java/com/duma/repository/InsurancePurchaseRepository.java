package com.duma.repository;

import com.duma.domain.InsurancePurchase;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InsurancePurchase entity.
 */
@SuppressWarnings("unused")
public interface InsurancePurchaseRepository extends JpaRepository<InsurancePurchase,Long> {

}

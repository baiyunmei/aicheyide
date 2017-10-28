package com.duma.repository;

import com.duma.domain.PurchaseTax;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PurchaseTax entity.
 */
@SuppressWarnings("unused")
public interface PurchaseTaxRepository extends JpaRepository<PurchaseTax,Long> {

}

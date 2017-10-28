package com.duma.repository;

import com.duma.domain.DownPayment;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DownPayment entity.
 */
@SuppressWarnings("unused")
public interface DownPaymentRepository extends JpaRepository<DownPayment,Long> {

}

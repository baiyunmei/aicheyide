package com.duma.repository;

import com.duma.domain.RetreatDeposit;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RetreatDeposit entity.
 */
@SuppressWarnings("unused")
public interface RetreatDepositRepository extends JpaRepository<RetreatDeposit,Long> {

}

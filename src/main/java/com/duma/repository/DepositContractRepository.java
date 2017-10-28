package com.duma.repository;

import com.duma.domain.DepositContract;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DepositContract entity.
 */
@SuppressWarnings("unused")
public interface DepositContractRepository extends JpaRepository<DepositContract,Long> {

}

package com.duma.repository;

import com.duma.domain.FormalContract;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FormalContract entity.
 */
@SuppressWarnings("unused")
public interface FormalContractRepository extends JpaRepository<FormalContract,Long> {

}

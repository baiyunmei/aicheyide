package com.duma.repository;

import com.duma.domain.ClaimSettlementAudit;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ClaimSettlementAudit entity.
 */
@SuppressWarnings("unused")
public interface ClaimSettlementAuditRepository extends JpaRepository<ClaimSettlementAudit,Long> {

}

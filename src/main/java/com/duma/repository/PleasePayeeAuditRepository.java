package com.duma.repository;

import com.duma.domain.PleasePayeeAudit;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PleasePayeeAudit entity.
 */
@SuppressWarnings("unused")
public interface PleasePayeeAuditRepository extends JpaRepository<PleasePayeeAudit,Long> {

}

package com.duma.repository;

import com.duma.domain.AuthorizationRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AuthorizationRecord entity.
 */
@SuppressWarnings("unused")
public interface AuthorizationRecordRepository extends JpaRepository<AuthorizationRecord,Long> {

}

package com.duma.repository;

import com.duma.domain.LogRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LogRecord entity.
 */
@SuppressWarnings("unused")
public interface LogRecordRepository extends JpaRepository<LogRecord,Long> {

}

package com.duma.repository;

import com.duma.domain.OutDangerRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OutDangerRecord entity.
 */
@SuppressWarnings("unused")
public interface OutDangerRecordRepository extends JpaRepository<OutDangerRecord,Long> {

}

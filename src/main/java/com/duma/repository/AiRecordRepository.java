package com.duma.repository;

import com.duma.domain.AiRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AiRecord entity.
 */
@SuppressWarnings("unused")
public interface AiRecordRepository extends JpaRepository<AiRecord,Long> {

}

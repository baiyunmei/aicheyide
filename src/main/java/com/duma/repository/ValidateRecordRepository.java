package com.duma.repository;

import com.duma.domain.ValidateRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ValidateRecord entity.
 */
@SuppressWarnings("unused")
public interface ValidateRecordRepository extends JpaRepository<ValidateRecord,Long> {

}

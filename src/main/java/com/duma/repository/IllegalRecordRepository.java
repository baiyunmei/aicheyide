package com.duma.repository;

import com.duma.domain.IllegalRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the IllegalRecord entity.
 */
@SuppressWarnings("unused")
public interface IllegalRecordRepository extends JpaRepository<IllegalRecord,Long> {

}

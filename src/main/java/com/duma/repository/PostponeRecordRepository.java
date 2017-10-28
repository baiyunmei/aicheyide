package com.duma.repository;

import com.duma.domain.PostponeRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PostponeRecord entity.
 */
@SuppressWarnings("unused")
public interface PostponeRecordRepository extends JpaRepository<PostponeRecord,Long> {

}

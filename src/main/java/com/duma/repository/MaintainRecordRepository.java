package com.duma.repository;

import com.duma.domain.MaintainRecord;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MaintainRecord entity.
 */
@SuppressWarnings("unused")
public interface MaintainRecordRepository extends JpaRepository<MaintainRecord,Long> {

}

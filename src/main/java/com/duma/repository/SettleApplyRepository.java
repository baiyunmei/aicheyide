package com.duma.repository;

import com.duma.domain.SettleApply;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SettleApply entity.
 */
@SuppressWarnings("unused")
public interface SettleApplyRepository extends JpaRepository<SettleApply,Long> {

}

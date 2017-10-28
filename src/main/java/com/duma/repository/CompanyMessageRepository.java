package com.duma.repository;

import com.duma.domain.CompanyMessage;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CompanyMessage entity.
 */
@SuppressWarnings("unused")
public interface CompanyMessageRepository extends JpaRepository<CompanyMessage,Long> {

}
